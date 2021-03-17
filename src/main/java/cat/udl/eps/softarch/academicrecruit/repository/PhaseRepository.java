package cat.udl.eps.softarch.academicrecruit.repository;

import cat.udl.eps.softarch.academicrecruit.domain.Phase;
import cat.udl.eps.softarch.academicrecruit.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PhaseRepository extends PagingAndSortingRepository<Phase, Long> {
    List<Phase> findByNameContaining(@Param("text") String text);

}