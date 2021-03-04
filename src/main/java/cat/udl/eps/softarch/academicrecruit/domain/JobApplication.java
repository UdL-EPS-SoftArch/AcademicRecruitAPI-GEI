package cat.udl.eps.softarch.academicrecruit.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "JobApplication") //Avoid collision with system table User in Postgres
@Data
public class JobApplication {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public List<String> getEvaluationCriteria() {
        return evaluationCriteria;
    }

    public void setEvaluationCriteria(List<String> evaluationCriteria) {
        this.evaluationCriteria = evaluationCriteria;
    }

    private String id;
    private String name;
    private String description;
    @ElementCollection
    private List<String> requirements;
    @ElementCollection
    private List<String> evaluationCriteria;
    //private Phase currentPhase;
    //private List<Phase> pases;
    //private List<Applicants> applicants;
    //private List<Committee> committees;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

}
