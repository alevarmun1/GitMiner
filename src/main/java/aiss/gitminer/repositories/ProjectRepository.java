package aiss.gitminer.repositories;

import aiss.gitminer.model.Project;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProjectRepository {
    public final String API_TOKEN = "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";
    public final String API_URL = "https://api.github.com/repos/";
    public final String API_POST_URL = "http://localhost:8080/gitminer/projects";
    public Project fetchGitLab(String user, String repo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_TOKEN);
        String url = API_URL + user + "/" + repo;
        HttpEntity<String> entity = new HttpEntity<String>(url,headers);
        ResponseEntity<Project> issues = restTemplate.exchange(url, HttpMethod.GET, entity, Project.class);
        return issues.getBody();
    }

    public void postGitMiner(Project project) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_POST_URL;
        restTemplate.postForObject(url, project, Project.class);
    }
}
