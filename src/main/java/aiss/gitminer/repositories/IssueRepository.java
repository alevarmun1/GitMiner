package aiss.gitminer.repositories;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {
    public final String API_TOKEN = "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";
    public final String API_URL = "https://api.github.com/repos/";
    public Issue[] fetchGitLab(String user, String repo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_TOKEN);
        String url = API_URL + user + "/" + repo + "/issues";
        HttpEntity<String> entity = new HttpEntity<String>(url,headers);
        ResponseEntity<Issue[]> issues = restTemplate.exchange(url, HttpMethod.GET, entity, Issue[].class);
        return issues.getBody();
    }

    Page<Issue> findById(String id, Pageable pageable);

    Page<Issue> findByState(String state, Pageable pageable);

    Page<Issue> findByRefId(String refId, Pageable pageable);

}
