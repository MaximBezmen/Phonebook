package database.dao;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource implements DAO {

    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl("jdbc:postgresql://localhost:5432/phonebook");
        ds.setUsername("postgres");
        ds.setPassword("mamant38");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection connect() throws SQLException {
        return ds.getConnection();
    }
    private DBCPDataSource(){ }
}
