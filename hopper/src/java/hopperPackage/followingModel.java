package hopperPackage;

import static hopperPackage.hopModel.getHops;
import static hopperPackage.hopModel.hopsList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class followingModel {
    
    public static ArrayList<following> followList = new ArrayList<>();
    public static ArrayList<following> getFollowing() {
        try {
            followList.clear();
            Connection connection = DBConnection.getConnection();

            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("select * from following");

            while (results.next()) {
                int user_id = results.getInt("user_id");
                int follow_user_id = results.getInt("follow_user_id");

                followList.add(new following(user_id, follow_user_id));
            }

            results.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            // todo something later
        } catch (ClassNotFoundException ex) {
            // todo something later
        }
        return followList;
    }
    
    
    public static String addFollow(following follow) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "insert into following (user_id, follow_user_id) "
                    + " values ( ?, ? )";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);
            // first index is 1, it's ok to cry
            statement.setInt(1, follow.getUser_id());
            statement.setInt(2, follow.getFollow_user_id());

            statement.execute();

        } catch (SQLException ex) {
            return ex.toString();
        } catch (ClassNotFoundException ex) {
            // todo something later
        }

        return "";
    }
    
    public static void deleteFollow(following follow) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "delete from following where user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);
            // need to have SQL delete where user_id and followingID = ? ?
            statement.setInt(1, follow.getUser_id());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
        }
    }

}
