package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.Admin;
import cat.udl.eps.softarch.academicrecruit.domain.User;
import cat.udl.eps.softarch.academicrecruit.repository.AdminRepository;
import cat.udl.eps.softarch.academicrecruit.repository.JobApplicationRepository;
import cat.udl.eps.softarch.academicrecruit.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JobApplicationSteps {
    final StepDefs stepDefs;
    final JobApplicationRepository jobApplication;
    final AdminRepository adminRepository;

    JobApplicationSteps(StepDefs stepDefs, JobApplicationRepository jobApplication, AdminRepository adminRepository) {
        this.stepDefs = stepDefs;
        this.jobApplication = jobApplication;
        this.adminRepository = adminRepository;
    }

    @Given("There is a registered secretary with username {string} and password {string} and email {string}")
    public void thereIsARegisteredSecretaryWithUsernameAndPasswordAndEmail(String username, String password, String mail) {

    }

    @When("I register a new job application name {string}, requirements {string} and description {string}")
    public void iRegisterANewJobApplicationNameRequirementsAndDescription(String arg0, String arg1, String arg2) {
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
