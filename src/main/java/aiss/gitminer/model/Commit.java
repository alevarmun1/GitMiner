package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Commit {
    @JsonProperty("sha")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("message")
    private String message;
    @JsonProperty("author_name")
    private String authorName;
    @JsonProperty("author_email")
    private String authorEmail;
    @JsonProperty("authored_date")
    private String authoredDate;
    @JsonProperty("committer_name")
    private String committerName;
    @JsonProperty("committer_email")
    private String committerEmail;

    @JsonProperty("committed_date")
    private String committedDate;

    @JsonProperty("html_url")
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
