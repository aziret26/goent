package kg.goent.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 3/20/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "UserStatus.findAll",
                query = "SELECT us FROM UserStatus us")
})
public class UserStatus implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userStatusId;

    @Column
    private String userStatusTitle;

    @OneToMany(mappedBy = "userStatus")
    private List<User> userList = new ArrayList<User>();

    public int getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(int userStatusId) {
        this.userStatusId = userStatusId;
    }

    public String getUserStatusTitle() {
        return userStatusTitle;
    }

    public void setUserStatusTitle(String userStatusTitle) {
        this.userStatusTitle = userStatusTitle;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
