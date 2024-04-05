package com.kalosha.lab.lab_1_web_dev.pool;

import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Log4j
@PropertySource("application.properties")
public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> freeConnectionQueue = new LinkedBlockingQueue<>(POOL_SIZE);
    private BlockingQueue<Connection> usedConnectionQueue = new LinkedBlockingQueue<>(POOL_SIZE);
    private static final int POOL_SIZE = 8;
    public static final String PROPERTIES = "/application.properties";

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            log.error("Connection failed: %s", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConnectionPool() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(String.valueOf(Paths.get(Paths.get(Objects.requireNonNull(ConnectionPool.class.getResource(PROPERTIES)).toURI()).toUri()))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                String url = properties.getProperty("db.url");
                String user = properties.getProperty("db.user");
                String password = properties.getProperty("db.password");
                Connection connection = DriverManager.getConnection(url, user, password);
                freeConnectionQueue.add(connection);
            } catch (SQLException e) {
                log.error("Connection failed: %s", e);
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        //TODO: lock
        instance = new ConnectionPool();
        //TODO: unlock
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

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnectionQueue.take().close();
            } catch (SQLException | InterruptedException e) {
                log.error("Connection failed: %s", e);
            }
        }
    }
}
