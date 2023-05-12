package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reaction {
    @JsonProperty("+1")
    public Integer upvotes;
    @JsonProperty("-1")
    public Integer downvotes;

    public Reaction() {
    }

    public Integer getUpvotes() {
        return upvotes;
    }
    public Integer getDownvotes() {
        return downvotes;
    }
}
