package database.dao.daoImpl;

import database.dao.DAO;
import entity.PhoneNumber;

public class PhoneNumberDao implements DAO {
    final String UPDATE_SQL = "UPDATE contact SET first_name=?, last_name=?, middle_name=?, birthday=?, gender=?," +
            "citizenship=?, family_status=?, web_site=?, email=?, current_place_of_work=?, address_id=? WHERE id=?";
    final String INSERT_SQL = "INSERT INTO contact (first_name, last_name, middle_name, birthday, gender," +
            "citizenship, family_status, web_site, email, current_place_of_work, address_id)"
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    final String DELETE_SQL = "DELETE FROM contact WHERE id=?";
    final String SELECT_SQL = "SELECT * FROM contact WHERE id=?";


    public Long save(PhoneNumber entity) {

        return null;
    }

    public void delete(Long id) {

    }

    public PhoneNumber update(PhoneNumber entity) {

        return null;
    }

    public PhoneNumber read(Long phoneNumberId) {

        return null;
    }
}
