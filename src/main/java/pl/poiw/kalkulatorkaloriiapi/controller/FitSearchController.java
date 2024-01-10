package pl.poiw.kalkulatorkaloriiapi.controller;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.poiw.kalkulatorkaloriiapi.HelloApplication;
import pl.poiw.kalkulatorkaloriiapi.model.*;
import pl.poiw.kalkulatorkaloriiapi.model.meal.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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
                    foodListView.getItems().add(i.toString());
                    switch (MealDB.getMeal()) {
                        case BREAKFAST -> BreakfastModelDB.getObservableListItems().add(i);
                        case BRUNCH -> BrunchModelDB.getObservableListItems().add(i);
                        case LUNCH -> LunchModelDB.getObservableListItems().add(i);
                        case SNACK -> SnackModelDB.getObservableListItems().add(i);
                        case DINNER -> DinnerModelDB.getObservableListItems().add(i);
                    }
                }
                setCheckBoxListCells();
                //zapisuje elementy z listy do statycznej listy Stringow
                switch (MealDB.getMeal()) {
                    case BREAKFAST -> BreakfastModelDB.setItemsAsStringList(foodListView.getItems());
                    case BRUNCH -> BrunchModelDB.setItemsAsStringList(foodListView.getItems());
                    case LUNCH -> LunchModelDB.setItemsAsStringList(foodListView.getItems());
                    case SNACK -> SnackModelDB.setItemsAsStringList(foodListView.getItems());
                    case DINNER -> DinnerModelDB.setItemsAsStringList(foodListView.getItems());
                }
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

    private void setCheckBoxListCells() {
        foodListView.setCellFactory(CheckBoxListCell.forListView(item -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            switch (MealDB.getMeal()) {
                case BREAKFAST -> observable = BreakfastModelDB.getStringBooleanPropertyMap().get(item);
                case BRUNCH -> observable = BrunchModelDB.getStringBooleanPropertyMap().get(item);
                case LUNCH -> observable = LunchModelDB.getStringBooleanPropertyMap().get(item);
                case SNACK -> observable = SnackModelDB.getStringBooleanPropertyMap().get(item);
                case DINNER -> observable = DinnerModelDB.getStringBooleanPropertyMap().get(item);
            }

            if (observable == null) {
                observable = new SimpleBooleanProperty(false); // Domyślnie ustaw na false, jeśli nie ma jeszcze wartości
                BooleanProperty finalObservable = observable;
                observable.addListener((obs, wasSelected, isNowSelected) -> {
                    System.out.println("Check box for " + item + " changed from " + wasSelected + " to " + isNowSelected);
                    if (isNowSelected) {
                        System.out.println(item);
                    }
                    if (finalObservable.getValue()) {
                        int startIndex = item.indexOf("name = ") + "name = ".length();
                        int endIndex = item.indexOf(",", startIndex);
                        String nameValue = item.substring(startIndex, endIndex).trim();
                        System.out.println(nameValue);
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
            return observable;
        }));

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