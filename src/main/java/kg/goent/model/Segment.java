
package kg.goent.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by iskyan on 04.02.2017.
 */

@Entity
@Table(name = "Segment")
@NamedQueries(
		@NamedQuery(name = "findAll",
		query = "SELECT s FROM Segment s")
)
public class Segment implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int segmentId;
	@Transient
	private String cachedTitle;
    @Column
	private String title;

	@Transient
	private int cachedHash;
    @Column
	private int hash;

    @ManyToOne
	@JoinColumn(name="segmentTypeId")
    private SegmentType type;

    @ManyToOne
	@JoinColumn(name = "segmentContainerId")
	private SegmentContainer segmentContainer;

    @Column
	private int segmentGroup;

	public Segment() {}

	public Segment(SegmentType type) {
		this.type = type;
	}

	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public String getCachedTitle() {
		return cachedTitle;
	}

	public void setCachedTitle(String cachedTitle) {
		this.cachedTitle = cachedTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCachedHash() {
		return cachedHash;
	}

	public void setCachedHash(int cachedHash) {
		this.cachedHash = cachedHash;
	}

	public int getHash() {
		return hash;
	}

	public void setHash(int hash) {
		this.hash = hash;
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

	public SegmentType getType() {
		return type;
	}

	public void setType(SegmentType type) {
		this.type = type;
	}
}

