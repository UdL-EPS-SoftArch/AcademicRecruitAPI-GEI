package cat.udl.eps.softarch.academicrecruit.domain;

import javax.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Qualification extends UriEntity<Long> {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;

	@Min(0)
	@Max(10)
	private Float mark;

	private String observation;

	@ManyToOne
	@JoinColumn(name = "committeeMember_id")
	@JsonIdentityReference(alwaysAsId = true)
	private CommitteeMember committeeMember;

	@ManyToOne
	@JoinColumn(name = "applicant_id")
	@JsonIdentityReference(alwaysAsId = true)
	private Applicant applicant;
}
