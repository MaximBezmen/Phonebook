package database.dao.daoImpl;

import database.dao.DAO;
import entity.Address;
import entity.Contact;
import type.FamilyStatusType;
import type.SexType;

import java.sql.*;
import java.time.LocalDate;

public class ContactDao implements DAO {

    private final AddressDao dao;

    public ContactDao(AddressDao dao) {
        this.dao = dao;
    }

    public Long save(Contact entity) {

        Long id = 0L;

        try (Connection connection = connect()) {

            final String INSERT_SQL = "INSERT INTO contact (first_name, last_name, middle_name, birthday, gender," +
                    "citizenship, family_status, web_site, email, current_place_of_work, address_id)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

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


    public Contact read(Long contactId) {
        String SELECT_SQL = "SELECT * FROM contact WHERE id=?";

        Contact contact = null;
        Long id = 0L;
        String firstName = "null";
        String lastName = "null";
        String middleName = "null";
        String birthday = "null";
        String gender = "null";
        String citizenship = "null";
        String familyStatus = "null";
        String webSite = "null";
        String email = "null";
        String currentPlaceOfWork = "null";
        Long addressId = null;
        Address address = null;
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
            preparedStatement.setLong(1, contactId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                    firstName = resultSet.getString("first_name");
                    lastName = resultSet.getString("last_name");
                    middleName = resultSet.getString("middle_name");
                    birthday = resultSet.getString("birthday");
                    gender = resultSet.getString("gender");
                    citizenship = resultSet.getString("citizenship");
                    familyStatus = resultSet.getString("family_status");
                    webSite = resultSet.getString("web_site");
                    email = resultSet.getString("email");
                    currentPlaceOfWork = resultSet.getString("current_place_of_work");
                    addressId = resultSet.getLong("address_id");

                }
                address = dao.read(addressId);
                contact = new Contact();
                contact.setId(id);
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setMiddleName(middleName);
                contact.setBirthday(LocalDate.parse(birthday));
                contact.setGender(SexType.valueOf(gender));
                contact.setCitizenship(citizenship);
                contact.setFamilyStatus(FamilyStatusType.valueOf(familyStatus));
                contact.setWebSite(webSite);
                contact.setEmail(email);
                contact.setCurrentPlaceOfWork(currentPlaceOfWork);
                contact.setAddress(address);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }
}
