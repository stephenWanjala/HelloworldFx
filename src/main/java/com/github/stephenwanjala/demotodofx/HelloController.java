package com.github.stephenwanjala.demotodofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

public class HelloController {

    @FXML
    public Button addButton;
    @FXML
    public TableColumn<Todo, Integer> idColumn;
    @FXML
    private TableView<Todo> todoTable;

    @FXML
    private TableColumn<Todo, Void> deleteColumn;

    @FXML
    private TableColumn<Todo, String> taskColumn;

    @FXML
    private TableColumn<Todo, String> descriptionColumn;
    @FXML
    private TableColumn<Todo, Boolean> doneColumn;

    private final ObservableList<Todo> todoList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set cell value factories for each column
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        taskColumn.setCellValueFactory(cellData -> cellData.getValue().taskProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        todoTable.setEditable(true);

        // Set cell factories for task and description columns
        taskColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Set on edit commit events for task and description columns
        taskColumn.setOnEditCommit(event -> {
            Todo todo = event.getRowValue();
            todo.setTask(event.getNewValue());
        });

        descriptionColumn.setOnEditCommit(event -> {
            Todo todo = event.getRowValue();
            todo.setDescription(event.getNewValue());
        });

        // Populate the table with sample data (you can replace this with your own data retrieval logic)
        todoList.add(new Todo("Task 1", "Description 1", false));
        todoList.add(new Todo("Task 2", "Description 2", true));
        todoList.add(new Todo("Task 3", "Description 3", false));
        // Bind the todoList to the table
        todoTable.setItems(todoList);
        doneColumn.setCellValueFactory(cellData -> cellData.getValue().doneProperty());
        doneColumn.setCellFactory(CheckBoxTableCell.forTableColumn(doneColumn));
        doneColumn.setOnEditCommit(event -> {
            Todo todo = event.getRowValue();
            todo.setDone(event.getNewValue());
        });

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Todo todo = getTableView().getItems().get(getIndex());
                    todoList.remove(todo);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
    }

    public void addTask() {
        Todo newTodo = new Todo("New Task", "New Description", false);
        todoList.add(newTodo);
    }
}