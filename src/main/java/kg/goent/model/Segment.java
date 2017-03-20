
package kg.goent.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by iskyan on 04.02.2017.
 */

@Entity
@Table(name = "Segment")
@NamedQueries(
		@NamedQuery(name = "Segment.findAll",
		query = "SELECT s FROM Segment s")
)
public class Segment implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int segmentId;
	@Transient
	private String segmentCachedTitle;
    @Column
	private String segmentTitle;

	@Transient
	private int segmentCachedHash;
    @Column
	private int segmentHash;

    @ManyToOne
	@JoinColumn(name="segmentTypeId")
    private SegmentType segmentType;

    @ManyToOne
	@JoinColumn(name = "segmentContainerId")
	private SegmentContainer segmentContainer;

    @Column
	private int segmentGroup;

	public Segment() {}

	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public String getSegmentCachedTitle() {
		return segmentCachedTitle;
	}

	public void setSegmentCachedTitle(String segmentCachedTitle) {
		this.segmentCachedTitle = segmentCachedTitle;
	}

	public String getSegmentTitle() {
		return segmentTitle;
	}

	public void setSegmentTitle(String segmentTitle) {
		this.segmentTitle = segmentTitle;
	}

	public int getSegmentCachedHash() {
		return segmentCachedHash;
	}

	public void setSegmentCachedHash(int segmentCachedHash) {
		this.segmentCachedHash = segmentCachedHash;
	}

	public int getSegmentHash() {
		return segmentHash;
	}

	public void setSegmentHash(int segmentHash) {
		this.segmentHash = segmentHash;
	}

	public SegmentType getSegmentType() {
		return segmentType;
	}

	public void setSegmentType(SegmentType segmentType) {
		this.segmentType = segmentType;
	}

	public int getSegmentGroup() {
		return segmentGroup;
	}

	public void setSegmentGroup(int segmentGroup) {
		this.segmentGroup = segmentGroup;
	}

	public SegmentContainer getSegmentContainer() {
		return segmentContainer;
	}

	public void setSegmentContainer(SegmentContainer segmentContainer) {
		this.segmentContainer = segmentContainer;
	}

}

