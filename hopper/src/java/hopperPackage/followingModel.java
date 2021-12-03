package hopperPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class followingModel {
    public static ArrayList<following> followList = new ArrayList<>();
    public static ArrayList<following> getFollowing() {
        try {
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
    
}
