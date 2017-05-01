package kg.goent.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by azire on 5/2/2017.
 */
@Entity
@Table(name = "Segment")
@NamedQueries(
        @NamedQuery(name = "Segment.findAll",
                query = "SELECT s FROM Segment s")
)
public class Segment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int segmentId;
    @Column
    private String segmentTitle;
    @Column
    private int segmentHash;

    @ManyToOne
    @JoinColumn(name = "segmentTypeId")
    private SegmentType segmentType;

    @ManyToOne
    @JoinColumn(name = "segmentContainerId")
    private SegmentContainer segmentContainer;

    @Column
    private int segmentGroup;
}