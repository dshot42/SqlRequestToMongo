package com.test.springMongo.mongoDb.model.productionstate;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.springMongo.mongoDb.model.ElementEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Document("Workorder")
public class Workorder extends ElementEntity implements Serializable {

	private static final long serialVersionUID = -2637951854383194842L;

	@Transient
	public static final String SEQUENCE_NAME = "workorder_sequence";


	@Id
	// @JsonSchema(title = "Workorder Internal ID", metadata = @JSData(key = "order", value = "10"))
	@JsonProperty
	@JsonPropertyDescription("Workorder Internal ID")
	public Long idEntity;

	@Indexed(unique = true)
	// @JsonSchema(title = "Workorder Internal ID", metadata = @JSData(key = "order", value = "10"))
	@JsonProperty
	public String id;

	// @JsonSchema(title = "Workorder Public ID", metadata = @JSData(key = "order", value = "20"))
	@JsonProperty @JsonPropertyDescription("Workorder Public ID")
	public String workOrderId;

	// @JsonSchema(title = "Associated Recipe ID", metadata = @JSData(key = "order", value = "30"))
	@JsonProperty @JsonPropertyDescription("Associated Recipe ID")
	protected String recipeId;

	// @JsonSchema(title = "Workorder creation date", metadata = @JSData(key = "order", value = "40"))
	@JsonProperty @JsonPropertyDescription("Workorder creation date")
	public String createdAt;

	// @JsonSchema(title = "Workorder start date", metadata = @JSData(key = "order", value = "45"))
	@JsonProperty @JsonPropertyDescription("Workorder start date")
	public String startedAt;

	// @JsonSchema(title = "Workorder state", metadata = @JSData(key = "order", value = "50"))
	@JsonProperty @JsonPropertyDescription("Workorder state")
	public String workorderState;

	//
	// if the qty to manufacture is not known,
	// this field stay null
	//
	// @JsonSchema(title = "Quantity to manufacture", metadata = @JSData(key = "order", value = "60"))
	@JsonProperty @JsonPropertyDescription("Quantity to manufacture")
	public Integer qtyToManufacture = null;

	/**
	 * cycle time objective (in seconds)
	 */
	// @JsonSchema(title = "cycle time objective for workorder (all system)", metadata = @JSData(key = "order", value = "70"))
	@JsonProperty @JsonPropertyDescription("cycle time objective for workorder (all system)")
	public Double cycletimeObjective;

	// @JsonSchema(title = "copy of the associated Recipe", metadata = @JSData(key = "order", value = "80"))
	@JsonProperty @JsonPropertyDescription("copy of the associated Recipe")
	public Recipe associatedRecipe = null;

	// @JsonSchema(title = "memoized part counters for subsystems", metadata = @JSData(key = "order", value = "90"))
	@JsonProperty @JsonPropertyDescription("memoized part counters for subsystems")
	public HashMap<String, PartCounter> subSystemCounters = new HashMap<>();

	/**
	 * Override to populate user additional informations
	 */

	public HashMap<String, Serializable> getUserAdditionalInformations() {
		return new HashMap<>();
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(Long idEntity) {
		this.idEntity = idEntity;
	}

	public String getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}

	public String getWorkorderState() {
		return workorderState;
	}

	public void setWorkorderState(String workorderState) {
		this.workorderState = workorderState;
	}

	public Integer getQtyToManufacture() {
		return qtyToManufacture;
	}

	public void setQtyToManufacture(Integer qtyToManufacture) {
		this.qtyToManufacture = qtyToManufacture;
	}

	public Double getCycletimeObjective() {
		return cycletimeObjective;
	}

	public void setCycletimeObjective(Double cycletimeObjective) {
		this.cycletimeObjective = cycletimeObjective;
	}

	public Recipe getAssociatedRecipe() {
		return associatedRecipe;
	}

	public void setAssociatedRecipe(Recipe associatedRecipe) {
		this.associatedRecipe = associatedRecipe;
	}

	public HashMap<String, PartCounter> getSubSystemCounters() {
		return subSystemCounters;
	}

	public void setSubSystemCounters(HashMap<String, PartCounter> subSystemCounters) {
		this.subSystemCounters = subSystemCounters;
	}

	@Override
	public String toString() {
		return "Workorder{" +
				"idEntity=" + idEntity +
				", id='" + id + '\'' +
				", workOrderId='" + workOrderId + '\'' +
				", recipeId='" + recipeId + '\'' +
				", createdAt='" + createdAt + '\'' +
				", startedAt='" + startedAt + '\'' +
				", workorderState='" + workorderState + '\'' +
				", qtyToManufacture=" + qtyToManufacture +
				", cycletimeObjective=" + cycletimeObjective +
				", associatedRecipe=" + associatedRecipe +
				", subSystemCounters=" + subSystemCounters +
				'}';
	}
}
