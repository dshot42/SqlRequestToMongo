package com.test.springMongo.mongoDb.model.productionstate;


import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * overload of the workorder to add product type and reference, along with
 * workorder state
 *
 * @author pfreydiere
 */
@Document("WorkOrder")
public class WorkorderWithChangingState extends Workorder {

    private static final long serialVersionUID = 3572137519386742633L;

    public WorkorderWithChangingState() {
    }

    // computed
    @JsonPropertyDescription("Workorder closing date")
    // // @JsonSchema(title = "", // metadata = @JSData(key = "order", value = "7"))
    public String workorderClosingDate;

    @JsonPropertyDescription("Product Reference")
    // @JsonSchema(title = "Product Reference",
    // metadata = @JSData(key = "order", value = "8"))
    public String productRef;

    @JsonPropertyDescription("Product Type")
    // @JsonSchema(title = "Type de produit",
    // metadata = @JSData(key = "order", value = "10"))
    public String productType;

    // computed
    @JsonPropertyDescription("Qty manufactured (computed)")
    // @JsonSchema(title = "Qty manufactured (computed)",
    // metadata = @JSData(key = "order", value = "8"))
    public int qtyManufactured;

    // computed
    @JsonPropertyDescription("Qty pending parts (computed)")
    // @JsonSchema(title = "Qty pending parts (computed)",
    // metadata = @JSData(key = "order", value = "9"))
    public int qtyPendingParts; // cannot be computed, on first line, not known

    // computed
    @JsonPropertyDescription("Qty left to introduce (computed)")
    // @JsonSchema(title = "Qty left to introduce for workorder (computed)",
    // metadata = @JSData(key = "order", value = "10"))
    public int qtyLeftToIntroduceParts;

    // computed
    @JsonPropertyDescription("Defects count associated to workorder (computed)")
    // @JsonSchema(title = "Defects count associated to workorder (computed)",
    // metadata = @JSData(key = "order", value = "11"))
    public int qtyRejectedParts; // cannot be computed, on first line, not known


    // status in base class -> managed by plugin
    public String getState() {
        return this.workorderState;
    }

    public void setState(String newState) {
        this.workorderState = newState;
    }


    public String getWorkorderClosingDate() {
        return workorderClosingDate;
    }

    public void setWorkorderClosingDate(String workorderClosingDate) {
        this.workorderClosingDate = workorderClosingDate;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getQtyManufactured() {
        return qtyManufactured;
    }

    public void setQtyManufactured(int qtyManufactured) {
        this.qtyManufactured = qtyManufactured;
    }

    public int getQtyPendingParts() {
        return qtyPendingParts;
    }

    public void setQtyPendingParts(int qtyPendingParts) {
        this.qtyPendingParts = qtyPendingParts;
    }

    public int getQtyLeftToIntroduceParts() {
        return qtyLeftToIntroduceParts;
    }

    public void setQtyLeftToIntroduceParts(int qtyLeftToIntroduceParts) {
        this.qtyLeftToIntroduceParts = qtyLeftToIntroduceParts;
    }

    public int getQtyRejectedParts() {
        return qtyRejectedParts;
    }

    public void setQtyRejectedParts(int qtyRejectedParts) {
        this.qtyRejectedParts = qtyRejectedParts;
    }
}
