package com.test.springMongo.mongoDb.model.productionstate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;

@Document("MeasureTrigger")
public class MeasureTrigger implements Serializable {

    private static final long serialVersionUID = -2582219498135710343L;

    @Id
    // @JsonSchema(title = "Metric Trigger internal ID")
    @JsonProperty
    @JsonPropertyDescription("Metric Trigger internal ID")
    public String id;

    // @JsonSchema(title = "Metric Trigger label")
    @JsonProperty
    @JsonPropertyDescription("Metric Trigger label")
    public String label;

    // @JsonSchema(title = "Measure identification")
    @JsonProperty
    @JsonPropertyDescription("Measure identification")
    public MeasureRef measureReference;

    // @JsonSchema(title = "Measure Trigger type")
    @JsonProperty
    @JsonPropertyDescription("Measure Trigger type")
    public String triggerType;

    // @JsonSchema(title = "Measure Trigger definition")
    @JsonProperty
    @JsonPropertyDescription("Measure Trigger definition")
    public HashMap<String, Object> definition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MeasureRef getMeasureReference() {
        return measureReference;
    }

    public void setMeasureReference(MeasureRef measureReference) {
        this.measureReference = measureReference;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public HashMap<String, Object> getDefinition() {
        return definition;
    }

    public void setDefinition(HashMap<String, Object> definition) {
        this.definition = definition;
    }
}
