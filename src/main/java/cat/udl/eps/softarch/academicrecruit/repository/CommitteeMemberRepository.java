package cat.udl.eps.softarch.academicrecruit.repository;

import cat.udl.eps.softarch.academicrecruit.domain.CommitteeMember;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommitteeMemberRepository extends PagingAndSortingRepository<CommitteeMember, Long> {
}
