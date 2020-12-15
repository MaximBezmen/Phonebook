package database.dao.daoImpl;

import database.dao.DAO;
import entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class AddressDao implements DAO {
    final String INSERT_SQL = "INSERT INTO address (country, city, street, house, flat) VALUES(?,?,?,?,?)";
    final String DELETE_SQL = "DELETE FROM address WHERE id=?";
    final String UPDATE_SQL = "UPDATE address SET country=?, city=?, street=?, house=?, flat=? WHERE id=?";
    final String SELECT_SQL = "SELECT * FROM address WHERE id=?";
    public static final Logger logger = LoggerFactory.getLogger(AddressDao.class);

    public Address save(Address entity, Connection connection) {

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
                    logger.error(e.getMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException exception) {
                logger.error(exception.getMessage());
            }
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    entity.setId(resultSet.getLong(1));
                    entity.setCountry(resultSet.getString("country"));
                    entity.setCity(resultSet.getString("city"));
                    entity.setStreet(resultSet.getString("street"));
                    entity.setHouse(Integer.parseInt(resultSet.getString("house")));
                    entity.setFlat(Integer.parseInt(resultSet.getString("flat")));
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException exception) {
                logger.error(exception.getMessage());
            }
        }
        return entity;
    }

    public void delete(Long id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException exception) {
                logger.error(exception.getMessage());
            }
        }
    }


    public Address update(Address entity, Connection connection) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, entity.getCountry());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getStreet());
            preparedStatement.setInt(4, entity.getHouse());
            preparedStatement.setInt(5, entity.getFlat());
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException exception) {
                logger.error(exception.getMessage());
            }
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)) {
            preparedStatement.setLong(1, entity.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    entity.setId(resultSet.getLong(1));
                    entity.setCountry(resultSet.getString("country"));
                    entity.setCity(resultSet.getString("city"));
                    entity.setStreet(resultSet.getString("street"));
                    entity.setHouse(Integer.parseInt(resultSet.getString("house")));
                    entity.setFlat(Integer.parseInt(resultSet.getString("flat")));
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException exception) {
                logger.error(exception.getMessage());
            }
        }

        return entity;
    }

    public Address read(Long addressId, Connection connection) {

        Address addressEntity = null;
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
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return addressEntity;
    }
}
