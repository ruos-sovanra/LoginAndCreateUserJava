package repository;

import model.User;
import utils.PropertyUtils;
import utils.SQLUtils;

import java.sql.*;
import java.util.Properties;

public class UserRepository {
    private final Properties properties;
    public UserRepository (){
       properties =  PropertyUtils.loadProperty();
    }
    private Connection startDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("DB_URL"),
                properties.getProperty("USERNAME"),
                properties.getProperty("PASSWORD")
        );
    }
    public int createNewUser(User user){
        try (
                Connection connection = startDatabaseConnection();
        ) {
            var ps = connection.prepareStatement(SQLUtils.UserSQL.CREATENEWUSERS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Failed to add new user !");
            ex.printStackTrace();
        }
        return 0;
    }
    public User loginUser(User user) {
        try(Connection connection = startDatabaseConnection();){
            var ps = connection.prepareStatement(SQLUtils.UserSQL.LOGINUSER);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
