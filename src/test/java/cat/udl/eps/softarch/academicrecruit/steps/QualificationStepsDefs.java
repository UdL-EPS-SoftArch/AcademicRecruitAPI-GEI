package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.Qualification;
import cat.udl.eps.softarch.academicrecruit.repository.ApplicantRepository;
import cat.udl.eps.softarch.academicrecruit.repository.CommitteeMemberRepository;
import cat.udl.eps.softarch.academicrecruit.repository.QualificationRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QualificationStepsDefs {
	final StepDefs stepDefs;
	final QualificationRepository qualificationRepository;
	final CommitteeMemberRepository committeeMemberRepository;
	final ApplicantRepository applicantRepository;

	QualificationStepsDefs(StepDefs stepDefs, QualificationRepository qualificationRepository, CommitteeMemberRepository committeeMemberRepository, ApplicantRepository applicantRepository) {
		this.stepDefs = stepDefs;
		this.qualificationRepository = qualificationRepository;
		this.committeeMemberRepository = committeeMemberRepository;
		this.applicantRepository = applicantRepository;
	}

	@When("I set a new qualification mark {string} and an observation {string} to an applicant with email {string}")
	public void i_set_a_new_qualification_mark_and_an_observation_to_an_applicant(String mark, String observation, String applicantMail) throws Exception {
		Qualification qualification = new Qualification();
		qualification.setMark(Float.parseFloat(mark));
		qualification.setObservation(observation);

		System.out.println(applicantMail);

		qualification.setApplicant(applicantRepository.findByEmailContaining(applicantMail).get(0));

		stepDefs.result = stepDefs.mockMvc.perform(
				post("/qualifications")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new JSONObject(stepDefs.mapper.writeValueAsString(qualification)).toString())
						.accept(MediaType.APPLICATION_JSON)
						.with(AuthenticationStepDefs.authenticate()))
				.andDo(print());
	}

	@And("I can check that the mark {string} and the observation is {string}")
	public void i_can_check_that_the_mark_and_observation_is(String mark, String observation) throws Exception {
		List<Qualification> qualificationList = qualificationRepository.findByObservationContaining(observation);

		stepDefs.result = stepDefs.mockMvc.perform(
				get("/qualifications/{id}", qualificationList.get(0).getId())
						.accept(MediaType.APPLICATION_JSON)
						.with(AuthenticationStepDefs.authenticate()))
				.andDo(print())
				.andExpect(jsonPath("$.mark", is(Double.parseDouble(mark))))
				.andExpect(jsonPath("$.observation", is(observation)));
	}

	@And("The mark with the observation {string} has not been created")
	public void the_mark_with_the_observation_has_not_been_created(String observation) throws Exception {
		List<Qualification> qualificationList = qualificationRepository.findByObservationContaining(observation);

		stepDefs.result = stepDefs.mockMvc.perform(
				get("/qualification", qualificationList)
						.accept(MediaType.APPLICATION_JSON)
						.with(AuthenticationStepDefs.authenticate()))
				.andExpect(status().isNotFound());
	}
}