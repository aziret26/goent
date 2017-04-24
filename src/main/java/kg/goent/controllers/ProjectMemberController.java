package kg.goent.controllers;

import kg.goent.facade.MemberRoleFacade;
import kg.goent.facade.UserFacade;
import kg.goent.models.MemberRole;
import kg.goent.models.ProjectMember;
import kg.goent.models.User;
import kg.goent.tools.Tools;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.lang.reflect.Member;

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
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public String addMember(){
        User u = new UserFacade().findByEmail(userEmail);
        if(u == null || u.getEmail() == null){
            Tools.faceMessageWarn("User doesn't exist.","Please, look if email is correct.");
            return "";
        }
        MemberRole mr = new MemberRoleFacade().findByRole(memberRole);
        if(mr == null || mr.getMemberRole() == null || mr.getMemberRoleId() != 1 || mr.getMemberRoleId() != 0){
            Tools.faceMessageWarn("User doesn't exist.","Please, look if email is correct.");
            return "";
        }

        projectMember.setUser(u);
        projectMember.setMemberRole(mr);
        


        return "";
    }
}
