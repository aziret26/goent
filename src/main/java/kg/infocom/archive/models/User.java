package kg.infocom.archive.models;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur on 13-Apr-17.
 */
@Entity
@ManagedBean(name="user")
@SessionScoped
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column
    private String fname;
    @Column
    private String lname;
    @Column
    private String password;

    private String confirm;

    @Column
    private String email;
    @Column
    private String phonel;
    @Column
    private String regDate;
    @Column
    private String activationKey;

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    @OneToMany(mappedBy = "userId")
    private List<ProjectMember> projectMemberList=new ArrayList<ProjectMember>();

    @Column
    @ManyToOne
    @JoinColumn(name = "userRoleId")
    private UserRole userRole;

    @Column
    @ManyToOne
    @JoinColumn(name = "userStatusId")
    private UserStatus userStatus;

    public int getUserId() {
        return userId;
    }
    public User(){}

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonel() {
        return phonel;
    }

    public void setPhonel(String phonel) {
        this.phonel = phonel;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public List<ProjectMember> getProjectMemberList() {
        return projectMemberList;
    }

    public void setProjectMemberList(List<ProjectMember> projectMemberList) {
        this.projectMemberList = projectMemberList;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
