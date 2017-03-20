package kg.goent.facade;

import kg.goent.dao.ProjectDao;
import kg.goent.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 3/20/2017.
 */
public class ProjectFacade {
    private ProjectDao projectDao = new ProjectDao();

    public void createProject(Project project) {
        projectDao.beginTransaction();
        projectDao.getEntityManager().persist(project);
        projectDao.commitAndCloseTransaction();
    }

    public void updateProject(Project project) {
        projectDao.beginTransaction();
        projectDao.getEntityManager().merge(project);
        projectDao.commitAndCloseTransaction();
    }

    public void deleteProject(Project project) {
        projectDao.beginTransaction();
        projectDao.getEntityManager().remove(project);
        projectDao.commitAndCloseTransaction();
    }

    public Project findById(Integer id) {
        projectDao.beginTransaction();
        Project project = projectDao.getEntityManager().find(Project.class, id);
        projectDao.commitAndCloseTransaction();
        return project;
    }
    public List<Project> getAll(){
        List<Project> projects = new ArrayList<Project>();
        projectDao.beginTransaction();
        projects = projectDao.getEntityManager().createNamedQuery("Project.findAll",Project.class).getResultList();
        projectDao.commitAndCloseTransaction();
        return projects;
    }
/*    public List<Project> getAllUserProjects(){
        List<Project> projects = new ArrayList<Project>();
        projectDao.beginTransaction();
        projects = projectDao.getEntityManager().createNamedQuery("Project.findAllUserprojects",Project.class).getResultList();
        projectDao.commitAndCloseTransaction();
        return projects;
    }*/
}
