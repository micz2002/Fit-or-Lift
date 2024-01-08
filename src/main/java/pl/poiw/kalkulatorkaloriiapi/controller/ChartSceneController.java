package pl.poiw.kalkulatorkaloriiapi.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.poiw.kalkulatorkaloriiapi.HelloApplication;
import pl.poiw.kalkulatorkaloriiapi.model.Items;
import pl.poiw.kalkulatorkaloriiapi.model.ModelDB;

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

        if (ModelDB.getItemsAsStringList() != null) {
            float sumCarbohydrates = 0;
            float sumFat = 0;
            float sumCalories = 0;
            float sumProtein = 0;
            //dodac strukture aby dodawac tylko zaznaczone produkty
            for (Items i : ModelDB.getObservableListItems()) {
                float calories = Float.parseFloat(i.getCalories());
                sumCalories += calories;
                float protein = Float.parseFloat(i.getProtein_g());
                sumProtein += protein;
                float fat = Float.parseFloat(i.getFat_total_g());
                sumFat += fat;
                float carbohydrates = Float.parseFloat(i.getCarbohydrates_total_g());
                sumCarbohydrates += carbohydrates;
            }
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Fat", sumFat),
                            new PieChart.Data("Protein", sumProtein),
                            new PieChart.Data("Carbohydrates", sumCarbohydrates));

            pieChart.getData().addAll(pieChartData);
            pieChart.setStartAngle(90);

            valueLabel.setTextFill(Color.BLACK);
            valueLabel.setStyle("-fx-font: 12 arial;");

            for (final PieChart.Data data : pieChart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    System.out.println("pie chart click");
                    valueLabel.setTranslateX(event.getSceneX() - valueLabel.getLayoutX());
                    valueLabel.setTranslateY(event.getSceneY() - valueLabel.getLayoutY());
                    valueLabel.setText(String.format("%.2fg", data.getPieValue()));
                });
                data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    data.getNode().setCursor(Cursor.HAND);
                });
            }
        }
    }
}

