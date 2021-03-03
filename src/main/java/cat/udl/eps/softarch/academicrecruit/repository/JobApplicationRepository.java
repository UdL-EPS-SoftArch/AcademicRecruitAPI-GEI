package cat.udl.eps.softarch.academicrecruit.repository;

import cat.udl.eps.softarch.academicrecruit.domain.JobApplication;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JobApplicationRepository  extends PagingAndSortingRepository<JobApplication, String> {
}
