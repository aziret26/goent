package kg.goent.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 3/19/2017.
 */
@Entity
@Table(name = "bmc")
public class Bmc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bmcId;

    @OneToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @OneToMany(mappedBy = "segmentContainerId")
    private List<SegmentContainer> segmentContainerList = new ArrayList<SegmentContainer>();

    public int getBmcId() {
        return bmcId;
    }

    public void setBmcId(int bmcId) {
        this.bmcId = bmcId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<SegmentContainer> getSegmentContainerList() {
        return segmentContainerList;
    }

    public void setSegmentContainerList(List<SegmentContainer> segmentContainerList) {
        this.segmentContainerList = segmentContainerList;
    }
}
