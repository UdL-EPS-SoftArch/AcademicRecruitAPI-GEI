package cat.udl.eps.softarch.academicrecruit.domain;

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
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;


}
