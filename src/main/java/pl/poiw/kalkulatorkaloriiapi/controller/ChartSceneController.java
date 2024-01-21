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
import javafx.stage.Stage;
import pl.poiw.kalkulatorkaloriiapi.HelloApplication;
import pl.poiw.kalkulatorkaloriiapi.model.meal.SummaryDB;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChartSceneController implements Initializable {
    private Parent root;
    @FXML
    private PieChart pieChart;

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

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Protein", Double.parseDouble(SummaryDB.getSummaryItem().getProtein_g().replace("," , "."))),
                            new PieChart.Data("Fat", Double.parseDouble(SummaryDB.getSummaryItem().getFat_total_g().replace("," , "."))),
                            new PieChart.Data("Carbohydrates", Double.parseDouble(SummaryDB.getSummaryItem().getCarbohydrates_total_g().replace("," , "."))));

            pieChart.getData().addAll(pieChartData);

            pieChartData.forEach(data ->
                        data.nameProperty().bind(
                                Bindings.concat(
                                        data.getName(), " amount: ", String.format("%.1fg", data.getPieValue())
                                )
                        )
            );
    }
}

