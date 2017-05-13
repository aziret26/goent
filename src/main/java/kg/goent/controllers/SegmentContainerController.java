package kg.goent.controllers;

import kg.goent.facade.bmc.SegmentContainerFacade;
import kg.goent.facade.bmc.SegmentFacade;
import kg.goent.facade.bmc.SegmentTypeFacade;
import kg.goent.models.bmc.Segment;
import kg.goent.models.bmc.SegmentContainer;
import kg.goent.models.bmc.SegmentType;
import kg.goent.tools.ViewPath;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/4/2017.
 */
@ManagedBean
@SessionScoped
public class SegmentContainerController {
    private SegmentContainer segmentContainer;

    @ManagedProperty(value = "#{segmentContainerSession}")
    private SegmentContainerSession segmentContainerSession;

    @ManagedProperty(value = "#{projectSession}")
    private ProjectSession projectSession;

    private List<Segment> csLsit,vpList,dcList,crList,rsList,krList,kaList,kpList,cStructList;

    public SegmentContainer getSegmentContainer() {
        return segmentContainer;
    }

    public void setSegmentContainer(SegmentContainer segmentContainer) {
        this.segmentContainer = segmentContainer;
    }

    public void setSegmentContainerSession(SegmentContainerSession segmentContainerSession) {
        this.segmentContainerSession = segmentContainerSession;
    }

    public void setProjectSession(ProjectSession projectSession) {
        this.projectSession = projectSession;
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

        projectSession.getProject().getBmc().setSegmentContainerList(
                new SegmentContainerFacade().findByBmc(projectSession.getProject().getBmc()));

        return ViewPath.BMC_OVERVIEW+ViewPath.REDIRECT;
    }

    public String createSegmentContainer(){
        new SegmentContainerFacade().create(segmentContainer);
        for(Segment segment : segmentContainer.getSegmentList()){
            new SegmentFacade().create(segment);
        }
        segmentContainer = new SegmentContainer();

        projectSession.getProject().getBmc().setSegmentContainerList(
                new SegmentContainerFacade().findByBmc(projectSession.getProject().getBmc()));

        return ViewPath.BMC_OVERVIEW + ViewPath.REDIRECT;
    }

    private void initSegmentContainer(){
        segmentContainer = new SegmentContainer();
        segmentContainer.setBmc(projectSession.getProject().getBmc());
        segmentContainer.setSegmentList(new ArrayList<Segment>());
        List<SegmentType> segmentTypeList = new SegmentTypeFacade().findAllOrdered();
        for(SegmentType st : segmentTypeList){
            Segment segment = new Segment();
            segment.setSegmentType(st);
            segment.setSegmentContainer(segmentContainer);
            segmentContainer.getSegmentList().add(segment);
        }
    }

    protected void destroySessions(){
        //System.out.printf("DESTROYING SEGMENT CONTAINER SESSION");
        segmentContainerSession = new SegmentContainerSession();
    }

/*
    public List<Segment> getCsLsit() {
        return csLsit;
    }

    public void setCsLsit(List<Segment> csLsit) {
        this.csLsit = csLsit;
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
