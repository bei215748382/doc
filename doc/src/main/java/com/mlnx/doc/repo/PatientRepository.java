package com.mlnx.doc.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.mlnx.doc.model.Patient;
import com.mlnx.doc.model.Patient.Gender;
import com.mlnx.doc.service.CassandraService;
import com.mlnx.doc.util.ResultTransformer;

@Service
public class PatientRepository implements ResultTransformer<Patient> {

	private static int patientId = 0;

	private static final String TABLE_NAME = "patient";

	private static enum Column {

		ID("id", DataType.cint()), NAME("name", DataType.text()), GENDER(
				"gender", DataType.text()), AGE("age", DataType.cint()), REMARK(
				"remark", DataType.text()), BIRTHDAY("birth", DataType
				.timestamp()), IDENTIFICATION("identification", DataType.text()), PASTDISEASEHISTORY(
				"past_disease_history", DataType.text()), CONTACT("contact",
				DataType.text()), PASTMEDICINEHISTORY("past_medicine_history",
				DataType.text()), PICTURE("picture", DataType.blob()), SIGN(
				"sign", DataType.text()), LASTUPDATETIME("last_update_time",
				DataType.timestamp());

		private String name;

		private DataType dataType;

		private Column(String name, DataType dataType) {
			this.name = name;
			this.dataType = dataType;
		}

		@Override
		public String toString() {

			return String.format("%s %s", name, dataType);
		}
	}

	private static final String CREATE_TABLE_CQL = String.format(
			"CREATE TABLE %s (\n", TABLE_NAME)
			+ String.format("  %s,\n", Column.ID)
			+ String.format("  %s,\n", Column.NAME)
			+ String.format("  %s,\n", Column.GENDER)
			+ String.format("  %s,\n", Column.AGE)
			+ String.format("  %s,\n", Column.REMARK)
			+ String.format("  %s,\n", Column.BIRTHDAY)
			+ String.format("  %s,\n", Column.IDENTIFICATION)
			+ String.format("  %s,\n", Column.PASTDISEASEHISTORY)
			+ String.format("  %s,\n", Column.CONTACT)
			+ String.format("  %s,\n", Column.PASTMEDICINEHISTORY)
			+ String.format("  %s,\n", Column.PICTURE)
			+ String.format("  %s,\n", Column.SIGN)
			+ String.format("  %s,\n", Column.LASTUPDATETIME)
			+ String.format("PRIMARY KEY ((%s), %s)\n", Column.ID.name,
					Column.LASTUPDATETIME.name) + ")";

	private static final String CREATE_NAME_INDEX_CQL = String.format(
			"CREATE INDEX %s" + " ON %s ( %s );", TABLE_NAME + "_name",
			TABLE_NAME, Column.NAME.name);

	private static final String CREATE_IDENTIFICATION_INDEX_CQL = String
			.format("CREATE INDEX %s" + " ON %s ( %s );", TABLE_NAME
					+ "_identification", TABLE_NAME, Column.IDENTIFICATION.name);

	private static final String CREATE_CONTACT_INDEX_CQL = String.format(
			"CREATE INDEX %s" + " ON %s ( %s );", TABLE_NAME + "_contact",
			TABLE_NAME, Column.CONTACT.name);

	private static final String INSERT_CQL = String.format(
			"INSERT INTO %s (\n", TABLE_NAME)
			+ String.format("  %s,\n", Column.ID.name)
			+ String.format("  %s,\n", Column.NAME.name)
			+ String.format("  %s,\n", Column.GENDER.name)
			+ String.format("  %s,\n", Column.AGE.name)
			+ String.format("  %s,\n", Column.REMARK.name)
			+ String.format("  %s,\n", Column.BIRTHDAY.name)
			+ String.format("  %s,\n", Column.IDENTIFICATION.name)
			+ String.format("  %s,\n", Column.PASTDISEASEHISTORY.name)
			+ String.format("  %s,\n", Column.CONTACT.name)
			+ String.format("  %s,\n", Column.PASTMEDICINEHISTORY.name)
			+ String.format("  %s,\n", Column.PICTURE.name)
			+ String.format("  %s,\n", Column.SIGN.name)
			+ String.format("  %s\n", Column.LASTUPDATETIME.name)
			+ ") VALUES (\n"
			+ CassandraService
					.generateValuePlaceholders(Column.values().length) + "\n)";

	private static final Select FIND_ALL_QUERY = QueryBuilder.select().all()
			.from(TABLE_NAME);

	private static final String FIND_BY_ID_CQL = String.format(
			"SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.ID.name);

	private static final String FIND_BY_NAME_CQL = String.format(
			"SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.NAME.name);

	private static final String FIND_BY_ID_AND_NAME_CQL = String.format(
			"SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.ID.name)
			+ String.format(" AND %s = ?", Column.NAME.name);

	private static final String FIND_BY_ID_AND_CONTACT_CQL = String.format(
			"SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.ID.name)
			+ String.format(" AND %s = ?", Column.CONTACT.name)
			+ " allow filtering";

	private static final String FIND_BY_NAME_AND_CONTACT_CQL = String.format(
			"SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.NAME.name)
			+ String.format(" AND %s = ?", Column.CONTACT.name)
			+ " allow filtering";

	private static final String FIND_BY_ID_ID_CQL = String.format(
			"SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.IDENTIFICATION.name)
			+ " allow filtering";

	private static final String FIND_BY_CONTACT_CQL = String.format(
			"SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.CONTACT.name)
			+ " allow filtering";

	private static final String FIND_BY_ID_AND_NAME_AND_CONTACT_CQL = String
			.format("SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.ID.name)
			+ String.format(" AND %s = ?", Column.NAME.name)
			+ String.format(" AND %s = ?", Column.CONTACT.name)
			+ " allow filtering";

	private static final String DELETE_CQL = String.format("DELETE FROM %s",
			TABLE_NAME) + String.format(" WHERE %s = ?", Column.ID.name);

	private static final String FIND_BY_TIME_CQL = String.format(
			"SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s > ?", Column.LASTUPDATETIME.name)
			+ " allow filtering";

	private static final Logger log = LoggerFactory
			.getLogger(PatientRepository.class);

	@Autowired
	private CassandraService cassandra;

	private PreparedStatement insertQuery;

	private PreparedStatement findByIdQuery;

	private PreparedStatement deleteQuery;

	private PreparedStatement findByIDQuery;

	private PreparedStatement findByContactQuery;

	private PreparedStatement findByIDAndContactQuery;

	private PreparedStatement findByTimeQuery;

	private PreparedStatement findByNameQuery;

	private PreparedStatement findByIdAndNameQuery;

	private PreparedStatement findByIdAndContactQuery;

	private PreparedStatement findByNameAndContactQuery;

	private PreparedStatement findByIdAndNameAndContactQuery;

	@PostConstruct
	private void init() throws Exception {

		cassandra.createTableIfNotExists(TABLE_NAME, CREATE_TABLE_CQL,
				CREATE_NAME_INDEX_CQL, CREATE_IDENTIFICATION_INDEX_CQL,
				CREATE_CONTACT_INDEX_CQL);
		insertQuery = cassandra.getSession().prepare(INSERT_CQL);
		findByIdQuery = cassandra.getSession().prepare(FIND_BY_ID_CQL);
		deleteQuery = cassandra.getSession().prepare(DELETE_CQL);
		findByIDQuery = cassandra.getSession().prepare(FIND_BY_ID_ID_CQL);
		findByContactQuery = cassandra.getSession()
				.prepare(FIND_BY_CONTACT_CQL);
		findByIDAndContactQuery = cassandra.getSession().prepare(
				FIND_BY_ID_AND_CONTACT_CQL);
		findByNameQuery = cassandra.getSession().prepare(FIND_BY_NAME_CQL);
		findByIdAndNameQuery = cassandra.getSession().prepare(
				FIND_BY_ID_AND_NAME_CQL);
		findByIdAndContactQuery = cassandra.getSession().prepare(
				FIND_BY_ID_AND_CONTACT_CQL);
		findByNameAndContactQuery = cassandra.getSession().prepare(
				FIND_BY_NAME_AND_CONTACT_CQL);
		findByIdAndNameAndContactQuery = cassandra.getSession().prepare(
				FIND_BY_ID_AND_NAME_AND_CONTACT_CQL);
		// findByTimeQuery = cassandra.getSession().prepare(FIND_BY_TIME_CQL);
		ResultSet resultSet = cassandra.getSession().execute(FIND_ALL_QUERY);
		for (Row result : resultSet) {
			Patient patient = transform(result);
			if (patient.getId() > patientId) {
				patientId = patient.getId();
			}
		}
		patientId++;
	}

	@Override
	public Patient transform(Row row) {

		Patient patient = new Patient();
		patient.setId(row.getInt(Column.ID.name));
		patient.setName(row.getString(Column.NAME.name));
		patient.setGender(row.getString(Column.GENDER.name) == null ? null
				: Gender.valueOf(row.getString(Column.GENDER.name)));
		patient.setAge(row.getInt(Column.AGE.name));
		patient.setRemark(row.getString(Column.REMARK.name));
		patient.setBirthday(row.getDate(Column.BIRTHDAY.name));
		patient.setIdentification(row.getString(Column.IDENTIFICATION.name));
		patient.setPastDiseaseHistory(row
				.getString(Column.PASTDISEASEHISTORY.name));
		patient.setContact(row.getString(Column.CONTACT.name));
		patient.setPastMedicineHistory(row
				.getString(Column.PASTMEDICINEHISTORY.name));
		patient.setPicture(row.getBytes(Column.PICTURE.name));
		patient.setSign(row.getString(Column.SIGN.name));
		patient.setLastUpdateTime(row.getDate(Column.LASTUPDATETIME.name));
		return patient;
	}

	/**
	 * 根据病人id查找病人信息
	 * 
	 * @param patient
	 * @return
	 */
	public Patient findById(int patientId) {

		BoundStatement boundFindByIdQuery = new BoundStatement(findByIdQuery);
		boundFindByIdQuery.bind(patientId);
		ResultSet resultSet = cassandra.getSession()
				.execute(boundFindByIdQuery);
		Row result = resultSet.one();
		return result == null ? null : transform(result);
	}

	/**
	 * 根据病人名字查找病人信息
	 * 
	 * @param patient
	 * @return
	 */
	public List<Patient> findByName(String name) {

		BoundStatement boundFindByIdQuery = new BoundStatement(findByNameQuery);
		boundFindByIdQuery.bind(name);
		ResultSet resultSet = cassandra.getSession()
				.execute(boundFindByIdQuery);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	/**
	 * 根据id，name查找病人
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public List<Patient> findByIdAndName(Integer id, String name) {
		BoundStatement boundFindByIdQuery = new BoundStatement(
				findByIdAndNameQuery);
		boundFindByIdQuery.bind(id, name);
		ResultSet resultSet = cassandra.getSession()
				.execute(boundFindByIdQuery);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	/**
	 * 根据id，contact查找病人
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public List<Patient> findByIdAndContact(Integer id, String contact) {
		BoundStatement boundFindByIdQuery = new BoundStatement(
				findByIdAndContactQuery);
		boundFindByIdQuery.bind(id, contact);
		ResultSet resultSet = cassandra.getSession()
				.execute(boundFindByIdQuery);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	/**
	 * 根据name，contact查找病人
	 * 
	 * @param contact
	 * @param name
	 * @return
	 */
	public List<Patient> findByNameAndContact(String name, String contact) {
		BoundStatement boundFindByIdQuery = new BoundStatement(
				findByNameAndContactQuery);
		boundFindByIdQuery.bind(name, contact);
		ResultSet resultSet = cassandra.getSession()
				.execute(boundFindByIdQuery);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	/**
	 * 根据id、name、contact查找病人
	 * 
	 * @param id
	 * @param name
	 * @param contact
	 * @return
	 */
	public List<Patient> findByIdAndNameAndContact(Integer id, String name,
			String contact) {
		BoundStatement boundFindByIdQuery = new BoundStatement(
				findByIdAndNameAndContactQuery);
		boundFindByIdQuery.bind(id, name, contact);
		ResultSet resultSet = cassandra.getSession()
				.execute(boundFindByIdQuery);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	/**
	 * 用身份证和联系方式查找
	 * 
	 * @param id
	 * @param contact
	 * @return
	 */
	public List<Patient> findByIDAndContact(String id, String contact) {
		BoundStatement boundFindByIdQuery = new BoundStatement(
				findByIDAndContactQuery);
		boundFindByIdQuery.bind(id, contact);
		ResultSet resultSet = cassandra.getSession()
				.execute(boundFindByIdQuery);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	/**
	 * 根据身份证查找
	 * 
	 * @param id
	 * @return
	 */
	public List<Patient> findByIDId(String id) {

		BoundStatement boundFindByIdQuery = new BoundStatement(findByIDQuery);
		boundFindByIdQuery.bind(id);
		ResultSet resultSet = cassandra.getSession()
				.execute(boundFindByIdQuery);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	/**
	 * 根据联系方式查找
	 * 
	 * @param contact
	 * @return
	 */
	public List<Patient> findByContact(String contact) {

		BoundStatement boundFindByIdQuery = new BoundStatement(
				findByContactQuery);
		boundFindByIdQuery.bind(contact);
		ResultSet resultSet = cassandra.getSession()
				.execute(boundFindByIdQuery);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	public List<Patient> findAllOrderedById() {

		// TODO Order
		ResultSet resultSet = cassandra.getSession().execute(FIND_ALL_QUERY);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	/**
	 * 根据最后上传时间查找最新数据
	 * 
	 * @param lastUploadTime
	 * @return
	 */
	public List<Patient> findAllBytTime(Date lastUploadTime) {
		BoundStatement boundFindByTimeQuery = new BoundStatement(
				findByTimeQuery);
		boundFindByTimeQuery.bind(lastUploadTime);
		ResultSet resultSet = cassandra.getSession().execute(
				boundFindByTimeQuery);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	/**
	 * 保存数据，自增，多系统时候会有问题
	 * 
	 * @param patient
	 * @return
	 */
	public int save(Patient patient) {

		BoundStatement boundInsertQuery = new BoundStatement(insertQuery);
		int pid = patientId;
		boundInsertQuery.bind(pid, patient.getName(),
				patient.getGender() == null ? null : patient.getGender()
						.toString(), patient.getAge(), patient.getRemark(),
				patient.getBirthday(), patient.getIdentification(), patient
						.getPastDiseaseHistory(), patient.getContact(), patient
						.getPastMedicineHistory(), patient.getPicture(),
				patient.getSign(), new Date(System.currentTimeMillis()));
		try {
			cassandra.getSession().execute(boundInsertQuery);
			patientId++;
		} catch (DriverException e) {
			log.error(String
					.format(" Failed to create/update database entry for patient #%d: %s",
							patient.getId(), e.getMessage()));
			throw e;
		}
		log.info(String.format("Created database entry for patient #%d", pid));
		return pid;
	}

	/**
	 * 根据病人唯一id保存信息
	 * 
	 * @param patient
	 * @return
	 */
	public int saveById(Patient patient) {

		BoundStatement boundInsertQuery = new BoundStatement(insertQuery);
		boundInsertQuery.bind(patient.getId(), patient.getName(), patient
				.getGender().toString(), patient.getAge(), patient.getRemark(),
				patient.getBirthday(), patient.getIdentification(), patient
						.getPastDiseaseHistory(), patient.getContact(), patient
						.getPastMedicineHistory(), patient.getPicture(),
				patient.getSign(), new Date(System.currentTimeMillis()));
		try {
			cassandra.getSession().execute(boundInsertQuery);
		} catch (DriverException e) {
			log.error(String
					.format("Failed to create/update database entry for patient #%d: %s",
							patient.getId(), e.getMessage()));
			throw e;
		}
		log.info(String.format("Created database entry for patient #%d ",
				patient.getId()));
		return patient.getId();
	}

	/**
	 * 根据病人唯一id保存远程更新的信息，病人最后一次修改时间不变
	 * 
	 * @param patient
	 * @return
	 */
	public int saveRemoteById(Patient patient) {

		BoundStatement boundInsertQuery = new BoundStatement(insertQuery);
		boundInsertQuery.bind(patient.getId(), patient.getName(), patient
				.getGender().toString(), patient.getAge(), patient.getRemark(),
				patient.getBirthday(), patient.getIdentification(), patient
						.getPastDiseaseHistory(), patient.getContact(), patient
						.getPastMedicineHistory(), patient.getPicture(),
				patient.getSign(), patient.getLastUpdateTime());
		try {
			cassandra.getSession().execute(boundInsertQuery);
		} catch (DriverException e) {
			log.error(String
					.format("Failed to create/update database entry for patient #%d: %s",
							patient.getId(), e.getMessage()));
			throw e;
		}
		log.info(String.format("Created database entry for patient #%d",
				patient.getId()));
		return patient.getId();
	}

	/**
	 * 修改病人
	 * 
	 * @param patient
	 * @return
	 */
	public Patient modify(Patient patient) {
		delete(patient);
		BoundStatement boundInsertQuery = new BoundStatement(insertQuery);
		boundInsertQuery.bind(patient.getId(), patient.getName(), patient
				.getGender().toString(), patient.getAge(), patient.getRemark(),
				patient.getBirthday(), patient.getIdentification(), patient
						.getPastDiseaseHistory(), patient.getContact(), patient
						.getPastMedicineHistory(), patient.getPicture(),
				patient.getSign(), new Date());
		try {
			cassandra.getSession().execute(boundInsertQuery);
		} catch (DriverException e) {
			log.error(String
					.format("Failed to create/update database entry for patient #%d:%s",
							patient.getId(), e.getMessage()));
			throw e;
		}
		log.info(String.format("Update database entry for patient #%d:",
				patient.getId()));
		return patient;
	}

	/**
	 * 删除病人
	 * 
	 * @param patient
	 */
	public void delete(Patient patient) {
		BoundStatement boundDeleteQuery = new BoundStatement(deleteQuery);
		boundDeleteQuery.bind(patient.getId());
		try {
			cassandra.getSession().execute(boundDeleteQuery);
		} catch (DriverException e) {
			log.error(String.format(
					"Failed to delete database entry for patient %d",
					patient.getId()));
			throw e;
		}
		log.info(String.format("Deleted database entry for patient %d",
				patient.getId()));
	}

	/**
	 * 根据ids查找病人
	 * 
	 * @param ids
	 * @return
	 */
	public List<Patient> findByIds(List<Integer> ids) {
		StringBuilder sb = new StringBuilder("(");
		for (int i = 0; i < ids.size() - 1; i++) {
			sb.append(i + ",");
		}
		sb.append(ids.get(ids.size() - 1) + ")");
		String cql = String.format("select *　from %s Where %s in ", TABLE_NAME,
				Column.ID.name) + sb.toString();
		ResultSet resultSet = cassandra.getSession().execute(cql);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	/**
	 * 根据ids和时间查找病人
	 * 
	 * @param ids
	 * @param lastUploadTime
	 * @return
	 */
	public List<Patient> findByIdsAndTime(List<Integer> ids, Date lastUploadTime) {
		StringBuilder sb = new StringBuilder("(");
		for (int i = 0; i < ids.size() - 1; i++) {
			sb.append(i + ",");
		}
		sb.append(ids.get(ids.size() - 1) + ")");
		String cql = String.format(
				"select *　from %s Where %s in %s and %s > %s ", TABLE_NAME,
				Column.ID.name, sb.toString(), Column.LASTUPDATETIME.name,
				lastUploadTime);
		ResultSet resultSet = cassandra.getSession().execute(cql);
		List<Patient> patients = new ArrayList<Patient>();
		for (Row result : resultSet) {
			Patient patient = transform(result);
			patients.add(patient);
		}
		return patients;
	}

	/**
	 * 根据ids删除病人
	 * 
	 * @param ids
	 * @return
	 */
	public void deleteByIds(List<Integer> ids) {
		StringBuilder sb = new StringBuilder("(");
		for (int i = 0; i < ids.size() - 1; i++) {
			sb.append(i + ",");
		}
		sb.append(ids.get(ids.size() - 1) + ")");
		String cql = String.format("delete from %s where %s in %s", TABLE_NAME,
				Column.ID.name, sb.toString());
		try {
			cassandra.getSession().execute(cql);
		} catch (DriverException e) {
			log.error(String.format(
					"Failed to delete database entry for patient ids in %s",
					sb.toString()));
			throw e;
		}
		log.info(String.format("delete database entry for patient ids in %s",
				sb.toString()));
	}


}
