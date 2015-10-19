/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package henkapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Bas
 */
public class HenkController implements Initializable {
    
    
    @FXML Button btnAdd;
    @FXML TreeView tvTree;
    @FXML TableView tvTable;
    @FXML TextField txtNaam;
    @FXML TextField txtPlaats;
    @FXML TextField txtTelefoonnummer;
    
    public ObservableList<Persoon> personen;
    private TableColumn naamCol;
    private TableColumn plaatsCol;
    private TableColumn telefoonCol;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        personen = observableArrayList();
        naamCol = new TableColumn("Naam");
        naamCol.setMinWidth(tvTable.getWidth()/3);
        plaatsCol = new TableColumn("Plaats");
        plaatsCol.setMinWidth(tvTable.getWidth()/3);
        telefoonCol = new TableColumn("Telefoon");
        telefoonCol.setMinWidth(tvTable.getWidth()/3);
        
        tvTable.getColumns().addAll(naamCol,plaatsCol,telefoonCol);
        naamCol.setCellValueFactory(new PropertyValueFactory<Persoon,String>("naam"));
        plaatsCol.setCellValueFactory(new PropertyValueFactory<Persoon,String>("plaats"));
        telefoonCol.setCellValueFactory(new PropertyValueFactory<Persoon,String>("telefoon"));
    }    
    
    public void btnAddClick(Event e)
    {
        personen.add(new Persoon(txtNaam.getText(), txtPlaats.getText(), txtTelefoonnummer.getText()));
        update();
    }
    
    private void update(){
        
    }
}
