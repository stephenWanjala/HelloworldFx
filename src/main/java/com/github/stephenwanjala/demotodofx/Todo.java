package com.github.stephenwanjala.demotodofx;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Todo {
    private StringProperty task;
    private BooleanProperty done;
    private int id;
    private StringProperty description;

    public Todo() {
        this.task = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.done = new SimpleBooleanProperty();
    }

    public Todo(String task, String description, boolean done) {
        this.task = new SimpleStringProperty(task);
        this.description = new SimpleStringProperty(description);
        this.done = new SimpleBooleanProperty(done);
        this.id = generateId();
    }

    public String getTask() {
        return task.get();
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getId() {
        return id;
    }

    public void setTask(String task) {
        this.task.set(task);
    }

    public boolean isDone() {
        return done.get();
    }

    public void setDone(boolean done) {
        this.done.set(done);
    }

    public ObservableValue<Boolean> doneProperty() {
        return done;
    }

    public ObservableValue<String> taskProperty() {
        return task;
    }

    public ObservableValue<String> descriptionProperty() {
        return description;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "task='" + task.get() + '\'' +
                ", done=" + done.get() +
                ", id=" + id +
                ", description='" + description.get() + '\'' +
                '}';
    }

    private int generateId() {
        return (int) (Math.random() * 1000);
    }
}