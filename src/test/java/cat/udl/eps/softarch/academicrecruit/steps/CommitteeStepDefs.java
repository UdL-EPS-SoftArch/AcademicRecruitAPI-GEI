package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.Committee;
import cat.udl.eps.softarch.academicrecruit.repository.AdminRepository;
import cat.udl.eps.softarch.academicrecruit.repository.ApplicantRepository;
import cat.udl.eps.softarch.academicrecruit.repository.CommitteeRepository;
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

public class CommitteeStepDefs {
    final StepDefs stepDefs;
    final CommitteeRepository committeeRepository;

    CommitteeStepDefs(StepDefs stepDefs, CommitteeRepository committeeRepository, AdminRepository adminRepository) {
        this.stepDefs = stepDefs;
        this.committeeRepository = committeeRepository;
    }

    @When("I assign a rank {string} to a user")
    public void iAssignARankToAUser(String rank) throws Exception {
        Committee committee = new Committee();
        committee.setRank(rank);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/committees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(stepDefs.mapper.writeValueAsString(committee)).toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("It has been assigned the rank {string} to a user")
    public void itHasBeenAssignedTheRankToAUser(String rank) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/committees/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.rank", is(rank)));
    }
}
