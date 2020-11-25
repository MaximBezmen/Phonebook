package database;

import entity.Contact;

public interface DAO {
    // JDBC driver name and database URL
   String JDBC_DRIVER = "org.postgresql.Driver";
   String DB_URL = "jdbc:postgresql://localhost:5432/phonebook";

    //  Database credentials
    String USER = "postgres";
    String PASS = "mamant38";

    void save(Contact entity);

    void delete(Long id);

    void update(Contact entity);

    Contact read(Long id);
}
