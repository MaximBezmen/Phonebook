package database.dao.daoImpl;

import database.dao.DAO;
import entity.Address;
import entity.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressDao implements DAO {

    public void save(Address entity) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Statement statement = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            statement = connection.createStatement();

            final String INSERT_SQL = "INSERT INTO address (country, city, street, house, flat) VALUES("
                    + entity.getCountry() + "," + entity.getCity() + "," + entity.getStreet() + "," + entity.getHouse().toString() + ","
                    + entity.getFlat().toString() + ")";
            statement.executeUpdate(INSERT_SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void delete(Long id) {

    }


    public void update(Address entity) {

    }

    public Contact read(Long id) {
        return null;
    }
}
