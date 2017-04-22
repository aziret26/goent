package kg.goent.controllers;

import kg.goent.facade.*;
import kg.goent.models.*;
import kg.goent.tools.Tools;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
@ManagedBean
@ViewScoped
public class ProjectController {
    private Project project;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    private ProjectSession projectSession;

    private ProjectMember projectMember;

    @PostConstruct
    void initialize(){
        project = new Project();
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public void setProjectSession(ProjectSession projectSession) {
        this.projectSession = projectSession;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getFromDbProject(int id){
        return new ProjectFacade().findById(id);
    }
    public String createProject(){
        if(!userSession.isLogged()){
            Tools.faceMessageWarn("Операция невозможна.","");
        }
        project.setProjectDate(new Date());
        project.setProjectStatus(new ProjectStatusFacade().findByStatus("active"));

        ProjectMember projectMember = new ProjectMember();
        projectMember.setMemberStatus(new MemberStatusFacade().findByStatus("accepted"));
        projectMember.setMemberRole(new MemberRoleFacade().findByRole("team leader"));
        projectMember.setActivationDate(new Date());
        projectMember.setUser(userSession.getUser());
        new ProjectFacade().create(project);
        projectMember.setProject(project);

        new ProjectMemberFacade().create(projectMember);

        userSession.setUser(new UserFacade().findById(userSession.getUser().getUserId()));

        return "index";
    }

    public String removeProject(Project project){
        ProjectMemberFacade pmf = new ProjectMemberFacade();
        List<ProjectMember> pm = pmf.findByProject(project);

        for(ProjectMember member : pm){
            pmf.delete(member);
        }
        new ProjectFacade().delete(project);

        userSession.setUser(new UserFacade().findById(userSession.getUser().getUserId()));

        return "index";
    }

    public String addProjectMember(String email){
        /*
        * check for existing of user
        * send invitation to email
        * add project member to project with pending status and teamMember role
        *
        * */
        User user = new UserFacade().findByEmail(email);
        if(user == null){
            Tools.faceMessageWarn("User does not exist.","check if email is correct.");
            return "";
        }




        return "";
    }

    public String projectOverView(Project project){
        projectSession.setProject(project);
        return "/pages/project-overview";
    }
}
