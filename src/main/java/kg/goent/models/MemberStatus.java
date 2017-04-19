package kg.goent.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur on 13-Apr-17.
 */
@Entity
public class MemberStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberStatusId;

    @OneToMany(mappedBy = "memberStatus")
    private List<ProjectMember> projectMemberList=new ArrayList<ProjectMember>();

    @Column
    private String memberStatus;

    public int getMemberStatusId() {
        return memberStatusId;
    }

    public void setMemberStatusId(int memberStatusId) {
        this.memberStatusId = memberStatusId;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public List<ProjectMember> getProjectMemberList() {
        return projectMemberList;
    }

    public void setProjectMemberList(List<ProjectMember> projectMemberList) {
        this.projectMemberList = projectMemberList;
    }
}
