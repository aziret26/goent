package kg.goent.bean;

import kg.goent.model.SegmentContainer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 3/18/2017.
 */
@ManagedBean
@SessionScoped
public class BmcBean {
    private List<SegmentContainer> segmentContainer;

    @ManagedProperty(value = "#{segmentTypeBean}")
    private SegmentTypeBean segmentTypeBean;
    @PostConstruct
    public void init(){
        segmentContainer = new ArrayList<SegmentContainer>();
    }

    public List<SegmentContainer> getSegmentContainer() {
        return segmentContainer;
    }

    public void setSegmentContainer(List<SegmentContainer> segmentContainer) {
        this.segmentContainer = segmentContainer;
    }

    public SegmentTypeBean getSegmentTypeBean() {
        return segmentTypeBean;
    }

    public void setSegmentTypeBean(SegmentTypeBean segmentTypeBean) {
        this.segmentTypeBean = segmentTypeBean;
    }
}
