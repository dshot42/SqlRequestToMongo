package com.test.springMongo.mongoDb.model.productionstate;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Reference description of a part assembled in the process, this elements is
 * used in the trace subsystem.
 *
 * @author pfreydiere
 */
@Document("AssembledComponent")
public class AssembledComponent implements Serializable {

    private static final long serialVersionUID = -6971193139205227934L;

    // @JsonSchema(title = "Type of component described in the system")
    @JsonProperty
    public String descriptionComponentTypeRef;

    // @JsonSchema(title = "Identified part ID, this may be null if parts are not identified")
    @JsonProperty
    public String componentPartId;

    // this reference comes from parameters or recipe information
    // @JsonSchema(title = "Reference of the assembled part")
    @JsonProperty
    public String reference;

    // *lot de fabrication* de la référence
    // @JsonSchema(title = "reference of the assembled component production batch")
    @JsonProperty
    public String productionBatchReference;

    // if the reference is found in the supply reference,
    // these informations below are populated into the part trace information
    // @JsonSchema(title = "Supply assembled reference, coming from store informations")
    @JsonProperty
    public SupplyReference supplyReference;

}
