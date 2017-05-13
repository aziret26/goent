package kg.goent.controllers;

import kg.goent.facade.*;
import kg.goent.facade.project.ProjectFacade;
import kg.goent.facade.project.ProjectMemberFacade;
import kg.goent.facade.project.ProjectStatusFacade;
import kg.goent.models.*;
import kg.goent.models.project.Project;
import kg.goent.models.project.ProjectMember;
import kg.goent.tools.Tools;
import kg.goent.tools.ViewPath;

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

    @ManagedProperty(value = "#{projectSession}")
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

    public String addProject(){
        //System.out.println("Path: "+ViewPath.ADD_PROJECT);
        return ViewPath.ADD_PROJECT;
    }

    public String createProject(){
        if(!userSession.isLogged()){
            Tools.faceMessageWarn("Операция невозможна.","");
            return "";
        }
        if(new ProjectFacade().findByTitle(project.getTitle()) != null){
            Tools.faceMessageWarn("Проект с таким названием уже существует.","");
            return "";
        }
        project.setProjectDate(new Date());
        project.setProjectStatus(new ProjectStatusFacade().findByStatus("active"));

        ProjectMember projectMember = new ProjectMember();
        projectMember.setMemberStatus(new MemberStatusFacade().findByStatus("accepted"));
        projectMember.setMemberRole(new MemberRoleFacade().findByRole("team leader"));
        projectMember.setActivationDate(new Date());
        projectMember.setUser(userSession.getUser());

        new ProjectFacade().create(project);
        System.out.print(project);
        projectMember.setProject(project);

        new ProjectMemberFacade().create(projectMember);

        userSession.setUser(new UserFacade().findById(userSession.getUser().getUserId()));
        return "/index?faces-redirect=true";

    }

    public String removeProject(Project project){
        ProjectMemberFacade pmFacade = new ProjectMemberFacade();
        List<ProjectMember> pmList = pmFacade.findByProject(project);

        for(ProjectMember pm : pmList){
            pmFacade.delete(pmFacade.findById(pm.getProjectMemberId()));
        }

        new ProjectFacade().delete(project);

        userSession.getUser().setProjectMemberList(pmFacade.findByUser(userSession.getUser()));

        return "index?faces-redirect=true";
    }

    public boolean existsProject(int projectId){
        Project p = new ProjectFacade().findById(projectId);
        return p != null && p.getTitle() != null;
    }

    public String addProjectMember(String email){
        /*
        * check for existing of user
        * send invitation to email
        * add PROJECT member to PROJECT with pending status and teamMember role
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
        return ViewPath.PROJECT_OVERVIEW + ViewPath.REDIRECT;
    }

    protected void destroySessions(){
        //System.out.printf("DESTROYING PROJECT SESSION");
        projectSession = new ProjectSession();
    }
}
