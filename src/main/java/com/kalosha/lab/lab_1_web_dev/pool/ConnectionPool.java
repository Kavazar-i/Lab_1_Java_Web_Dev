package com.kalosha.lab.lab_1_web_dev.pool;

import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Log4j
public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> freeConnectionQueue = new LinkedBlockingQueue<>(POOL_SIZE);
    private BlockingQueue<Connection> usedConnectionQueue = new LinkedBlockingQueue<>(POOL_SIZE);
    private static final int POOL_SIZE = 8;

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            log.error("Connection failed: %s", e);
        }
    }

    private ConnectionPool() {
        String db_url = "jdbc:postgresql://localhost:5432/phonestest";

        Properties props = new Properties();
        props.put("user", "postgres"); //TODO: to ENV
        props.put("password", "1234567890"); //TODO: to ENV

        props.put("autoReconnect", "true");
        props.put("characterEncoding", "UTF-8");
        props.put("useUnicode", "true");
        props.put("useSSL", "true");
        props.put("useJDBCCompliantTimezoneShift", "true");
        props.put("useLegacyDatetimeCode", "false");
        props.put("serverTimezone", "UTC");
        props.put("serverSslCert", "classpath:server.crt");

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(db_url, props);
                freeConnectionQueue.add(connection);
            } catch (SQLException e) {
                log.error("Connection failed: %s", e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null; //TODO
        try {
            connection = freeConnectionQueue.take();
            usedConnectionQueue.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            usedConnectionQueue.remove(connection);
            freeConnectionQueue.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
