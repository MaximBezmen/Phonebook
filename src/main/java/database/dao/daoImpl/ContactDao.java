package database.dao.daoImpl;

import database.dao.DAO;
import entity.Contact;

import java.sql.*;

public class ContactDao implements DAO {

    public Long save(Contact entity) {

        Long id = 0L;

        try (Connection connection = connect()) {

            final String INSERT_SQL = "INSERT INTO contact (first_name, last_name, middle_name, birthday, gender," +
                    "citizenship, family_status, web_site, email, current_place_of_work, address_id)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            AddressDao dao = new AddressDao();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getMiddleName());
            preparedStatement.setString(4, entity.getBirthday().toString());
            preparedStatement.setString(5, entity.getGender().name());
            preparedStatement.setString(6, entity.getCitizenship());
            preparedStatement.setString(7, entity.getFamilyStatus().name());
            preparedStatement.setString(8, entity.getWebSite());
            preparedStatement.setString(9, entity.getEmail());
            preparedStatement.setString(10, entity.getCurrentPlaceOfWork());
            preparedStatement.setString(11, dao.save(entity.getAddress()).toString());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        id = resultSet.getLong(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    public void delete(Long id) {

    }


    public void update(Contact entity) {

    }


    public Contact read(Long id) {
        return null;
    }
}
