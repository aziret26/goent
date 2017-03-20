package kg.goent.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
 *
 * Created by azire on 3/17/2017.
 */

@Entity
@NamedQueries(
        @NamedQuery(name = "Project.findAll",
        query = "SELECT p FROM Project p")
)
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    @Column
    private String projectTitle;
    @Column
    private String projectDescription;
    @Column
    private boolean projectStatus;
    @Column
    private Date projectDate;

    @OneToOne(mappedBy = "project")
    private Bmc bmc;

    @ManyToMany
    @JoinTable(name = "projectToUser",
    joinColumns = @JoinColumn(name = "projectId"),
    inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<User> userList = new ArrayList<User>();

    public Project(){}

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public boolean isProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(boolean projectStatus) {
        this.projectStatus = projectStatus;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Date getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(Date projectDate) {
        this.projectDate = projectDate;
    }

    public Bmc getBmc() {
        return bmc;
    }

    public void setBmc(Bmc bmc) {
        this.bmc = bmc;
    }
}
