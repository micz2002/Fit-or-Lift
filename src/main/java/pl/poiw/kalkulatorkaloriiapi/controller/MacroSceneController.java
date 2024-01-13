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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import pl.poiw.kalkulatorkaloriiapi.HelloApplication;
import pl.poiw.kalkulatorkaloriiapi.model.Items;
import pl.poiw.kalkulatorkaloriiapi.model.Macro;
import pl.poiw.kalkulatorkaloriiapi.model.meal.BreakfastModelDB;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MacroSceneController implements Initializable {

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

        float sumSodium = 0; //mg
        float sumSugar = 0;

        float sumCholesterol = 0;   //mg
        float sumFiber = 0;
        float sumFat_saturated = 0;
        float sumPotassium = 0;  //mg

        for (Items i : BreakfastModelDB.getObservableListItems()) {
            final boolean[] shouldBeCounted = new boolean[1];

            //dodawanie tylko zaznaczonych produktów
            BreakfastModelDB.getStringBooleanPropertyMap().forEach((keyItemAsString, valueBooleanProperty) -> {
                if (keyItemAsString.equals(i.toString()) && valueBooleanProperty.getValue()) {
                    shouldBeCounted[0] = true;
                }
            });

            if (shouldBeCounted[0]) {
                float sodium = Float.parseFloat(i.getSodium_mg());
                float sugar = Float.parseFloat(i.getSugar_g());
                float cholesterol = Float.parseFloat(i.getCholesterol_mg());
                float fiber = Float.parseFloat(i.getFiber_g());
                float fat_saturated = Float.parseFloat(i.getFat_saturated_g());
                float potassium = Float.parseFloat(i.getPotassium_mg());

                sumSodium += sodium; //mg
                sumSugar += sugar;
                sumCholesterol += cholesterol; //mg
                sumFiber += fiber;
                sumFat_saturated += fat_saturated;
                sumPotassium += potassium; //mg
            }
        }
        listMacro = FXCollections.observableArrayList();
//juz w gramach
        listMacro.add(new Macro("sugar", sumSugar));
        listMacro.add(new Macro("sodium", sumSodium / 1000));
        listMacro.add(new Macro("cholesterol", sumCholesterol / 1000));
        listMacro.add(new Macro("fiber", sumFiber));
        listMacro.add(new Macro("fat_saturated", sumFat_saturated));
        listMacro.add(new Macro("potassium", sumPotassium / 1000));

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
