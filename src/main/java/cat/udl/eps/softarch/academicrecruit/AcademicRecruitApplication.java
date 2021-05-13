package cat.udl.eps.softarch.academicrecruit;

import cat.udl.eps.softarch.academicrecruit.domain.JobApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class AcademicRecruitApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AcademicRecruitApplication.class, args);
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.exposeIdsFor(JobApplication.class);
	}
}
