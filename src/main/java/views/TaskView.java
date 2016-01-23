package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import models.Task;
import controllers.TaskController;

public class TaskView {
    TaskController taskController;
    DialogForNewTask dialogCreatingTask;
    TableModelViewAllTasks tableModelViewAllTasks;
    InformationDialog informationDialog;


    public void setTaskController(TaskController taskController) {
        this.taskController = taskController;
    }


    public void createMainWindow() {
        JFrame frame = new JFrame("Task Manager");
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
                if (countSelectedRows.length >0 ){
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
                if (countSelectedRows.length>0) {
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

        //ДОДЕЛАТЬ ФОРМАТИРОВАНИЕ РАЗМЕРА КОЛОНОК:
      /*  tableViewAllTasks.getTableHeader().setResizingAllowed(false);
        tableViewAllTasks.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tableViewAllTasks.getColumnModel().getColumn(0).setPreferredWidth(190);
        //tableViewAllTasks.getColumnModel().getColumn(2).setMinWidth(90);
        tableViewAllTasks.getColumnModel().getColumn(1).setPreferredWidth(200);*/
        // tableViewAllTasks.getColumnModel().getColumn(2).setPreferredWidth(10);


        //ДОБАВИТЬ ПЕРЕНОС ДАННЫХ В ЯЧЕЙКЕ НА ДРУГУЮ СТРОКУ:
        //.................


        //panelButtons.setBorder(new TitledBorder(""));

        //panelButtons.setSize(10,40);

        // panelButtons.setLayout(new BorderLayout().EAST);
        frame.setLayout(new FlowLayout());
        frame.add(new BorderLayout().WEST, panelViewAllTasks);
        frame.add(new BorderLayout().EAST, panelButtons);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setVisible(true);
    }


    private void createDialogForNewTask() {
        dialogCreatingTask = new DialogForNewTask();

        dialogCreatingTask.setTitle("Создание новой задачи");
        
        ButtonsPanelForDialog buttonsPanel = new ButtonsPanelForDialog();
        dialogCreatingTask.add(new BorderLayout().SOUTH,buttonsPanel);
        
        buttonsPanel.setListenerTo(buttonsPanel.getButtonOk(), checkDoNameAndTimeAlertsIsEmpty);
        buttonsPanel.setListenerTo(buttonsPanel.getButtonCancel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String taskName = dialogCreatingTask.getNameTask();
                String taskDescription = dialogCreatingTask.getDescriptionTask();
                String taskTimeAlerts = dialogCreatingTask.getTimeAlertsTask();
                String taskContactsPhone = dialogCreatingTask.getContactsPhone();
                String taskContactsName = dialogCreatingTask.getContactsName();

                // переделать взаимодействие через массив!!!!!!!!!!
                String[] textFields = new String[]{
                        taskName,
                        taskDescription,
                        taskTimeAlerts,
                        taskContactsPhone,
                        taskContactsName};

                //  Boolean fieldDontHaveTexts = taskName.isEmpty() || taskName == null;

                //while (fieldDontHaveTexts==true) {
                for (int i = 0; i < textFields.length; i++) {
                    if (textFields[i].isEmpty()) {
                        if (i == textFields.length - 1) {
                            dialogCreatingTask.dispose();

                        } else continue;

                    } else {
                        createDialogExit(dialogCreatingTask);
                        return;
                    }
                }

                // добавить звездочки в название полей

            }
        });
    }
    
    
    private void creatingDialogForEditingTask(final int countSelectedRows) {
    	final DialogForNewTask dialogForEditingTask = new DialogForNewTask();
        Task taskForEditing = taskController.getAllTasksModel().get(countSelectedRows);

        dialogForEditingTask.labelAndFieldPanel.setBorder(new TitledBorder(" "));



        String nameTaskField = taskController.getTasknameTaskField(taskForEditing);
        String descriptionTaskField = taskController.getDescriptionTaskField();
        String timeAlertsTaskField = taskController.getTimeAlertsTaskField();
        String contactsPhoneField = taskController.getContactsPhoneField();
        String contactsNameField = taskController.getContactsNameField();

        dialogForEditingTask.setTitle("Редактирование задачи: " + nameTaskField);

        dialogForEditingTask.setTextForFieldDialog(nameTaskField, descriptionTaskField,
                timeAlertsTaskField, contactsPhoneField, contactsNameField);
        
        ButtonsPanelForDialog buttonsPanel = new ButtonsPanelForDialog();
        dialogForEditingTask.add(new BorderLayout().SOUTH, buttonsPanel);
        
        
        //добавить лисенеры
        buttonsPanel.setListenerTo(buttonsPanel.getButtonOk(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        String taskName = dialogForEditingTask.getNameTask();
                        String taskDescription = dialogForEditingTask.getDescriptionTask();
                        String taskTimeAlerts = dialogForEditingTask.getTimeAlertsTask();
                        String taskContactsPhone = dialogForEditingTask.getContactsPhone();
                        String taskContactsName = dialogForEditingTask.getContactsName();

                        taskController.setFieldsTaskFromModel(countSelectedRows, taskName,
                                taskDescription, taskTimeAlerts, taskContactsPhone,
                                taskContactsName);

                        updateTable();
                        dialogForEditingTask.dispose();

                    }
                }
        );
        
        
        buttonsPanel.setListenerTo(buttonsPanel.getButtonCancel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dialogForEditingTask.dispose();

            }
        });
    }
        
				
				
				
			

        
    
				
			
    
    
    


    private void createDialogExit(JDialog dialogWindow) {
        new DialogForExit().exitingFromOtherDialog(dialogWindow);
    }

    private void updateTable() {
        Set<TableModelListener> listenersTableModel = tableModelViewAllTasks.getListeners();
        Iterator<TableModelListener> listenersIterator = listenersTableModel.iterator();
        while (listenersIterator.hasNext()) {
            TableModelListener listenerElem = listenersIterator.next();
            listenerElem.tableChanged(new TableModelEvent(tableModelViewAllTasks));
        }
    }

    private ActionListener checkDoNameAndTimeAlertsIsEmpty = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String taskName = dialogCreatingTask.getNameTask();
            String taskDescription = dialogCreatingTask.getDescriptionTask();
            String taskTimeAlerts = dialogCreatingTask.getTimeAlertsTask();
            String taskContactsPhone = dialogCreatingTask.getContactsPhone();
            String taskContactsName = dialogCreatingTask.getContactsName();

            // переделать взаимодействие через массив!!!!!!!!!!
            String[] textFields = new String[]{
                    taskName,
                    taskDescription,
                    taskTimeAlerts,
                    taskContactsPhone,
                    taskContactsName};

            Boolean nameDontHaveTextsInField = taskName.isEmpty() || taskName == null;

            Boolean timeAlertDontHaveTextsInField = taskTimeAlerts.isEmpty() || taskTimeAlerts == null;

            if ((nameDontHaveTextsInField) || (timeAlertDontHaveTextsInField)) {
                System.out.println("поля пустые");
                // содание информационного окна
                informationDialog = new InformationDialog();
                informationDialog.setTextInformationLabel("Поля \"Name Task\" и \"Time Alerts's Task\""
                        + "обязательно должны быть заполнены!");


                // добавить звездочки в название полей

            } else {
                taskController.saveNewTask(taskName, taskDescription,
                        taskTimeAlerts, taskContactsPhone,
                        taskContactsName);
                updateTable();

                dialogCreatingTask.dispose();
            }
        }
    };

   /* private ActionListener checkDoAllFieldIsEmpty = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String taskName = dialogCreatingTask.getNameTask();
            String taskDescription = dialogCreatingTask.getDescriptionTask();
            String taskTimeAlerts = dialogCreatingTask.getTimeAlertsTask();
            String taskContactsPhone = dialogCreatingTask.getContactsPhone();
            String taskContactsName = dialogCreatingTask.getContactsName();

            // переделать взаимодействие через массив!!!!!!!!!!
            String[] textFields = new String[]{
                    taskName,
                    taskDescription,
                    taskTimeAlerts,
                    taskContactsPhone,
                    taskContactsName};

            Boolean fieldDontHaveTexts = taskName.isEmpty() || taskName == null;

            while (fieldDontHaveTexts) {
                for (int i=0; i<textFields.length; i++) {
                    if (textFields[i].isEmpty()  ){
                        if (i== textFields.length-1) {
                            //!!!!!!!!!!!!!!!!!!!!!
                            // fieldDontHaveTexts = true;
                            System.out.println("поля пустые");
                            dialogCreatingTask.dispose();
                        }

                    } else {
                        fieldDontHaveTexts = false;
                    }
                }

                // добавить звездочки в название полей

            }
            if (fieldDontHaveTexts == false){

                dialogCreatingTask.dispose();
            }



        }
    };*/


}






