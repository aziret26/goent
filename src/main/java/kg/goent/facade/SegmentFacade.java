package kg.goent.facade;

import kg.goent.dao.ObjectDao;
import kg.goent.models.Segment;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class SegmentFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(Segment segment) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(segment);
        objectDao.commitAndCloseTransaction();
    }

    public void update(Segment segment) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(segment);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(Segment segment) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(segment) ? segment : objectDao.getEntityManager().merge(segment));
        objectDao.commitAndCloseTransaction();
    }

    public List<Segment> findAll(){
        List<Segment> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("Segment.findAll",Segment.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public Segment findById(Integer id) {
        Segment segment;
        try {
            objectDao.beginTransaction();
            segment = objectDao.getEntityManager().find(Segment.class, id);
        }catch (Exception ex){
            segment = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return segment;
    }

//    public Segment findByStatus(String status){
//        Segment ms;
//        try {
//            objectDao.beginTransaction();
//            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",Segment.class)
//                    .setParameter("status",status).getSingleResult();
//        }catch (Exception ex){
//            ms = null;
//        }finally {
//            objectDao.commitAndCloseTransaction();
//        }
//        return ms;
//    }


}
