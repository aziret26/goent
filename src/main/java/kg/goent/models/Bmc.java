package kg.goent.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/2/2017.
 */
@Entity
public class Bmc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bmcId;

    @OneToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @OneToMany(mappedBy = "bmc")
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