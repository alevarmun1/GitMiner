package aiss.gitminer.repositories;

import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Issue;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommitRepository {
    public final String API_TOKEN = "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";
    public final String API_URL = "https://api.github.com/repos/";
    public Commit[] fetchGitLab(String user, String repo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_TOKEN);
        String url = API_URL + user + "/" + repo + "/commits";
        HttpEntity<String> entity = new HttpEntity<String>(url,headers);
        ResponseEntity<Commit[]> commits = restTemplate.exchange(url, HttpMethod.GET, entity, Commit[].class);
        return commits.getBody();
    }

}
