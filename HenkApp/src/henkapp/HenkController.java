/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package henkapp;

import java.net.URL;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
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
    
    /*////*/// Geef hier het aantal personen in: /*////*////*////*////*////*////*////*////*///
    /*////*////*////*////*////*////*////*////*////*////*////*////*////*////*////*////*////*///
    /*////*/  int aantal_personen = 10000;  /*////*////*////*////*////*////*////*////*////*///
    /*////*////*////*////*////*////*////*////*////*////*////*////*////*////*////*////*////*///
    // 1000000 duurt   4 seconden om te genereren maar wordt niet zichtbaar in de treeview binnen een normale tijd.
    //  100000 duurt 0.3 seconden om te genereren en  29 seconden om de treeview te maken/vullen.
    //   50000 duurt 0.2 seconden om te genereren en 8.3 seconden om de treeview te maken/vullen.
    //   25000 duurt 0.2 seconden om te genereren en   2 seconden om de treeview te maken/vullen.
    //   10000 duurt 0.1 seconden om te genereren en 0.3 seconden om de treeview te maken/vullen.
    //    5000 duurt 0.1 seconden om te genereren en 0.2 seconden om de treeview te maken/vullen.
    //    1000 duurt 0.0 seconden om te genereren en 0.0 seconden om de treeview te maken/vullen.
    
    public Set<Persoon> personen;
    public Set<Persoon> mensen;
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
        
        mensen = new TreeSet();
        
        ObservableSet<Persoon> obvSet = null;
        ///*
        mensen.add(new Persoon("Arie", "Amsterdam", "020"));
        mensen.add(new Persoon("Arie", "Amsterdam", "020"));
        mensen.add(new Persoon("Arie", "Amsterdam", "010"));
        mensen.add(new Persoon("AArie", "Amsterdam", "020"));
        mensen.add(new Persoon("ACrie", "Amsterdam", "020"));
        mensen.add(new Persoon("ABrie", "Amsterdam", "020"));
        //*/
        ///*
        long startTime = System.nanoTime();
        Random rand = new Random();
        List<String> plaatsen = new ArrayList<>(aantal_personen/10);
        for(int i = 0; i<aantal_personen/10;i++){
            plaatsen.add(UUID.randomUUID().toString());
        }
        List<Persoon> mensenArray = new ArrayList<>(aantal_personen);
        for(int i = 0; i<aantal_personen;i++){
            mensenArray.add(new Persoon(UUID.randomUUID().toString(),plaatsen.get((int) (Math.random() * aantal_personen/10)),UUID.randomUUID().toString()));
        }
        mensen.addAll(mensenArray);
        long endTime = System.nanoTime();
        
        System.out.println("Tijd genereren:" + ((endTime - startTime)*0.000000001));
        //*/
        
        
        Bindings.bindContent(mensen, obvSet);
        
        
        createTreeView();
        tvTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Persoon>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Persoon>> observable, TreeItem<Persoon> oldValue, TreeItem<Persoon> newValue) {
                try {
                    personen.clear();
                    if (newValue.getValue().getNaam().equals("Mensen")) {
                        personen.addAll(mensen);
                    } else if (newValue.getChildren().size() != 0) {
                        for (Persoon p : mensen) {
                            if (p.getPlaats().equals(newValue.getValue().getNaam())) {
                                personen.add(p);
                            }
                        }
                    } else {
                        personen.add(newValue.getValue());
                        System.out.print(newValue.getValue().getNaam());
                    }
                } catch (Exception e) {
                }
            }
        });
        tvTable.setEditable(true);

        naamCol = new TableColumn("Naam");
        naamCol.setMinWidth(170.0);
        plaatsCol = new TableColumn("Plaats");
        plaatsCol.setMinWidth(170.0);
        telefoonCol = new TableColumn("Telefoon");
        telefoonCol.setMinWidth(170.0);

        naamCol.setCellValueFactory(new PropertyValueFactory<Persoon, String>("naam"));
        plaatsCol.setCellValueFactory(new PropertyValueFactory<Persoon, String>("plaats"));
        telefoonCol.setCellValueFactory(new PropertyValueFactory<Persoon, String>("telefoon"));

        naamCol.setCellFactory(TextFieldTableCell.forTableColumn());
        naamCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Persoon, String>>() {
                    @Override
                    public void handle(CellEditEvent<Persoon, String> t) {
                        t.getRowValue().setNaam(t.getNewValue());
                        createTreeView();
                    }
                }
        );
        naamCol.setComparator((naam1, naam2) -> naam1.toString().compareTo(naam2.toString()));

        plaatsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        plaatsCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Persoon, String>>() {
                    @Override
                    public void handle(CellEditEvent<Persoon, String> t) {
                        t.getRowValue().setPlaats(t.getNewValue());
                        createTreeView();
                    }
                }
        );
        plaatsCol.setComparator((plts1, plts2) -> plts1.toString().compareTo(plts2.toString()));

        telefoonCol.setCellFactory(TextFieldTableCell.forTableColumn());
        telefoonCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Persoon, String>>() {
                    @Override
                    public void handle(CellEditEvent<Persoon, String> t) {
                        t.getRowValue().setTelefoon(t.getNewValue());
                        createTreeView();
                    }
                }
        );
        telefoonCol.setComparator((tel1, tel2) -> tel1.toString().compareTo(tel2.toString()));
        
        //tvTable.setItems(personen);
        tvTable.getColumns().addAll(naamCol, plaatsCol, telefoonCol);
    }

    public void btnAddClick(Event e) {
        personen.add(new Persoon(txtNaam.getText(), txtPlaats.getText(), txtTelefoonnummer.getText()));
        mensen.add(new Persoon(txtNaam.getText(), txtPlaats.getText(), txtTelefoonnummer.getText()));
        createTreeView();
    }

    private void createTreeView() {
        long startTime = System.nanoTime();
        root = new TreeItem<Persoon>(new Persoon("Mensen", "", ""));
        ArrayList<Persoon> plaatsen = new ArrayList<Persoon>();
        boolean found = false;
        for (Persoon p : mensen) {            // for in een for in een for
            TreeItem<Persoon> persLeaf = new TreeItem<Persoon>(p);
            found = false;
            for (Persoon plaats : plaatsen) {
                if (p.getPlaats().equals(plaats.getNaam())) {
                    for (TreeItem<Persoon> plaatsNode : root.getChildren()) {
                        if (plaatsNode.getValue().equals(plaats)) {
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
        long endTime = System.nanoTime();
        System.out.println("Tijd Treeview maken:" + ((endTime - startTime)*0.000000001));
    }
}
