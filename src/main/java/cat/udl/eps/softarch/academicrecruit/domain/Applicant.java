package cat.udl.eps.softarch.academicrecruit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Applicant extends UriEntity<Long> {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    @Column(unique = true)
    @Length(min = 9, max = 9)
    private String dni;

}
