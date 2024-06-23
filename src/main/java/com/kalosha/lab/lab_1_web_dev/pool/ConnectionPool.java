package com.kalosha.lab.lab_1_web_dev.pool;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Log4j
public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> freeConnectionQueue;
    private BlockingQueue<Connection> usedConnectionQueue;
    private static final int POOL_SIZE = 8;
    public static final String PROPERTIES = "application.properties";
    private static final Lock lock = new ReentrantLock();

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to register PostgreSQL driver", e);
        }
    }

    private ConnectionPool() {
        freeConnectionQueue = new LinkedBlockingQueue<>(POOL_SIZE);
        usedConnectionQueue = new LinkedBlockingQueue<>(POOL_SIZE);

        Properties properties = new Properties();
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + PROPERTIES);
            }

            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties", e);
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                freeConnectionQueue.add(connection);
            } catch (SQLException e) {
                log.error("Failed to create connection: ", e);
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            Connection connection = freeConnectionQueue.take();
            usedConnectionQueue.put(connection);
            return connection;
        } catch (InterruptedException e) {
            log.error("Failed to get connection", e);
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public void releaseConnection(Connection connection) {
        try {
            if (usedConnectionQueue.remove(connection)) {
                freeConnectionQueue.put(connection);
            }
        } catch (InterruptedException e) {
            log.error("Failed to release connection", e);
            Thread.currentThread().interrupt();
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnectionQueue.take().close();
            } catch (SQLException | InterruptedException e) {
                log.error("Failed to close connection", e);
            }
        }
        deregisterDriver();
    }

    private void deregisterDriver() {
        try {
            DriverManager.deregisterDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            log.error("Failed to deregister driver", e);
        }
    }
}
