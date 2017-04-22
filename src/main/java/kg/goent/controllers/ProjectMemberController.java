package kg.goent.controllers;

import kg.goent.models.ProjectMember;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by b-207 on 4/21/2017.
 */
@ManagedBean
@ViewScoped
public class ProjectMemberController {
    private ProjectMember projectMember;

    private String userEmail;
    private String memberRole;

    @PostConstruct
    public void init(){
        projectMember = new ProjectMember();
        userEmail = "";
        memberRole = "";
    }

    public ProjectMember getProjectMember() {
        return projectMember;
    }

    public void setProjectMember(ProjectMember projectMember) {
        this.projectMember = projectMember;
    }

    public String getUserEmail() {
        if(projectMember.getUser() != null){
            return projectMember.getUser().getEmail();
        }
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        projectMember.getUser().setEmail(null);
        this.userEmail = userEmail;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }
}
