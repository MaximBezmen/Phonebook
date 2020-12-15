package database.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAO {
    Logger logger = LoggerFactory.getLogger(DAO.class);

    default Connection connect() {
        try {
//            HikariConfig config = new HikariConfig();
//            config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
//            config.setUsername("postgres");
//            config.setPassword("mamant38");
//            config.addDataSourceProperty("databaseName", "phonebook");
//            config.addDataSourceProperty("serverName", "127.0.0.1");

            HikariConfig config = new HikariConfig("/hikaricp.properties");

            HikariDataSource ds = new HikariDataSource(config);

            Connection connection = ds.getConnection();
            return connection;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return null;
    }
}


//        // JDBC driver name and database URL
//        String JDBC_DRIVER = "org.postgresql.Driver";
//        String DB_URL = "jdbc:postgresql://localhost:5432/phonebook";
//
//        //  Database credentials
//        String USER = "postgres";
//        String PASS = "mamant38";
//
//        default Connection connect () throws SQLException {
//            try {
//                Class.forName(JDBC_DRIVER);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            return DriverManager.getConnection(DB_URL,USER, PASS);