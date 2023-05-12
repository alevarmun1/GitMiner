package aiss.gitminer.repositories;

import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CommentRepository {
    public final String API_TOKEN = "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";
    public final String API_URL = "https://api.github.com/repos/";
    public Comment[] fetchGitLab(String user, String repo, Integer number) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_TOKEN);
        String url = API_URL + user + "/" + repo + "/issues/" + number + "/comments";
        HttpEntity<String> entity = new HttpEntity<String>(url,headers);
        ResponseEntity<Comment[]> comments = restTemplate.exchange(url, HttpMethod.GET, entity, Comment[].class);
        return comments.getBody();
    }

}
