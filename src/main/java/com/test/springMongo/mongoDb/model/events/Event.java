package com.test.springMongo.mongoDb.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Document("Event")
public class Event implements Serializable {

    @Id
    public String id;

    @JsonProperty
    @JsonPropertyDescription("equipment that originate the message, scope of the event")
    private String subsystem;

    /////////////////////////////////////////////////////////////////////////////////////////////////


    @JsonProperty
    @JsonPropertyDescription("id of the event, unique by equipment, used for tracability")
    private int eventId;


    @JsonProperty
    @JsonPropertyDescription("name of the event")
    private String eventName;


    @JsonProperty
    @JsonPropertyDescription("category of the event, event meaning .. ")
    private String eventDomain;


    @JsonProperty
    @JsonPropertyDescription("date code of the message")
    protected String timestamp;


    /////////////////////////////////////////////////////////////////////////////////////////////////

    // for later use, just in case

    @JsonProperty
    @JsonPropertyDescription("used when additional informations has to be given, additional context information")
    private Object args;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDomain() {
        return eventDomain;
    }

    public void setEventDomain(String eventDomain) {
        this.eventDomain = eventDomain;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Object getArgs() {
        return args;
    }

    public void setArgs(Object args) {
        this.args = args;
    }
}
