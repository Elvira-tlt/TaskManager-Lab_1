package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import models.TaskModel;
import controllers.TaskController;

public class TaskView {
    TaskController taskController;
    DialogForNewTask dialogCreatingTask;
    TableModelViewAllTasks tableModelViewAllTasks;


    public void setTaskController (TaskController taskController) {
        this.taskController = taskController;
    }
    
    
    

    public void createMainWindow () {
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
        JTable tableViewAllTasks = new JTable(tableModelViewAllTasks);

      /*  panelViewAllTasks.add(new BorderLayout().NORTH,new JLabel("Созданные задачи"));*/
        panelViewAllTasks.add(tableViewAllTasks);
        panelViewAllTasks.add(new JScrollPane(tableViewAllTasks));



        


        createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDialogForNewTask();
            }
        });

        //Редактирование задач
        //???создать Event, вызывающий окно редактирования данных 
        //выбранной задачи?
        
        editTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int[] countSelectedRows = tableViewAllTasks.getSelectedRows();
				for (int i = 0; i<countSelectedRows.length;i++) {

					//Создать окно редактирования (назвать его так же)
                    //creating dialog for editing selected task
                   // Task taskForEditing = taskController.getAllTasksModel().get(countSelectedRows[i]);
                    //taskController.getContactsNameField(taskController.getAllTasksModel().get(countSelectedRows[i]));


                    DialogForNewTask dialogForEditingTask = new DialogForNewTask();
                    String nameTaskField = taskController.getTasknameTaskField(taskController.getAllTasksModel().get(countSelectedRows[i]));
                    String descriptionTaskField = dialogForEditingTask.getDescriptionTask();
                    String timeAlertsTaskField = dialogForEditingTask.getTimeAlertsTask();
                    String contactsPhoneField = dialogForEditingTask.getContactsPhone();
                    String contactsNameField = dialogForEditingTask.getContactsName();

                    dialogForEditingTask.setTextForFieldDialog(nameTaskField,descriptionTaskField,
                            timeAlertsTaskField, contactsPhoneField, contactsNameField);


                }


            }
        });
        
        
        deleteTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int[] countSelectedRows = tableViewAllTasks.getSelectedRows();
                for (int i = countSelectedRows.length-1; i>=0; i--) {
                    taskController.deleteSelectedTask(countSelectedRows[i]);
				}
						updateTable();
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


    private void createDialogForNewTask () {
        dialogCreatingTask = new DialogForNewTask();
        createButtonsAndListenerForDialog(dialogCreatingTask);
        
    }

	private void createButtonsAndListenerForDialog(JDialog dialogWindow) {
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		dialogWindow.add(new BorderLayout().SOUTH, buttonsPanel);
		
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Cancel");
		
		//adding listener for Buttons
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskController.saveNewTask(dialogCreatingTask.getNameTask(), dialogCreatingTask.getDescriptionTask(),
                        dialogCreatingTask.getTimeAlertsTask(), dialogCreatingTask.getContactsPhone(),
                        dialogCreatingTask.getContactsName());
                updateTable();

                dialogCreatingTask.dispose();
            }
        });
        
       

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                //ВЫВОДИТЬ, ЕСЛИ ВВЕДЕНО ХОТЬ КАКОЕ-ТО ЗНАЧЕНИЕ, ЕСЛИ НЕТ, ТО ПРОСТО ВЫХОДИТЬ, НЕ ЗАПРАШИВАЯ СОХРАНЕНИЯ ДАННЫХ
				createDialogExit(dialogWindow);

			}
		});
		
		
	
		//Добавить панель для кнопок
		buttonsPanel.add(ok);
		buttonsPanel.add(cancel);
		
	}
	
	private void createDialogExit(JDialog dialogWindow){
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
   
    public static void main(String[] args) {
        TaskView view = new TaskView();
        view.setTaskController(new TaskController(new TaskModel(), view));
        view.createMainWindow();

        
    }



}
