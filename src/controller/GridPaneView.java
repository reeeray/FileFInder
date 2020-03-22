package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class GridPaneView implements Initializable {
    @FXML
    private TextField pathId;

    @FXML
    private TextField endId;

    @FXML
    private ProgressBar barId;

    @FXML
    private ListView<String> listId;

    private Task task;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        controller.FileFinder.items.addListener(new ListChangeListener() {
//            @Override
//            public void onChanged(ListChangeListener.Change obj) {
//                listId.setItems(obj.getList());
//            }
//        });
        listId.setItems(FileFinder.items);
    }
    @FXML
    protected void btnFindClick(ActionEvent event) {
        String path=pathId.getText();
        String end =endId.getText();
        task = new FindTask(FileFinder.items, path, end);

        barId.progressProperty().bind(task.progressProperty());

        FileFinder.items.clear();
        //barId.setProgress(-1);

        //new FindTask(JavaFXApplication4.items, path, end).doWork();

        //run asynk Task in concurrent version

        //Platform.runLater(task);

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();


        //barId.setProgress(1);
    }
    @FXML
    protected void btnCancelClick(ActionEvent event) {
        System.out.println("Try to Cancel!");
        if (task!=null) {
            task.cancel();
        }
    }
}

