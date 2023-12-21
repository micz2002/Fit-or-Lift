module pl.poiw.kalkulatorkaloriiapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp;


    opens pl.poiw.kalkulatorkaloriiapi to javafx.fxml, com.google.gson;
    exports pl.poiw.kalkulatorkaloriiapi;
}