/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entitties.Evenement.Evenement;
import Services.EvenementCrud;
import Tools.Myconnexion;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ZIZOU
 */
public class EvenementController implements Initializable {

    
   private String Nom;
    @FXML
    private TextField nome;
    @FXML
    private TextField datede;
    @FXML
    private TextField datefe;
    @FXML
    private TextField types;
    @FXML
    private TextField typee;
    @FXML
    private TextField lieue;
    @FXML
    private Button ajoutere;
    @FXML
    private TableView<Evenement> tabev;
    EvenementCrud pcr=new  EvenementCrud();


    @FXML
    private TableColumn<Evenement, Date> datedev;
    @FXML
    private TableColumn<Evenement, String> lieuev;
    @FXML
    private TableColumn<Evenement, Date> datefev;
    @FXML
    private TableColumn<Evenement, String> typesev;
    @FXML
    private TableColumn<Evenement, String> typeev;
    @FXML
    private TableColumn<Evenement, String> nomev;
    @FXML
    private TextField searchev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEvenements();
        SearchEvent();
        // TODO
    }    

    @FXML
    private void ajouterevenement(ActionEvent event) {
        String rnome= nome.getText();
        String rdatede = datede.getText();
        String rdatefe = datefe.getText();
        String rtypes = types.getText();
        String rtypee = typee.getText();
        String rlieue = lieue.getText();
        Date rdatede1 = null;
        Date rdatefe1 = null;
        try {
             rdatede1=new SimpleDateFormat("dd/MM/yyyy").parse(rdatede);
            rdatefe1=new SimpleDateFormat("dd/MM/yyyy").parse(rdatefe);
        } catch (ParseException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
Evenement ev = new Evenement(rnome,rdatede1,rdatefe1,rtypes,rtypee,rlieue);
 EvenementCrud evc =new EvenementCrud();
 evc.addEvent(ev);
 showEvenements();

    }
    
public void showEvenements() {
        System.out.println("DEBUGG!!!!");
        ObservableList<Evenement> list =pcr.getEvenementList();
        //ObservableList<Evenement> list = FXCollections.observableList(pcd.getEvenementList());
        nomev.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom_evenement"));
        datedev.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_debut"));
        datefev.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_fin"));

        typesev.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type_de_sport"));
        typeev.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type_evenement"));
        lieuev.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieu_evenement"));

        tabev.setItems(list);
      
    }

    @FXML
    private void SetValue(javafx.scene.input.MouseEvent event) {
            Evenement selected = tabev.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nome.setText(selected.getNom_evenement());
            datede.setText(selected.getDate_debut().toString());
            datefe.setText(selected.getDate_debut().toString());           
            types.setText(selected.getType_de_sport());
            typee.setText(selected.getType_evenement());
            lieue.setText(selected.getLieu_evenement());


            Nom = nome.getText();
        }
    }

    @FXML
    private void SupprimerEevenement(ActionEvent event) {
        try {
                         System.out.println(Nom);

             String requete = "DELETE FROM EVENEMENT WHERE nom_evenement= '"+Nom+"' ";
            PreparedStatement pst
                    = new Myconnexion().conn.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Evenement supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
          showEvenements();
    }

    @FXML
    private void ModifierEvenement(ActionEvent event) {
         try {
            String requete = "UPDATE Evenement SET nom_evenement=?, date_debut=?, date_fin=?, type_de_sport=?, type_evenement=?, lieu_evenement=?  WHERE nom_evenement='"+Nom+"'";
            PreparedStatement pst
                    = new Myconnexion().conn.prepareStatement(requete);
            pst.setString(1, nome.getText());
            pst.setString(2, datede.getText());
             pst.setString(3, datefe.getText() );
            pst.setString(4, types.getText());
             pst.setString(5, typee.getText());
             pst.setString(6, lieue.getText());

                    
            pst.executeUpdate();
            System.out.println("Evenement modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
          showEvenements();
    }

    @FXML
    private void sortEvents(ActionEvent event) {
         ObservableList<Evenement> list = FXCollections.observableArrayList();
        try {
            String requete= "SELECT nom_evenement,date_debut,date_fin,type_de_sport,type_evenement,lieu_evenement FROM Evenement ORDER BY nom_evenement desc    ";

PreparedStatement pst= new Myconnexion().conn.prepareStatement(requete);
ResultSet rs = pst.executeQuery();
          
            while(rs.next()){
             list.add(new Evenement (rs.getString(1),rs.getDate(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getString(6)));            
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        nomev.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom_evenement"));
        datedev.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_debut"));
        datefev.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_fin"));

        typesev.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type_de_sport"));
        typeev.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type_evenement"));
        lieuev.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieu_evenement"));

        tabev.setItems(list);
    }
    private void SearchEvent() { 
ObservableList<Evenement> list =pcr.getEvenementList();
         // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Evenement> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchev.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Evenement -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Evenement.getNom_evenement().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches  name.
                } 
                                //else if (Evenement.getCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //return true; // Filter matches categorie.
                //}
                //else if (String.valueOf(Evenement.getPrix()).indexOf(lowerCaseFilter)!=-1)
                    // return true;
                     else
                         return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        //       Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tabev.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tabev.setItems(sortedData);
    }


       
}
