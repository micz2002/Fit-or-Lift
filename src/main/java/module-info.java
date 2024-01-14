module pl.poiw.kalkulatorkaloriiapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp;


    opens pl.poiw.kalkulatorkaloriiapi to javafx.fxml, com.google.gson;
    exports pl.poiw.kalkulatorkaloriiapi;
    exports pl.poiw.kalkulatorkaloriiapi.controller;
    opens pl.poiw.kalkulatorkaloriiapi.controller to com.google.gson, javafx.fxml;
    exports pl.poiw.kalkulatorkaloriiapi.model;
    opens pl.poiw.kalkulatorkaloriiapi.model to com.google.gson, javafx.fxml;
    exports pl.poiw.kalkulatorkaloriiapi.model.meal;
    opens pl.poiw.kalkulatorkaloriiapi.model.meal to com.google.gson, javafx.fxml;
    exports pl.poiw.kalkulatorkaloriiapi.model.apiproduct;
    opens pl.poiw.kalkulatorkaloriiapi.model.apiproduct to com.google.gson, javafx.fxml;
}