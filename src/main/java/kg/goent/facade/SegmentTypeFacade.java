package kg.goent.facade;

import kg.goent.dao.SegmentTypeDao;
import kg.goent.model.SegmentType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 3/18/2017.
 */
public class SegmentTypeFacade {
    SegmentTypeDao segmentDao = new SegmentTypeDao();
    public void createType(SegmentType segmentType) {
        segmentDao.beginTransaction();
        segmentDao.getEntityManager().persist(segmentType);
        segmentDao.commitAndCloseTransaction();
    }

    public void updateType(SegmentType segmentType) {
        segmentDao.beginTransaction();
        segmentDao.getEntityManager().merge(segmentType);
        segmentDao.commitAndCloseTransaction();
    }

    public void deleteType(SegmentType segmentType) {
        segmentDao.beginTransaction();
        segmentDao.getEntityManager().remove(segmentDao);
        segmentDao.commitAndCloseTransaction();
    }

    public SegmentType findById(Integer id) {
        segmentDao.beginTransaction();
        SegmentType user = segmentDao.getEntityManager().find(SegmentType.class, id);
        segmentDao.commitAndCloseTransaction();
        return user;
    }
    public List<SegmentType> findAll(){
        List<SegmentType> typesList = new ArrayList<SegmentType>();
        segmentDao.beginTransaction();
        typesList = segmentDao.getEntityManager().createNamedQuery("SegmentType.findAll",SegmentType.class).getResultList();
        segmentDao.commitAndCloseTransaction();
        return typesList;
    }
}
