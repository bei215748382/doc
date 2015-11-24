package com.mlnx.doc.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.AlreadyExistsException;
import com.datastax.driver.core.exceptions.DriverException;

public class CassandraService {

	private static final String CONFIG_RESOURCE_NAME = "property/cassandra.properties";

	private static final String[] DEFAULT_NODES = { "121.40.137.14" };

	private static final int DEFAULT_PORT = 19042;

	private static final String DEFAULT_KEYSPACE_NAME = "mlnx";

	private static final String DEFAULT_STRATEGY_CLASS = "SimpleStrategy";

	private static final int DEFAULT_REPLICATION_FACTOR = 1;

	private static final String CREATE_KEYSPACE_CQL = "CREATE KEYSPACE %s"
			+ " WITH replication = { 'class' : '%s'"
			+ ", 'replication_factor' : %d }";

	private static final String USE_KEYSPACE_CQL = "USE %s";

	private static final Logger log = LoggerFactory
			.getLogger(CassandraService.class);

	private String[] nodes;

	private int port;

	private String keyspaceName;

	private String strategyClass;

	private int replicationFactor;

	private Cluster cluster;

	private Session session;

	@PostConstruct
	public void start() throws Exception {

		loadConfig(CONFIG_RESOURCE_NAME);

		cluster = Cluster.builder().addContactPoints(nodes).withPort(port)
				.build();
		String clusterName = cluster.getMetadata().getClusterName();
		log.info("Connected to Cassandra cluster %s", clusterName);

		session = cluster.connect();
		try {
			session.execute(String.format(CREATE_KEYSPACE_CQL, keyspaceName,
					strategyClass, replicationFactor));
			log.info("Created keyspace %s in cluster %s", keyspaceName,
					clusterName);
		} catch (AlreadyExistsException e) {
			log.info("Found keyspace %s in cluster %s", keyspaceName,
					clusterName);
		}
		session.execute(String.format(USE_KEYSPACE_CQL, keyspaceName));
	}

	@PreDestroy
	public void stop() {

		if (cluster != null) {
			cluster.close();
		}
	}

	private void loadConfig(String configResourceName) {

		Properties config = new Properties();
		try (InputStream configIn = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(configResourceName)) {
			config.load(configIn);
			log.info("Using Cassandra configuration %s", configResourceName);
		} catch (IOException e) {
			log.info(String
					.format("%s:Failed to load Cassandra configuration %s from classpath - using default configuration",
							e.getMessage(), configResourceName));
		}

		String nodesValue = config.getProperty("cassandra.nodes");
		nodes = nodesValue == null ? DEFAULT_NODES : nodesValue.split(",");
		String portValue = config.getProperty("port");
		try {
			port = Integer.parseInt(portValue);
		} catch (Exception e) {
			port = DEFAULT_PORT;
		}
		keyspaceName = config.getProperty("cassandra.keyspaceName",
				DEFAULT_KEYSPACE_NAME);
		strategyClass = config.getProperty("cassandra.strategyClass",
				DEFAULT_STRATEGY_CLASS);

		String replicationFactorValue = config
				.getProperty("cassandra.replicationFactor");
		if (replicationFactorValue == null) {
			replicationFactor = DEFAULT_REPLICATION_FACTOR;
		} else {
			try {
				replicationFactor = Integer.parseInt(replicationFactorValue);
			} catch (NumberFormatException e) {
				log.info(String
						.format("%s:Invalid replication factor %s  - using default value %d instead",
								e.getMessage(), replicationFactorValue,
								DEFAULT_REPLICATION_FACTOR));
				replicationFactor = DEFAULT_REPLICATION_FACTOR;
			}
		}
	}

	public String getKeyspaceName() {

		return keyspaceName;
	}

	public Session getSession() {

		return session;
	}

	public void createTableIfNotExists(String tableName,
			String createTableQuery, String... createIndexQueries) {

		try {
			session.execute(createTableQuery);
			for (String createIndexQuery : createIndexQueries) {
				session.execute(createIndexQuery);
			}
			log.info(String.format("Created table %s in keyspace %s",
					tableName, keyspaceName));
		} catch (AlreadyExistsException e) {
			log.info(String.format("%s:Found table %s in keyspace %s",
					e.getMessage(), tableName, keyspaceName));
		} catch (DriverException e) {
			log.error(String.format(
					"%s:Failed to create table %s in keyspace %s",
					e.getMessage(), tableName, keyspaceName));
			throw e;
		}
	}

	public static String generateValuePlaceholders(int numValues) {

		if (numValues <= 0) {
			throw new IllegalArgumentException("numValues");
		}

		final String valuePlaceholder = "  ?";
		final String delimiter = ",\n";

		StringBuilder sb = new StringBuilder(valuePlaceholder.length()
				* numValues + delimiter.length() * (numValues - 1));
		for (int i = 0; i < numValues - 1; i++) {
			sb.append(valuePlaceholder).append(delimiter);
		}
		sb.append(valuePlaceholder);
		return sb.toString();
	}

}
