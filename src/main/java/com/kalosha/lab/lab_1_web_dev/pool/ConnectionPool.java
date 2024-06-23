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

@Log4j
//@PropertySource("application.properties") todo
public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> freeConnectionQueue = new LinkedBlockingQueue<>(POOL_SIZE);
    private BlockingQueue<Connection> usedConnectionQueue = new LinkedBlockingQueue<>(POOL_SIZE);
    private static final int POOL_SIZE = 8;
    public static final String PROPERTIES = "prop/application.properties";

    static {
        try {
//            DriverManager.registerDriver(new org.postgresql.Driver()); todo 55 video 3
            Class.forName("org.postgresql.Driver");
//        }  throw new ExceptionInInitializerError(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); //TODO: end of everything
//            throw new ExceptionInInitializerError(e); //TODO: end of everything
        }
    }

    private ConnectionPool() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("application.properties");
// ...
        Properties properties = new Properties();
        try {
            properties.load(input);
        } catch (IOException e) {
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
                throw new ExceptionInInitializerError(e); //TODO: end of everything softer можно все в фор с логом и потом если конекшнов меньше 8 досоздать
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
            log.error("Connection failed: %s", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            usedConnectionQueue.remove(connection);
            freeConnectionQueue.put(connection);
        } catch (InterruptedException e) {
            log.error("Connection failed: %s", e);
            Thread.currentThread().interrupt();
        }
    }

    //TODO: deregister driver
    public void deregisterDriver() {
        try {
            DriverManager.deregisterDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            log.error("Connection failed: %s", e);
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
