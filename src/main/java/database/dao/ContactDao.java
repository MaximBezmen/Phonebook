package database.dao;

import database.DAO;
import entity.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactDao implements DAO {

    @Override
    public void save(Contact entity) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Statement statement = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            statement = connection.createStatement();
            final String INSERT_SQL = "INSERT INTO contact (first_name, last_name, middle_name, birthday, gender," +
                    "citizenship, family_status, web_site, email, current_place_of_work, address_id) VALUES ("
                    + entity.getFirstName() + entity.getLastName() + entity.getMiddleName() + entity.getBirthday().toString()
                    + entity.getGender().name() + entity.getCitizenship() + entity.getFamilyStatus().name()
                    + entity.getWebSite() + entity.getEmail() + entity.getCurrentPlaceOfWork() + entity.getAddress().getId().toString() + ")";
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

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Contact entity) {

    }

    @Override
    public Contact read(Long id) {
        return null;
    }
}
