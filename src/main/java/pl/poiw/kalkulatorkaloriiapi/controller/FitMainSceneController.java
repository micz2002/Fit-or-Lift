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
import pl.poiw.kalkulatorkaloriiapi.model.apiproduct.Items;
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

    @FXML
    private Label summaryLabel;

    private Items breakfastItem;
    private Items brunchItem;
    private Items lunchItem;
    private Items snackItem;
    private Items dinnerItem;

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

    @FXML
    public void switchBreakfastToMacroScene(ActionEvent event) throws IOException {
        switchScene(event, "macroTableScene.fxml");
        MealDB.setMeal(Meal.BREAKFAST);
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

            breakfastItem = new Items();
            breakfastItem.setCalories(String.valueOf(sumCalories));
            breakfastItem.setProtein_g(String.valueOf(sumProtein));
            breakfastItem.setFat_total_g(String.valueOf(sumFat));
            breakfastItem.setCarbohydrates_total_g(String.valueOf(sumCarbohydrates));

            setKcalMealLabel(sumCalories, sumProtein, sumFat, sumCarbohydrates, kcalBreakfastLabel);
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

            brunchItem = new Items();
            brunchItem.setCalories(String.valueOf(sumCalories));
            brunchItem.setProtein_g(String.valueOf(sumProtein));
            brunchItem.setFat_total_g(String.valueOf(sumFat));
            brunchItem.setCarbohydrates_total_g(String.valueOf(sumCarbohydrates));

            setKcalMealLabel(sumCalories, sumProtein, sumFat, sumCarbohydrates, kcalBrunchLabel);
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

            lunchItem = new Items();
            //wrzucic do metody
            lunchItem.setCalories(String.valueOf(sumCalories));
            lunchItem.setProtein_g(String.valueOf(sumProtein));
            lunchItem.setFat_total_g(String.valueOf(sumFat));
            lunchItem.setCarbohydrates_total_g(String.valueOf(sumCarbohydrates));

            setKcalMealLabel(sumCalories, sumProtein, sumFat, sumCarbohydrates, kcalLunchLabel);
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

            snackItem = new Items();
            snackItem.setCalories(String.valueOf(sumCalories));
            snackItem.setProtein_g(String.valueOf(sumProtein));
            snackItem.setFat_total_g(String.valueOf(sumFat));
            snackItem.setCarbohydrates_total_g(String.valueOf(sumCarbohydrates));

            setKcalMealLabel(sumCalories, sumProtein, sumFat, sumCarbohydrates, kcalSnackLabel);
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

            dinnerItem = new Items();
            dinnerItem.setCalories(String.valueOf(sumCalories));
            dinnerItem.setProtein_g(String.valueOf(sumProtein));
            dinnerItem.setFat_total_g(String.valueOf(sumFat));
            dinnerItem.setCarbohydrates_total_g(String.valueOf(sumCarbohydrates));
//            Items summaryItem = new Items();
//            summaryItem.setCalories(String.valueOf(String.format("%.1f", sumCalories)));
//            summaryItem.setProtein_g(String.valueOf(String.format("%.1f", sumProtein)));
//            summaryItem.setFat_total_g(String.valueOf(String.format("%.1f", sumFat)));
//            summaryItem.setCarbohydrates_total_g(String.valueOf(String.format("%.1f", sumCarbohydrates)));
//            SummaryDB.setSummaryItem(summaryItem);

            setKcalMealLabel(sumCalories, sumProtein, sumFat, sumCarbohydrates, kcalDinnerLabel);
        }
    }

    private void setKcalMealLabel(float sumCalories, float sumProtein, float sumFat, float sumCarbohydrates, Label kcalMealLabel) {
        kcalMealLabel.setText("Kcal: " + String.format("%.0fg", sumCalories) + " P: " + String.format("%.0fg", sumProtein)
                + " F: " + String.format("%.0fg", sumFat) + " C: " + String.format("%.0fg", sumCarbohydrates));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        breakfastCountingProductsMacro();
        brunchCountingProductsMacro();
        lunchCountingProductsMacro();
        snackCountingProductsMacro();
        dinnerCountingProductsMacro();

        float breakfastCalories = 0.0f, breakfastProtein = 0.0f, breakfastFat = 0.0f, breakfastCarbohydrates = 0.0f,
                brunchCalories = 0.0f, brunchProtein = 0.0f, brunchFat = 0.0f, brunchCarbohydrates = 0.0f,
                lunchCalories = 0.0f, lunchProtein = 0.0f, lunchFat = 0.0f, lunchCarbohydrates = 0.0f,
                snackCalories = 0.0f, snackProtein = 0.0f, snackFat = 0.0f, snackCarbohydrates = 0.0f,
                dinnerCalories = 0.0f, dinnerProtein = 0.0f, dinnerFat = 0.0f, dinnerCarbohydrates = 0.0f;

        //zsumowanie makro z posilkow do calosciowej dziennej ilosci
        if (breakfastItem != null) {
            breakfastCalories = Float.parseFloat(breakfastItem.getCalories());
            breakfastProtein = Float.parseFloat(breakfastItem.getProtein_g());
            breakfastFat = Float.parseFloat(breakfastItem.getFat_total_g());
            breakfastCarbohydrates = Float.parseFloat(breakfastItem.getCarbohydrates_total_g());
        }

        if (brunchItem != null) {
            brunchCalories = Float.parseFloat(brunchItem.getCalories());
            brunchProtein = Float.parseFloat(brunchItem.getProtein_g());
            brunchFat = Float.parseFloat(brunchItem.getFat_total_g());
            brunchCarbohydrates = Float.parseFloat(brunchItem.getCarbohydrates_total_g());
        }

        if (lunchItem != null) {
            lunchCalories = Float.parseFloat(lunchItem.getCalories());
            lunchProtein = Float.parseFloat(lunchItem.getProtein_g());
            lunchFat = Float.parseFloat(lunchItem.getFat_total_g());
            lunchCarbohydrates = Float.parseFloat(lunchItem.getCarbohydrates_total_g());
        }

        if (snackItem != null) {
            snackCalories = Float.parseFloat(snackItem.getCalories());
            snackProtein = Float.parseFloat(snackItem.getProtein_g());
            snackFat = Float.parseFloat(snackItem.getFat_total_g());
            snackCarbohydrates = Float.parseFloat(snackItem.getCarbohydrates_total_g());
        }

        if (dinnerItem != null) {
            dinnerCalories = Float.parseFloat(dinnerItem.getCalories());
            dinnerProtein = Float.parseFloat(dinnerItem.getProtein_g());
            dinnerFat = Float.parseFloat(dinnerItem.getFat_total_g());
            dinnerCarbohydrates = Float.parseFloat(dinnerItem.getCarbohydrates_total_g());
        }

        float summarizedCalories = breakfastCalories + brunchCalories + lunchCalories
                + snackCalories + dinnerCalories;
        SummaryDB.getSummaryItem().setCalories(String.valueOf(String.format("%.1f", summarizedCalories)));

        float summarizedProtein = breakfastProtein + brunchProtein + lunchProtein
                + snackProtein + dinnerProtein;
        SummaryDB.getSummaryItem().setProtein_g(String.valueOf(String.format("%.1f", summarizedProtein)));

        float summarizedFat = breakfastFat + brunchFat + lunchFat
                + snackFat + dinnerFat;
        SummaryDB.getSummaryItem().setFat_total_g(String.valueOf(String.format("%.1f", summarizedFat)));

        float summarizedCarbohydrates = breakfastCarbohydrates + brunchCarbohydrates
                + lunchCarbohydrates + snackCarbohydrates + dinnerCarbohydrates;
        SummaryDB.getSummaryItem().setCarbohydrates_total_g(String.valueOf(String.format("%.1f", summarizedCarbohydrates)));

        String caloriesTotal = SummaryDB.getSummaryItem().getCalories();
        String proteinTotal = SummaryDB.getSummaryItem().getProtein_g();
        String fatTotal = SummaryDB.getSummaryItem().getFat_total_g();
        String carbohydratesTotal = SummaryDB.getSummaryItem().getCarbohydrates_total_g();
        summaryLabel.setText("Kcal: " + caloriesTotal + " P: " + proteinTotal
                + " F: " + fatTotal + " C: " + carbohydratesTotal);

        summaryLabel.setStyle("-fx-font-weight: 600;");


    }
}
