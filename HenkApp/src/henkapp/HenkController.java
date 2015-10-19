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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

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
    public ObservableList<Persoon> mensen;
    private TableColumn naamCol;
    private TableColumn plaatsCol;
    private TableColumn telefoonCol;
    private TreeItem<Persoon> root = new TreeItem<Persoon>(new Persoon("Mensen", "", ""));

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mensen = observableArrayList();
        mensen.add(new Persoon("Henk", "Drunen", "0416"));
        mensen.add(new Persoon("Bas", "Drunen", "0416"));
        mensen.add(new Persoon("Marjon", "Temse", "0416"));
        mensen.add(new Persoon("Harry", "Best", "0416"));
        mensen.add(new Persoon("Joel", "Porvoo", "0416"));
        mensen.add(new Persoon("Meyke", "Kuick", "0416"));
        mensen.add(new Persoon("Harry", "Kuick", "0416"));
        
        createTreeView();
        
        
        tvTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Persoon>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Persoon>> observable, TreeItem<Persoon> oldValue, TreeItem<Persoon> newValue) {
                personen.clear();
                if(newValue.getValue().getNaam().equals("Mensen"))
                    personen.addAll(mensen);
                else if(newValue.getChildren()!= null)
                {
                    for (Persoon p : mensen)
                        if(p.getPlaats().equals(newValue.getValue().getNaam()))
                            personen.add(p);
                }
                else                
                    personen.add(new Persoon(newValue.getValue().getNaam(),newValue.getValue().getPlaats(),newValue.getValue().getTelefoon()));
            }});
        
        
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
        mensen.add(new Persoon(txtNaam.getText(), txtPlaats.getText(), txtTelefoonnummer.getText()));
        createTreeView();
    }
    
    private void createTreeView() {
        root = new TreeItem<Persoon>(new Persoon("Mensen", "", ""));
        ArrayList<Persoon> plaatsen = new ArrayList<Persoon>();
        boolean found = false;
        for (Persoon p : mensen) {
            TreeItem<Persoon> persLeaf = new TreeItem<Persoon>(p);
            found = false;
            for (Persoon plaats : plaatsen) {
                if (p.getPlaats().equals(plaats.getNaam())) {
                    for (TreeItem<Persoon> plaatsNode : root.getChildren()) {
                        if (plaatsNode.getValue().contentEquals(plaats)) {
                            plaatsNode.getChildren().add(persLeaf);
                            found = true;
                            break;
                        }
                        if (found) {
                            break;
                        }
                    }
                }
            }
            if (!found) {
                Persoon pers = new Persoon(persLeaf.getValue().getPlaats(), "", "");
                plaatsen.add(pers);
                TreeItem<Persoon> plaatsNode = new TreeItem<Persoon>(pers);
                root.getChildren().add(plaatsNode);
                plaatsNode.getChildren().add(persLeaf);
            }
        }
        tvTree.setRoot(root);
        root.setExpanded(true);
    }
}
