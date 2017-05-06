package kg.goent.controllers;

import kg.goent.facade.*;
import kg.goent.models.*;
import kg.goent.tools.Tools;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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

    @ManagedProperty(value = "#{projectSession}")
    private ProjectSession projectSession;

    @PostConstruct
    public void init(){
        projectMember = new ProjectMember();
        userEmail = "";
        memberRole = "";
    }

    public void setProjectSession(ProjectSession projectSession) {
        this.projectSession = projectSession;
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
        projectMember = new ProjectMember();
        User u = new UserFacade().findByEmail(userEmail);
        if(u == null || u.getEmail() == null){
            Tools.faceMessageWarn("User doesn't exist.","Please, look if email is correct.");
            return "";
        }
        MemberRole mr = new MemberRoleFacade().findByRole(memberRole);
        if(mr == null || mr.getMemberRole() == null || mr.getMemberRoleId() <= 1){
            Tools.faceMessageWarn("Invalid memberole assigned: "+memberRole,"");
            return "";
        }


        Project project = new ProjectFacade().findById(projectSession.getProject().getProjectId());
        if(project == null || project.getTitle() == null){
            Tools.faceMessageWarn("Invalid PROJECT id","");
            return "";
        }
        ProjectMember tempMember = new ProjectMemberFacade().findByUserAndProject(u,project);
        if(tempMember != null && tempMember.getMemberRole().getMemberRoleId() == 1){
            Tools.faceMessageWarn("Cannot add team leader","");
            return "";
        }

        if(tempMember != null){
            Tools.faceMessageWarn("User already in PROJECT team","");
            return "";
        }

        ProjectMember pm = new ProjectMemberFacade().findByUserAndProject(userSession.getUser(),project);

        if(pm == null || pm.getMemberRole().getMemberRoleId() != 1){
            Tools.faceMessageWarn("You do not have privileges","");
            return "";
        }

        projectMember.setUser(u);
        projectMember.setMemberRole(mr);
        project.getMemberList().add(projectMember);
        projectMember.setMemberStatus(new MemberStatusFacade().findByStatus("pending"));
        projectMember.setProject(project);
        new ProjectMemberFacade().create(projectMember);

        userSession.getUser().setProjectMemberList(new ProjectMemberFacade().findByUser(userSession.getUser()));

        Tools.faceMessageWarn("New team member has been added","Success");
        return "";
    }

    public List<MemberRole> findAllSimpleUsers(){
        return new MemberRoleFacade().findAllSimpleUsers();
    }
}
