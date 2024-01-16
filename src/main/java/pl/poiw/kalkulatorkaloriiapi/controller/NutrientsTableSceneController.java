package pl.poiw.kalkulatorkaloriiapi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import pl.poiw.kalkulatorkaloriiapi.HelloApplication;
import pl.poiw.kalkulatorkaloriiapi.model.apiproduct.Items;
import pl.poiw.kalkulatorkaloriiapi.model.Macro;
import pl.poiw.kalkulatorkaloriiapi.model.meal.BreakfastModelDB;
import pl.poiw.kalkulatorkaloriiapi.model.meal.SummaryDB;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NutrientsTableSceneController implements Initializable {

    private Parent root;
    @FXML
    private TableView<Macro> table;
    ObservableList<Macro> listMacro;

    @FXML
    public void switchToPreviousScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("fxml/fitMainScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

                float sumSodium = Float.parseFloat(SummaryDB.getSummaryItem().getSodium_mg().replace(",", "."));
                float sumSugar = Float.parseFloat(SummaryDB.getSummaryItem().getSugar_g().replace(",", "."));
                float sumCholesterol = Float.parseFloat(SummaryDB.getSummaryItem().getCholesterol_mg().replace(",", "."));
                float sumFiber = Float.parseFloat(SummaryDB.getSummaryItem().getFiber_g().replace(",", "."));
                float sumFat_saturated = Float.parseFloat(SummaryDB.getSummaryItem().getFat_saturated_g().replace(",", "."));
                float sumPotassium = Float.parseFloat(SummaryDB.getSummaryItem().getPotassium_mg().replace(",", "."));

        listMacro = FXCollections.observableArrayList();
//juz w gramach
        listMacro.add(new Macro("sodium", sumSodium));
        listMacro.add(new Macro("sugar", sumSugar));
        listMacro.add(new Macro("cholesterol", sumCholesterol ));
        listMacro.add(new Macro("fiber", sumFiber));
        listMacro.add(new Macro("fat_saturated", sumFat_saturated));
        listMacro.add(new Macro("potassium", sumPotassium));

        TableColumn<Macro, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Macro, Float> amountColumn = new TableColumn<>("Amount in g");

        table.getColumns().add(nameColumn);
        table.getColumns().add(amountColumn);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        table.setItems(listMacro);
    }
}
