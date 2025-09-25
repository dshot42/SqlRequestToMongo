package com.test.springMongo.mongoDb.model.productionstate;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PartCounter")
public class PartCounter implements Serializable {


	private static final long serialVersionUID = 1093147955399915632L;

	
	@JsonProperty @JsonPropertyDescription("Good part number")
	private int goodParts = 0;
	
	@JsonProperty
	@JsonPropertyDescription("Defective parts number")
	private int defectiveParts = 0;

	////////////////////////////////////////////////////
	// steps counters
	
	@JsonProperty @JsonPropertyDescription("Good Passed steps")
	private int goodPassedSteps = 0;
	
	@JsonProperty @JsonPropertyDescription("Bad passed steps")
	private int badPassedSteps = 0;
	
	
	public PartCounter() {
	}

	public int getGoodParts() {
		return goodParts;
	}

	public void setGoodParts(int goodParts) {
		this.goodParts = goodParts;
	}

	public int getDefectiveParts() {
		return defectiveParts;
	}

	public void setDefectiveParts(int defectiveParts) {
		this.defectiveParts = defectiveParts;
	}

	public void incrementDefectiveParts() {
		defectiveParts++;
	}

	public void incrementGoodParts() {
		goodParts++;
	}

	public void aPartHasBeedRecovered() {
		defectiveParts --;
		goodParts ++;
	}
	
	/////////////////////////////////////////////////////
	// steps counting
	
	public int getGoodPassedSteps() {
		return goodPassedSteps;
	}
	
	public void setGoodPassedSteps(int goodPassedSteps) {
		this.goodPassedSteps = goodPassedSteps;
	}
	
	public void incGoodPassedSteps() {
		this.goodPassedSteps ++;
	}
	
	
	public int getBadPassedSteps() {
		return badPassedSteps;
	}
	
	public void setBadPassedSteps(int badPassedSteps) {
		this.badPassedSteps = badPassedSteps;
	}
	
	public void incBadPassedSteps() {
		this.badPassedSteps ++;
	}
	
	/////////////////////////////////////////////////////
	
	public String toString() {
		return "goodParts: " + goodParts + " defectiveParts: " + defectiveParts;
	}

}
