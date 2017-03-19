package kg.goent.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aziret on 3/17/2017.
 */
@Entity
@Table(name = "segment_type")
@NamedQueries({
        @NamedQuery(name = "SegmentType.findAll",
                query = "SELECT st FROM SegmentType st"),
        @NamedQuery(name = "SegmentType.findAllOrdered",
                query = "SELECT st FROM SegmentType st ORDER BY st.segmentOrder")
})
public class SegmentType implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int segmentTypeId;

    @Column
    private String name;

    @Column
    private int segmentOrder;

    @OneToMany(mappedBy = "type")
    private List<Segment> listSegment = new ArrayList<Segment>();

    public int getSegmentTypeId() {
        return segmentTypeId;
    }

    public void setSegmentTypeId(int segmentTypeId) {
        this.segmentTypeId = segmentTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSegmentOrder() {
        return segmentOrder;
    }

    public void setSegmentOrder(int segmentOrder) {
        this.segmentOrder = segmentOrder;
    }

    public List<Segment> getListSegment() {
        return listSegment;
    }

    public void setListSegment(List<Segment> listSegment) {
        this.listSegment = listSegment;
    }
}
