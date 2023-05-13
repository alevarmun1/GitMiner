package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Issue {

    @JsonProperty("id")
    @NotEmpty(message = "Id is required")
    private String id;
    @JsonProperty("number")
    @NotEmpty(message = "Number is required")
    private Integer refId;
    @JsonProperty("title")
    @NotEmpty(message = "Title is required")
    private String title;
    @JsonProperty("body")
    @NotEmpty(message = "Body is required")
    private String description;
    @JsonProperty("state")
    @NotEmpty(message = "State id is required")
    private String state;
    @JsonProperty("created_at")
    @NotEmpty(message = "Creation date is required")
    private String createdAt;
    @JsonProperty("updated_at")
    @NotEmpty(message = "Update date is required")
    private String updatedAt;
    @JsonProperty("closed_at")
    @NotEmpty(message = "Closing date is required")
    private String closedAt;

    //FIX
    @JsonProperty("labels")
    public void labelsRead(Label[] objectLabels)
    {
        List<String> labelsList = new ArrayList<>();
        for(Label l: objectLabels){
            labelsList.add(l.getName());
        }
        String[] array = labelsList.toArray(new String[labelsList.size()]);
        this.retLabels = array;
    }
    @JsonProperty("retLabels")
    String[] retLabels;
    @JsonProperty("user")
    @NotNull(message = "User cannot be null")
    private User author;

    @JsonProperty("assignee")
    @NotNull(message = "Assignee cannot be null")
    private User assignee;

    @JsonProperty("upvotes")
    private Integer upvotes;
    @JsonProperty("downvotes")
    private Integer downvotes;
    @JsonProperty("reactions")
    public void downUpVotesRead(Reaction reaction)
    {
        this.upvotes = reaction.getUpvotes();
        this.downvotes = reaction.getDownvotes();
    }
    @JsonProperty("url")
    private String webUrl;
    @JsonProperty("commentsRet")
    private Comment[] comments;

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }
    public Integer getRefId() {
        return refId;
    }
}
