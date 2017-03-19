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
    @OneToOne(mappedBy = "segmentId")
    private Segment customerSegment;
    @OneToOne(mappedBy = "segmentId")
    private Segment valueProposition;
    @OneToOne(mappedBy = "segmentId")
    private Segment distributionChannel;
    @OneToOne(mappedBy = "segmentId")
    private Segment customerRelationship;
    @OneToOne(mappedBy = "segmentId")
    private Segment revenueStreams;
    @OneToOne(mappedBy = "segmentId")
    private Segment keyResources;
    @OneToOne(mappedBy = "segmentId")
    private Segment keyActivities;
    @OneToOne(mappedBy = "segmentId")
    private Segment keyPartners;
    @OneToOne(mappedBy = "segmentId")
    private Segment costStructure;

    public SegmentContainer() {}

    public SegmentContainer(List<SegmentType> segmentTypeList) {
        customerSegment         = new Segment(segmentTypeList.get(0));
        valueProposition        = new Segment(segmentTypeList.get(1));
        distributionChannel     = new Segment(segmentTypeList.get(2));
        customerRelationship    = new Segment(segmentTypeList.get(3));
        revenueStreams          = new Segment(segmentTypeList.get(4));
        keyResources            = new Segment(segmentTypeList.get(5));
        keyActivities           = new Segment(segmentTypeList.get(6));
        keyPartners             = new Segment(segmentTypeList.get(7));
        costStructure           = new Segment(segmentTypeList.get(8));
    }

    public int getSegmentContainerId() {
        return segmentContainerId;
    }

    public void setSegmentContainerId(int segmentContainerId) {
        this.segmentContainerId = segmentContainerId;
    }

    public Segment getCustomerSegment() {
        return customerSegment;
    }

    public void setCustomerSegment(Segment customerSegment) {
        this.customerSegment = customerSegment;
    }

    public Segment getValueProposition() {
        return valueProposition;
    }

    public void setValueProposition(Segment valueProposition) {
        this.valueProposition = valueProposition;
    }

    public Segment getDistributionChannel() {
        return distributionChannel;
    }

    public void setDistributionChannel(Segment distributionChannel) {
        this.distributionChannel = distributionChannel;
    }

    public Segment getCustomerRelationship() {
        return customerRelationship;
    }

    public void setCustomerRelationship(Segment customerRelationship) {
        this.customerRelationship = customerRelationship;
    }

    public Segment getRevenueStreams() {
        return revenueStreams;
    }

    public void setRevenueStreams(Segment revenueStreams) {
        this.revenueStreams = revenueStreams;
    }

    public Segment getKeyResources() {
        return keyResources;
    }

    public void setKeyResources(Segment keyResources) {
        this.keyResources = keyResources;
    }

    public Segment getKeyActivities() {
        return keyActivities;
    }

    public void setKeyActivities(Segment keyActivities) {
        this.keyActivities = keyActivities;
    }

    public Segment getKeyPartners() {
        return keyPartners;
    }

    public void setKeyPartners(Segment keyPartners) {
        this.keyPartners = keyPartners;
    }

    public Segment getCostStructure() {
        return costStructure;
    }

    public void setCostStructure(Segment costStructure) {
        this.costStructure = costStructure;
    }

}
