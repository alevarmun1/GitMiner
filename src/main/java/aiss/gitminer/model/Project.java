package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Project {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("html_url")
    private String webUrl;
    @JsonProperty("commits")
    private Commit[] commits;

    @JsonProperty("issues")
    private Issue[] issues;

    public Project() {
    }

    public void setCommits(Commit[] commits) {
        this.commits = commits;
    }

    public void setIssues(Issue[] issues) {
        this.issues = issues;
    }
}
