package cat.udl.eps.softarch.academicrecruit.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


import lombok.Data;


import lombok.Value;


@Entity
@Data
@Value

public class Qualifications extends UriEntity<Long>{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String observation;

    @NotBlank
    @Min(0)
    @Max(10)
    private Float mark;



}
