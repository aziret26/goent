package kg.goent.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by azire on 3/19/2017.
 */
@Entity

public class SegmentContainer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int segmentContainerId;

    @OneToMany(mappedBy = "segmentId")
    private List<Segment> segmentList;

    @ManyToOne
    @JoinColumn(name = "bmcId")
    private Bmc bmc;

    public SegmentContainer() {}

    public int getSegmentContainerId() {
        return segmentContainerId;
    }

    public void setSegmentContainerId(int segmentContainerId) {
        this.segmentContainerId = segmentContainerId;
    }


    public List<Segment> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<Segment> segmentList) {
        this.segmentList = segmentList;
    }

    public Bmc getBmc() {
        return bmc;
    }

    public void setBmc(Bmc bmc) {
        this.bmc = bmc;
    }
}
