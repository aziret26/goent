package kg.goent.facade;


import kg.goent.dao.SegmentDao;
import kg.goent.model.Segment;

import java.util.ArrayList;
import java.util.List;

public class SegmentFacade {

    private SegmentDao segmentDao = new SegmentDao();

    public void createUser(Segment segment) {
        segmentDao.beginTransaction();
        segmentDao.getEntityManager().persist(segment);
        segmentDao.commitAndCloseTransaction();
    }

    public void updateUser(Segment segment) {
        segmentDao.beginTransaction();
        segmentDao.getEntityManager().merge(segment);
        segmentDao.commitAndCloseTransaction();
    }

    public void deleteUser(Segment segment) {
        segmentDao.beginTransaction();
        segmentDao.getEntityManager().remove(segment);
        segmentDao.commitAndCloseTransaction();
    }

    public Segment findById(Integer id) {
        segmentDao.beginTransaction();
        Segment segment = segmentDao.getEntityManager().find(Segment.class, id);
        segmentDao.commitAndCloseTransaction();
        return segment;
    }
    public List<Segment> getAll(){
        List<Segment> users = new ArrayList<Segment>();
        segmentDao.beginTransaction();
        users = segmentDao.getEntityManager().createNamedQuery("Segment.findAll",Segment.class).getResultList();
        segmentDao.commitAndCloseTransaction();
        return users;
    }
}
