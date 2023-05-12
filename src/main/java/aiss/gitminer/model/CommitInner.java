package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommitInner {
    @JsonProperty("title")
    private String title;

    @JsonProperty("message")
    public void readBody(String body)
    {
        String[] parts = body.split("\\n");
        this.title = parts[0];
        if(parts.length > 1)
        {
            this.messageBody = parts[1];
        }
    }
    private String messageBody;


    @JsonProperty("author")
    private void authorRead(CommitUser commitUser){
        this.authorName = commitUser.getName();
        this.authoredDate = commitUser.getDate();
        this.authorEmail = commitUser.getEmail();
    }

    @JsonProperty("committer")
    private void committerRead(CommitUser commitUser){
        this.committerName = commitUser.getName();
        this.committedDate = commitUser.getDate();
        this.committerEmail = commitUser.getEmail();
    }

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

    public String getTitle() {
        return title;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public String getAuthoredDate() {
        return authoredDate;
    }

    public String getCommitterName() {
        return committerName;
    }

    public String getCommitterEmail() {
        return committerEmail;
    }

    public String getCommittedDate() {
        return committedDate;
    }
}
