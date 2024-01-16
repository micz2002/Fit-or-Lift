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

            //mikroelementy i inne
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
                    sumCalories += Float.parseFloat(i.getCalories());
                    sumProtein += Float.parseFloat(i.getProtein_g());
                    sumFat += Float.parseFloat(i.getFat_total_g());
                    sumCarbohydrates += Float.parseFloat(i.getCarbohydrates_total_g());
                    //pozostale mikro i inne
                    sumSodium += Float.parseFloat(i.getSodium_mg()); //mg
                    sumSugar += Float.parseFloat(i.getSugar_g());
                    sumCholesterol += Float.parseFloat(i.getCholesterol_mg());   //mg
                    sumFiber += Float.parseFloat(i.getFiber_g());
                    sumFat_saturated += Float.parseFloat(i.getFat_saturated_g());
                    sumPotassium += Float.parseFloat(i.getPotassium_mg());  //mg
                }
            }

            breakfastItem = new Items();
            setSummarizedMealNutrients(breakfastItem, sumCalories, sumProtein, sumFat, sumCarbohydrates, sumSodium, sumSugar, sumCholesterol,
                    sumFiber, sumFat_saturated, sumPotassium);

            setKcalMealLabel(sumCalories, sumProtein, sumFat, sumCarbohydrates, kcalBreakfastLabel);
        }
    }

    private void brunchCountingProductsMacro() {
        if (BrunchModelDB.getItemsAsStringList() != null) {
            float sumCalories = 0;
            float sumProtein = 0;
            float sumFat = 0;
            float sumCarbohydrates = 0;

            //mikroelementy i inne
            float sumSodium = 0; //mg
            float sumSugar = 0;
            float sumCholesterol = 0;   //mg
            float sumFiber = 0;
            float sumFat_saturated = 0;
            float sumPotassium = 0;  //mg


            for (Items i : BrunchModelDB.getObservableListItems()) {

                final boolean[] shouldBeCounted = new boolean[1];

                //dodawanie tylko zaznaczonych produktów
                BrunchModelDB.getStringBooleanPropertyMap().forEach((keyItemAsString, valueBooleanProperty) -> {
                    if (keyItemAsString.equals(i.toString()) && valueBooleanProperty.getValue()) {
                        shouldBeCounted[0] = true;
                    }
                });

                if (shouldBeCounted[0]) {
                    sumCalories += Float.parseFloat(i.getCalories());
                    sumProtein += Float.parseFloat(i.getProtein_g());
                    sumFat += Float.parseFloat(i.getFat_total_g());
                    sumCarbohydrates += Float.parseFloat(i.getCarbohydrates_total_g());
                    //pozostale mikro i inne
                    sumSodium += Float.parseFloat(i.getSodium_mg()); //mg
                    sumSugar += Float.parseFloat(i.getSugar_g());
                    sumCholesterol += Float.parseFloat(i.getCholesterol_mg());   //mg
                    sumFiber += Float.parseFloat(i.getFiber_g());
                    sumFat_saturated += Float.parseFloat(i.getFat_saturated_g());
                    sumPotassium += Float.parseFloat(i.getPotassium_mg());  //mg
                }
            }

            brunchItem = new Items();
            setSummarizedMealNutrients(brunchItem, sumCalories, sumProtein, sumFat, sumCarbohydrates, sumSodium, sumSugar, sumCholesterol,
                    sumFiber, sumFat_saturated, sumPotassium);

            setKcalMealLabel(sumCalories, sumProtein, sumFat, sumCarbohydrates, kcalBrunchLabel);
        }
    }

    private void lunchCountingProductsMacro() {
        if (LunchModelDB.getItemsAsStringList() != null) {
            float sumCalories = 0;
            float sumProtein = 0;
            float sumFat = 0;
            float sumCarbohydrates = 0;

            //mikroelementy i inne
            float sumSodium = 0; //mg
            float sumSugar = 0;
            float sumCholesterol = 0;   //mg
            float sumFiber = 0;
            float sumFat_saturated = 0;
            float sumPotassium = 0;  //mg

            for (Items i : LunchModelDB.getObservableListItems()) {

                final boolean[] shouldBeCounted = new boolean[1];

                //dodawanie tylko zaznaczonych produktów
                LunchModelDB.getStringBooleanPropertyMap().forEach((keyItemAsString, valueBooleanProperty) -> {
                    if (keyItemAsString.equals(i.toString()) && valueBooleanProperty.getValue()) {
                        shouldBeCounted[0] = true;
                    }
                });

                if (shouldBeCounted[0]) {
                    sumCalories += Float.parseFloat(i.getCalories());
                    sumProtein += Float.parseFloat(i.getProtein_g());
                    sumFat += Float.parseFloat(i.getFat_total_g());
                    sumCarbohydrates += Float.parseFloat(i.getCarbohydrates_total_g());
                    //pozostale mikro i inne
                    sumSodium += Float.parseFloat(i.getSodium_mg()); //mg
                    sumSugar += Float.parseFloat(i.getSugar_g());
                    sumCholesterol += Float.parseFloat(i.getCholesterol_mg());   //mg
                    sumFiber += Float.parseFloat(i.getFiber_g());
                    sumFat_saturated += Float.parseFloat(i.getFat_saturated_g());
                    sumPotassium += Float.parseFloat(i.getPotassium_mg());  //mg
                }
            }

            lunchItem = new Items();
            setSummarizedMealNutrients(lunchItem, sumCalories, sumProtein, sumFat, sumCarbohydrates, sumSodium, sumSugar, sumCholesterol,
                    sumFiber, sumFat_saturated, sumPotassium);

            setKcalMealLabel(sumCalories, sumProtein, sumFat, sumCarbohydrates, kcalLunchLabel);
        }
    }

    private void snackCountingProductsMacro() {
        if (SnackModelDB.getItemsAsStringList() != null) {
            float sumCalories = 0;
            float sumProtein = 0;
            float sumFat = 0;
            float sumCarbohydrates = 0;

            //mikroelementy i inne
            float sumSodium = 0; //mg
            float sumSugar = 0;
            float sumCholesterol = 0;   //mg
            float sumFiber = 0;
            float sumFat_saturated = 0;
            float sumPotassium = 0;  //mg

            for (Items i : SnackModelDB.getObservableListItems()) {

                final boolean[] shouldBeCounted = new boolean[1];

                //dodawanie tylko zaznaczonych produktów
                SnackModelDB.getStringBooleanPropertyMap().forEach((keyItemAsString, valueBooleanProperty) -> {
                    if (keyItemAsString.equals(i.toString()) && valueBooleanProperty.getValue()) {
                        shouldBeCounted[0] = true;
                    }
                });

                if (shouldBeCounted[0]) {
                    sumCalories += Float.parseFloat(i.getCalories());
                    sumProtein += Float.parseFloat(i.getProtein_g());
                    sumFat += Float.parseFloat(i.getFat_total_g());
                    sumCarbohydrates += Float.parseFloat(i.getCarbohydrates_total_g());
                    //pozostale mikro i inne
                    sumSodium += Float.parseFloat(i.getSodium_mg()); //mg
                    sumSugar += Float.parseFloat(i.getSugar_g());
                    sumCholesterol += Float.parseFloat(i.getCholesterol_mg());   //mg
                    sumFiber += Float.parseFloat(i.getFiber_g());
                    sumFat_saturated += Float.parseFloat(i.getFat_saturated_g());
                    sumPotassium += Float.parseFloat(i.getPotassium_mg());  //mg
                }
            }

            snackItem = new Items();
            setSummarizedMealNutrients(snackItem, sumCalories, sumProtein, sumFat, sumCarbohydrates, sumSodium, sumSugar, sumCholesterol,
                    sumFiber, sumFat_saturated, sumPotassium);

            setKcalMealLabel(sumCalories, sumProtein, sumFat, sumCarbohydrates, kcalSnackLabel);
        }
    }

    private void dinnerCountingProductsMacro() {
        if (DinnerModelDB.getItemsAsStringList() != null) {
            float sumCalories = 0;
            float sumProtein = 0;
            float sumFat = 0;
            float sumCarbohydrates = 0;

            //mikroelementy i inne
            float sumSodium = 0; //mg
            float sumSugar = 0;
            float sumCholesterol = 0;   //mg
            float sumFiber = 0;
            float sumFat_saturated = 0;
            float sumPotassium = 0;  //mg

            for (Items i : DinnerModelDB.getObservableListItems()) {

                final boolean[] shouldBeCounted = new boolean[1];

                //dodawanie tylko zaznaczonych produktów
                DinnerModelDB.getStringBooleanPropertyMap().forEach((keyItemAsString, valueBooleanProperty) -> {
                    if (keyItemAsString.equals(i.toString()) && valueBooleanProperty.getValue()) {
                        shouldBeCounted[0] = true;
                    }
                });

                if (shouldBeCounted[0]) {
                    sumCalories += Float.parseFloat(i.getCalories());
                    sumProtein += Float.parseFloat(i.getProtein_g());
                    sumFat += Float.parseFloat(i.getFat_total_g());
                    sumCarbohydrates += Float.parseFloat(i.getCarbohydrates_total_g());
                    //pozostale mikro i inne
                    sumSodium += Float.parseFloat(i.getSodium_mg()); //mg
                    sumSugar += Float.parseFloat(i.getSugar_g());
                    sumCholesterol += Float.parseFloat(i.getCholesterol_mg());   //mg
                    sumFiber += Float.parseFloat(i.getFiber_g());
                    sumFat_saturated += Float.parseFloat(i.getFat_saturated_g());
                    sumPotassium += Float.parseFloat(i.getPotassium_mg());  //mg
                }
            }

            dinnerItem = new Items();
            setSummarizedMealNutrients(dinnerItem, sumCalories, sumProtein, sumFat, sumCarbohydrates, sumSodium, sumSugar, sumCholesterol,
                    sumFiber, sumFat_saturated, sumPotassium);

            setKcalMealLabel(sumCalories, sumProtein, sumFat, sumCarbohydrates, kcalDinnerLabel);
        }
    }

    private void setSummarizedMealNutrients(Items item, float sumCalories, float sumProtein, float sumFat, float sumCarbohydrates,
                                            float sumSodium, float sumSugar, float sumCholesterol, float sumFiber, float sumFat_saturated,
                                            float sumPotassium) {
        item.setCalories(String.valueOf(sumCalories));
        item.setProtein_g(String.valueOf(sumProtein));
        item.setFat_total_g(String.valueOf(sumFat));
        item.setCarbohydrates_total_g(String.valueOf(sumCarbohydrates));
        //pozostale mikro i inne
        item.setSodium_mg(String.valueOf(sumSodium));
        item.setSugar_g(String.valueOf(sumSugar));
        item.setCholesterol_mg(String.valueOf(sumCholesterol));
        item.setFiber_g(String.valueOf(sumFiber));
        item.setFat_saturated_g(String.valueOf(sumFat_saturated));
        item.setPotassium_mg(String.valueOf(sumPotassium));
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

        float breakfastCalories = 0, breakfastProtein = 0, breakfastFat = 0, breakfastCarbohydrates = 0,
                brunchCalories = 0, brunchProtein = 0, brunchFat = 0, brunchCarbohydrates = 0,
                lunchCalories = 0, lunchProtein = 0, lunchFat = 0, lunchCarbohydrates = 0,
                snackCalories = 0, snackProtein = 0, snackFat = 0, snackCarbohydrates = 0,
                dinnerCalories = 0, dinnerProtein = 0, dinnerFat = 0, dinnerCarbohydrates = 0;
        //mikroskladniki i inne
        float breakfastSodium = 0 /*mg*/, breakfastSugar = 0, breakfastCholesterol = 0 /*mg*/, breakfastFiber = 0, breakfastFat_saturated = 0, breakfastPotasium = 0 /*mg*/,
                brunchSodium = 0 /*mg*/, brunchSugar = 0, brunchCholesterol = 0 /*mg*/, brunchFiber = 0, brunchFat_saturated = 0, brunchPotasium = 0 /*mg*/,
                lunchSodium = 0 /*mg*/, lunchSugar = 0, lunchCholesterol = 0 /*mg*/, lunchFiber = 0, lunchFat_saturated = 0, lunchPotasium = 0 /*mg*/,
                snackSodium = 0 /*mg*/, snackSugar = 0, snackCholesterol = 0 /*mg*/, snackFiber = 0, snackFat_saturated = 0, snackPotasium = 0 /*mg*/,
                dinnerSodium = 0 /*mg*/, dinnerSugar = 0, dinnerCholesterol = 0 /*mg*/, dinnerFiber = 0, dinnerFat_saturated = 0, dinnerPotasium = 0 /*mg*/;

        //zsumowanie makro i mikro z posilkow do calosciowej dziennej ilosci
        if (breakfastItem != null) {
            breakfastCalories = Float.parseFloat(breakfastItem.getCalories());
            breakfastProtein = Float.parseFloat(breakfastItem.getProtein_g());
            breakfastFat = Float.parseFloat(breakfastItem.getFat_total_g());
            breakfastCarbohydrates = Float.parseFloat(breakfastItem.getCarbohydrates_total_g());

            //pozostale mikro i inne
            breakfastSodium = Float.parseFloat(breakfastItem.getSodium_mg());
            breakfastSugar = Float.parseFloat(breakfastItem.getSugar_g());
            breakfastCholesterol = Float.parseFloat(breakfastItem.getCholesterol_mg());
            breakfastFiber = Float.parseFloat(breakfastItem.getFiber_g());
            breakfastFat_saturated = Float.parseFloat(breakfastItem.getFat_saturated_g());
            breakfastPotasium = Float.parseFloat(breakfastItem.getPotassium_mg());

        }

        if (brunchItem != null) {
            brunchCalories = Float.parseFloat(brunchItem.getCalories());
            brunchProtein = Float.parseFloat(brunchItem.getProtein_g());
            brunchFat = Float.parseFloat(brunchItem.getFat_total_g());
            brunchCarbohydrates = Float.parseFloat(brunchItem.getCarbohydrates_total_g());

            //pozostale mikro i inne
            brunchSodium = Float.parseFloat(brunchItem.getSodium_mg());
            brunchSugar = Float.parseFloat(brunchItem.getSugar_g());
            brunchCholesterol = Float.parseFloat(brunchItem.getCholesterol_mg());
            brunchFiber = Float.parseFloat(brunchItem.getFiber_g());
            brunchFat_saturated = Float.parseFloat(brunchItem.getFat_saturated_g());
            brunchPotasium = Float.parseFloat(brunchItem.getPotassium_mg());
        }

        if (lunchItem != null) {
            lunchCalories = Float.parseFloat(lunchItem.getCalories());
            lunchProtein = Float.parseFloat(lunchItem.getProtein_g());
            lunchFat = Float.parseFloat(lunchItem.getFat_total_g());
            lunchCarbohydrates = Float.parseFloat(lunchItem.getCarbohydrates_total_g());

            //pozostale mikro i inne
            lunchSodium = Float.parseFloat(lunchItem.getSodium_mg());
            lunchSugar = Float.parseFloat(lunchItem.getSugar_g());
            lunchCholesterol = Float.parseFloat(lunchItem.getCholesterol_mg());
            lunchFiber = Float.parseFloat(lunchItem.getFiber_g());
            lunchFat_saturated = Float.parseFloat(lunchItem.getFat_saturated_g());
            lunchPotasium = Float.parseFloat(lunchItem.getPotassium_mg());
        }

        if (snackItem != null) {
            snackCalories = Float.parseFloat(snackItem.getCalories());
            snackProtein = Float.parseFloat(snackItem.getProtein_g());
            snackFat = Float.parseFloat(snackItem.getFat_total_g());
            snackCarbohydrates = Float.parseFloat(snackItem.getCarbohydrates_total_g());

            //pozostale mikro i inne
            snackSodium = Float.parseFloat(snackItem.getSodium_mg());
            snackSugar = Float.parseFloat(snackItem.getSugar_g());
            snackCholesterol = Float.parseFloat(snackItem.getCholesterol_mg());
            snackFiber = Float.parseFloat(snackItem.getFiber_g());
            snackFat_saturated = Float.parseFloat(snackItem.getFat_saturated_g());
            snackPotasium = Float.parseFloat(snackItem.getPotassium_mg());
        }

        if (dinnerItem != null) {
            dinnerCalories = Float.parseFloat(dinnerItem.getCalories());
            dinnerProtein = Float.parseFloat(dinnerItem.getProtein_g());
            dinnerFat = Float.parseFloat(dinnerItem.getFat_total_g());
            dinnerCarbohydrates = Float.parseFloat(dinnerItem.getCarbohydrates_total_g());

            //pozostale mikro i inne
            dinnerSodium = Float.parseFloat(dinnerItem.getSodium_mg());
            dinnerSugar = Float.parseFloat(dinnerItem.getSugar_g());
            dinnerCholesterol = Float.parseFloat(dinnerItem.getCholesterol_mg());
            dinnerFiber = Float.parseFloat(dinnerItem.getFiber_g());
            dinnerFat_saturated = Float.parseFloat(dinnerItem.getFat_saturated_g());
            dinnerPotasium = Float.parseFloat(dinnerItem.getPotassium_mg());
        }

        //zsumowanie mikro i innych
        float summarizedSodium = breakfastSodium + brunchSodium + lunchSodium
                + snackSodium + dinnerSodium;
        SummaryDB.getSummaryItem().setSodium_mg(String.valueOf(String.format("%.2f", summarizedSodium/1000)));
        float summarizedSugar = breakfastSugar + brunchSugar + lunchSugar
                + snackSugar + dinnerSugar;
        SummaryDB.getSummaryItem().setSugar_g(String.valueOf(String.format("%.2f", summarizedSugar)));
        float summarizedCholesterol = breakfastCholesterol + brunchCholesterol + lunchCholesterol
                + snackCholesterol + dinnerCholesterol;
        SummaryDB.getSummaryItem().setCholesterol_mg(String.valueOf(String.format("%.2f", summarizedCholesterol/1000)));
        float summarizedFiber = breakfastFiber + brunchFiber + lunchFiber
                + snackFiber + dinnerFiber;
        SummaryDB.getSummaryItem().setFiber_g(String.valueOf(String.format("%.2f", summarizedFiber)));
        float summarizedFat_saturated = breakfastFat_saturated + brunchFat_saturated + lunchFat_saturated
                + snackFat_saturated + dinnerFat_saturated;
        SummaryDB.getSummaryItem().setFat_saturated_g(String.valueOf(String.format("%.2f", summarizedFat_saturated)));
        float summarizedPotasium = breakfastPotasium + brunchPotasium + lunchPotasium
                + snackPotasium + dinnerPotasium;
        SummaryDB.getSummaryItem().setPotassium_mg(String.valueOf(String.format("%.2f", summarizedPotasium/1000)));
        //

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
