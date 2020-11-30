package database.dao.daoImpl;

import database.dao.DAO;
import entity.Address;

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


    public void update(Address entity) {

    }

    public Address read(Long addressId) {
        Address addressEntity = null;
        long id = 0L;
        String country = "null";
        String city = "null";
        String street = "null";
        String house = "null";
        String flat = "null";

        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
            preparedStatement.setString(1,"address");
            preparedStatement.setLong(2, addressId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                    country = resultSet.getString("country");
                    city = resultSet.getString("city");
                    street = resultSet.getString("street");
                    house = resultSet.getString("house");
                    flat = resultSet.getString("flat");
                }
                addressEntity = new Address(Long.valueOf(id), country, city, street, Integer.parseInt(house), Integer.parseInt(flat));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addressEntity;
    }
}
