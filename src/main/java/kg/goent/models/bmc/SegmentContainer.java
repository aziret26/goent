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

    @Transient
    private List<Segment> vpList,dsList,crList,rsList,krList,kaList,kpList,cStructList;
    @Transient
    private Segment customerSegment;

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

    public Segment getCustomerSegment() {
        for (Segment segment: segmentList){
            if(segment.getSegmentType().getSegmentTypeId()==1) {
                customerSegment = segment;
            }
        }
        return customerSegment;
    }

    public void setCustomerSegment(Segment customerSegment) {
        this.customerSegment = customerSegment;
    }

    public List<Segment> getSegments(int type){
        /**
         * 1 - Customer Segment
         * 2 - Value Prop
         * 3 - Distribution Channel
         * 4 - Customer Relationship
         * 5 - Revenue Stream
         * 6 - Key Resources
         * 7 - Key Activities
         * 8 - Key Partners
         * 9 - Cost Structure
         */
        List<Segment> list = new ArrayList<Segment>();
        for(Segment s : segmentList){
            if(s.getSegmentType().getSegmentTypeId() == type){
                list.add(s);
            }
        }
        return list;
    }

    public List<String> getSegmentsAsStrList(int type){
        /**
         * 1 - Customer Segment
         * 2 - Value Prop
         * 3 - Distribution Channel
         * 4 - Customer Relationship
         * 5 - Revenue Stream
         * 6 - Key Resources
         * 7 - Key Activities
         * 8 - Key Partners
         * 9 - Cost Structure
         */
        List<String> list = new ArrayList<String>();
        for(Segment s : segmentList){
            if(s.getSegmentType().getSegmentTypeId() == type){
                list.add(s.getSegmentTitle());
            }
        }
        System.out.println("type: "+type+" | len: "+list.size());
        return list;
    }

    public List<Segment> getVpList() {
        return vpList;
    }

    public void setVpList(List<Segment> vpList) {
        this.vpList = vpList;
    }

    public List<Segment> getDsList() {
        return dsList;
    }

    public void setDsList(List<Segment> dsList) {
        this.dsList = dsList;
    }

    public List<Segment> getCrList() {
        return crList;
    }

    public void setCrList(List<Segment> crList) {
        this.crList = crList;
    }

    public List<Segment> getRsList() {
        return rsList;
    }

    public void setRsList(List<Segment> rsList) {
        this.rsList = rsList;
    }

    public List<Segment> getKrList() {
        return krList;
    }

    public void setKrList(List<Segment> krList) {
        this.krList = krList;
    }

    public List<Segment> getKaList() {
        return kaList;
    }

    public void setKaList(List<Segment> kaList) {
        this.kaList = kaList;
    }

    public List<Segment> getKpList() {
        return kpList;
    }

    public void setKpList(List<Segment> kpList) {
        this.kpList = kpList;
    }

    public List<Segment> getcStructList() {
        return cStructList;
    }

    public void setcStructList(List<Segment> cStructList) {
        this.cStructList = cStructList;
    }

    public void initLists(){
        if(segmentList.size() == 0){
            System.out.println("size: "+segmentList.size());
            return;
        }
        vpList = new ArrayList<Segment>();
        dsList = new ArrayList<Segment>();
        crList = new ArrayList<Segment>();
        rsList = new ArrayList<Segment>();
        krList = new ArrayList<Segment>();
        kaList = new ArrayList<Segment>();
        kpList = new ArrayList<Segment>();
        cStructList = new ArrayList<Segment>();
        for(Segment segment : segmentList){
            switch (segment.getSegmentType().getSegmentTypeId()){
                case 2: vpList.add(segment);break;
                case 3: dsList.add(segment);break;
                case 4: crList.add(segment);break;
                case 5: rsList.add(segment);break;
                case 6: krList.add(segment);break;
                case 7: kaList.add(segment);break;
                case 8: kpList.add(segment);break;
                case 9: cStructList.add(segment);break;
            }
        }
    }

    public void refreshSegmentList(){
        segmentList = new ArrayList<Segment>();
        segmentList.add(customerSegment);
        for(Segment s : vpList){
            System.out.println("Saving: "+s.getSegmentTitle());
            segmentList.add(s);
        }
        for(Segment s: dsList){
            System.out.println("Saving: "+s.getSegmentTitle());
            segmentList.add(s);
        }
        for(Segment s: crList){
            System.out.println("Saving: "+s.getSegmentTitle());
            segmentList.add(s);
        }
        for(Segment s: rsList){
            System.out.println("Saving: "+s.getSegmentTitle());
            segmentList.add(s);
        }
        for(Segment s: krList){
            System.out.println("Saving: "+s.getSegmentTitle());
            segmentList.add(s);
        }
        for(Segment s: kaList){
            System.out.println("Saving: "+s.getSegmentTitle());
            segmentList.add(s);
        }
        for(Segment s: kpList){
            System.out.println("Saving: "+s.getSegmentTitle());
            segmentList.add(s);
        }
        for(Segment s: cStructList){
            System.out.println("Saving: "+s.getSegmentTitle());
            segmentList.add(s);
        }
    }
}
