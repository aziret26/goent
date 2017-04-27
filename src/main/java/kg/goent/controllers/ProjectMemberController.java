package kg.goent.controllers;

import antlr.Tool;
import kg.goent.facade.*;
import kg.goent.models.*;
import kg.goent.tools.Tools;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.lang.reflect.Member;
import java.util.List;

/**
 * Created by b-207 on 4/21/2017.
 */
@ManagedBean
@ViewScoped
public class ProjectMemberController {
    private ProjectMember projectMember;

    private String userEmail;
    private String memberRole;
    private int projectId;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    @PostConstruct
    public void init(){
        projectMember = new ProjectMember();
        userEmail = "";
        memberRole = "";
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public UserSession getUserSession() {
        return userSession;
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
        System.out.println("searching if user exists");
        if(u == null || u.getEmail() == null){
            Tools.faceMessageWarn("User doesn't exist.","Please, look if email is correct.");
            return "";
        }
        System.out.println("user exists\n\nif member role correct");
        MemberRole mr = new MemberRoleFacade().findByRole(memberRole);
        if(mr == null || mr.getMemberRole() == null || mr.getMemberRoleId() <= 1){
            Tools.faceMessageWarn("Invalid memberole assigned: "+memberRole,"");
            return "";
        }

        System.out.println("member role correct\n\nsearching for project");

        Project project = new ProjectFacade().findById(projectId);
        if(project == null || project.getTitle() == null){
            Tools.faceMessageWarn("Invalid project id","");
            return "";
        }

        System.out.println("project exists\n\nlooking for privileges");
        ProjectMember pm = new ProjectMemberFacade().findByUserAndProject(userSession.getUser(),project);

        if(pm == null || pm.getMemberRole().getMemberRoleId() != 1){
            Tools.faceMessageWarn("You do not have privileges","");
            return "";
        }
        System.out.println("adding member is allowed\n\ncreating project member");

        projectMember.setUser(u);
        projectMember.setMemberRole(mr);
        projectMember.setMemberStatus(new MemberStatusFacade().findByStatus("pending"));
        new ProjectFacade().update(project);
        projectMember.setProject(new ProjectFacade().findById(projectId));
        System.out.println("setting project with id: "+project.getProjectId());
        new ProjectMemberFacade().create(projectMember);
        System.out.println("\n\n\n\nproject member successfully created.\n\n\n\n");

        Tools.faceMessageWarn("New team member has been added","Success");
        return "";
    }

    public List<MemberRole> findAllSimpleUsers(){
        return new MemberRoleFacade().findAllSimpleUsers();
    }
}
