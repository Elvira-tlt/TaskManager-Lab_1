package views;

import models.Task;

import javax.swing.*;
import java.util.List;

public class TableViewAllTasks extends JTable {

    TableViewAllTasks( List<Task> tasks) {
        super();
        TableModelViewAllTasks tableModel = new TableModelViewAllTasks(tasks);
        this.setModel(tableModel);
    }

    public void createTableViewAllTasks() {
        setSize(260,220);
        setVisible(true);
    }



}
