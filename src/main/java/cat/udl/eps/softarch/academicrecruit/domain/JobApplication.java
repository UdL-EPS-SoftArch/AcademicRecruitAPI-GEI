package cat.udl.eps.softarch.academicrecruit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class JobApplication extends UriEntity<Long> {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private String description;
    @ElementCollection
    private List<String> requirements = new ArrayList<>();
    @ElementCollection
    private List<String> evaluationCriteria = new ArrayList<>();
    //private Phase currentPhase;
    //private List<Phase> pases;
    //private List<Applicants> applicants;
    //private List<Committee> committees;
}
