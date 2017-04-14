package kg.infocom.archive.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur on 13-Apr-17.
 */
@Entity
public class UserStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userStatusId;
    @Column
    private String userStatus;

    @OneToMany(mappedBy = "userStatus")
    private List<User> userList=new ArrayList<User>();


    public int getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(int userStatusId) {
        this.userStatusId = userStatusId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
