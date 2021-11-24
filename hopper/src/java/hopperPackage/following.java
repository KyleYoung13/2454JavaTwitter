package hopperPackage;

import java.io.Serializable;


public class following implements Serializable{
    private int user_id;
    private int follow_user_id;

    public following() {
        this(0, 0);
    }

    public following(int user_id, int follow_user_id) {
        this.user_id = user_id;
        this.follow_user_id = follow_user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFollow_user_id() {
        return follow_user_id;
    }

    public void setFollow_user_id(int follow_user_id) {
        this.follow_user_id = follow_user_id;
    }
    
    
    
    
}
