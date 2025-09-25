package com.test.springMongo.mongoDb.model.productionstate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.springMongo.mongoDb.model.ElementEntity;
import com.test.springMongo.mongoDb.service.DateUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Document("PartHistory")
public class PartHistory extends ElementEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3952453873952666388L;

    // the step the part was
    // this is the *step implementation*
    // @JsonSchema(title = "Step Name")
    @JsonProperty("atStep")
    public String AtStep;

    // the associated measures
    // @JsonSchema(title = "Associated measures")
    @JsonProperty
    public Map<String, Object> measures = new HashMap<>();

    // @JsonSchema(title = "Input Parameters")
    @JsonProperty
    public Map<String, Object> parameters = new HashMap<>();

    // the entering timestamp for the step
    // @JsonSchema(title = "Part Enter step At")
    @JsonProperty
    public String stepEnteringTimeStamp; // might be null if unknown

    // @JsonSchema(title = "Part Exit step At")
    @JsonProperty
    public String stepExitingTimeStamp;

    // @JsonSchema(title = "Result Code for Step")
    @JsonProperty
    public Integer stepResultCode;

    // @JsonSchema(title = "Part Defect details (if any)")
//    @JsonProperty
//    public List<PartDefect> partDefectDetails;

    // @JsonSchema(title = "Encountered machine defects while processing the step")
    public List<MachineDefect> encounteredMachineDefects = new ArrayList<>();

    // @JsonSchema(title = "Assembled components in this step")
    public List<AssembledComponent> assembledComponent = new ArrayList<>();

    public PartHistory() {
    }

    public PartHistory(String AtStep, Map<String, Object> parameters, Map<String, Object> measures,
                       String stepEnteringTimeStamp, String stepExitingTimeStamp, Integer resultCode) {

        if (AtStep == null || AtStep.isBlank()) {
            throw new RuntimeException("creating history, step cannot be null");
        }

        this.AtStep = AtStep;
        this.measures = measures;
        this.parameters = parameters;
        this.stepEnteringTimeStamp = stepEnteringTimeStamp;
        this.stepExitingTimeStamp = stepExitingTimeStamp;
        this.stepResultCode = resultCode;
    }

    public String getAtStep() {
        return AtStep;
    }

    public Map<String, Object> getMeasures() {
        return measures;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public String getStepEnteringTimeStamp() {
        return stepEnteringTimeStamp;
    }

    public String getStepExitingTimeStamp() {
        return stepExitingTimeStamp;
    }

    public void setStepEnteringTimeStamp(String stepEnteringTimeStamp) {
        this.stepEnteringTimeStamp = stepEnteringTimeStamp;
    }

    public void setStepExitingTimeStamp(String stepExitingTimeStamp) {
        this.stepExitingTimeStamp = stepExitingTimeStamp;
    }

    public Duration computeTimeInStep() {
        if (stepEnteringTimeStamp == null || stepExitingTimeStamp == null) {
            return Duration.ZERO; // no information available
        }

        return Duration.between(DateUtils.stringToDate(stepEnteringTimeStamp), DateUtils.stringToDate(stepExitingTimeStamp));
    }

    public Integer getStepResultCode() {
        return stepResultCode;
    }

    public void changeResultCode(Integer resultCode) {
        this.stepResultCode = resultCode;
    }

    public void setEncounteredMachineDefects(List<MachineDefect> encounteredMachineDefects) {
        this.encounteredMachineDefects = encounteredMachineDefects;
    }

    public void setAssembledComponents(List<AssembledComponent> assembledComponents) {
        this.assembledComponent.clear();
        this.assembledComponent.addAll(assembledComponents);
    }

    public List<AssembledComponent> getAssembledComponents() {
        return assembledComponent;
    }

    public List<MachineDefect> getEncounteredMachineDefects() {
        return encounteredMachineDefects;
    }

    public void mergePartInInformations(PartHistory partInputHistory) {
        if (partInputHistory == null) {
            return;
        }
        this.stepEnteringTimeStamp = partInputHistory.stepEnteringTimeStamp;
        this.parameters.putAll(partInputHistory.parameters);
    }

    @Override
    public String toString() {

        Duration d = computeTimeInStep();

        return "(" + AtStep + " enteringAt:" + stepEnteringTimeStamp + " - exitingAt:" + stepExitingTimeStamp + " "
                + measures + ") duration " + d.toMillis() + " ms";

    }

}
