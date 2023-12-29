package pl.poiw.kalkulatorkaloriiapi.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.poiw.kalkulatorkaloriiapi.HelloApplication;
import pl.poiw.kalkulatorkaloriiapi.model.Items;
import pl.poiw.kalkulatorkaloriiapi.model.ModelDB;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FitMainSceneController implements Initializable {

    @FXML
    private Label kcalLabel1;
   private Parent root;
   @FXML
    public void switchToPreviousScene(ActionEvent event) throws IOException {
       switchScene(event, "mainScene.fxml");
    }

    @FXML
    public void switchToFitSearchScene(ActionEvent event) throws IOException {
        switchScene(event, "fitSearchScene.fxml");
    }

    private void switchScene(ActionEvent event, String sceneName) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("fxml/" + sceneName)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       if(ModelDB.getItemsAsStringList() != null) {
           float sumCalories = 0;
           float sumProtein = 0;
           float sumFat = 0;
           float sumCarbohydrates = 0;
           //dodac strukture aby dodawac tylko zaznaczone produkty
           for(Items i : ModelDB.getObservableListItems()) {
               float calories = Float.parseFloat(i.getCalories());
               sumCalories += calories;
               float protein = Float.parseFloat(i.getProtein_g());
               sumProtein += protein;
               float fat = Float.parseFloat(i.getFat_total_g());
               sumFat += fat;
               float carbohydrates = Float.parseFloat(i.getCarbohydrates_total_g());
               sumCarbohydrates += carbohydrates;
           }
           kcalLabel1.setText("Kcal: " + sumCalories + " P: " + sumProtein
           + " F: " + sumFat + " C: " + sumCarbohydrates);
       }

    }
}
