package database.dao;

import entity.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DAO {
    // JDBC driver name and database URL
   String JDBC_DRIVER = "org.postgresql.Driver";
   String DB_URL = "jdbc:postgresql://localhost:5432/phonebook";

    //  Database credentials
    String USER = "postgres";
    String PASS = "mamant38";
    default Connection connect () throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB_URL,USER, PASS);
    }
}
