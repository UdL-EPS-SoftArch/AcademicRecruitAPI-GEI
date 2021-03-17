package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.Qualification;
import cat.udl.eps.softarch.academicrecruit.repository.QualificationRepository;
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

public class QualificationStepsDefs {
    final StepDefs stepDefs;
    final QualificationRepository qualificationRepository;

    QualificationStepsDefs(StepDefs stepDefs, QualificationRepository qualificationRepository) {
        this.stepDefs = stepDefs;
        this.qualificationRepository = qualificationRepository;
    }

    @When("I set a new qualification mark {float} and an observation {string}")
    public void i_set_a_new_qualification_mark_and_an_observation(Float mark, String observation) throws Exception {
        Qualification qualification = new Qualification();
        qualification.setMark(mark);
        qualification.setObservation(observation);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/qualifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(stepDefs.mapper.writeValueAsString(qualification)).toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}