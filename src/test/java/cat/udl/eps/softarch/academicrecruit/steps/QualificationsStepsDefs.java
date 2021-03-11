package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.Qualifications;
import cat.udl.eps.softarch.academicrecruit.domain.User;
import cat.udl.eps.softarch.academicrecruit.repository.AdminRepository;
import cat.udl.eps.softarch.academicrecruit.repository.Qualifications;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class QualificationsStepsDefs {
    final StepDefs stepDefs;
    final QualificationsRepository qualificationsRepository;

    QualificationsStepsDefs(StepDefs stepDefs, QualificationsRepository qualificationsRepository) {
        this.stepsDefs = stepsDefs;
        this.qualificationsRepository = qualificationsRepository;
    }

    @When("I set a new qualification mark {int} and an observation {string}")
    public void iRegisterANewQualificationMarkAndObservation(int mark, String observation) throws Exception {
        Qualifications qualifications = new Qualifications();
        qualifications.setMark(mark);
        qualifications.setObservation(observation);

        stepDefs.result = stepDefs.mockMvc.perform(
            post("/qualifications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JSONObject(stepDefs.mapper.writeValueAsString(job)).toString())
                .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("I can check that the mark {int} is correct")
    public void iCanCheckThatTheMarkIsCorrect(int mark) {

    }

    @And("I can check that the observation {string} is correct")
    public void iCanCheckThatTheObservationIsCorrect(String observation) {

    }
}