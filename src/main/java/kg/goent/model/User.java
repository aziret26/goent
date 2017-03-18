package kg.goent.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by azire on 3/16/2017.
 */
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name="User.findAll",
                query="SELECT u FROM User u"),
        @NamedQuery(name="User.findByPrimaryKey",
                query="SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name="User.findByLogin",
                query="SELECT u FROM User u WHERE u.login = :login")
})
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "first_name")
    private String fname;
    @Column(name = "last_name")
    private String lname;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private int type = 3;
    @Column(name="activation_key")
    private String activationKey;
    @Column
    private Date regDate;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String name) {
        this.fname = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public String phoneF() {
        String temp = "";
        if(phone != null)
            if(phone.length() == 13) {
                temp = phone.substring(0,4);
                temp += " "+phone.substring(4,7);
                temp += " "+phone.substring(7,10);
                temp += " "+phone.substring(10,13);
            }else
            if(phone.length() == 10){
                temp = phone.substring(0,4);
                temp += " "+phone.substring(4,7);
                temp += " "+phone.substring(7,10);
            }else{
                temp = phone;
            }
        return temp;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String toString(){
        String ans = "";
        if(getId()!=-1)
            ans = "ID\t= "+getId()+"\n";
        if(getLogin() != null)
            ans += "login\t= "+getLogin()+"\n";
        if(getFname()!=null)
            ans += "first name\t= "+getFname()+"\n";
        if(getLname()!=null)
            ans += "last name\t= "+getLname()+"\n";
        if(getEmail()!=null)
            ans += "email\t= "+getEmail()+"\n";
        if(getPhone()!=null)
            ans += "phone\t= "+getPhone()+"\n";
        if(getActivationKey() != null)
            ans += "activation_key\t= "+getActivationKey()+"\n\n";
        return ans;
    }

    public boolean isActive(){
        if(getActivationKey().length() == 0)
            return true;
        return false;
    }
}
