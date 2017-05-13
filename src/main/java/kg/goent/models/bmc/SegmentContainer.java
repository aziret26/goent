package kg.goent.models.bmc;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/2/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "SegmentContainer.findAll",
                query = "SELECT sc FROM SegmentContainer sc"),
        @NamedQuery(name = "SegmentContainer.findByBmc",
                query = "SELECT sc FROM SegmentContainer sc WHERE sc.bmc = :bmc")
})
public class SegmentContainer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int segmentContainerId;

    @OneToMany(mappedBy = "segmentContainer",fetch = FetchType.EAGER)
    private List<Segment> segmentList = new ArrayList<Segment>();

    @ManyToOne
    @JoinColumn(name = "bmcId")
    private Bmc bmc;

    public Segment getValueProposition(){
        for (Segment segment: segmentList){
            if(segment.getSegmentType().getSegmentTypeId()==1)
                return segment;
        }
        return null;
    }

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
