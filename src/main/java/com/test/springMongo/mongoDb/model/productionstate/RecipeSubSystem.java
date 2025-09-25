package com.test.springMongo.mongoDb.model.productionstate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeSubSystem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4291556692379129799L;

    /**
     * Assigned parameters for the recipe
     */
    // @JsonSchema(title = "SubSystem associated parameters, parameters definitions depends on configuration")
    protected Map<String, Object> parameters = new HashMap<>();

    public Map<String, Object> getParametersByRef() {
        return parameters;
    }

    // @JsonSchema(title = "SubSystem associated parameters, unstructured, permit to save information about additional parameters")
    protected List<String> additionalInformations = new ArrayList<>();

    public List<String> getAdditionalInformationsByRef() {
        return additionalInformations;
    }

}
