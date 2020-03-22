package controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FileFinder extends Application {

    public static final ObservableList<String> items = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/GridPane.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Concurrency in JavaFX");
        stage.setScene(scene);
        stage.show();
    }
}

