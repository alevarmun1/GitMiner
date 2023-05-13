
package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class User {

    @JsonProperty("id")
    @NotEmpty(message = "User id is required")
    private String id;
    @JsonProperty("login")
    @NotEmpty(message = "Login is required")
    private String username;
    @JsonProperty("name")
    @NotEmpty(message = "Name is required")
    private String name;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("url")
    private String webUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
