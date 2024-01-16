package pl.poiw.kalkulatorkaloriiapi.controller;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pl.poiw.kalkulatorkaloriiapi.HelloApplication;
import pl.poiw.kalkulatorkaloriiapi.model.meal.*;
import pl.poiw.kalkulatorkaloriiapi.model.apiproduct.Items;
import pl.poiw.kalkulatorkaloriiapi.model.apiproduct.Product;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FitSearchController implements Initializable {

    @FXML
    private TextField kcalTextField;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private Button getButton;
    @FXML
    private TextField productTextField;
    @FXML
    private ListView<String> foodListView;
    //musi byc albo do osobnej klasy albo cos innego bo sie resetuje i dupa a nie poprawny zapis labela w listView
    List<Items> auxiliaryItems = new ArrayList<>();
    private Parent root;

    @FXML
    public void switchToPreviousScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("fxml/fitMainScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void getProduct(ActionEvent event) {

        OkHttpClient client = new OkHttpClient();

        String query = productTextField.getText();
        Request request = new Request.Builder()
                .url("https://api.calorieninjas.com/v1/nutrition?query=" + query)
                .get()
                .addHeader("X-Api-Key", "Y0gboJMx0d3q8EvasAnmTg==yjCO2a4uVcOoVaFc")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            if (response.isSuccessful()) {

                Gson gson = new Gson();

                Product product = gson.fromJson(responseBody, Product.class);

                Items[] items = product.getItems();

                for (Items i : items) {

                    kcalTextField.setText(i.getCalories() + " kcal/100g");
                    //foodListView.getItems().add("name: " + i.getName() + ", kcal/100g: " + i.getCalories());

                    //COS TU NEI DZIALA [ogolnie ten kod ma zapobiegac duplikatom przy wywolywaniu api
//                    if(!foodListView.getItems().isEmpty()) {
//                        for (String s : foodListView.getItems()) {
//                            boolean equalItems = i.toString().equals(s);
//                            if (equalItems) {
//                                foodListView.getItems().add(i.toString());
//                                ModelDB.getObservableListItems().add(i);
//                            }
//                        }
//                    }else {
                    auxiliaryItems.add(i);
                    foodListView.getItems().add(i.toString());
                    switch (MealDB.getMeal()) {
                        case BREAKFAST -> BreakfastModelDB.getObservableListItems().add(i);
                        case BRUNCH -> BrunchModelDB.getObservableListItems().add(i);
                        case LUNCH -> LunchModelDB.getObservableListItems().add(i);
                        case SNACK -> SnackModelDB.getObservableListItems().add(i);
                        case DINNER -> DinnerModelDB.getObservableListItems().add(i);
                    }

                }
                setCheckBoxListCells(auxiliaryItems);
                //zapisuje elementy z listy do statycznej listy Stringow
                switch (MealDB.getMeal()) {
                    case BREAKFAST -> BreakfastModelDB.setItemsAsStringList(foodListView.getItems());
                    case BRUNCH -> BrunchModelDB.setItemsAsStringList(foodListView.getItems());
                    case LUNCH -> LunchModelDB.setItemsAsStringList(foodListView.getItems());
                    case SNACK -> SnackModelDB.setItemsAsStringList(foodListView.getItems());
                    case DINNER -> DinnerModelDB.setItemsAsStringList(foodListView.getItems());
                }
//                foodListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
//                    @Override
//                    public ListCell<String> call(ListView<String> param) {
//                        return new ListCell<String>() {
//                            @Override
//                            protected void updateItem(String item, boolean empty) {
//                                super.updateItem(item, empty);
//                                if (item == null || empty) {
//                                    setText(null);
//                                } else {
//                                    setText("cos jest");
//                                }
//                            }
//                        };
//                    }
//                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        foodListView.getStylesheets().add(HelloApplication.class.getResource("styles/fitSearchScene.css").toExternalForm());

        switch (MealDB.getMeal()) {
            case BREAKFAST -> {
                if (BreakfastModelDB.getItemsAsStringList() != null) {
                    foodListView.setItems(BreakfastModelDB.getItemsAsStringList());
                    setCheckBoxListCellsFromMap();
                }
            }
            case BRUNCH -> {
                if (BrunchModelDB.getItemsAsStringList() != null) {
                    foodListView.setItems(BrunchModelDB.getItemsAsStringList());
                    setCheckBoxListCellsFromMap();
                }
            }
            case LUNCH -> {
                if (LunchModelDB.getItemsAsStringList() != null) {
                    foodListView.setItems(LunchModelDB.getItemsAsStringList());
                    setCheckBoxListCellsFromMap();
                }
            }
            case SNACK -> {
                if (SnackModelDB.getItemsAsStringList() != null) {
                    foodListView.setItems(SnackModelDB.getItemsAsStringList());
                    setCheckBoxListCellsFromMap();
                }
            }
            case DINNER -> {
                if (DinnerModelDB.getItemsAsStringList() != null) {
                    foodListView.setItems(DinnerModelDB.getItemsAsStringList());
                    setCheckBoxListCellsFromMap();
                }
            }
        }

    }

    private void setCheckBoxListCells(List<Items> i) {
//        foodListView.setCellFactory(CheckBoxListCell.forListView(item -> {
//            BooleanProperty observable = new SimpleBooleanProperty();
//            switch (MealDB.getMeal()) {
//                case BREAKFAST -> observable = BreakfastModelDB.getStringBooleanPropertyMap().get(item);
//                case BRUNCH -> observable = BrunchModelDB.getStringBooleanPropertyMap().get(item);
//                case LUNCH -> observable = LunchModelDB.getStringBooleanPropertyMap().get(item);
//                case SNACK -> observable = SnackModelDB.getStringBooleanPropertyMap().get(item);
//                case DINNER -> observable = DinnerModelDB.getStringBooleanPropertyMap().get(item);
//            }
//
//            if (observable == null) {
//                observable = new SimpleBooleanProperty(false); // Domyślnie ustaw na false, jeśli nie ma jeszcze wartości
//                BooleanProperty finalObservable = observable;
//                observable.addListener((obs, wasSelected, isNowSelected) -> {
//                    System.out.println("Check box for " + item + " changed from " + wasSelected + " to " + isNowSelected);
//                    if (isNowSelected) {
//                        System.out.println(item);
//                    }
//                    //wycina fragment "name" to mialo sluzyc do sprawdzenia czy 2 produkty maja taka sama nazwe
////                    if (finalObservable.getValue()) {
////                        int startIndex = item.indexOf("name = ") + "name = ".length();
////                        int endIndex = item.indexOf(",", startIndex);
////                        String nameValue = item.substring(startIndex, endIndex).trim();
////                        System.out.println(nameValue);
////                    }
//                });
//                switch (MealDB.getMeal()) {
//                    case BREAKFAST -> BreakfastModelDB.getStringBooleanPropertyMap().put(item, observable);
//                    case BRUNCH -> BrunchModelDB.getStringBooleanPropertyMap().put(item, observable);
//                    case LUNCH -> LunchModelDB.getStringBooleanPropertyMap().put(item, observable);
//                    case SNACK -> SnackModelDB.getStringBooleanPropertyMap().put(item, observable);
//                    case DINNER -> DinnerModelDB.getStringBooleanPropertyMap().put(item, observable);
//                }
//            }
//            return observable;
//        }));

        foodListView.setCellFactory(param -> new ListCell<String>() {
            private final CheckBox checkBox = new CheckBox();
            private final Label label = new Label();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    BooleanProperty observable = switch (MealDB.getMeal()) {
                        case BREAKFAST -> BreakfastModelDB.getStringBooleanPropertyMap().get(item);
                        case BRUNCH -> BrunchModelDB.getStringBooleanPropertyMap().get(item);
                        case LUNCH -> LunchModelDB.getStringBooleanPropertyMap().get(item);
                        case SNACK -> SnackModelDB.getStringBooleanPropertyMap().get(item);
                        case DINNER -> DinnerModelDB.getStringBooleanPropertyMap().get(item);
                    };

                    if (observable == null) {
                        observable = new SimpleBooleanProperty(false);

                        observable.addListener((obs, wasSelected, isNowSelected) -> {
                            System.out.println("Check box for " + item + " changed from " + wasSelected + " to " + isNowSelected);
                            if (isNowSelected) {
                                System.out.println(item);
                            }
                        });

                        switch (MealDB.getMeal()) {
                            case BREAKFAST -> BreakfastModelDB.getStringBooleanPropertyMap().put(item, observable);
                            case BRUNCH -> BrunchModelDB.getStringBooleanPropertyMap().put(item, observable);
                            case LUNCH -> LunchModelDB.getStringBooleanPropertyMap().put(item, observable);
                            case SNACK -> SnackModelDB.getStringBooleanPropertyMap().put(item, observable);
                            case DINNER -> DinnerModelDB.getStringBooleanPropertyMap().put(item, observable);
                        }
                    }
                    //ustawienie checkboxa i labela w listView
                    checkBox.selectedProperty().bindBidirectional(observable);
                    if (i != null) {
                        switch (MealDB.getMeal()) {
                            case BREAKFAST -> {
                                Optional<Items> matchedItemFound = BreakfastModelDB.getObservableListItems().stream()
                                        .filter(element -> element.toString().equals(item))
                                        .findFirst();
                                    if(matchedItemFound.isPresent()) {

                                        label.textProperty().bind(Bindings.when(observable)
                                                .then(setProductLabelInListView(matchedItemFound))
                                                .otherwise(setProductLabelInListView(matchedItemFound)));
                                    }
                            }
                            case BRUNCH -> {
                                Optional<Items> matchedItemFound = BrunchModelDB.getObservableListItems().stream()
                                        .filter(element -> element.toString().equals(item))
                                        .findFirst();
                                if(matchedItemFound.isPresent()) {

                                    label.textProperty().bind(Bindings.when(observable)
                                            .then(setProductLabelInListView(matchedItemFound))
                                            .otherwise(setProductLabelInListView(matchedItemFound)));
                                }
                            }
                            case LUNCH -> {
                                Optional<Items> matchedItemFound = LunchModelDB.getObservableListItems().stream()
                                        .filter(element -> element.toString().equals(item))
                                        .findFirst();
                                if(matchedItemFound.isPresent()) {

                                    label.textProperty().bind(Bindings.when(observable)
                                            .then(setProductLabelInListView(matchedItemFound))
                                            .otherwise(setProductLabelInListView(matchedItemFound)));
                                }
                            }
                            case SNACK -> {
                                Optional<Items> matchedItemFound = SnackModelDB.getObservableListItems().stream()
                                        .filter(element -> element.toString().equals(item))
                                        .findFirst();
                                if(matchedItemFound.isPresent()) {

                                    label.textProperty().bind(Bindings.when(observable)
                                            .then(setProductLabelInListView(matchedItemFound))
                                            .otherwise(setProductLabelInListView(matchedItemFound)));
                                }
                            }
                            case DINNER -> {
                                Optional<Items> matchedItemFound = DinnerModelDB.getObservableListItems().stream()
                                        .filter(element -> element.toString().equals(item))
                                        .findFirst();
                                if(matchedItemFound.isPresent()) {

                                    label.textProperty().bind(Bindings.when(observable)
                                            .then(setProductLabelInListView(matchedItemFound))
                                            .otherwise(setProductLabelInListView(matchedItemFound)));
                                }
                            }
                        };
                    }
                    HBox hbox = new HBox(10);
                    hbox.getChildren().addAll(checkBox, label);
                    setGraphic(hbox);
                }
            }
        });
    }

    private String setProductLabelInListView(Optional<Items> matchedItemFound) {
        return matchedItemFound.get().getName() + " " + matchedItemFound.get().getServing_size_g()
                + "g" + " kcal: " + matchedItemFound.get().getCalories()
                + " protein: " + matchedItemFound.get().getProtein_g() + " fat: " + matchedItemFound.get().getFat_total_g()
                + " carbs: " + matchedItemFound.get().getCarbohydrates_total_g();
    }

    private void setCheckBoxListCellsFromMap() {
        foodListView.setCellFactory(CheckBoxListCell.forListView(item -> switch (MealDB.getMeal()) {
            case BREAKFAST -> BreakfastModelDB.getStringBooleanPropertyMap().get(item);
            case BRUNCH -> BrunchModelDB.getStringBooleanPropertyMap().get(item);
            case LUNCH -> LunchModelDB.getStringBooleanPropertyMap().get(item);
            case SNACK -> SnackModelDB.getStringBooleanPropertyMap().get(item);
            case DINNER -> DinnerModelDB.getStringBooleanPropertyMap().get(item);
        }));
    }
}