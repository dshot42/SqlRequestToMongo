package com.test.springMongo.mongoDb.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.test.springMongo.mongoDb.model.ElementEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("User")
public class User extends ElementEntity implements Serializable {

    private static final long serialVersionUID = 454141312732634680L;

    @Id
    // @JsonSchema(title = "Workorder Internal ID", metadata = @JSData(key = "order", value = "10"))
    @JsonProperty
    @JsonPropertyDescription("Workorder Internal ID")
    public Long idEntity;

    @Indexed(unique = true)
    @JsonProperty
    //// @JsonSchema(title = "User login", metadata = { @JSData(key = "order", value = "10") })
    //	@JsonProperty @JsonPropertyDescription("User login")
    protected long id;


    // @JsonSchema(title = "User name", metadata = { @JSData(key = "order", value = "50") })
    @JsonProperty
    @JsonPropertyDescription("User name")
    public String name;

    // @JsonSchema(title = "Encrypted Password", metadata = { @JSData(key = "order", value = "100") })
    @JsonProperty
    @JsonPropertyDescription("Encrypted Password")
    public String encodedPassword;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
}
