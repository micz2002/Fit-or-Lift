package pl.poiw.kalkulatorkaloriiapi.controller;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.poiw.kalkulatorkaloriiapi.HelloApplication;
import pl.poiw.kalkulatorkaloriiapi.model.Items;
import pl.poiw.kalkulatorkaloriiapi.model.meal.BreakfastModelDB;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChartSceneController implements Initializable {
    private Parent root;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label valueLabel;

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

        if (BreakfastModelDB.getItemsAsStringList() != null) {
            float sumCarbohydrates = 0;
            float sumFat = 0;
            float sumProtein = 0;
            //dodac strukture aby dodawac tylko zaznaczone produkty
            for (Items i : BreakfastModelDB.getObservableListItems()) {
                final boolean[] shouldBeCounted = new boolean[1];

                //dodawanie tylko zaznaczonych produktów
                BreakfastModelDB.getStringBooleanPropertyMap().forEach((keyItemAsString, valueBooleanProperty) -> {
                    if (keyItemAsString.equals(i.toString()) && valueBooleanProperty.getValue()) {
                        shouldBeCounted[0] = true;
                    }
                });

                if (shouldBeCounted[0]) {
                    float protein = Float.parseFloat(i.getProtein_g());
                    float fat = Float.parseFloat(i.getFat_total_g());
                    float carbohydrates = Float.parseFloat(i.getCarbohydrates_total_g());
                    sumProtein += protein;
                    sumFat += fat;
                    sumCarbohydrates += carbohydrates;
                }
            }
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Fat", sumFat),
                            new PieChart.Data("Protein", sumProtein),
                            new PieChart.Data("Carbohydrates", sumCarbohydrates));

            pieChart.getData().addAll(pieChartData);


//            valueLabel.setTextFill(Color.BLACK);
//            valueLabel.setStyle("-fx-font: 12 arial;");

//            for (final PieChart.Data data : pieChart.getData()) {
//                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//                    System.out.println("pie chart click");
//                    valueLabel.setTranslateX(event.getSceneX() - valueLabel.getLayoutX());
//                    valueLabel.setTranslateY(event.getSceneY() - valueLabel.getLayoutY());
//                    valueLabel.setText(String.format("%.2fg", data.getPieValue()));
//                });
//                data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
//                    data.getNode().setCursor(Cursor.HAND);
//                });
//            }
            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName(), " amount: ", String.format("%.2fg", data.getPieValue())
                            )
                    )
            );
        }
    }
}

