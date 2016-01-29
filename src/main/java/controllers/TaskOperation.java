package controllers;

import models.Task;

import java.util.Date;

public class TaskOperation {
	private Task task;
	private TaskController taskController;
	
	public void setTask(Task task) {
		this.task = task;
	}
	
	public TaskOperation(TaskController taskController) {
		this.taskController = taskController;
	}
	
	//Setters models Task
    public void setTaskName (String name) {
        task.setName(name);
    }
    
    public void setTaskDescription(String description) {
        task.setDescription(description);
    }

    public void setTaskTimeAlerts(Date timeAlerts) {
        task.setTimeAlerts(timeAlerts);
    }
    
    public void setTaskContactsPhone(String contactsPhone) {
        task.setContactsPhone(contactsPhone);
    }
    
    public void setTaskContactsName(String contactsName) {
        task.setContactsName(contactsName);
    }
    
    public void createNewTaskFullFields(String nameTask, String descriptionTask, Date timeAlertsTask,
            String contactsPhone, String contactsName) {
    	task = new Task();
    	setFieldsTask(nameTask, descriptionTask, timeAlertsTask, contactsPhone, contactsName);
    }
  
    
    public void setFieldsTask(String nameTask, String descriptionTask, Date timeAlertsTask,
                               String contactsPhone, String contactsName) {
        setTaskName(nameTask);
        setTaskDescription(descriptionTask);
        setTaskTimeAlerts(timeAlertsTask); //Преобразовать в Дату
        setTaskContactsPhone(contactsPhone);
        setTaskContactsName(contactsName);
        
    }
    
    public Task getTaskForSave () {
    	//сохранение задачи через контроллер в модель
    	return task;
    }
    
    
    
  //Getters models Task
    public String getTaskName() {
        return task.getName();
    }

    public String getTaskDescription() {
        return task.getDescription();
    }

    public Date getTaskTimeAlerts() {
        return task.getTimeAlerts();
    }

    public String getTaskContactsPhone() {
        return task.getContactsPhone();
    }
    
    public String getTaskContactsName() {
        return task.getContactsName();
    }
    

}
