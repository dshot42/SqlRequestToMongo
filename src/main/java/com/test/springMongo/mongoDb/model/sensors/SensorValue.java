package com.test.springMongo.mongoDb.model.sensors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.test.springMongo.mongoDb.model.ElementEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;


@Document("SensorValue")
public class SensorValue extends ElementEntity implements Serializable {

    public SensorValue() {
    }

    public SensorValue(String sensorName, Object sensorValue, String measureTime, String associatedStep) {
        this.sensorName = sensorName;
        this.sensorValue = sensorValue;
        this.measureTime = measureTime;
        this.associatedStepName = associatedStep;
    }

    // @JsonSchema(title = "Sensor ID")
    @JsonProperty
    @JsonPropertyDescription("Sensor ID")

    protected String sensorName; // sensor Name

    public String getSensorName() {
        return sensorName;
    }

    // @JsonSchema(title = "Sensor Value")
    @JsonProperty
    @JsonPropertyDescription("Sensor Value")

    protected Object sensorValue; // freely defined by the sensor

    public Object getSensorValue() {
        return sensorValue;
    }

    // @JsonSchema(title = "Measure Time Code")
    @JsonPropertyDescription("Measure Time Code")
    protected String measureTime; // the measured time

    public String getMeasureTime() {
        return measureTime;
    }

    // @JsonSchema(title = "Associated Step Name (may be null)")
    @JsonProperty
    @JsonPropertyDescription("Associated step name (may be null)")
    protected String associatedStepName; // the associated Step Name

    public String getAssociatedStepName() {
        return associatedStepName;
    }

}
