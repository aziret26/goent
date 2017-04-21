package kg.goent.facade;

import kg.goent.dao.ObjectDao;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class ProjectStatusFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(ProjectFacade projectFacade) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(projectFacade);
        objectDao.commitAndCloseTransaction();
    }

    public void update(ProjectFacade projectFacade) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(projectFacade);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(ProjectFacade projectFacade) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(projectFacade);
        objectDao.commitAndCloseTransaction();
    }

    public List<ProjectFacade> findAll(){
        List<ProjectFacade> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("ProjectFacade.findAll",ProjectFacade.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public ProjectFacade findById(Integer id) {
        ProjectFacade projectFacade;
        try {
            objectDao.beginTransaction();
            projectFacade = objectDao.getEntityManager().find(ProjectFacade.class, id);
        }catch (Exception ex){
            projectFacade = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return projectFacade;
    }

    public ProjectFacade findByStatus(String status){
        ProjectFacade ms;
        try {
            objectDao.beginTransaction();
            ms = objectDao.getEntityManager().createNamedQuery("ProjectStatus.findByMemberStatus",ProjectFacade.class)
                    .setParameter("status",status).getSingleResult();
        }catch (Exception ex){
            ms = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return ms;
    }


}
