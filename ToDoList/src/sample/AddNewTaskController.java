package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import java.io.IOException;
import java.time.LocalDate;

public class AddNewTaskController {

    String mtitle;
    String description;
    String priority;
    LocalDate date;

    @FXML
    public javafx.scene.control.DatePicker DatePicker;
    @FXML
    public TextArea DescriptionArea;
    @FXML
    public ComboBox PriorityBox;
    @FXML
    public TextField TitleField;
    @FXML
    public Button AddNewButton;

    @FXML
    public void initialize() {
        PriorityBox.getItems().addAll("Low", "Medium", "High"); //Priority values
    }


                    /**************ADD NEW TASK***************/
    @FXML
    void newTaskAdd(ActionEvent event) throws Exception {
        try {
            mtitle = TitleField.getText();
            description = DescriptionArea.getText();
            priority = PriorityBox.getSelectionModel().getSelectedItem().toString();
            date = DatePicker.getValue();
            Task task = new Task(mtitle, description, priority, date);
            task.title = task.getTitle();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = fxmlLoader.load();
            Controller controller = fxmlLoader.<Controller>getController();
            controller.setToDoArea(task);
            AddNewButton.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
