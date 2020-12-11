package database.dao.daoImpl;

import database.dao.DAO;
import entity.Address;

import java.sql.*;

public class AddressDao implements DAO {
    final String INSERT_SQL = "INSERT INTO address (country, city, street, house, flat) VALUES(?,?,?,?,?)";
    final String DELETE_SQL = "DELETE FROM address WHERE id=?";
    final String UPDATE_SQL = "UPDATE address SET country=?, city=?, street=?, house=?, flat=? WHERE id=?";
    final String SELECT_SQL = "SELECT * FROM address WHERE id=?";

    public Long save(Address entity, Connection connection) {

        Long id = 0L;

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
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

        try (Connection connection = connect()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Address update(Address entity) {

        try (Connection connection = connect()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public Address read(Long addressId) {

        Address addressEntity = null;
        try (Connection connection = connect()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addressEntity;
    }
}
