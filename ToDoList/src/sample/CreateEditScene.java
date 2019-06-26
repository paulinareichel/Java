package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateEditScene {

    public static Stage editTaskStage = new Stage();
    public static ObservableList<Task> TempList = null;
    public static int TempIndex;

    public static void CreateEditTaskWindow(ObservableList<Task> list, int position) throws IOException {
        try {
            TempList = list;
            TempIndex = position;

            FXMLLoader loaderEditTask = new FXMLLoader();
            loaderEditTask.setLocation(CreateEditScene.class.getResource("EditScene.fxml"));
            Parent root1 = loaderEditTask.load();
            editTaskStage.setTitle("Edit Task");
            editTaskStage.setScene(new Scene(root1));
            editTaskStage.show();
        } catch (Exception e) {
            System.err.println("Can not create new scene! " + e);
        }
    }
}
