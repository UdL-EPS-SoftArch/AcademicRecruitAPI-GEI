package cat.udl.eps.softarch.academicrecruit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CommitteeMember extends UriEntity<Long> {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public Long id;

    public Rank rank;

    @ManyToOne
    @JoinColumn(name="username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "jobapplication_id")
    private JobApplication jobApplication;

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
