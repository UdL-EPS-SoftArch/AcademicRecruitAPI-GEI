package cat.udl.eps.softarch.academicrecruit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Committee extends UriEntity<Long> {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public Long id;

    public Rank rank;

    //private Qualification qualification;


    public void setRank(String role){
        switch (role){
            case "SECRETARY":
                rank = Rank.SECRETARY;
                break;
            case "PRESIDENT":
                rank = Rank.PRESIDENT;
                break;
            default:
                rank = Rank.VOCAL;
                break;
        }
    }
}
