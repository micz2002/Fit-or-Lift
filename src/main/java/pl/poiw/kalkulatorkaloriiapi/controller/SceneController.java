package pl.poiw.kalkulatorkaloriiapi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.poiw.kalkulatorkaloriiapi.HelloApplication;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToFitScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("fxml/fitMainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLiftScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("fxml/mainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
