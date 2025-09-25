package com.test.springMongo.mongoDb.model.productionstate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;

/**
 * declaration of a machine defect in the part
 *
 * @author pfreydiere
 */

@Document("MachineDefect")
public class MachineDefect implements Serializable {

    @JsonProperty
    @JsonPropertyDescription("TimeStamp")
    public String timeStamp;

    @JsonProperty
    @JsonPropertyDescription("Whether or not the machine defect is known in description")
    public boolean isUnknown;

    @JsonProperty
    @JsonPropertyDescription("if the event is unknown, the original ID")
    public String unknownEventID;

    @JsonProperty
    @JsonPropertyDescription("SubSystem")
    public String subSystem;

    @JsonProperty
    @JsonPropertyDescription("Known Machine defect")
    public MachineDefect machineDefect;

    public MachineDefect() {

    }

//    public MachineDefect(ImmutableMachineDefect machineDefect) {
//        assert machineDefect != null;
//        this.machineDefect = machineDefect.machineDefect;
//        this.timeStamp = machineDefect.timeStamp;
//    }
//
//    public MachineDefect(ImmutableMachineDefectUnknown machineDefect) {
//        this.isUnknown = true;
//        this.unknownEventID = machineDefect.eventId;
//        this.timeStamp = machineDefect.timeStamp;
//    }

}
