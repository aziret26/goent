package kg.goent.bean;

import kg.goent.dao.SegmentTypeDao;
import kg.goent.facade.SegmentTypeFacade;
import kg.goent.model.SegmentType;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 3/18/2017.
 */
@ManagedBean
public class SegmentTypeBean {
    private List<SegmentType> segmentTypeList = new ArrayList<SegmentType>();
    private SegmentType segmentType = new SegmentType();

    private SegmentTypeDao segmentTypeDao = new SegmentTypeDao();

    private SegmentTypeFacade segmentTypeFacade = new SegmentTypeFacade();
    @PostConstruct
    void init(){
        segmentTypeList = segmentTypeFacade.findAllOrdered();
    }

    public List<SegmentType> getSegmentTypeList() {
        return segmentTypeList;
    }

    public void setSegmentTypeList(ArrayList<SegmentType> segmentTyList) {
        this.segmentTypeList = segmentTyList;
    }

    public SegmentType getSegmentType() {
        return segmentType;
    }

    public void setSegmentType(SegmentType segmentType) {
        this.segmentType = segmentType;
    }

    public String createSegmentType(){
        segmentTypeFacade.createType(segmentType);
        return "manageSegmentType";
    }

    public void save(){
        segmentTypeDao.beginTransaction();
        EntityManager em = segmentTypeDao.getEntityManager();
        for(SegmentType st : segmentTypeList){
            em.persist(st);
        }
        segmentTypeDao.commitAndCloseTransaction();
    }

}
