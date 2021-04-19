package cat.udl.eps.softarch.academicrecruit.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Document extends UriEntity<Long> {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String path;
    private String name;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Applicant applicant;

    @ManyToMany
    @JsonIdentityReference(alwaysAsId = true)
    private List<Phase> phases = new ArrayList<>();

    public void addPhase(Phase phase) {
        this.phases.add(phase);
    }
}
