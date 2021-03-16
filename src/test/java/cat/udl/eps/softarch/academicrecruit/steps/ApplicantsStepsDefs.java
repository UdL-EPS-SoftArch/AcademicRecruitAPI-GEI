package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.Applicant;
import cat.udl.eps.softarch.academicrecruit.repository.AdminRepository;
import cat.udl.eps.softarch.academicrecruit.repository.ApplicantRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ApplicantsStepsDefs {
    final StepDefs stepDefs;
    final ApplicantRepository applicantRepository;

    ApplicantsStepsDefs(StepDefs stepDefs, ApplicantRepository applicantRepository, AdminRepository adminRepository) {
        this.stepDefs = stepDefs;
        this.applicantRepository = applicantRepository;
    }

    @When("I register a new applicant with email {string} and name {string} and lastname {string} and dni {string}")
    public void iRegisterANewApplicantWithEmailNameLastnameDni(String email, String name, String lastname, String dni) throws Exception {
        Applicant applicant = new Applicant();
        applicant.setEmail(email);
        applicant.setName(name);
        applicant.setLastname(lastname);
        applicant.setDni(dni);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/applicants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(stepDefs.mapper.writeValueAsString(applicant)).toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("It has been created a new applicant with email {string}, name {string}, lastname {string} and dni {string}")
    public void itHasBeenCreatedANewApplicantWithEmailNameLastnameAndDni(String email, String name, String lastname, String dni) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/applicants/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.lastname", is(lastname)))
                .andExpect(jsonPath("$.dni", is(dni)));
    }
}
