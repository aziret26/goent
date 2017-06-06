package kg.goent.controllers;

import kg.goent.facade.bmc.SegmentFacade;
import kg.goent.facade.bmc.SegmentTypeFacade;
import kg.goent.models.bmc.Segment;
import kg.goent.models.hypothesis.Hypothesis;
import kg.goent.models.hypothesis.HypothesisContainer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by azire on 5/12/2017.
 */
@ManagedBean
@SessionScoped
public class HypothesisController {
    private List<Segment> csList,vpList,dcList,crList;

    private Hypothesis hypothesis;
    private HypothesisContainer hypothesisContainer;

    @PostConstruct
    public void init(){
        csList = new SegmentFacade().findBySegmentType(new SegmentTypeFacade().findById(1));
        vpList = new SegmentFacade().findBySegmentType(new SegmentTypeFacade().findById(2));
        dcList = new SegmentFacade().findBySegmentType(new SegmentTypeFacade().findById(3));
        crList = new SegmentFacade().findBySegmentType(new SegmentTypeFacade().findById(4));
    }


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
}
