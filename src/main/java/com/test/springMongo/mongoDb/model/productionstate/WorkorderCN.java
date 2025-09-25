package com.test.springMongo.mongoDb.model.productionstate;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("WorkOrder")
public class WorkorderCN extends Workorder {
    private static final long serialVersionUID = 5505880918841130171L;


    @JsonProperty // @JsonSchema(title = "Temps Prevu", metadata = @JSData(key = "order", value = "20"))
    public Double tpsPrevu;

    @JsonProperty // @JsonSchema(title = "Date Fin Prevu", metadata = @JSData(key = "order", value = "25"))
    public String dateFinPrevu;

    @JsonProperty // @JsonSchema(title = "Unite Temps Prevu", metadata = @JSData(key = "order", value = "30"))
    public Double uniteTpsPrevu;

    @JsonProperty // @JsonSchema(title = "Equipement Produit",
    //   metadata = @JSData(key = "order", value = "10"))
    public String equipementProduit;

    @JsonProperty // @JsonSchema(title = "Quantité Lopins",
    //   metadata = @JSData(key = "order", value = "10"))
    public int qteLopins;

    @JsonProperty // @JsonSchema(title = "Date Fin Atelier",
    //   metadata = @JSData(key = "order", value = "10"))
    public String dateFinAtelier;
    @JsonProperty // @JsonSchema(title = "Reference Boitier",
    //   metadata = @JSData(key = "order", value = "10"))
    public String refBoitier;
    @JsonProperty // @JsonSchema(title = "Quantité Connecteurs",
    //   metadata = @JSData(key = "order", value = "10"))
    public int qteConnecteurs;


    public Double getTpsPrevu() {
        return tpsPrevu;
    }

    public void setTpsPrevu(Double tpsPrevu) {
        this.tpsPrevu = tpsPrevu;
    }

    public String getDateFinPrevu() {
        return dateFinPrevu;
    }

    public void setDateFinPrevu(String dateFinPrevu) {
        this.dateFinPrevu = dateFinPrevu;
    }

    public Double getUniteTpsPrevu() {
        return uniteTpsPrevu;
    }

    public void setUniteTpsPrevu(Double uniteTpsPrevu) {
        this.uniteTpsPrevu = uniteTpsPrevu;
    }

    public String getEquipementProduit() {
        return equipementProduit;
    }

    public void setEquipementProduit(String equipementProduit) {
        this.equipementProduit = equipementProduit;
    }

    public int getQteLopins() {
        return qteLopins;
    }

    public void setQteLopins(int qteLopins) {
        this.qteLopins = qteLopins;
    }

    public String getDateFinAtelier() {
        return dateFinAtelier;
    }

    public void setDateFinAtelier(String dateFinAtelier) {
        this.dateFinAtelier = dateFinAtelier;
    }

    public String getRefBoitier() {
        return refBoitier;
    }

    public void setRefBoitier(String refBoitier) {
        this.refBoitier = refBoitier;
    }

    public int getQteConnecteurs() {
        return qteConnecteurs;
    }

    public void setQteConnecteurs(int qteConnecteurs) {
        this.qteConnecteurs = qteConnecteurs;
    }
}


