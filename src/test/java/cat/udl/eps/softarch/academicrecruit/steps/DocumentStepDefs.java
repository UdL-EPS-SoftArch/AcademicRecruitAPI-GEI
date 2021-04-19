package cat.udl.eps.softarch.academicrecruit.steps;

import cat.udl.eps.softarch.academicrecruit.domain.*;
import cat.udl.eps.softarch.academicrecruit.repository.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @When("I create a new document with name {string}, path {string} for applicant {string}")
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

    @And("I associate the previous document to phase {string}")
    public void iAssociateThePreviousDocumentToPhase(String phaseName) throws Exception {
        Phase phase =   phaseRepository.findByNameContaining(phaseName).get(0);
        stepDefs.result = stepDefs.mockMvc.perform(
                post(newUriResource + "/phases")
                        .contentType("text/uri-list")
                        .content(phase.getUri())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Transactional
    @When("It has been created a new document with name {string}, path {string} that is assigned to phase {string} and belongs to an applicant with email {string}")
    public void iHaveADocumentWithASetPhaseAndBelongsToApplicant(String name, String path, String phase_name, String email) throws Exception {
        Document doc = documentRepository.findByNameContaining(name).get(0);
        Phase phase = phaseRepository.findByNameContaining(phase_name).get(0);
        Assert.assertEquals(path, doc.getPath());
        Assert.assertEquals(email, doc.getApplicant().getEmail());
        MatcherAssert.assertThat(doc.getPhases(), hasItem(phase));
        Assert.assertEquals(1, doc.getPhases().size());
    }

    @And("I have a document with name {string}, path {string} that belongs to applicant with email {string}")
    public void itHasBeenCreatedANewDocumentWithNamePath(String name, String path, String email) throws Exception {
        List<Document> documentList = documentRepository.findByPathContaining(path);

        stepDefs.result = stepDefs.mockMvc.perform(
                get("/documents/{id}", documentList.get(0).getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))

                .andDo(print())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.path", is(path)));

        stepDefs.result = stepDefs.mockMvc.perform(
                get(newUriResource + "/applicant")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.email", is(email)));
    }

    @And("It has not been created a new document with name {string}, path {string}")
    public void itHasNotBeenCreatedANewDocumentWithNamePath(String name, String path) throws Exception {
        List<Document> documentList = documentRepository.findByPathContaining(path);
        Assert.assertEquals(0, documentList.size());
    }

    @And("I have a document with name {string}, path {string} that belongs to applicant with email {string} that have a qualification mark {string} with observation {string}")
    public void documentThatBelongsToApplicantHaveQualificationMarkAndObservation(String name, String path, String email, String mark, String observation) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get(newUriResource)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))

                .andDo(print())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.path", is(path)));

        stepDefs.result = stepDefs.mockMvc.perform(
                get(newUriResource + "/applicant")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.email", is(email)));

        stepDefs.result = stepDefs.mockMvc.perform(
                get(newUriResource + "/qualification")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.mark", is(Double.parseDouble(mark))))
                .andExpect(jsonPath("$.observation", is(observation)));
    }
}
