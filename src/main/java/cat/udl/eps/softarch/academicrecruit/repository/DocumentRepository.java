package cat.udl.eps.softarch.academicrecruit.repository;

import cat.udl.eps.softarch.academicrecruit.domain.Document;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DocumentRepository extends PagingAndSortingRepository<Document, Long> {
    List<Document> findByPathContaining(@Param("path") String path);
    List<Document> findByNameContaining(@Param("name") String name);
}
