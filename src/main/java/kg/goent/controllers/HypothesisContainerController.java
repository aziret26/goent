package kg.goent.controllers;

import com.sun.org.apache.regexp.internal.RE;
import kg.goent.facade.hypothesis.HypothesisContainerFacade;
import kg.goent.facade.hypothesis.HypothesisFacade;
import kg.goent.models.bmc.Segment;
import kg.goent.models.bmc.SegmentContainer;
import kg.goent.models.hypothesis.Hypothesis;
import kg.goent.models.hypothesis.HypothesisContainer;
import kg.goent.tools.Tools;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static kg.goent.tools.ViewPath.*;

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

    @ManagedProperty(value = "#{messageViewController}")
    private MessageViewController messagesViewController;

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

    public String initializeHypothesisContainer() {
        /**
         *
         */
        if (projectSession.getProject().getBmc().getBmcStatus().getBmcStatusId() == 2){
            messagesViewController.addErrorMessage(Tools.getFieldMsg("bmcIsNotFinished"));
            return PROJECT_OVERVIEW + REDIRECT;
        }
        if(projectSession.getProject().getHypothesisContainer() != null &&
                projectSession.getProject().getHypothesisContainer().getHypothesisList().size() !=0 ){
            hypothesisContainer = projectSession.getProject().getHypothesisContainer();
        }else {
            hypothesisContainer = new HypothesisContainerFacade().findByProject(projectSession.getProject());
            if (hypothesisContainer == null || hypothesisContainer.getHypothesisList().size() == 0) {
                createHypothesisList();
            }
        }
        return HYPOTHESIS_OVERVIEW+REDIRECT;
    }


    public String viewHypothesisOverView(){
        /**
         * initializes hypothesis lists
         */

        if(hypothesisContainer == null ||
                hypothesisContainer.getHypothesisList().size() == 0){
            initializeHypothesisContainer();
        }

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

    public String createHypothesisList(){
        HypothesisContainer hc = new HypothesisContainer();
        for(SegmentContainer sc : projectSession.getProject().getBmc().getSegmentContainerList()){
            for(Segment s : sc.getSegmentList()){
                if(s.getSegmentType().getSegmentTypeId() <= 4){
                    Hypothesis hypothesis = new Hypothesis();
                    hypothesis.setSegment(s);
                    hypothesis.setStatus(1);
                    new HypothesisFacade().create(hypothesis);
                    hc.getHypothesisList().add(hypothesis);
                }
            }
        }
        new HypothesisContainerFacade().create(hc);
        projectSession.getProject().setHypothesisContainer(hc);
        return HYPOTHESIS_OVERVIEW+ REDIRECT;
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
