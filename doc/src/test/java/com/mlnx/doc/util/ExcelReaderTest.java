package com.mlnx.doc.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.doc.entity.City;
import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.entity.Hospital;
import com.mlnx.doc.entity.Province;
import com.mlnx.doc.service.CityService;
import com.mlnx.doc.service.DoctorService;
import com.mlnx.doc.service.HospitalService;
import com.mlnx.doc.service.ProvinceService;
import com.mlnx.doc.web.BaseCol;
public class ExcelReaderTest extends BaseCol {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private CityService cityService;

	@Autowired
	private HospitalService hospitalService;

	// // 数据库连接
	// private static final String URL = "jdbc:mysql://localhost:3306/test";
	// private static final String NAME = "root";
	// private static final String PASS = "123456";
	// private static final String DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * 批量注册医生
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		Workbook wb = ExcelReader.createWb("V://doctor_list.xls");

		// 获取Workbook中Sheet个数
		int sheetTotal = wb.getNumberOfSheets();
		System.out.println(String.format("工作簿中的工作表个数为：%s", sheetTotal));

		// 获取Sheet
		Sheet sheet = ExcelReader.getSheet(wb, 0);

		// 遍历Sheet
		List<Object[]> list = ExcelReader.listFromSheet(sheet);
		list.remove(0);// 删除第一行的标头
		long count = 12312340000l;
		for (Object[] o : list) {
			count ++;
			String proviceName = (String) o[1];
			Province p = provinceService.findByUName(proviceName);
			// if(p==null){
			// Province province = new Province();
			// province.setName(proviceName);
			// provinceService.save(province);
			// }

			String cityName = (String) o[2];
			City c = cityService.findByUName(cityName);
			// if(c==null){
			// City city = new City();
			// city.setName(cityName);
			// city.setProvince_id(p.getId());
			// cityService.save(city);
			// }
			String hospitalName = (String) o[3];
			Hospital h = hospitalService.findByUName(hospitalName);
			// if(h==null){
			// Hospital hospital = new Hospital();
			// hospital.setName(hospitalName);
			// hospital.setCity_id(c.getId());
			// hospitalService.save(hospital);
			// }
			String name = (String) o[4];
			String sex = (String) o[5];
			String title = (String) o[7];
			String skill = (String) o[8];
			String background = (String) o[9];
			String achievement = (String) o[10];

			Doctor doctor = new Doctor();
			doctor.setAchievement(achievement);
			doctor.setBackground(background);
			/**
			 * 随机产生70年前的生日\年龄,联系方式，性别
			 */
			double random = Math.random();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, (int) (-70 * random));
			cal.add(Calendar.MONTH, (int) (-12 * random));
			cal.add(Calendar.DATE, (int) (-30 * random));
			Date birthday = new Date(cal.getTimeInMillis());
			doctor.setBirthday(birthday);
			doctor.setCity(c.getId()+"");
			doctor.setHospital(hospitalName);
			doctor.setHospital_id(h.getId());
			doctor.setName(name);
			String[] offices = {"呼吸内科","消化内科","神经内科","心血管内科","肾内科","血液内科","免疫科","内分泌科","普通外科","神经外科","心胸外科","泌尿外科","心血管外科","乳腺外科","肝胆外科","器官移植","肛肠外科","烧伤科","骨外科"};
			doctor.setOffice(offices[(int) (offices.length * Math.random())]);
			doctor.setPassword("123456");
			doctor.setPhone(count+"");
			doctor.setProvince(p.getId()+"");
			doctor.setSex(sex);
			doctor.setSkill(skill);
			doctor.setTitle(title);
			doctor.setUsername((String)o[0]);
			doctorService.register(doctor);
		}

		// // 创建连接
		// Connection con = null;
		// // 查要生成实体类的表
		// String sql =
		// "INSERT INTO t_doctor(achievement,background,birthday,city,date,hospital,hospital_id,name,office,password,phone,pic,province,sex,skill,title,username,voip_account,voip_password) VALUES (?, 'background',?, ?, ?, ?, ?, ?, ?, '2121', ?, ?, ?, '男', ?,?, ?, ?, ?);";
		//
		// PreparedStatement pStemt = null;
		// try {
		// try {
		// Class.forName(DRIVER);
		// } catch (ClassNotFoundException e1) {
		// e1.printStackTrace();
		// }
		// con = DriverManager.getConnection(URL, NAME, PASS);
		// // 获取所有表名
		// pStemt = con.prepareStatement(sql);
		// pStemt.setString(1, "nihao");
		// ResultSet rs = pStemt.executeQuery(sql);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// } finally {
		// try {
		// con.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }

		// 使用jdbc进行插入，hibernate的批量插入效果可能不会很好
		// System.out.println(list.toString());
	}

}
