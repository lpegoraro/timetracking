package br.pegoraro.code.timetracking.model.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import br.pegoraro.code.timetracking.model.Project;

public interface ProjectRepository extends ReactiveMongoRepository<Project, String> {
    
}
