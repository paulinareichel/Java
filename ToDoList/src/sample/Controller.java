package sample;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.time.LocalDate.parse;
import static sample.CreateEditScene.CreateEditTaskWindow;

public class Controller extends ListCell<Task> implements Initializable, Serializable {

    public static ObservableList<Task> toDo = FXCollections.observableArrayList();
    public static ObservableList<Task> inProgress = FXCollections.observableArrayList();
    public static ObservableList<Task> done = FXCollections.observableArrayList();

    public static Stage primaryStage = new Stage();

    public MenuItem contextMenuToDoDelete;
    public MenuItem contextMenuInProgressDelete;
    public MenuItem contextMenuDoneDelete;
    public MenuItem contextMenuToDoEdit;
    public MenuItem contextMenuInProgressEdit;
    public MenuItem contextMenuDoneEdit;
    public Button addButton;

    public ListView toDoList = new ListView<>(toDo);
    public ListView progressArea = new ListView<>(inProgress);
    public ListView doneArea = new ListView<>(done);

    public javafx.scene.control.MenuBar MenuBar;
    public AnchorPane anchor;
    public MenuItem saveTask;
    public MenuItem openTask;
    public MenuItem exportTask;
    public MenuItem importTask;

    Serializer serializer = new Serializer();

    /************** Click on add new task button****************/
    @FXML
    void display(ActionEvent event) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("addTask.fxml"));
        Parent root = fxmlloader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add new task");
        stage.show();
        stage = (Stage) addButton.getScene().getWindow();
        stage.close();

    }

    /************** Setting my task lists ***************/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toDoList.setItems(toDo);
        progressArea.setItems(inProgress);
        doneArea.setItems(done);

        /**************************  Set Tooltip **************************/
        toDoList.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> listView) {
                return new createTooltip();
            }
        });

        progressArea.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> listView) {
                return new createTooltip();
            }
        });

        doneArea.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> listView) {
                return new createTooltip();
            }
        });

        /************************** Drag-drop **************************/
        toDoList.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                if (!toDoList.getItems().isEmpty()) {
                    progressArea.getItems().add(toDoList.getItems().get(toDoList.getFocusModel().getFocusedIndex()));
                    toDoList.getItems().remove(toDoList.getItems().get(toDoList.getFocusModel().getFocusedIndex()));
                }
            }
        });

        progressArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                if (!progressArea.getItems().isEmpty()) {
                    doneArea.getItems().add(progressArea.getItems().get(progressArea.getFocusModel().getFocusedIndex()));
                    progressArea.getItems().remove(progressArea.getItems().get(progressArea.getFocusModel().getFocusedIndex()));
                }
            } else if (event.getCode() == KeyCode.LEFT) {
                if (!progressArea.getItems().isEmpty()) {
                    toDoList.getItems().add(progressArea.getItems().get(progressArea.getFocusModel().getFocusedIndex()));
                    progressArea.getItems().remove(progressArea.getItems().get(progressArea.getFocusModel().getFocusedIndex()));
                }
            }
        });

        doneArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                if (!doneArea.getItems().isEmpty()) {
                    progressArea.getItems().add(doneArea.getItems().get(doneArea.getFocusModel().getFocusedIndex()));
                    doneArea.getItems().remove(doneArea.getItems().get(doneArea.getFocusModel().getFocusedIndex()));
                }
            }
        });

        /************************** Delete or Edit **************************/
        toDoList.setOnMouseClicked(event -> {

            if (toDoList.getItems().isEmpty()) {
                contextMenuToDoDelete.setVisible(false);
                contextMenuToDoEdit.setVisible(false);
            } else {
                contextMenuToDoDelete.setVisible(true);
                contextMenuToDoEdit.setVisible(true);
            }

        });
        progressArea.setOnMouseClicked(event -> {
            if (progressArea.getItems().isEmpty()) {
                contextMenuInProgressDelete.setVisible(false);
                contextMenuInProgressEdit.setVisible(false);
            } else {
                contextMenuInProgressDelete.setVisible(true);
                contextMenuInProgressEdit.setVisible(true);

            }
        });
        doneArea.setOnMouseClicked(event -> {
            if (doneArea.getItems().isEmpty()) {
                contextMenuDoneDelete.setVisible(false);
                contextMenuDoneEdit.setVisible(false);
            } else {
                contextMenuDoneDelete.setVisible(true);
                contextMenuDoneEdit.setVisible(true);

            }
        });

        /************************ Delete Item ************************/
        contextMenuToDoDelete.setOnAction(event -> {
            if (!toDoList.getItems().isEmpty()) {
                toDoList.getItems().remove(toDoList.getItems().get(toDoList.getFocusModel().getFocusedIndex()));
            }
        });
        contextMenuInProgressDelete.setOnAction(event -> {
            if (!progressArea.getItems().isEmpty()) {
                progressArea.getItems().remove(progressArea.getItems().get(progressArea.getFocusModel().getFocusedIndex()));
            }
        });

        contextMenuDoneDelete.setOnAction(event -> {
            if (!doneArea.getItems().isEmpty()) {
                doneArea.getItems().remove(doneArea.getItems().get(doneArea.getFocusModel().getFocusedIndex()));
            }
        });

        /************************ Edit Item ************************/
        contextMenuToDoEdit.setOnAction(event -> {
            try {
                CreateEditTaskWindow(toDo, toDoList.getFocusModel().getFocusedIndex());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        contextMenuInProgressEdit.setOnAction(event -> {
            try {
                CreateEditTaskWindow(inProgress, progressArea.getFocusModel().getFocusedIndex());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        contextMenuDoneEdit.setOnAction(event -> {
            try {
                CreateEditTaskWindow(done, doneArea.getFocusModel().getFocusedIndex());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /*************** Set To Do Area **************/
    public void setToDoArea(Task task) {
        toDo.add(task);
    }


    /*************** Main window ****************/
    public void show() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("To Do List");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /*************** Deserialization ****************/
    private void deserializeData(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        serializer = (Serializer) inputStream.readObject();

        toDo = FXCollections.observableArrayList(serializer.toDO);
        toDoList.setItems(toDo);

        inProgress = FXCollections.observableArrayList(serializer.inProgress);
        progressArea.setItems(inProgress);

        done = FXCollections.observableArrayList(serializer.done);
        doneArea.setItems(done);
    }


    /*************** JSON ****************/
    public void generateJSONfile(File file) {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter(file.getAbsolutePath())) {
            gson.toJson(serializer, writer);
        } catch (IOException e) {
        }
    }

    public void loadJSONfile(Reader reader) {
        Gson gson = new Gson();
        serializer = gson.fromJson(reader, Serializer.class);
    }

    /*************** Save & Open ****************/
       public void saveToFile(ActionEvent actionEvent) {
      serializer.toDO = new ArrayList<>(toDo);
        serializer.inProgress = new ArrayList<>(inProgress);
        serializer.done = new ArrayList<>(done);
        Stage stage = (Stage) addButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save lists");

        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("Ser files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                outputStream.writeObject(serializer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openFromFile(ActionEvent actionEvent) {
        Stage stage = (Stage) addButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open lists");

        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("Ser files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(stage);
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            deserializeData(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*************** Export & Import ****************/

   @FXML
   public void  exportToFile(ActionEvent actionEvent) {
       FileChooser fileChooser = new FileChooser();
       fileChooser.setTitle("Save lists");

       FileChooser.ExtensionFilter extFilter =
               new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
       fileChooser.getExtensionFilters().add(extFilter);

       File file = fileChooser.showSaveDialog(null);

       try (Writer writer = new BufferedWriter(new FileWriter(file))) {
           for (Task event : toDo) {
               String text = event.title + "," + event.description + "," + event.date + "," + event.priority + "\n";
               writer.write(text);
           }
           writer.write("progress\n");
           for (Task event : inProgress) {
               String text = event.title + "," + event.description + "," + event.date + "," + event.priority + "\n";
               writer.write(text);
           }
           writer.write("done\n");
           for (Task event : done) {
               String text = event.title + "," + event.description + "," + event.date + "," + event.priority + "\n";
               writer.write(text);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public void importFromFile(ActionEvent actionEvent) {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open lists");

            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(null);

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                List<Task> list = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    if(line.equals("progress")) {
                        break;
                    }
                    String[] fields = line.split(",", -1);

                    LocalDate local = LocalDate.parse(fields[2]);
                    list.add(new Task(fields[0], fields[1], fields[3], local));
                }
                toDo.removeAll();
                toDo.addAll(list);

                List<Task> progressList = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    if(line.equals("done")) {
                        break;
                    }
                    String[] fields = line.split(",", -1);
                    LocalDate local = parse(fields[2]);
                    progressList.add(new Task(fields[0], fields[1], fields[3], local));
                }
                inProgress.removeAll();
                inProgress.addAll(progressList);
                progressArea.refresh();

                List<Task> doneLi = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",", -1);
                    LocalDate local = parse(fields[2]);
                    doneLi.add(new Task(fields[0], fields[1], fields[3], local) );
                }
                done.removeAll();
                done.addAll(doneLi);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    /************* Class to create a tooltip *************/
    static class createTooltip extends ListCell<Task> {
        @Override
        public void updateItem(Task item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setTooltip(null);
            } else {
                setText(getItem().getTitle());
                Tooltip tooltip = new Tooltip();
                tooltip.setText(getItem().getDescription());
                setTooltip(tooltip);
            }
        }

    }


}

