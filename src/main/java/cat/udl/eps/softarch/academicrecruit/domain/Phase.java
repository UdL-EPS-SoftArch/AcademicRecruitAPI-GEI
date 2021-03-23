package cat.udl.eps.softarch.academicrecruit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Phase extends UriEntity<Long>{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private Integer phaseNumber;
    private String name;
    private Date initialDate;
    private Date finalDate;


    @ManyToOne
    @JoinColumn(name = "jobApplication_id")
    private JobApplication jobApplication;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "phase_document")
    private List<Document> documentList;


}
