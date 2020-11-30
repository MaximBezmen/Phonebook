package database.dao.daoImpl;

import database.dao.DAO;
import entity.Address;
import entity.Contact;

import java.sql.*;

public class AddressDao implements DAO {

    public Long save(Address entity) {

        Long id = 0L;

        try (Connection connection = connect()) {
            final String INSERT_SQL = "INSERT INTO address (country, city, street, house, flat)"
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getCountry());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getStreet());
            preparedStatement.setInt(4, entity.getHouse());
            preparedStatement.setInt(5, entity.getFlat());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if(resultSet.next()){
                    id = resultSet.getLong(1);
                    }
                }catch (SQLException e){
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


    public void update(Address entity) {

    }

    public Contact read(Long id) {
        return null;
    }
}
