 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitties.Evenement;

import java.util.Date;

/**
 *
 * @author ZIZOU
 */
public class Evenement {

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setType_de_sport(String type_de_sport) {
        this.type_de_sport = type_de_sport;
    }

    public void setType_evenement(String type_evenement) {
        this.type_evenement = type_evenement;
    }

    public void setLieu_evenement(String lieu_evenement) {
        this.lieu_evenement = lieu_evenement;
    }
    int id_evenement;
    String nom_evenement;
    Date date_debut;
    Date date_fin;
    String type_de_sport;
    String type_evenement;
    String lieu_evenement;

    public Evenement() {
    }

    public Evenement( String nom_evenement, Date date_debut, Date date_fin, String type_de_sport, String type_evenement, String lieu_evenement) {
        this.id_evenement = id_evenement;
        this.nom_evenement = nom_evenement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type_de_sport = type_de_sport;
        this.type_evenement = type_evenement;
        this.lieu_evenement = lieu_evenement;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public String getLieu_evenement() {
        return lieu_evenement;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public String getType_de_sport() {
        return type_de_sport;
    }

    public String getType_evenement() {
        return type_evenement;
    }
    
    
}
