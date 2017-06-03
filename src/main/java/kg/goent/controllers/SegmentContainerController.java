package kg.goent.controllers;

import kg.goent.facade.bmc.BmcFacade;
import kg.goent.facade.bmc.SegmentContainerFacade;
import kg.goent.facade.bmc.SegmentFacade;
import kg.goent.facade.bmc.SegmentTypeFacade;
import kg.goent.facade.project.ProjectFacade;
import kg.goent.models.bmc.Segment;
import kg.goent.models.bmc.SegmentContainer;
import kg.goent.models.bmc.SegmentType;
import kg.goent.tools.ViewPath;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

import static kg.goent.tools.ViewPath.*;

/**
 * Created by azire on 5/4/2017.
 */
@ManagedBean
@ViewScoped
public class SegmentContainerController extends GetReqBean {
    private SegmentContainer segmentContainer;

    private List<SegmentContainer> segmentContainerList;

    @ManagedProperty(value = "#{segmentContainerSession}")
    private SegmentContainerSession segmentContainerSession;

    @ManagedProperty(value = "#{getReqBean}")
    private GetReqBean getReqBean;

    public void setSegmentContainerSession(SegmentContainerSession segmentContainerSession) {
        this.segmentContainerSession = segmentContainerSession;
    }

    public void setGetReqBean(GetReqBean getReqBean) {
        this.getReqBean = getReqBean;
    }

    @Override
    public void setBmcId(int bmcId) {
        if(bmcId != 0){
            segmentContainerList = new SegmentContainerFacade().findByBmc(new BmcFacade().findById(bmcId));
        }
        super.setBmcId(bmcId);
    }

    public SegmentContainer getSegmentContainer() {
        return segmentContainer;
    }

    public void setSegmentContainer(SegmentContainer segmentContainer) {
        this.segmentContainer = segmentContainer;
    }

    public List<SegmentContainer> getSegmentContainerList() {
        return segmentContainerList;
    }

    public void setSegmentContainerList(List<SegmentContainer> segmentContainerList) {
        this.segmentContainerList = segmentContainerList;
    }

    @Override
    public void setSegmentContainerId(int segmentContainerId) {
        if(segmentContainerId != 0){
            segmentContainer = new SegmentContainerFacade().findById(segmentContainerId);
        }
        super.setSegmentContainerId(segmentContainerId);
    }

    public String addSegmentContainer(){
        initSegmentContainer();
        return ViewPath.ADD_SEGMENT_CONTAINER + ViewPath.REDIRECT;
    }

    public String editSegmentContainer(SegmentContainer sc){

        String path = EDIT_SEGMENT_CONTAINER + REDIRECT+"projectId="+projectId+"&bmcId="+
                bmcId+"&segmentContainerId="+sc.getSegmentContainerId();
        System.out.println(path);
        return path;
    }

    public String saveSegmentContainer(){
//        new SegmentContainerFacade().update(segmentContainerSession.getSegmentContainer());
        System.out.println("segment size: "+segmentContainer.getSegmentList().size());
        for(Segment segment : segmentContainer.getSegmentList()){
            System.out.println("Saving: "+segment.getSegmentTitle());
            new SegmentFacade().update(segment);
        }
        return BMC_OVERVIEW + REDIRECT+"projectId="+projectId+"&bmcId="+bmcId;
    }

    public String createSegmentContainer(){
        new SegmentContainerFacade().create(segmentContainer);
        for(Segment segment : segmentContainer.getSegmentList()){
            new SegmentFacade().create(segment);
        }
        segmentContainer = new SegmentContainer();

        return ViewPath.BMC_OVERVIEW + ViewPath.REDIRECT;
    }

    private void initSegmentContainer(){
        segmentContainer = new SegmentContainer();
        segmentContainer.setBmc(new ProjectFacade().findById(projectId).getBmc());
        segmentContainer.setSegmentList(new ArrayList<Segment>());
        List<SegmentType> segmentTypeList = new SegmentTypeFacade().findAllOrdered();
        for(SegmentType st : segmentTypeList){
            Segment segment = new Segment();
            segment.setSegmentType(st);
            segment.setSegmentContainer(segmentContainer);
            segmentContainer.getSegmentList().add(segment);
        }
    }

    public List<Segment> getSegmentList(int type){
        /**
         * 1 - Customer Segment
         * 2 - Value Prop
         * 3 - Distribution Channel
         * 4 - Customer Relationship
         * 5 - Revenue Stream
         * 6 - Key Resources
         * 7 - Key Activities
         * 8 - Key Partners
         * 9 - Cost Structure
         */
        List<Segment> list = new ArrayList<Segment>();
        for(SegmentContainer sc : segmentContainerList){
            for(Segment s : sc.getSegmentList()){
                if(s.getSegmentType().getSegmentTypeId() == type){
                    list.add(s);
                }
            }
        }
        return list;
    }
    public List<Segment> getSegments(int type){
        /**
         * 1 - Customer Segment
         * 2 - Value Prop
         * 3 - Distribution Channel
         * 4 - Customer Relationship
         * 5 - Revenue Stream
         * 6 - Key Resources
         * 7 - Key Activities
         * 8 - Key Partners
         * 9 - Cost Structure
         */
        List<Segment> list = new ArrayList<Segment>();
        for(Segment s : segmentContainer.getSegmentList()){
            if(s.getSegmentType().getSegmentTypeId() == type){
                list.add(s);
            }
        }
        return list;
    }
    public List<Segment> getSegmentsFrom(SegmentContainer sc,int type){
        /**
         * 1 - Customer Segment
         * 2 - Value Prop
         * 3 - Distribution Channel
         * 4 - Customer Relationship
         * 5 - Revenue Stream
         * 6 - Key Resources
         * 7 - Key Activities
         * 8 - Key Partners
         * 9 - Cost Structure
         */
        List<Segment> list = new ArrayList<Segment>();
        for(Segment s : sc.getSegmentList()){
            if(s.getSegmentType().getSegmentTypeId() == type){
                list.add(s);
            }
        }
        return list;
    }

    public Segment getCS(){
        return new Segment();
    }

}