package pl.poiw.kalkulatorkaloriiapi.model.meal;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.poiw.kalkulatorkaloriiapi.model.Items;

import java.util.HashMap;
import java.util.Map;

public class DinnerModelDB {
    private static ObservableList<String> itemsAsStringList;
    private static Map<String, BooleanProperty> stringBooleanPropertyMap = new HashMap<>();

    private static ObservableList<Items> observableListItems = FXCollections.observableArrayList();

    public static ObservableList<Items> getObservableListItems() {
        return observableListItems;
    }

    public static void setObservableListItems(ObservableList<Items> observableListItems) {
        DinnerModelDB.observableListItems = observableListItems;
    }

    public static ObservableList<String> getItemsAsStringList() {
        return itemsAsStringList;
    }
    public static void setItemsAsStringList(ObservableList<String> itemsAsStringList) {
        DinnerModelDB.itemsAsStringList = itemsAsStringList;
    }

    public static Map<String, BooleanProperty> getStringBooleanPropertyMap() {
        return stringBooleanPropertyMap;
    }

    public static void setStringBooleanPropertyMap(Map<String, BooleanProperty> stringBooleanPropertyMap) {
        DinnerModelDB.stringBooleanPropertyMap = stringBooleanPropertyMap;
    }
}
