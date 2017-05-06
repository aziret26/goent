package kg.goent.controllers;

import kg.goent.facade.BmcFacade;
import kg.goent.facade.ProjectFacade;
import kg.goent.facade.SegmentContainerFacade;
import kg.goent.models.Bmc;
import kg.goent.models.SegmentContainer;
import kg.goent.tools.ViewPath;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

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
            new BmcFacade().create(bmc);
            projectSession.setProject(new ProjectFacade().findById(projectSession.getProject().getProjectId()));
            projectSession.getProject().setBmc(bmc);
        }
        projectSession.getProject().getBmc().setSegmentContainerList(new SegmentContainerFacade().findByBmc(projectSession.getProject().getBmc()));

        return ViewPath.BMC_OVERVIEW + ViewPath.REDIRECT;
    }
    private List<SegmentContainer> loadSegmentContainer(Bmc bmc){
    //    List<SegmentContainer> segmentContainerList = new ArrayList<SegmentContainer>();
        return new SegmentContainerFacade().findByBmc(bmc);
    }
}
