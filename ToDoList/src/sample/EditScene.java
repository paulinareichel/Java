package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

import static sample.Controller.*;
import static sample.CreateEditScene.TempIndex;
import static sample.CreateEditScene.TempList;
import static sample.CreateEditScene.editTaskStage;

public class EditScene {
    @FXML
    private Button EditButton;
    @FXML
    public TextField textFieldEditTaskTitle;
    @FXML
    public ComboBox comboBoxEditTaskPriority;
    @FXML
    public DatePicker datePickerEditTaskDate;
    @FXML
    public TextArea textFieldEditTaskDescription;


    /************************ Setting edit positions ************************/
    @FXML
    public void initialize() {

        comboBoxEditTaskPriority.getItems().addAll("Low", "Medium", "High");
        if (TempList == toDo) {
            textFieldEditTaskTitle.setText(toDo.get(TempIndex).getTitle());
            comboBoxEditTaskPriority.getSelectionModel().select(toDo.get(TempIndex).getPriority());
            textFieldEditTaskDescription.setText(toDo.get(TempIndex).getDescription());
            datePickerEditTaskDate.setValue(toDo.get(TempIndex).getLocalDate());
        } else if (TempList == inProgress) {
            textFieldEditTaskTitle.setText(inProgress.get(TempIndex).getTitle());
            comboBoxEditTaskPriority.getSelectionModel().select(inProgress.get(TempIndex).getPriority());
            textFieldEditTaskDescription.setText(inProgress.get(TempIndex).getDescription());
            datePickerEditTaskDate.setValue(inProgress.get(TempIndex).getLocalDate());
        } else if (TempList == done) {
            textFieldEditTaskTitle.setText(done.get(TempIndex).getTitle());
            comboBoxEditTaskPriority.getSelectionModel().select(done.get(TempIndex).getPriority());
            textFieldEditTaskDescription.setText(done.get(TempIndex).getDescription());
            datePickerEditTaskDate.setValue(done.get(TempIndex).getLocalDate());
        }

        EditButton.setOnMouseClicked(event -> {
            EditTaskClick();
        });

    }

    /************************ Editing Item ************************/
    public void EditTaskClick() {
        if (TempList == toDo) {
            String title = textFieldEditTaskTitle.getText();
            String description = textFieldEditTaskDescription.getText();
            String priority = comboBoxEditTaskPriority.getValue().toString();
            LocalDate date = datePickerEditTaskDate.getValue();
            Controller.toDo.remove(TempIndex);
            Controller.toDo.add(new Task(title, description, priority, date));
        } else if (TempList == inProgress) {
            String title = textFieldEditTaskTitle.getText();
            String description = textFieldEditTaskDescription.getText();
            String priority = comboBoxEditTaskPriority.getValue().toString();
            LocalDate date = datePickerEditTaskDate.getValue();
            Controller.inProgress.remove(TempIndex);
            Controller.inProgress.add(new Task(title, description, priority, date));

        } else if (TempList == done) {

            String title = textFieldEditTaskTitle.getText();
            String description = textFieldEditTaskDescription.getText();
            String priority = comboBoxEditTaskPriority.getValue().toString();
            LocalDate date = datePickerEditTaskDate.getValue();
            Controller.done.remove(TempIndex);
            Controller.done.add(new Task(title, description, priority, date));
        }
        editTaskStage.close();
    }
}
