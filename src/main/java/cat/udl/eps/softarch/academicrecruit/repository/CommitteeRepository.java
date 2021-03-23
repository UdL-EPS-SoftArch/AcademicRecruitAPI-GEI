package cat.udl.eps.softarch.academicrecruit.repository;

import cat.udl.eps.softarch.academicrecruit.domain.Applicant;
import cat.udl.eps.softarch.academicrecruit.domain.Committee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommitteeRepository extends PagingAndSortingRepository<Committee, Long> {
}
