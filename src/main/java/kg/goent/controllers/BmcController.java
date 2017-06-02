package kg.goent.controllers;

import kg.goent.facade.bmc.BmcFacade;
import kg.goent.facade.bmc.BmcStatusFacade;
import kg.goent.facade.project.ProjectFacade;
import kg.goent.facade.bmc.SegmentContainerFacade;
import kg.goent.models.bmc.Bmc;
import kg.goent.models.bmc.SegmentContainer;
import kg.goent.models.project.Project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

import static kg.goent.tools.ViewPath.*;

/**
 * Created by B-207 on 5/6/2017.
 */
@ManagedBean
@ViewScoped
public class BmcController extends GenericController{

    private Bmc bmc;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;



    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public Bmc getBmc() {
        if(bmcId > 0){
            bmc = new BmcFacade().findById(bmcId);
        }
        return bmc;
    }

    public void setBmc(Bmc bmc) {
        this.bmc = bmc;
    }

    public int getBmcId() {
        return bmcId;
    }

    public void setBmcId(int bmcId) {
        if(bmcId != 0){
            bmc = new BmcFacade().findById(bmcId);
        }
        this.bmcId = bmcId;
    }

    public String bmcOverview(Project project){
        /**
         * show PROJECT's bmc and if PROJECT doesn't have bmc
         * it will create one, then redirects to the bmc overview page
         */
        bmc = new BmcFacade().findByProject(project);
        if (bmc == null) {
            bmc = new Bmc();
            bmc.setProject(project);
            bmc.setBmcStatus(new BmcStatusFacade().findById(2));
            new BmcFacade().create(bmc);
        }
        System.out.println(BMC_OVERVIEW + REDIRECT+"bmcId="+bmc.getBmcId());
        return BMC_OVERVIEW + REDIRECT+"projectId"+bmc.getProject().getProjectId()+"&bmcId="+bmc.getBmcId();
    }
    private List<SegmentContainer> loadSegmentContainer(Bmc bmc){
        return new SegmentContainerFacade().findByBmc(bmc);
    }

    public String finishBmc(){
        new BmcFacade().update(bmc);
        return PROJECT_OVERVIEW+REDIRECT;
    }
}
