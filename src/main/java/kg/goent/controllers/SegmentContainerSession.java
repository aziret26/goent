package kg.goent.controllers;

import kg.goent.models.SegmentContainer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by azire on 5/5/2017.
 */
@ManagedBean
@SessionScoped
public class SegmentContainerSession {
    private SegmentContainer segmentContainer;

    public SegmentContainer getSegmentContainer() {
        return segmentContainer;
    }

    public void setSegmentContainer(SegmentContainer segmentContainer) {
        this.segmentContainer = segmentContainer;
    }
}
