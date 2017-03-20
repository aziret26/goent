package kg.goent.bean;

import kg.goent.facade.ProjectFacade;
import kg.goent.model.Project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by azire on 3/19/2017.
 */
@ManagedBean
@SessionScoped
public class ProjectBean {
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String createProject(){
        project.setProjectDate(new Date());
        project.setProjectStatus(true);
        ProjectFacade pf = new ProjectFacade();
        pf.createProject(project);
        return "/index";
    }
}
