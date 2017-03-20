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
        @NamedQuery(name = "UserType.findAll",
                query = "SELECT ut FROM UserType ut")
})
public class UserType implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userTypeId;

    @Column
    private String userType;

    @OneToMany(mappedBy = "userType")
    private List<User> userList = new ArrayList<User>();

    public int getUserTypeId() {

        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


}
