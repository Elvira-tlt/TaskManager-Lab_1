package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import models.Task;
import controllers.TaskController;
import savingAndLoadingTasks.Database;

public class TaskView {
    TaskController taskController;
    TableModelViewAllTasks tableModelViewAllTasks;
    InformationDialog informationDialog;

    public void setTaskController(TaskController taskController) {
        this.taskController = taskController;
    }


    public void createMainWindow() {
        JFrame mainWindow = new JFrame("Task Manager");
        mainWindow.addWindowListener(closingProgram);

        JButton createTaskButton = new JButton("Create Task / Создать задачу");
        JButton editTasksButton = new JButton("Edit Tasks/ Редактирование задач");
        JButton deleteTaskButton = new JButton("Delete task / Удалить задачу");
        JPanel panelButtons = new JPanel();
        JPanel panelViewAllTasks = new JPanel();

        createTaskButton.setToolTipText("Создание новой задачи");
        editTasksButton.setToolTipText("Редактирование задач");
        deleteTaskButton.setToolTipText("Удалить выбранную задачу");

        //Panel view all tasks:
        tableModelViewAllTasks = new TableModelViewAllTasks(taskController.getAllTasksModel());
        final JTable tableViewAllTasks = new JTable(tableModelViewAllTasks);

        //frame.add(new BorderLayout().NORTH,new JLabel("Созданные задачи"));
        panelViewAllTasks.add(tableViewAllTasks);
        panelViewAllTasks.add(new JScrollPane(tableViewAllTasks));


        createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDialogForNewTask();
            }
        });

        editTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] countSelectedRows = tableViewAllTasks.getSelectedRows();
                if (countSelectedRows.length > 0) {
                    for (int i = 0; i < countSelectedRows.length; i++) {
                        creatingDialogForEditingTask(countSelectedRows[i]);
                    }
                } else {
                    informationDialog = new InformationDialog();
                    informationDialog.setTextInformationLabel("Выберите задачу для редактирования");
                }
            }
        });


        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int[] countSelectedRows = tableViewAllTasks.getSelectedRows();
                if (countSelectedRows.length > 0) {
                    for (int i = countSelectedRows.length - 1; i >= 0; i--) {
                        taskController.deleteSelectedTask(countSelectedRows[i]);
                    }
                    updateTable();
                } else {
                    informationDialog = new InformationDialog();
                    informationDialog.setTextInformationLabel("Выберите задачу для удаления");
                }
            }
        });

        panelButtons.setLayout(new GridLayout(8, 1));
        panelButtons.add(createTaskButton);
        panelButtons.add(new JLabel(" "));
        panelButtons.add(editTasksButton);
        panelButtons.add(new JLabel(" "));
        panelButtons.add(deleteTaskButton);

        tableViewAllTasks.setRowHeight(40);

        tableViewAllTasks.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tableViewAllTasks.getColumnModel().getColumn(0).setPreferredWidth(85);
        tableViewAllTasks.getColumnModel().getColumn(2).setMinWidth(85);
        tableViewAllTasks.getColumnModel().getColumn(1).setPreferredWidth(140);


        mainWindow.setLayout(new FlowLayout());
        mainWindow.add(new BorderLayout().WEST, panelViewAllTasks);
        mainWindow.add(new BorderLayout().EAST, panelButtons);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setSize(750, 500);
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
    }

    private void createDialogForNewTask() {
        DialogForNewTask dialogCreatingTask = new DialogForNewTask(null);
        dialogCreatingTask.createDialogForTask(this);
        dialogCreatingTask.setTitle("Создание новой задачи");

        ButtonsPanelForDialog buttonsPanel = new ButtonsPanelForDialog();
        dialogCreatingTask.add(new BorderLayout().SOUTH, buttonsPanel);

        buttonsPanel.setListenerTo(buttonsPanel.getButtonOk(), dialogCreatingTask.getReadingDateValueForCreating());
        buttonsPanel.setListenerTo(buttonsPanel.getButtonCancel(), dialogCreatingTask.getCancelSaveValueFieldDialog() );
    }

    private void creatingDialogForEditingTask(final int countSelectedRows) {

        Task taskForEditing = taskController.getAllTasksModel().get(countSelectedRows);

        final DialogForNewTask dialogForEditingTask = new DialogForNewTask(taskForEditing);

        dialogForEditingTask.createDialogForTask(this);

        ButtonsPanelForDialog buttonsPanel = new ButtonsPanelForDialog();
        dialogForEditingTask.add(new BorderLayout().SOUTH, buttonsPanel);

        buttonsPanel.setListenerTo(buttonsPanel.getButtonOk(), dialogForEditingTask.getReadingDateValueForCreating());

        buttonsPanel.setListenerTo(buttonsPanel.getButtonCancel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dialogForEditingTask.dispose();
            }
        });
    }
        
    public void updateTable() {
        Set<TableModelListener> listenersTableModel = tableModelViewAllTasks.getListeners();
        Iterator<TableModelListener> listenersIterator = listenersTableModel.iterator();
        while (listenersIterator.hasNext()) {
            TableModelListener listenerElem = listenersIterator.next();
            listenerElem.tableChanged(new TableModelEvent(tableModelViewAllTasks));
        }
    }

    WindowListener closingProgram = new java.awt.event.WindowAdapter() {
        public void windowClosing(java.awt.event.WindowEvent evt) {
            new Database().savingTasksToFile(taskController.getAllTasksModel());
            //System.exit(1);
        }
    };
}







