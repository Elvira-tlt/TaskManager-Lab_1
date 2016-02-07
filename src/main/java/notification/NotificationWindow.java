package notification;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import views.TaskView;
import models.Task;
import models.TaskModel;

public class NotificationWindow extends JFrame {

    private Task taskForNotification;
    static TaskModel taskModel;
    private TaskView taskView;

    private JButton stopButton = new JButton("Стоп");
    private JButton prorogueButton = new JButton("Отложить на 10 минут");

    private JPanel panelButtons = new JPanel();
    private JPanel datasPanel = new JPanel();

    public void setTaskModel(TaskModel model) {
        taskModel = model;
    }

    public void setTaskView(TaskView view) {
        taskView = view;
    }


    public NotificationWindow(Task taskForNotification) {
        this.taskForNotification = taskForNotification;

        stopButton.addActionListener(stop);
        prorogueButton.addActionListener(prorogue);

        JLabel nameLabel = new JLabel("Название/ имя задачи: ");
        JLabel taskName = new JLabel(taskForNotification.getName());
        JLabel descriptionLabel = new JLabel("Описание: ");
        String description = taskForNotification.getDescription();
        if (description == null) {
            description = "-";
        }
        JLabel taskDescription = new JLabel(description);
        JLabel contactsLabel = new JLabel("Контакты: ");

        String contactsPhone = taskForNotification.getContactsPhone();
        String contactsName = taskForNotification.getContactsName();
        String contacts = contactsPhone + " " + contactsName;

        JLabel contactsTask = new JLabel(contacts);

        datasPanel.add(nameLabel);
        datasPanel.add(taskName);
        datasPanel.add(descriptionLabel);
        datasPanel.add(taskDescription);
        datasPanel.add(contactsLabel);
        datasPanel.add(contactsTask);


        panelButtons.add(stopButton);
        panelButtons.add(prorogueButton);

        add(new BorderLayout().SOUTH, panelButtons);
        add(datasPanel);

        datasPanel.setLayout(new GridLayout(3, 2));

        setSize(500, 300);
        setVisible(true);
    }

    ActionListener stop = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            taskView.updateTable();
            dispose();
        }
    };


    ActionListener prorogue = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Calendar calendarTaskForProrogue = new GregorianCalendar();
            calendarTaskForProrogue.add(Calendar.MINUTE, 10);

            taskForNotification.setTimeAlerts(calendarTaskForProrogue.getTime());

            taskModel.addTaskToModel(taskForNotification);

            taskView.updateTable();
            dispose();
        }
    };

};



