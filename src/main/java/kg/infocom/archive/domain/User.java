package kg.infocom.archive.domain;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    private int id;
    private String name;
    private String surname;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", unique = true)
    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    @Column(name = "surname")
    public String getPassword() {
        return surname;
    }

    public void setPassword(String surname) {
        this.surname = surname;
    }
}
