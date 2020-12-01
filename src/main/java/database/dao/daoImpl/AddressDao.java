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


    public Address update(Address entity) {
        String UPDATE_SQL = "UPDATE address SET country=?, city=?, street=?, house=?, flat=? WHERE id=?";

        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, entity.getCountry());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getStreet());
            preparedStatement.setInt(4, entity.getHouse());
            preparedStatement.setInt(5, entity.getFlat());
            preparedStatement.setLong(6, entity.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    entity.setId(resultSet.getLong(1));
                    entity.setCountry(resultSet.getString("country"));
                    entity.setStreet(resultSet.getString("city"));
                    entity.setStreet(resultSet.getString("street"));
                    entity.setHouse(Integer.parseInt(resultSet.getString("house")));
                    entity.setFlat(Integer.parseInt(resultSet.getString("flat")));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public Address read(Long addressId) {
        String SELECT_SQL = "SELECT * FROM address WHERE id=?";
        Address addressEntity = null;
             try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
            preparedStatement.setLong(1, addressId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    addressEntity = new Address();
                    addressEntity.setId(resultSet.getLong(1));
                    addressEntity.setCountry(resultSet.getString("country"));
                    addressEntity.setCity(resultSet.getString("city"));
                    addressEntity.setStreet(resultSet.getString("street"));
                    addressEntity.setHouse(Integer.parseInt(resultSet.getString("house")));
                    addressEntity.setFlat(Integer.parseInt(resultSet.getString("flat")));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addressEntity;
    }
}
