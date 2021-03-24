package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.CommitteeMember;
import cat.udl.eps.softarch.academicrecruit.domain.User;
import cat.udl.eps.softarch.academicrecruit.repository.AdminRepository;
import cat.udl.eps.softarch.academicrecruit.repository.CommitteeMemberRepository;
import cat.udl.eps.softarch.academicrecruit.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CommitteeMemberStepDefs {
    final StepDefs stepDefs;
    final CommitteeMemberRepository committeeMemberRepository;
    final UserRepository userRepository;

    CommitteeMemberStepDefs(StepDefs stepDefs, CommitteeMemberRepository committeeMemberRepository, UserRepository userRepository, AdminRepository adminRepository) {
        this.stepDefs = stepDefs;
        this.committeeMemberRepository = committeeMemberRepository;
        this.userRepository = userRepository;
    }

    @When("I assign a rank {string} to a user")
    public void iAssignARankToAUser(String rank) throws Exception {
        CommitteeMember committeeMember = new CommitteeMember();
        committeeMember.setRank(rank);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/committeeMembers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(stepDefs.mapper.writeValueAsString(committeeMember)).toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("It has been assigned the rank {string} to a user")
    public void itHasBeenAssignedTheRankToAUser(String rank) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/committeeMembers/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.rank", is(rank)));
    }

    @When("I assign a rank {string} to a user with username {string}")
    public void iAssignARankToAUserWithUsername(String rank, String username) throws Exception {
        CommitteeMember committeeMember = new CommitteeMember();
        committeeMember.setRank(rank);
        User user = userRepository.findByUsernameContaining(username).get(0);
        System.out.println(user);
        committeeMember.setUser(user);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/committeeMembers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(stepDefs.mapper.writeValueAsString(committeeMember)).toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("It has been assigned the rank {string} to a user with username {string}")
    public void itHasBeenAssignedTheRankToAUserWithUsername(String rank, String username) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/committeeMembers/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.rank", is(rank)));
    }
}
