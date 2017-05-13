package kg.goent.models.hypothesis;


import kg.goent.models.project.Project;
import kg.goent.models.bmc.Segment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/13/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "HypothesisContainer.findAll",
                query = "SELECT hc FROM HypothesisContainer hc"),
        @NamedQuery(name = "HypothesisContainer.findByProject",
                query = "SELECT hc FROM HypothesisContainer hc WHERE hc.project = :project")
})
public class HypothesisContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hypothesisContainerId;

    @OneToMany(mappedBy = "hypothesisContainer",fetch = FetchType.EAGER)
    private List<Hypothesis> hypothesisList = new ArrayList<Hypothesis>();

    @OneToOne
    @JoinColumn(name = "projectId")
    private Project project;

    public int getHypothesisContainerId() {
        return hypothesisContainerId;
    }

    public void setHypothesisContainerId(int hypothesisContainerId) {
        this.hypothesisContainerId = hypothesisContainerId;
    }

    public List<Hypothesis> getHypothesisList() {
        return hypothesisList;
    }

    public void setHypothesisList(List<Hypothesis> hypothesisList) {
        this.hypothesisList = hypothesisList;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


}
