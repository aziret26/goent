
package kg.goent.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by iskyan on 04.02.2017.
 */

@Entity
@Table(name = "Segment")
public class Segment implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int segmentId;
	@Transient
	private String c_title;
    @Column
	private String s_title;

	@Transient
	private String c_hash;
    @Column
	private String s_hash;

    @ManyToOne
	@JoinColumn(name="segmentTypeId")
    private SegmentType type;

	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentTd) {
		this.segmentId = segmentTd;
	}

	public String getC_title() {
		return c_title;
	}

	public void setC_title(String c_title) {
		this.c_title = c_title;
	}

	public String getS_title() {
		return s_title;
	}

	public void setS_title(String s_title) {
		this.s_title = s_title;
	}

	public String getC_hash() {
		return c_hash;
	}

	public void setC_hash(String c_hash) {
		this.c_hash = c_hash;
	}

	public String getS_hash() {
		return s_hash;
	}

	public void setS_hash(String s_hash) {
		this.s_hash = s_hash;
	}

	public SegmentType getType() {
		return type;
	}

	public void setType(SegmentType type) {
		this.type = type;
	}
}

