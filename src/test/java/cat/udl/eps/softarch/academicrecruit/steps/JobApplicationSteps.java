package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.Admin;
import cat.udl.eps.softarch.academicrecruit.domain.JobApplication;
import cat.udl.eps.softarch.academicrecruit.domain.User;
import cat.udl.eps.softarch.academicrecruit.repository.AdminRepository;
import cat.udl.eps.softarch.academicrecruit.repository.JobApplicationRepository;
import cat.udl.eps.softarch.academicrecruit.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JobApplicationSteps {
    final StepDefs stepDefs;
    final JobApplicationRepository jobApplication;

    JobApplicationSteps(StepDefs stepDefs, JobApplicationRepository jobApplication, AdminRepository adminRepository) {
        this.stepDefs = stepDefs;
        this.jobApplication = jobApplication;
    }

    @Given("There is a registered secretary with username {string} and password {string} and email {string}")
    public void thereIsARegisteredSecretaryWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        if (!jobApplication.existsById(username)) {
            User user = new User();
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.encodePassword();
            // jobApplication.save(user);
        }
    }

    @When("I register a new job application name {string}, requirements {string} and description {string}")
    public void iRegisterANewJobApplicationNameRequirementsAndDescription(String name, String requirements, String description) throws Exception {
        List<String> requirementsList = Arrays.asList(requirements.split(","));
        JobApplication job = new JobApplication();
        job.setName(name);
        job.setRequirements(requirementsList);
        job.setDescription(description);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/jobapplication")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(
                                stepDefs.mapper.writeValueAsString(job)
                        ).put("name", name).put("requirements", requirementsList).put("description", description).toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("It has been created a new job application with name {string}, requirements {string} and description {string}")
    public void itHasBeenCreatedANewJobApplicationWithNameRequirementsAndDescription(String arg0, String arg1, String arg2) {
    }

    @And("I can check the job application")
    public void iCanCheckTheJobApplication() {
    }

    @And("It has not been created a user with name {string}")
    public void itHasNotBeenCreatedAUserWithName(String arg0) {
    }
}
