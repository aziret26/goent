package kg.infocom.archive.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by timur on 13-Apr-17.
 */
@Entity
public class ProjectMember implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberDetailId;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @Column
    private Date activationDate;
    @Column
    private Date lastActiveDate;

    @ManyToOne
    @JoinColumn(name = "memberStatusId")
    private MemberStatus memberStatus;

    @ManyToOne
    @JoinColumn(name = "memberRoleId")
    private MemberRole memberRole;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public int getMemberDetailId() {
        return memberDetailId;
    }

    public void setMemberDetailId(int memberDetailId) {
        this.memberDetailId = memberDetailId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(Date lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
