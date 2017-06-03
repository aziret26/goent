package kg.goent.controllers;

import kg.goent.models.bmc.SegmentContainer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by azire on 5/5/2017.
 */
@ManagedBean
@SessionScoped
public class SegmentContainerSession extends GetReqBean {
    private SegmentContainer segmentContainer;

    public SegmentContainer getSegmentContainer() {
        return segmentContainer;
    }

    public void setSegmentContainer(SegmentContainer segmentContainer) {
        this.segmentContainer = segmentContainer;
    }
}
