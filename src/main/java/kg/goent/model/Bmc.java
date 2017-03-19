package kg.goent.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by azire on 3/19/2017.
 */
@Entity
@Table(name = "bmc")
public class Bmc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bmcId;

    @OneToOne
    @JoinColumn(name = "projectId")
    private Project project;





}
