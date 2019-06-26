package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public ProgressBar Progress;
    public Button StartButton;
    public Canvas Screen;
    public TextField TextFIeldPoints;
    public TextField TextFIeldResult;
    private Drawer task;
    static double value;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFIeldPoints.setText("1000");
        TextFIeldPoints.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {

                if ((newValue.matches("\\d*")) && (Integer.parseInt(TextFIeldPoints.getText()))<=8999999) {
                } else { TextFIeldPoints.setText(oldValue); }
            }
        });
    }

    @FXML
    void setStartButton() {
        GraphicsContext gc = Screen.getGraphicsContext2D();
        Integer NoPoints = Integer.parseInt(TextFIeldPoints.getText());
        task = new Drawer(gc, NoPoints);

        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                value = ((double) task.getValue())*16*16;
                TextFIeldResult.setText(String.valueOf(value));
            }
        });

        task.setOnCancelled(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                TextFIeldResult.setText(String.valueOf(value));
            }
        });
        Progress.progressProperty().bind(task.progressProperty());
        new Thread(task).start();


    }

    @FXML
    void setStopButton() {
        task.cancel();
    }
}
