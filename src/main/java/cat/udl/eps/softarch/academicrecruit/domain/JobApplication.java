package cat.udl.eps.softarch.academicrecruit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class JobApplication extends UriEntity<Long> {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    //@NotBlank
    private String name;
    private String description;
    @ElementCollection
    private List<String> requirements = new ArrayList<>();
    @ElementCollection
    private List<String> evaluationCriteria = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "phase_id")
    //@NotNull
    private Phase currentPhase;
}
