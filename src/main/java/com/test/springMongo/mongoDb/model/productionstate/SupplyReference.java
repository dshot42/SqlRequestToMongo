package com.test.springMongo.mongoDb.model.productionstate;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * this class define the supply reference of a components,
 * that can be assembled with other parts in the process.
 *
 * @author pfreydiere
 */

@Document("SupplyReference")
public class SupplyReference implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6507639655919003225L;

    @Id
    // @JsonSchema(title = "ID of the supply reference (optional if used inline)",
//    metadata =
//    @JSData(key = "order", value = "1"))
    public String id;

    // @JsonSchema(title = "Reference of the supply, permit to trace the assembled component",
    //  metadata = @JSData(key = "order", value = "5"))
    @JsonProperty
    public String supplyReference;

    // @JsonSchema(title = "Manufacturer identifier of the supply provider",
//    metadata =
//    @JSData(key = "order", value = "10"))
    @JsonProperty
    public String manufacturer;

    // @JsonSchema(title = "Additional informations that permit to trace the supply reference (date of production, additional informations)",
//    metadata =
//    @JSData(key = "order", value = "20"))
    @JsonProperty
    public String additionalManufacturerSupplyInformations;

}

