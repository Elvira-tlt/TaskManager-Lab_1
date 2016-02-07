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

//import models.TaskModel;


public class NotificationWindow extends JFrame{
	
	private Task taskForNotification;
	static TaskModel taskModel;
	private TaskView taskView;
	
	private JButton stopButton = new JButton("Стоп");
	private JButton prorogueButton = new JButton("Отложить на 10 минут");
	//private JRadioButton prorogueRadioButton = new JRadioButton();
	
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
		Boolean contactsPhoneNotEmpty = contactsPhone != null || !contactsPhone.isEmpty() ;
		Boolean contactsNameNotEmpty = contactsName != null || !contactsName.isEmpty() ;
		String contacts = "-";
		if (contactsPhoneNotEmpty || contactsNameNotEmpty ) {
			contacts = "телефон: " + contactsPhone + "\n имя контакта: " + contactsName;
		} else if (contactsPhoneNotEmpty) {
			contacts = "телефон: " + contactsPhone;
		} else {
			contacts = "имя контакта: " + contactsName;
		}


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

		datasPanel.setLayout(new GridLayout(3,2));

		//setLayout(new FlowLayout());
		setSize(500,300);
		setVisible(true);
	}
	
	ActionListener stop = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//taskModel.getTaskModel().remove(taskForNotification);
			taskView.updateTable();
			dispose();
		}
	};
	
	
	ActionListener prorogue = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//prorogueRadioButton.setSelected(true);
			
			//прибавить 10 минут ко времени оповещения
			
			//Date dateTaskForProrogue = taskForNotification.getTimeAlerts();
			Calendar calendarTaskForProrogue = new GregorianCalendar();
			calendarTaskForProrogue.add(Calendar.MINUTE, 10);
			
		//	Date dateTaskAfterProrogue = new Date();
			
			///проверить правильность работы
			//  dateTaskAfterProrogue.setTime(calendarTaskForProrogue.getTimeInMillis());
			taskForNotification.setTimeAlerts(calendarTaskForProrogue.getTime());

			taskModel.addTaskToModel(taskForNotification);
			
			taskView.updateTable();
			dispose();
			
		}
	};
	
	ActionListener forProrogue = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			/*Date [] timesForProrogue = {
					//добавить время для выбора
			};
			JComboBox<Date> comboBoxProrogueTime = new JComboBox(timesForProrogue);
			
			proroguePanel.add(comboBoxProrogueTime);
			*/
			//возвращает значение, на которое нужно отложить (добавить к текущему значению)
			//Date selectedTimeForProrogue = (Date)comboBoxProrogueTime.getSelectedItem();
			
			
			
			
		}
	};

	
	
			
	
	
}
