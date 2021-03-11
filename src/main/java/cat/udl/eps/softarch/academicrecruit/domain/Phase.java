package cat.udl.eps.softarch.academicrecruit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Phase extends UriEntity<Long>{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer phaseNumber;
    private String name;
    private Date initialDate;
    private Date finalDate;


}
