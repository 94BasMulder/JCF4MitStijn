/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package henkapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;

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

    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void btnAddClick(Event e)
    {
    
    }
    
}
