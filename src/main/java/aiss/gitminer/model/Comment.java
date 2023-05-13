
package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Comment {

    @JsonProperty("id")
    @NotEmpty(message = "Comment Id is required")
    private String id;
    @JsonProperty("body")
    @NotEmpty(message = "Body is required")
    private String body;

    @JsonProperty("user")
    @NotNull(message = "User cannot be null")
    private User author;

    @JsonProperty("created_at")
    @NotEmpty(message = "Creation date is required")
    private String createdAt;
    @JsonProperty("updated_at")
    @NotEmpty(message = "Update date is required")
    private String updatedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
