package hopperPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserModel {

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();

            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("select * from user");

            while (results.next()) {
                int id = results.getInt("id");
                String username = results.getString("username");
                String password = results.getString("password");

                users.add(new User(id, username, password));
            }

            results.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            // todo something later
        } catch (ClassNotFoundException ex) {
            // todo something later
        }

        return users;
    }
    

    public static String addUser(User user) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "insert into user (username, password) "
                    + " values ( ?, ? )";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);
            // first index is 1, it's ok to cry
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());

            statement.execute();

        } catch (SQLException ex) {
            return ex.toString();
        } catch (ClassNotFoundException ex) {
            // todo something later
        }

        return "";
    }

    public static void updateUser(User user) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "update user set username = ?, password = ? "
                    + " where id = ? ";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);
            // first index is 1, it's ok to cry
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());

            statement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            // todo something later
        }
    }

    public static void searchUser(User user) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "select from user where id = ? ";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);
            // first index is 1, it's ok to cry
            statement.setInt(1, user.getId());

            statement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            // todo something later
        }
    }

}
