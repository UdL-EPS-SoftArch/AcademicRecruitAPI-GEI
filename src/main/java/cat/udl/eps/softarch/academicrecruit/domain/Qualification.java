package cat.udl.eps.softarch.academicrecruit.domain;

import javax.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Qualification extends UriEntity<Long>{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Min(0)
    @Max(10)
    private Float mark;

    private String observation;

    @ManyToOne
    @JoinColumn(name="committeeMember_id")
    private CommitteeMember committeeMember;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
}
