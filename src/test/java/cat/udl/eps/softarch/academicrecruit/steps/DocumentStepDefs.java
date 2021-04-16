package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.Applicant;
import cat.udl.eps.softarch.academicrecruit.domain.Document;
import cat.udl.eps.softarch.academicrecruit.domain.JobApplication;
import cat.udl.eps.softarch.academicrecruit.domain.User;
import cat.udl.eps.softarch.academicrecruit.repository.*;
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

public class DocumentStepDefs {

    final StepDefs stepDefs;
    final DocumentRepository documentRepository;
    final ApplicantRepository applicantRepository;
    final PhaseRepository phaseRepository;
    final QualificationRepository qualificationRepository;
    private String newUriResource;

    DocumentStepDefs(StepDefs stepDefs, DocumentRepository docRepo, ApplicantRepository appRepo, PhaseRepository phaseRepo, QualificationRepository qualRepo) {
        this.stepDefs = stepDefs;
        this.documentRepository = docRepo;
        this.applicantRepository = appRepo;
        this.phaseRepository = phaseRepo;
        this.qualificationRepository = qualRepo;
    }
    @When("I create a new document with name {string}, path {string} that belongs to an applicant with email {string}")
    public void iCreateANewDocumentWithNamePath(String name, String path, String applicantMail) throws Exception {
        Document doc = new Document();
        doc.setName(name);
        doc.setPath(path);

        doc.setApplicant(applicantRepository.findByEmailContaining(applicantMail).get(0));

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(stepDefs.mapper.writeValueAsString(doc)).toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        newUriResource = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @When("I have a document with name {string}, path {string} that have a setted phase {int} and belongs to an applicant with email {string}")
    public iHaveADocumentWithASettedPhaseAndBelongsToApplicant() throws Exception {

    }

    @And("It has been created a new document with name {string}, path {string} that belongs to applicant with email {string}")
    public void itHasBeenCreatedANewDocumentWithNamePath(String name, String path) throws Exception {
        List<Document> documentList = documentRepository.findByPathContaining(path);

        stepDefs.result = stepDefs.mockMvc.perform(
                get("/documents/{id}", documentList.get(0).getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))

                .andDo(print())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.path", is(path)))
        ;
    }

    @And("It has not been created a new document with name {string}, path {string}")
    public void itHasNotBeenCreatedANewDocumentWithNamePath(String name, String path) {
        List<Document> applicantList = documentRepository.findByPathContaining(path);

        Assert.assertEquals(0, applicantList.size());
    }

    @And("The document with name {string}, path {string} that belongs to applicant with email {string} have a qualification mark {int} with observation {string}")
    public void documentThatBelongsToApplicantHaveQualificationMarkAndObservation() throws Exception {

    }
}

