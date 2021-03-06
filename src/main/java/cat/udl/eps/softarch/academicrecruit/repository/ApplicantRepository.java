package cat.udl.eps.softarch.academicrecruit.repository;

import cat.udl.eps.softarch.academicrecruit.domain.Applicant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ApplicantRepository extends PagingAndSortingRepository<Applicant, Long> {

    List<Applicant> findByEmailContaining(@Param("email") String email);
}
