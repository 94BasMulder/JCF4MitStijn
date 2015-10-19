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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author Bas
 */
public class HenkController implements Initializable {

    @FXML
    Button btnAdd;
    @FXML
    TreeView tvTree;
    @FXML
    TableView tvTable;
    @FXML
    TextField txtNaam;
    @FXML
    TextField txtPlaats;
    @FXML
    TextField txtTelefoonnummer;

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
        
        tvTable.setEditable(true);
        
        personen = observableArrayList();
        naamCol = new TableColumn("Naam");
        naamCol.setMinWidth(111.1);
        plaatsCol = new TableColumn("Plaats");
        plaatsCol.setMinWidth(111.1);
        telefoonCol = new TableColumn("Telefoon");
        telefoonCol.setMinWidth(111.1);

        naamCol.setCellValueFactory(new PropertyValueFactory<Persoon, String>("naam"));
        plaatsCol.setCellValueFactory(new PropertyValueFactory<Persoon, String>("plaats"));
        telefoonCol.setCellValueFactory(new PropertyValueFactory<Persoon, String>("telefoon"));

        naamCol.setCellFactory(TextFieldTableCell.forTableColumn());
        naamCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Persoon, String>>() {
                    @Override
                    public void handle(CellEditEvent<Persoon, String> t) {
                        ((Persoon) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNaam(t.getNewValue());
                    }
                }
        );
        
        plaatsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        plaatsCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Persoon, String>>() {
                    @Override
                    public void handle(CellEditEvent<Persoon, String> t) {
                        ((Persoon) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setPlaats(t.getNewValue());
                    }
                }
        );
        
        telefoonCol.setCellFactory(TextFieldTableCell.forTableColumn());
        telefoonCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Persoon, String>>() {
                    @Override
                    public void handle(CellEditEvent<Persoon, String> t) {
                        ((Persoon) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTelefoon(t.getNewValue());
                    }
                }
        );

        tvTable.setItems(personen);
        tvTable.getColumns().addAll(naamCol, plaatsCol, telefoonCol);
    }

    public void btnAddClick(Event e) {
        personen.add(new Persoon(txtNaam.getText(), txtPlaats.getText(), txtTelefoonnummer.getText()));
        System.out.println(personen.toString());
    }
}
