package br.pegoraro.code.timetracking.control;

import java.net.InetAddress;
import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.pegoraro.code.timetracking.model.Project;
import br.pegoraro.code.timetracking.model.repo.ProjectRepository;
import io.micrometer.core.annotation.Timed;
import reactor.core.publisher.Mono;


@RestController
@Timed("timetracking.project")
public class ProjectResource {
    private static final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    private final ProjectRepository projectRepository;

    public ProjectResource(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostMapping("/track/project")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProjectRequest projectRequest) {
        log.info("Saving project {}", projectRequest);
        Project project = new Project(UUID.randomUUID().toString(), 
            projectRequest.name(), 
            Instant.now(), 
            Instant.now(), 
            projectRequest.activate() ? Instant.now() : null,
            null);

        projectRepository.save(project);
    }

    @GetMapping("/track/project/{projectId}")
    public ProjectVo getProject(String id) {
        Project project = projectRepository.findById(id).block();
        String status = project.activationDate().isAfter(Instant.now()) && project.projectFinish() != null ? "active" : "idle";
        return new ProjectVo(id, project.name(), project.creationTime(), project.lastUpdate(), status, project.activationDate(), buildUri(id));
    }


    @PutMapping("/track/project/{projectId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateProject(String id) {
        log.info("activating project {}, with {}", id);
        Project project = projectRepository.findById(id).block();
        Project newProject = new Project(id, project.name(), project.creationTime(), Instant.now(), Instant.now(), null);
        projectRepository.save(newProject);
    }

    @PutMapping("/track/project/{projectId}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateProject(String id) {
        var project = projectRepository.findById(id)
            .blockOptional()
            .orElseThrow(() -> new ResourceNotFoundException("project not found"));
        if (project.activationDate() == null) {
            throw new ResourceInvalidException("project must be active");
        }
        log.info("deactivating project {}, with {}", id);
        Project newProject = new Project(id, project.name(), project.creationTime(), Instant.now(), project.activationDate(), Instant.now());
        projectRepository.save(newProject);
    }

    private String buildUri(String id) {
        try {
            return new StringBuffer()
            .append(InetAddress.getLocalHost().getHostName())
            .append("/track/project/")
            .append(id)
            .toString();
        } catch(Exception e) {
            log.error("exception building Uri", e);
            return null;
        }
    }
}

