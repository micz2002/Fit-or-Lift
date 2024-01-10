package pl.poiw.kalkulatorkaloriiapi.controller;

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
import pl.poiw.kalkulatorkaloriiapi.model.meal.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FitMainSceneController implements Initializable {

    @FXML
    private Label kcalBreakfastLabel;

    @FXML
    private Label kcalBrunchLabel;

    @FXML
    private Label kcalDinnerLabel;

    @FXML
    private Label kcalLunchLabel;

    @FXML
    private Label kcalSnackLabel;
    private Parent root;

    @FXML
    public void switchToPreviousScene(ActionEvent event) throws IOException {
        switchScene(event, "mainScene.fxml");
    }

    @FXML
    public void switchToChartScene(ActionEvent event) throws IOException {
        switchScene(event, "chartScene.fxml");
    }

    //zrobic tak aby przekazywac info o tym jaki posilek jest wybrany np poprzez wyslanie info do labela na scenie nastepej
    //a potem odczytanie go i na tej podstawie uzywac odpowiednich struktur z ModelDB
    @FXML
    public void switchToBreakfastFitSearchScene(ActionEvent event) throws IOException {
        MealDB.setMeal(Meal.BREAKFAST);
        switchScene(event, "fitSearchScene.fxml");
    }

    @FXML
    public void switchToBrunchFitSearchScene(ActionEvent event) throws IOException {
        MealDB.setMeal(Meal.BRUNCH);
        switchScene(event, "fitSearchScene.fxml");
    }

    @FXML
    public void switchToLunchFitSearchScene(ActionEvent event) throws IOException {
        MealDB.setMeal(Meal.LUNCH);
        switchScene(event, "fitSearchScene.fxml");
    }

    @FXML
    public void switchToSnackFitSearchScene(ActionEvent event) throws IOException {
        MealDB.setMeal(Meal.SNACK);
        switchScene(event, "fitSearchScene.fxml");
    }

    @FXML
    public void switchToDinnerFitSearchScene(ActionEvent event) throws IOException {
        MealDB.setMeal(Meal.DINNER);
        switchScene(event, "fitSearchScene.fxml");
    }

    private void switchScene(ActionEvent event, String sceneName) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("fxml/" + sceneName)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void breakfastCountingProductsMacro() {
        if (BreakfastModelDB.getItemsAsStringList() != null) {
            float sumCalories = 0;
            float sumProtein = 0;
            float sumFat = 0;
            float sumCarbohydrates = 0;

            for (Items i : BreakfastModelDB.getObservableListItems()) {

                final boolean[] shouldBeCounted = new boolean[1];

                //dodawanie tylko zaznaczonych produktów
                BreakfastModelDB.getStringBooleanPropertyMap().forEach((keyItemAsString, valueBooleanProperty) -> {
                    if (keyItemAsString.equals(i.toString()) && valueBooleanProperty.getValue()) {
                        shouldBeCounted[0] = true;
                    }
                });

                if (shouldBeCounted[0]) {
                    float calories = Float.parseFloat(i.getCalories());
                    float protein = Float.parseFloat(i.getProtein_g());
                    float fat = Float.parseFloat(i.getFat_total_g());
                    float carbohydrates = Float.parseFloat(i.getCarbohydrates_total_g());
                    sumCalories += calories;
                    sumProtein += protein;
                    sumFat += fat;
                    sumCarbohydrates += carbohydrates;
                }
            }
            kcalBreakfastLabel.setText("Kcal: " + sumCalories + " P: " + sumProtein
                    + " F: " + sumFat + " C: " + sumCarbohydrates);
        }
    }

    private void brunchCountingProductsMacro() {
        if (BrunchModelDB.getItemsAsStringList() != null) {
            float sumCalories = 0;
            float sumProtein = 0;
            float sumFat = 0;
            float sumCarbohydrates = 0;

            for (Items i : BrunchModelDB.getObservableListItems()) {

                final boolean[] shouldBeCounted = new boolean[1];

                //dodawanie tylko zaznaczonych produktów
                BrunchModelDB.getStringBooleanPropertyMap().forEach((keyItemAsString, valueBooleanProperty) -> {
                    if (keyItemAsString.equals(i.toString()) && valueBooleanProperty.getValue()) {
                        shouldBeCounted[0] = true;
                    }
                });

                if (shouldBeCounted[0]) {
                    float calories = Float.parseFloat(i.getCalories());
                    float protein = Float.parseFloat(i.getProtein_g());
                    float fat = Float.parseFloat(i.getFat_total_g());
                    float carbohydrates = Float.parseFloat(i.getCarbohydrates_total_g());
                    sumCalories += calories;
                    sumProtein += protein;
                    sumFat += fat;
                    sumCarbohydrates += carbohydrates;
                }
            }
            kcalBrunchLabel.setText("Kcal: " + sumCalories + " P: " + sumProtein
                    + " F: " + sumFat + " C: " + sumCarbohydrates);
        }
    }

    private void lunchCountingProductsMacro() {
        if (LunchModelDB.getItemsAsStringList() != null) {
            float sumCalories = 0;
            float sumProtein = 0;
            float sumFat = 0;
            float sumCarbohydrates = 0;

            for (Items i : LunchModelDB.getObservableListItems()) {

                final boolean[] shouldBeCounted = new boolean[1];

                //dodawanie tylko zaznaczonych produktów
                LunchModelDB.getStringBooleanPropertyMap().forEach((keyItemAsString, valueBooleanProperty) -> {
                    if (keyItemAsString.equals(i.toString()) && valueBooleanProperty.getValue()) {
                        shouldBeCounted[0] = true;
                    }
                });

                if (shouldBeCounted[0]) {
                    float calories = Float.parseFloat(i.getCalories());
                    float protein = Float.parseFloat(i.getProtein_g());
                    float fat = Float.parseFloat(i.getFat_total_g());
                    float carbohydrates = Float.parseFloat(i.getCarbohydrates_total_g());
                    sumCalories += calories;
                    sumProtein += protein;
                    sumFat += fat;
                    sumCarbohydrates += carbohydrates;
                }
            }
            kcalLunchLabel.setText("Kcal: " + sumCalories + " P: " + sumProtein
                    + " F: " + sumFat + " C: " + sumCarbohydrates);
        }
    }

    private void snackCountingProductsMacro() {
        if (SnackModelDB.getItemsAsStringList() != null) {
            float sumCalories = 0;
            float sumProtein = 0;
            float sumFat = 0;
            float sumCarbohydrates = 0;

            for (Items i : SnackModelDB.getObservableListItems()) {

                final boolean[] shouldBeCounted = new boolean[1];

                //dodawanie tylko zaznaczonych produktów
                SnackModelDB.getStringBooleanPropertyMap().forEach((keyItemAsString, valueBooleanProperty) -> {
                    if (keyItemAsString.equals(i.toString()) && valueBooleanProperty.getValue()) {
                        shouldBeCounted[0] = true;
                    }
                });

                if (shouldBeCounted[0]) {
                    float calories = Float.parseFloat(i.getCalories());
                    float protein = Float.parseFloat(i.getProtein_g());
                    float fat = Float.parseFloat(i.getFat_total_g());
                    float carbohydrates = Float.parseFloat(i.getCarbohydrates_total_g());
                    sumCalories += calories;
                    sumProtein += protein;
                    sumFat += fat;
                    sumCarbohydrates += carbohydrates;
                }
            }
            kcalSnackLabel.setText("Kcal: " + sumCalories + " P: " + sumProtein
                    + " F: " + sumFat + " C: " + sumCarbohydrates);
        }
    }

    private void dinnerCountingProductsMacro() {
        if (DinnerModelDB.getItemsAsStringList() != null) {
            float sumCalories = 0;
            float sumProtein = 0;
            float sumFat = 0;
            float sumCarbohydrates = 0;

            for (Items i : DinnerModelDB.getObservableListItems()) {

                final boolean[] shouldBeCounted = new boolean[1];

                //dodawanie tylko zaznaczonych produktów
                DinnerModelDB.getStringBooleanPropertyMap().forEach((keyItemAsString, valueBooleanProperty) -> {
                    if (keyItemAsString.equals(i.toString()) && valueBooleanProperty.getValue()) {
                        shouldBeCounted[0] = true;
                    }
                });

                if (shouldBeCounted[0]) {
                    float calories = Float.parseFloat(i.getCalories());
                    float protein = Float.parseFloat(i.getProtein_g());
                    float fat = Float.parseFloat(i.getFat_total_g());
                    float carbohydrates = Float.parseFloat(i.getCarbohydrates_total_g());
                    sumCalories += calories;
                    sumProtein += protein;
                    sumFat += fat;
                    sumCarbohydrates += carbohydrates;
                }
            }
            kcalDinnerLabel.setText("Kcal: " + sumCalories + " P: " + sumProtein
                    + " F: " + sumFat + " C: " + sumCarbohydrates);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        breakfastCountingProductsMacro();
        brunchCountingProductsMacro();
        lunchCountingProductsMacro();
        snackCountingProductsMacro();
        dinnerCountingProductsMacro();
    }
}
