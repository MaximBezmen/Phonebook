package repository;

import java.sql.*;

public interface DAO {
    default String connectionDbForSimple(String sql) {
        String s = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/phonebook", "postgres", "mamant38")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                s = resultSet.getString("first_name");
            }
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    default void disconnectionDb(ResultSet resultSet){
        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
