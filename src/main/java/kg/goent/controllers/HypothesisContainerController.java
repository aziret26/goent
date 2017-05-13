package kg.goent.controllers;

import kg.goent.facade.hypothesis.HypothesisContainerFacade;
import kg.goent.facade.hypothesis.HypothesisFacade;
import kg.goent.models.hypothesis.Hypothesis;
import kg.goent.models.hypothesis.HypothesisContainer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/13/2017.
 */
@ManagedBean
@SessionScoped
public class HypothesisContainerController {

    private HypothesisContainer hypothesisContainer;

    private List<Hypothesis> csHypList,vpHypList,dcHypList,crHypList;

    @ManagedProperty(value = "#{projectSession}")
    private ProjectSession projectSession;

    public HypothesisContainer getHypothesisContainer() {
        return hypothesisContainer;
    }

    public void setHypothesisContainer(HypothesisContainer hypothesisContainer) {
        this.hypothesisContainer = hypothesisContainer;
    }

    public List<Hypothesis> getCsHypList() {
        List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 1){
                list.add(h);
            }
        }
        return list;
    }

    public void setCsHypList(List<Hypothesis> csHypList) {
        this.csHypList = csHypList;
    }

    public List<Hypothesis> getVpHypList() {
        List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 2){
                list.add(h);
            }
        }
        return list;    }

    public void setVpHypList(List<Hypothesis> vpHypList) {
        this.vpHypList = vpHypList;
    }

    public List<Hypothesis> getDcHypList() {List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 3){
                list.add(h);
            }
        }
        return list;
    }

    public void setDcHypList(List<Hypothesis> dcHypList) {
        this.dcHypList = dcHypList;
    }

    public List<Hypothesis> getCrHypList() {List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 4){
                list.add(h);
            }
        }
        return list;
    }

    public void setCrHypList(List<Hypothesis> ctHypList) {
        this.crHypList = ctHypList;
    }

    public String viewHypothesisOverView(){
/*        if(hypothesisContainer == null){
            hypothesisContainer = new HypothesisContainerFacade().findByProject(projectSession.getProject());
        }
        if(hypothesisContainer.getHypothesisList().size() == 0){
            hypothesisContainer.setHypothesisList(new HypothesisFacade().findByPro);
        }
*/
        if(csHypList == null || csHypList.size() == 0){
            for (Hypothesis h : hypothesisContainer.getHypothesisList()){
                if(h.getSegment().getSegmentType().getSegmentTypeId() == 1)
                    csHypList.add(h);
            }
        }
        if(vpHypList == null || vpHypList.size() == 0){
            for (Hypothesis h : hypothesisContainer.getHypothesisList()){
                if(h.getSegment().getSegmentType().getSegmentTypeId() == 2)
                    vpHypList.add(h);
            }
        }
        if(dcHypList == null || dcHypList.size() == 0){
            for (Hypothesis h : hypothesisContainer.getHypothesisList()){
                if(h.getSegment().getSegmentType().getSegmentTypeId() == 3)
                    dcHypList.add(h);
            }
        }
        if(crHypList == null || crHypList.size() == 0){
            for (Hypothesis h : hypothesisContainer.getHypothesisList()){
                if(h.getSegment().getSegmentType().getSegmentTypeId() == 4)
                    crHypList.add(h);
            }
        }
        return "";
    }

/*
    public List<Hypothesis> getCSHypothesis(){
        List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 1){
                list.add(h);
            }
        }
        return list;
    }
    public List<Hypothesis> getVPHypothesis(){
        List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 2){
                list.add(h);
            }
        }
        return list;
    }
    public List<Hypothesis> getDCHypothesis(){
        List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 3){
                list.add(h);
            }
        }
        return list;
    }
    public List<Hypothesis> getCRHypothesis(){
        List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 4){
                list.add(h);
            }
        }
        return list;
    }
    */
}
