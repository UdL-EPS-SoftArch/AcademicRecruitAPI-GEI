package cat.udl.eps.softarch.academicrecruit.domain;

import javax.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Qualifications extends UriEntity<Long>{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @NotBlank
    @Min(0)
    @Max(10)
    private Float mark;
    private String observation;
}
