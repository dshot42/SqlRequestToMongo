package com.test.springMongo.mongoDb.model.productionstate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * reference to a measure, this object is comparable for hashing
 *
 * @author pfreydiere
 */
@Document("MeasureRef")
public class MeasureRef implements Serializable {

    private static final long serialVersionUID = 5407395097262993094L;

    // @JsonSchema(title = "datasetId")
    @JsonProperty
    @JsonPropertyDescription("Dataset Id")
    public String datasetId;

    // @JsonSchema(title = "measureId")
    @JsonProperty
    @JsonPropertyDescription("Measure Id")
    public String measureId;

    // @JsonSchema(title = "equipmentId")
    @JsonProperty
    @JsonPropertyDescription("Equipment reference Id")
    public String equipmentId;

    public MeasureRef() {

    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getMeasureId() {
        return measureId;
    }

    public void setMeasureId(String measureId) {
        this.measureId = measureId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public MeasureRef(String datasetId, String measureId) {
        assert datasetId != null;
        this.datasetId = datasetId;

        assert measureId != null;
        this.measureId = measureId;
    }


    @Override
    public int hashCode() {
        return Objects.hash(datasetId, measureId, equipmentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasureRef that = (MeasureRef) o;
        return Objects.equals(datasetId, that.datasetId) && Objects.equals(measureId, that.measureId) && Objects.equals(equipmentId, that.equipmentId);
    }

    @Override
    public String toString() {
        return "[" + measureId + "(" + datasetId + ")]";
    }

}
