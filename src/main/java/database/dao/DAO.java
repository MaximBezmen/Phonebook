package database.dao;

import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DAO {

    //    default Connection connect() {
    //        DataSource ds = null;
    //        try {
    //            InitialContext initContext = new InitialContext();
    //            ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/postgres");
    //            if (ds == null) {
    //                System.out.print("Data source not found!");
    //            }
    //        } catch (Exception e) {
    //
    //        }
    //        try (Connection connection = ds.getConnection()) {
    //            return connection;
    //        } catch (Exception e) {
    //            return null;
    //        }
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
    //        }
    }

