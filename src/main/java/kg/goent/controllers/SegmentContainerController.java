package kg.goent.controllers;

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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/4/2017.
 */
@ManagedBean
@ViewScoped
public class SegmentContainerController extends GenericController{
    private SegmentContainer segmentContainer;

    private List<SegmentContainer> segmentContainerList;

    @ManagedProperty(value = "#{segmentContainerSession}")
    private SegmentContainerSession segmentContainerSession;

    public void setSegmentContainerSession(SegmentContainerSession segmentContainerSession) {
        this.segmentContainerSession = segmentContainerSession;
    }

    @Override
    public void setBmcId(int bmcId) {
        if(bmcId != 0){

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

    public String addSegmentContainer(){
        initSegmentContainer();
        return ViewPath.ADD_SEGMENT_CONTAINER + ViewPath.REDIRECT;
    }

    public String editSegmentContainer(SegmentContainer sc){
        segmentContainerSession.setSegmentContainer(sc);
        return ViewPath.EDIT_SEGMENT_CONTAINER + ViewPath.REDIRECT;
    }

    public String saveSegmentContainer(){
        new SegmentContainerFacade().update(segmentContainerSession.getSegmentContainer());
        for(Segment segment : segmentContainerSession.getSegmentContainer().getSegmentList()){
            new SegmentFacade().update(segment);
        }

        return ViewPath.BMC_OVERVIEW+ViewPath.REDIRECT;
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
/*
    public List<Segment> getCsList() {
        return csList;
    }

    public void setCsList(List<Segment> csList) {
        this.csList = csList;
    }

    public List<Segment> getVpList() {
        return vpList;
    }

    public void setVpList(List<Segment> vpList) {
        this.vpList = vpList;
    }

    public List<Segment> getDcList() {
        return dcList;
    }

    public void setDcList(List<Segment> dcList) {
        this.dcList = dcList;
    }

    public List<Segment> getCrList() {
        return crList;
    }

    public void setCrList(List<Segment> crList) {
        this.crList = crList;
    }

    public List<Segment> getRsList() {
        return rsList;
    }

    public void setRsList(List<Segment> rsList) {
        this.rsList = rsList;
    }

    public List<Segment> getKrList() {
        return krList;
    }

    public void setKrList(List<Segment> krList) {
        this.krList = krList;
    }

    public List<Segment> getKaList() {
        return kaList;
    }

    public void setKaList(List<Segment> kaList) {
        this.kaList = kaList;
    }

    public List<Segment> getKpList() {
        return kpList;
    }

    public void setKpList(List<Segment> kpList) {
        this.kpList = kpList;
    }

    public List<Segment> getcStructList() {
        return cStructList;
    }

    public void setcStructList(List<Segment> cStructList) {
        this.cStructList = cStructList;
    }

    public String getContainers(){

        return "";
    }
*/

}