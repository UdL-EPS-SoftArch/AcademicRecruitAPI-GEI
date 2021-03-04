package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.Admin;
import cat.udl.eps.softarch.academicrecruit.domain.User;
import cat.udl.eps.softarch.academicrecruit.repository.AdminRepository;
import cat.udl.eps.softarch.academicrecruit.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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

public class RegisterUserStepDefs {
  final StepDefs stepDefs;
  final UserRepository userRepository;
  final AdminRepository adminRepository;

  RegisterUserStepDefs(StepDefs stepDefs, UserRepository userRepository, AdminRepository adminRepository) {
    this.stepDefs = stepDefs;
    this.userRepository = userRepository;
    this.adminRepository = adminRepository;
  }

  @Given("^There is no registered user with username \"([^\"]*)\"$")
  public void thereIsNoRegisteredUserWithUsername(String user) {
    Assert.assertFalse("user \""
                    +  user + "\"shouldn't exist",
                    userRepository.existsById(user));
  }

  @Given("^There is a registered user with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
  public void thereIsARegisteredUserWithUsernameAndPasswordAndEmail(String username, String password, String email) {
    if (!userRepository.existsById(username)) {
      User user = new User();
      user.setEmail(email);
      user.setUsername(username);
      user.setPassword(password);
      user.encodePassword();
      userRepository.save(user);
    }
  }

  @Given("^There is a registered administrator with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
  public void thereIsARegisteredAdminWithUsernameAndPasswordAndEmail(String username, String password, String email) {
    if (!adminRepository.existsById(username)) {
      Admin admin = new Admin();
      admin.setEmail(email);
      admin.setUsername(username);
      admin.setPassword(password);
      admin.encodePassword();
      userRepository.save(admin);
    }
  }

  @When("^I register a new user with username \"([^\"]*)\", email \"([^\"]*)\" and password \"([^\"]*)\"$")
  public void iRegisterANewUserWithUsernameEmailAndPassword(String username, String email, String password) throws Throwable {
    User user = new User();
    user.setUsername(username);
    user.setEmail(email);

    stepDefs.result = stepDefs.mockMvc.perform(
            post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new JSONObject(
                            stepDefs.mapper.writeValueAsString(user)
                    ).put("password", password).toString())
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
            .andDo(print());
  }

  @And("^It has been created a user with username \"([^\"]*)\" and email \"([^\"]*)\", the password is not returned$")
  public void itHasBeenCreatedAUserWithUsername(String username, String email) throws Throwable {
    stepDefs.result = stepDefs.mockMvc.perform(
            get("/users/{username}", username)
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
            .andDo(print())
            .andExpect(jsonPath("$.email", is(email)))
            .andExpect(jsonPath("$.password").doesNotExist());
  }

  @And("^It has not been created a user with username \"([^\"]*)\"$")
  public void itHasNotBeenCreatedAUserWithUsername(String username) throws Throwable {
    stepDefs.result = stepDefs.mockMvc.perform(
            get("/users/{username}", username)
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
            .andExpect(status().isNotFound());
  }

  @Given("There is a registered administrator with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\" and name \"([^\"]*)\" and lastname \"([^\"]*)\" and dni \"([^\"]*)\"$")
  public void thereIsARegisteredAdministratorWithUsernameAndPasswordAndEmailAndNameAndLastnameAndDni(String username, String password, String email, String name, String lastname, String dni) {
    if (!adminRepository.existsById(username)) {
      Admin admin = new Admin();
      admin.setEmail(email);
      admin.setUsername(username);
      admin.setPassword(password);
      admin.encodePassword();
      admin.setName(name);
      admin.setLastname(lastname);
      admin.setDni(dni);
      userRepository.save(admin);
    }
  }

  @When("I register a new user with username \"([^\"]*)\" , email \"([^\"]*)\" , password \"([^\"]*)\" , name \"([^\"]*)\" , lastname \"([^\"]*)\" and dni \"([^\"]*)\"$")
  public void iRegisterANewUserWithUsernameEmailPasswordNameLastnameAndDni(String username, String email, String password, String name, String lastname, String dni) throws Throwable {
    User user = new User();
    user.setUsername(username);
    user.setEmail(email);
    user.setName(name);
    user.setLastname(lastname);
    user.setDni(dni);

    stepDefs.result = stepDefs.mockMvc.perform(
            post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new JSONObject(
                            stepDefs.mapper.writeValueAsString(user)
                    ).put("password", password).toString())
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
            .andDo(print());
  }

  @And("It has been created a user with username \"([^\"]*)\" , email \"([^\"]*)\" , name \"([^\"]*)\" , lastname \"([^\"]*)\" and dni \"([^\"]*)\" , the password is not returned")
  public void itHasBeenCreatedAUserWithUsernameEmailNameLastnameAndDniThePasswordIsNotReturned(String username, String email, String name, String lastname, String dni) throws Throwable {
    stepDefs.result = stepDefs.mockMvc.perform(
            get("/users/{username}", username)
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
            .andDo(print())
            .andExpect(jsonPath("$.email", is(email)))
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.lastname", is(lastname)))
            .andExpect(jsonPath("$.dni", is(dni)))
            .andExpect(jsonPath("$.password").doesNotExist());
  }

  @Given("There is a registered user with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\" and dni \"([^\"]*)\"")
  public void thereIsARegisteredUserWithUsernameAndPasswordAndEmailAndDni(String username, String password, String email, String dni) {
    if (!userRepository.existsById(username)) {
      User user = new User();
      user.setEmail(email);
      user.setUsername(username);
      user.setPassword(password);
      user.encodePassword();
      user.setDni(dni);
      userRepository.save(user);
    }
  }
}
