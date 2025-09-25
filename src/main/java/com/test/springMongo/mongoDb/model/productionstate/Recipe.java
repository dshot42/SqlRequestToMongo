package com.test.springMongo.mongoDb.model.productionstate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Recipe contains parameters to operate a workorder, this define the what on
 * the production, with associated parameters, this is not a "how" structure,
 * that will be derivated from plans (with part process decisions)
 *
 * @author pfreydiere
 */

@Document("Recipe")
public class Recipe implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7932247548763206863L;

    /**
     * id of the recipe, permitting to load and save
     */
    @Id
    @JsonProperty
    @JsonPropertyDescription("Internal Id")
    public String id;

    // @JsonSchema(title = "User Recipe Identification")
    @JsonProperty
    @JsonPropertyDescription("User Recipe identification")
    public String recipeId;

    // @JsonSchema(title = "Product reference constructed with this recipe")
    @JsonProperty
    @JsonPropertyDescription("Product reference constructed with this recipe")
    public String productReference;

    // @JsonSchema(title = "Recipe Version")
    @JsonProperty
    @JsonPropertyDescription("Recipe Version")
    public String version;

    // @JsonSchema(title = "Last modification date")
    @JsonProperty
    @JsonPropertyDescription("Last modification date")
    public String lastModifiedDate;

    /**
     * Assigned parameters for the recipe, "system"
     */
    // @JsonSchema(title = "Global Recipe Parameters")
    @JsonProperty
    @JsonPropertyDescription("Global Recipe Parameters")
    public Map<String, Object> globalParametersValues;

    /**
     * sub system recipe parameters values
     */
    // @JsonSchema(title = "Per Subsystem parameters")
    @JsonProperty
    @JsonPropertyDescription("Per Subsystem parameters")
    public Map<String, RecipeSubSystem> subSystemRecipe = new HashMap<>();

}
