package database.dao.daoImpl;

import database.dao.DAO;
import entity.Address;
import entity.Contact;
import entity.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import type.FamilyStatusType;
import type.SexType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactDao implements DAO {
    final String UPDATE_SQL = "UPDATE contact SET first_name=?, last_name=?, middle_name=?, birthday=?, gender=?," +
            "citizenship=?, family_status=?, web_site=?, email=?, current_place_of_work=?, address_id=? WHERE id=?";
    final String INSERT_SQL = "INSERT INTO contact (first_name, last_name, middle_name, birthday, gender," +
            "citizenship, family_status, web_site, email, current_place_of_work, address_id)"
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    final String DELETE_SQL = "DELETE FROM contact WHERE id=?";
    final String SELECT_SQL = "SELECT * FROM contact WHERE id=?";
    final String SELECT_SIMPLE_SQL = "SELECT address_id FROM contact WHERE id=?";
    final Logger logger = LoggerFactory.getLogger(ContactDao.class);

    private final AddressDao addressDao;
    private final PhoneNumberDao phoneNumberDao;

    public ContactDao(AddressDao addressDao, PhoneNumberDao phoneNumberDao) {
        this.addressDao = addressDao;
        this.phoneNumberDao = phoneNumberDao;
    }

    public Contact save(Contact entity) {
        Long contactId = 0L;
        Address addressEntity = null;
        List<PhoneNumber> phoneNumbers = null;
        try (Connection connection = connect()) {
            connection.setAutoCommit(false);
            Date birthday = Date.valueOf(entity.getBirthday());

            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, entity.getFirstName());
                preparedStatement.setString(2, entity.getLastName());
                preparedStatement.setString(3, entity.getMiddleName());
                preparedStatement.setDate(4, birthday);
                preparedStatement.setString(5, entity.getGender().name());
                preparedStatement.setString(6, entity.getCitizenship());
                preparedStatement.setString(7, entity.getFamilyStatus().name());
                preparedStatement.setString(8, entity.getWebSite());
                preparedStatement.setString(9, entity.getEmail());
                preparedStatement.setString(10, entity.getCurrentPlaceOfWork());
                addressEntity = addressDao.save(entity.getAddress(), connection);
                preparedStatement.setLong(11, addressEntity.getId());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            contactId = resultSet.getLong(1);
                        }
                    } catch (SQLException e) {
                        logger.error(e.getMessage());
                    }
                }
                phoneNumbers = new ArrayList<>();
                for (PhoneNumber phoneNumber : entity.getPhoneNumbers()) {
                    phoneNumbers.add(phoneNumberDao.save(phoneNumber, contactId, connection));
                }
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                connection.rollback();
            }
            try (PreparedStatement prepareStatement = connection.prepareStatement(SELECT_SQL)) {
                prepareStatement.setLong(1, contactId);
                try (ResultSet resultSet = prepareStatement.executeQuery()) {
                    if (resultSet.next()) {

                        entity.setId(resultSet.getLong(1));
                        entity.setFirstName(resultSet.getString("first_name"));
                        entity.setLastName(resultSet.getString("last_name"));
                        entity.setMiddleName(resultSet.getString("middle_name"));
                        entity.setBirthday(LocalDate.parse(resultSet.getString("birthday")));
                        entity.setGender(SexType.valueOf(resultSet.getString("gender")));
                        entity.setCitizenship(resultSet.getString("citizenship"));
                        entity.setFamilyStatus(FamilyStatusType.valueOf(resultSet.getString("family_status")));
                        entity.setWebSite(resultSet.getString("web_site"));
                        entity.setEmail(resultSet.getString("email"));
                        entity.setCurrentPlaceOfWork(resultSet.getString("current_place_of_work"));
                        entity.setAddress(addressEntity);
                        entity.setPhoneNumbers(phoneNumbers);
                    }
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    public void delete(Long id) {
        Long addressId = null;
        try (Connection connection = connect()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SIMPLE_SQL)) {
                preparedStatement.setLong(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()){
                        addressId = resultSet.getLong("address_id");
                    }
                }catch (SQLException e){
                    logger.error(e.getMessage());
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
                phoneNumberDao.deleteByContactId(id, connection);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                addressDao.delete(addressId, connection);
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }


    public Contact update(Contact entity) {
        List<PhoneNumber> phoneNumbers = null;
        Address addressEntity = null;
        Date birthday = Date.valueOf(entity.getBirthday());
        try (Connection connection = connect()) {

            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
                preparedStatement.setString(1, entity.getFirstName());
                preparedStatement.setString(2, entity.getLastName());
                preparedStatement.setString(3, entity.getMiddleName());
                preparedStatement.setDate(4, birthday);
                preparedStatement.setString(5, entity.getGender().name());
                preparedStatement.setString(6, entity.getCitizenship());
                preparedStatement.setString(7, entity.getFamilyStatus().name());
                preparedStatement.setString(8, entity.getWebSite());
                preparedStatement.setString(9, entity.getEmail());
                preparedStatement.setString(10, entity.getCurrentPlaceOfWork());
                addressEntity = addressDao.update(entity.getAddress(), connection);
                preparedStatement.setLong(11, addressEntity.getId());
                preparedStatement.setLong(12, entity.getId());
                preparedStatement.executeUpdate();
                phoneNumbers = new ArrayList<>();
                for (PhoneNumber phoneNumber : entity.getPhoneNumbers()) {
                    phoneNumbers.add(phoneNumberDao.update(phoneNumber, entity.getId(), connection));
                }
                entity.setPhoneNumbers(phoneNumbers);
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                try {
                    logger.error(e.getMessage());
                    connection.rollback();
                } catch (SQLException exception) {
                    logger.error(exception.getMessage());
                }
            }

            try (PreparedStatement preparedStatement = connect().prepareStatement(SELECT_SQL)) {
                preparedStatement.setLong(1, entity.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        entity.setId(resultSet.getLong(1));
                        entity.setFirstName(resultSet.getString("first_name"));
                        entity.setLastName(resultSet.getString("last_name"));
                        entity.setMiddleName(resultSet.getString("middle_name"));
                        entity.setBirthday(LocalDate.parse(resultSet.getString("birthday")));
                        entity.setGender(SexType.valueOf(resultSet.getString("gender")));
                        entity.setCitizenship(resultSet.getString("citizenship"));
                        entity.setFamilyStatus(FamilyStatusType.valueOf(resultSet.getString("family_status")));
                        entity.setWebSite(resultSet.getString("web_site"));
                        entity.setEmail(resultSet.getString("email"));
                        entity.setCurrentPlaceOfWork(resultSet.getString("current_place_of_work"));
                    }
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    public Contact read(Long contactId) {
        Contact contact = null;
        try (Connection connection = connect()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)) {
                preparedStatement.setLong(1, contactId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        contact = new Contact();
                        contact.setId(resultSet.getLong(1));
                        contact.setFirstName(resultSet.getString("first_name"));
                        contact.setLastName(resultSet.getString("last_name"));
                        contact.setMiddleName(resultSet.getString("middle_name"));
                        contact.setBirthday(LocalDate.parse(resultSet.getString("birthday")));
                        contact.setGender(SexType.valueOf(resultSet.getString("gender")));
                        contact.setCitizenship(resultSet.getString("citizenship"));
                        contact.setFamilyStatus(FamilyStatusType.valueOf(resultSet.getString("family_status")));
                        contact.setWebSite(resultSet.getString("web_site"));
                        contact.setEmail(resultSet.getString("email"));
                        contact.setCurrentPlaceOfWork(resultSet.getString("current_place_of_work"));
                        Long addressId = resultSet.getLong("address_id");
                        contact.setAddress(addressDao.read(addressId, connection));
                        contact.setPhoneNumbers(phoneNumberDao.getAllPhoneNumberByContactId(contactId, connection));
                    }
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return contact;
    }
}
