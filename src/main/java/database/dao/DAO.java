package database.dao;

import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.SQLException;

public interface DAO {

    default Connection connect() throws SQLException {
        DataSource ds = null;
        try {
            InitialContext initContext = new InitialContext();
            ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/postgres");
            if (ds == null) {
                System.out.print("Data source not found!");
            }
        } catch (Exception e) {

        }

        return ds.getConnection();
    }
}
