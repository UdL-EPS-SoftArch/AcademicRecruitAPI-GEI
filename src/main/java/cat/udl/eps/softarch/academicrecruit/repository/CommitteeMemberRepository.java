package cat.udl.eps.softarch.academicrecruit.repository;

import cat.udl.eps.softarch.academicrecruit.domain.CommitteeMember;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestResource
public interface CommitteeMemberRepository extends PagingAndSortingRepository<CommitteeMember, Long> {
}
