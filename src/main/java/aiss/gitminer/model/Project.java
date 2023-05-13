package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;


public class Project {

    @JsonProperty("id")
    @NotEmpty(message = "Project Id is required")
    private String id;

    @JsonProperty("name")
    @NotEmpty(message = "Project name is required")
    private String name;

    @JsonProperty("html_url")
    @NotEmpty(message = "Project html url is required")
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
