package hopperPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class hopModel {

    public static ArrayList<hop> getHops() {
        ArrayList<hop> hopsList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();

            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("select * from hop");

            while (results.next()) {
                int id = results.getInt("id");
                int user_id = results.getInt("user_id");
                String content = results.getString("content");
                String datetime = results.getString("datetime");
                int likes = results.getInt("likes");

                hopsList.add(new hop(id, user_id, content, datetime, likes));
            }

            results.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            // todo something later
        } catch (ClassNotFoundException ex) {
            // todo something later
        }

        return hopsList;
    }
    
    public static ArrayList<hop> getUsersHops() {
        ArrayList<hop> singlePersonHops = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("select * from hop where user_id = ?");
            while (results.next()) {
                int id = results.getInt("id");
                int user_id = results.getInt("user_id");
                String content = results.getString("content");
                String datetime = results.getString("datetime");
                int likes = results.getInt("likes");
                singlePersonHops.add(new hop(id, user_id, content, datetime, likes));
            }
            results.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            // todo something later
        } catch (ClassNotFoundException ex) {
            // todo something later
        }

        return singlePersonHops;
    }

    public static String addHop(hop hop) {
        try {
            Connection connection = DBConnection.getConnection();
            String preparedSQL = "insert into hop (content) "
                    + " values ( ? )";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);
            statement.setString(1, hop.getContent());
            statement.execute();
        } catch (SQLException ex) {
            return ex.toString();
        } catch (ClassNotFoundException ex) {

        }
        return "";
    }

    public static void deleteHop(hop hop) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "delete from hop where id = ? ";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);
            // first index is 1, it's ok to cry
            statement.setInt(1, hop.getId());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
        }
    }

    public static void addLike(hop hop) {
        //TODO 
        //UPDATE LIKE COUNT BY ONE
        try {
            Connection connection = DBConnection.getConnection();
            String preparedSQL = "update hop set likes "
                    + "where id = ?";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);
            // first index is 1, it's ok to cry
            statement.setInt(1, hop.getLikes() + 1);
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
        }
    }
}
