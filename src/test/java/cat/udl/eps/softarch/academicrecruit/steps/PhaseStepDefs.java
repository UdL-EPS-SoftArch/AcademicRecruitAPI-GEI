package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.JobApplication;
import cat.udl.eps.softarch.academicrecruit.domain.Phase;
import cat.udl.eps.softarch.academicrecruit.repository.PhaseRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.MediaType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PhaseStepDefs {
    final StepDefs stepDefs;
    final PhaseRepository phaseRepository;

    PhaseStepDefs(StepDefs stepDefs, PhaseRepository phaseRepository) {
        this.stepDefs = stepDefs;
        this.phaseRepository = phaseRepository;
    }


    @When("I create a new phase with name {string}, initialDate {string} and finishDate {string}")
    public void iCreateANewPhaseWithNameInitialDateAndFinishDate(String name, String initialDate, String finalDate)
            throws Exception {
        Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(initialDate);
        Date finDate = new SimpleDateFormat("dd/MM/yyyy").parse(finalDate);


        Phase phase = new Phase();
        phase.setPhaseNumber(1);
        phase.setName(name);
        phase.setInitialDate(initDate);
        phase.setFinalDate(finDate);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/phases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(stepDefs.mapper.writeValueAsString(phase)).toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("It has been created a new phase with id {long} number {int} name {string}, initialDate {string} and finishDate {string}")
    public void itHasBeenCreatedANewPhaseWithNumberNameInitialDateAndFinishDate(Long id, int number, String name, String initialDate, String finalDate) throws  Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/phases/{phases}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.phaseNumber", is(number)))
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.initialDate").exists())
                .andExpect(jsonPath("$.finalDate").exists())
        ;
    }

    @And("I can check the phase")
    public void iCanCheckThePhase() {
    }

    @And("It phase with name {string} has not been created.")
    public void itPhaseWithNameHasNotBeenCreated(String arg0) {
        List<Phase> phaseList = phaseRepository.findByNameContaining(arg0);
        Assert.assertTrue(phaseList.size() == 0);
    }
}
