package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import controllers.TaskController;

public class TaskView {
    TaskController taskController;
    DialogView dialogCreatingTask;

    public void setTaskController (TaskController taskController) {
        this.taskController = taskController;
    }

    //реализовать методы:
    //      выводящие информацию о задаче
    //      для создания задачи (заполнения полей) ?????????

    public void printTaskDetails (String taskName, String taskDescription,
                                  Date taskNotificationDate, String taskContacts) {
        System.out.println("models.Task \" " + taskName + " \" :");
        System.out.println("Description: " + taskDescription);
        System.out.println("Notification date: " + taskNotificationDate);
        System.out.println("Contacts: " + taskContacts);
    }

    public void createJFrame () {
        JFrame frame = new JFrame("models.Task Manager");
        JButton createTaskButton = new JButton("Create models.Task / Создать задачу");
        JButton viewTasksButton = new JButton("View Tasks/ Посмотреть задачи");
        JPanel panelButtons = new JPanel();

        createTaskButton.setToolTipText("Создание новой задачи");
        viewTasksButton.setToolTipText("Просмотреть имеющиеся задачи");


        createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDialogForNewTask();
            }
        });


        panelButtons.add(createTaskButton);
        panelButtons.add(viewTasksButton);
        panelButtons.setBorder(new TitledBorder(""));

       // panelButtons.setSize(10,40);



      // panelButtons.setLayout(new BorderLayout().EAST);


        frame.add(/*new BorderLayout().EAST, */panelButtons);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setVisible(true);
    }


    private void createDialogForNewTask () {
        dialogCreatingTask = new DialogView();
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
                dialogCreatingTask.dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //м.б. добавить запрос на отмену заполнения формы для новой задачи??
                JDialog dialogExitFromCreatingTask = new JDialog();
                JLabel questionAboutExit = new JLabel("Are you sure you want to exit the task creation? All unsaved data will be lost.");
                JPanel exitButtonsPanel = new JPanel();
                JButton okExit = new JButton("Yes, exit");
                JButton cancelExit = new JButton("Cancel exit");

                exitButtonsPanel.add(okExit);
                exitButtonsPanel.add(cancelExit);

                //exitButtonsPanel.setLayout(new FlowLayout());

                dialogExitFromCreatingTask.add(questionAboutExit);
                dialogExitFromCreatingTask.add(new BorderLayout().SOUTH, exitButtonsPanel);

                dialogExitFromCreatingTask.setLayout(new FlowLayout());
                dialogExitFromCreatingTask.setSize(600,120);
                dialogExitFromCreatingTask.setVisible(true);





                dialogWindow.dispose();
            }
        });
	
		
		//
		
		//ДОбавить панель для кнопок
		buttonsPanel.add(ok);
		buttonsPanel.add(cancel);
		
	}
   
    
    
    public static void main(String[] args) {
        TaskView view = new TaskView();
        view.createJFrame();

        
    }



}
