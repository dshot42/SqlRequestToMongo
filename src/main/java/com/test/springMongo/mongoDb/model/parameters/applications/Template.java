package com.test.springMongo.mongoDb.model.parameters.applications;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


/**
 * this class permit to specify the render of objects (table, details, or in other elements)
 * @author pfreydiere
 *
 */
@Document("Template")
public class Template implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4050322306630760999L;

	public enum TEMPLATING_ENGINE {
		MUSTASHE
	}
	
	@Id
	// @JsonSchema(title="Template id", metadata = { @JSData(key = "order", value="10") })
	public String id;
	
	// @JsonSchema(title="Object Reference, can be either fieldrendering or objects",  metadata = { @JSData(key = "order", value="20") })
	@JsonProperty
	public String objectReference;
	
	// @JsonSchema(title="Condition of template application (all, table, table_details, ... ",  metadata = { @JSData(key = "order", value="30") })
	@JsonProperty
	public String application;
	
	// @JsonSchema(title="Template engine", metadata = { @JSData(key = "order", value="40") })
	@JsonProperty
	public TEMPLATING_ENGINE templateEngine;
	
	// @JsonSchema(title="Template Definition",  metadata = { @JSData(key = "order", value="50") })
	@JsonProperty
	public String templateDefinition;
	
}
