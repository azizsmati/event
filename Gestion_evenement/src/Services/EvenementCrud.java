/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entitties.Evenement.Evenement;
import Tools.Myconnexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ZIZOU
 */
public class EvenementCrud {
   
    public void addEvent(Evenement E) {
        try {
            String requete = "INSERT INTO EVENEMENT (nom_evenement,date_debut,date_fin,type_de_sport,type_evenement,lieu_evenement)" + " VALUES(?,?,?,?,?,?)";
            PreparedStatement pst= new Myconnexion().conn.prepareStatement(requete);
            pst.setString(1, E.getNom_evenement());
          // Date Datedebut=new java.sql.Date(E.getDate_debut());
            pst.setDate(2,new Date(E.getDate_debut().getTime()));
            // Date Datefin=new java.sql.Date(E.getDate_fin());
            pst.setDate(3, new Date(E.getDate_fin().getTime()));
            pst.setString(4, E.getType_de_sport());
            pst.setString(5, E.getType_evenement());
            pst.setString(6, E.getLieu_evenement());

            pst.executeUpdate();
            System.out.println("Evenement  ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       public ObservableList<Evenement> getEvenementList() {

        ObservableList<Evenement> EvenementList = FXCollections.observableArrayList();
        String requete = "SELECT * FROM Evenement";
        try {
            PreparedStatement pst
                    = new Myconnexion().conn.prepareStatement(requete);
            //Statement st;
            ResultSet rs;
            try {
                //System.out.println("AHAYYYAA!!!!");
                //st=conn.createStatement();
                //System.out.println("AHAYYYAA222!!!!");
                rs = pst.executeQuery(requete);
                Evenement e;

                while (rs.next()) {
              e = new Evenement(rs.getString("nom_evenement"), rs.getDate("date_debut"), rs.getDate("date_fin"), rs.getString("type_de_sport"), rs.getString("type_evenement"),rs.getString("lieu_evenement"));
                    EvenementList.add(e);
                }

            } catch (Exception ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        return EvenementList;
    }
 
}
