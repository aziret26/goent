package kg.goent.controllers;

import kg.goent.facade.bmc.BmcFacade;
import kg.goent.facade.bmc.BmcStatusFacade;
import kg.goent.facade.project.ProjectFacade;
import kg.goent.facade.bmc.SegmentContainerFacade;
import kg.goent.models.bmc.Bmc;
import kg.goent.models.bmc.SegmentContainer;

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
public class BmcController {

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    @ManagedProperty(value = "#{projectSession}")
    private ProjectSession projectSession;

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public void setProjectSession(ProjectSession projectSession) {
        this.projectSession = projectSession;
    }

    public String getBmc(){
        /**
         * show PROJECT's bmc and if PROJECT doesn't have bmc
         * it will create one, then redirects to the bmc overview page
         */
        if(projectSession.getProject().getBmc() == null){
            Bmc bmc = new Bmc();
            bmc.setProject(projectSession.getProject());
            bmc.setBmcStatus(new BmcStatusFacade().findById(2));
            new BmcFacade().create(bmc);
            projectSession.setProject(new ProjectFacade().findById(projectSession.getProject().getProjectId()));
            projectSession.getProject().setBmc(bmc);
        }
        projectSession.getProject().getBmc().setSegmentContainerList(new SegmentContainerFacade().findByBmc(projectSession.getProject().getBmc()));

        return BMC_OVERVIEW + REDIRECT;
    }
    private List<SegmentContainer> loadSegmentContainer(Bmc bmc){
    //    List<SegmentContainer> segmentContainerList = new ArrayList<SegmentContainer>();
        return new SegmentContainerFacade().findByBmc(bmc);
    }

    public String finishBmc(){
        projectSession.getProject().getBmc().setBmcStatus(new BmcStatusFacade().findById(1));
        new BmcFacade().update(projectSession.getProject().getBmc());
        return PROJECT_OVERVIEW+REDIRECT;
    }
}
