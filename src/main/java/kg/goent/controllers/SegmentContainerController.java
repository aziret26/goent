package kg.goent.controllers;

import kg.goent.facade.SegmentContainerFacade;
import kg.goent.facade.SegmentFacade;
import kg.goent.facade.SegmentTypeFacade;
import kg.goent.models.Segment;
import kg.goent.models.SegmentContainer;
import kg.goent.models.SegmentType;
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
@SessionScoped
public class SegmentContainerController {
    private SegmentContainer segmentContainer;

    @ManagedProperty(value = "#{segmentContainerSession}")
    private SegmentContainerSession segmentContainerSession;

    @ManagedProperty(value = "#{projectSession}")
    private ProjectSession projectSession;

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
}
