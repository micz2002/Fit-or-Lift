package pl.poiw.kalkulatorkaloriiapi.model;

import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public class DBHelloController {
    private static ObservableList<String> itemsAsStringList;
    private static Map<String, BooleanProperty> stringBooleanPropertyMap = new HashMap<>();
    public static ObservableList<String> getItemsAsStringList() {
        return itemsAsStringList;
    }
    public static void setItemsAsStringList(ObservableList<String> itemsAsStringList) {
        DBHelloController.itemsAsStringList = itemsAsStringList;
    }

    public static Map<String, BooleanProperty> getStringBooleanPropertyMap() {
        return stringBooleanPropertyMap;
    }

    public static void setStringBooleanPropertyMap(Map<String, BooleanProperty> stringBooleanPropertyMap) {
        DBHelloController.stringBooleanPropertyMap = stringBooleanPropertyMap;
    }
}
