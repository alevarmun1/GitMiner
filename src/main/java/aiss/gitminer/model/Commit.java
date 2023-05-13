package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;


public class Commit {
    @JsonProperty("sha")
    @NotEmpty(message = "Comment id is required")
    private String id;
    @JsonProperty("title")
    @NotEmpty(message = "Commit title is required")
    private String title;
    @JsonProperty("message")
    private String message;
    @JsonProperty("author_name")
    @NotEmpty(message = "Author name is required")
    private String authorName;
    @JsonProperty("author_email")
    @NotEmpty(message = "Author email is required")
    private String authorEmail;
    @JsonProperty("authored_date")
    @NotEmpty(message = "Authored date is required")
    private String authoredDate;
    @JsonProperty("committer_name")
    @NotEmpty(message = "Committer name is required")
    private String committerName;
    @JsonProperty("committer_email")
    @NotEmpty(message = "Committer email is required")
    private String committerEmail;

    @JsonProperty("committed_date")
    @NotEmpty(message = "Committer date is required")
    private String committedDate;

    @JsonProperty("html_url")
    @NotEmpty(message = "Html url is required")
    private String webUrl;

    @JsonProperty("commit")
    private void innerCommitRead(CommitInner inner){
        this.title = inner.getTitle();
        this.message = inner.getMessageBody();
        this.authorName = inner.getAuthorName();
        this.authorEmail = inner.getAuthorEmail();
        this.authoredDate = inner.getAuthoredDate();
        this.committerName = inner.getCommitterName();
        this.committerEmail = inner.getCommitterEmail();
        this.committedDate = inner.getCommittedDate();
    }

}
