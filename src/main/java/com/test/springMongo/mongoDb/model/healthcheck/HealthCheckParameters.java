package com.test.springMongo.mongoDb.model.healthcheck;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("HealthCheckParameters")
public class HealthCheckParameters implements Serializable {

    @Id
    // @JsonSchema(title = "Monitoring health check subsystem", metadata = { @JSData(key = "order", value = "10") })
    public String id;

    public String getId() {
        return id;
    }

    // @JsonSchema(title = "If the system does not respond in this timelapse, emit an alert", metadata = {
    //  @JSData(key = "order", value = "20") })
    @JsonProperty
    public long reportTimeoutAlertInMillis;

    @JsonProperty
    public long getReportTimeoutAlertInMillis() {
        return reportTimeoutAlertInMillis;
    }


    // @JsonSchema(title = "is part of system integrity check", metadata = {
//@JSData(key = "order", value = "30") })
    @JsonProperty
    public Boolean partOfSystemIntegrity = true;

    public void setId(String id) {
        this.id = id;
    }

    public void setReportTimeoutAlertInMillis(long reportTimeoutAlertInMillis) {
        this.reportTimeoutAlertInMillis = reportTimeoutAlertInMillis;
    }

    public Boolean getPartOfSystemIntegrity() {
        return partOfSystemIntegrity;
    }

    public void setPartOfSystemIntegrity(Boolean partOfSystemIntegrity) {
        this.partOfSystemIntegrity = partOfSystemIntegrity;
    }
}
