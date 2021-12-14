package hopperPackage;

import java.io.InputStream;
import java.io.Serializable;

public class hop implements Serializable {

    private int id;
    private int user_id;
    private String content;
    private String datetime;
    private int likes;
    private InputStream image;
    private String filename;

    public hop() {
        this(0, 0, "", "", 0);
    }

    public hop(int id, int user_id, String content, String datetime, int likes) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.datetime = datetime;
        this.likes = likes;
    }

   
    public hop(int id, int user_id, String content, String datetime, int likes, InputStream image, String filename) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.datetime = datetime;
        this.likes = likes;
        this.image = image;
        this.filename = filename;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
      

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
