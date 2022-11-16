package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String command = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age TINYINT UNSIGNED)";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(command);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String command = "DROP TABLE IF EXISTS users;";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(command);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String command = "INSERT users (name, lastname, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(command);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        String command = "DELETE FROM users WHERE id = " + id;
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(command);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {


            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));

                list.add(user);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public void cleanUsersTable() {
            String command = "TRUNCATE users";
            try (Statement statement = Util.getConnection().createStatement()) {
                statement.executeUpdate(command);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
}
