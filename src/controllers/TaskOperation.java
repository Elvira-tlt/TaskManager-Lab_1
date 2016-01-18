package controllers;

import models.Task;

public class TaskOperation {
	private Task task;
	private TaskController taskController;
	
	/*public void setTask(Task task) {
		this.task = task;
	}*/
	
	public TaskOperation(TaskController taskController) {
		this.taskController = taskController;
	}
	
	
	
	//Setters models.Task
    public void setTaskName (String name) {
        task.setName(name);
    }
    
    public void setTaskDescription(String description) {
        task.setDescription(description);
    }

    public void setTaskTimeAlerts(String  timeAlerts) {
        task.setTimeAlerts(timeAlerts);
    }
    
    public void setTaskContacts(String contactsPhone, String contactsName) {
        task.setContacts(contactsPhone, contactsName);
    }
    
    
    public void createNewTaskFullFields(String nameTask, String descriptionTask, String timeAlertsTask,
            String contactsPhone, String contactsName) {
    	task = new Task();
    	setFieldsTask(nameTask, descriptionTask, timeAlertsTask, contactsPhone, contactsName);
    }
  
    
    private void setFieldsTask(String nameTask, String descriptionTask, String timeAlertsTask,
                               String contactsPhone, String contactsName) {
        setTaskName(nameTask);
        setTaskDescription(descriptionTask);
        setTaskTimeAlerts(timeAlertsTask); //Преобразовать в Дату
        setTaskContacts(contactsPhone, contactsName);
    }
    
    public Task getTaskForSave () {
    	//сохранение задачи через контроллер в модель
    	return task;
    }
    
    
    
  //Getters models.Task
    public String getTaskName() {
        return task.getName();
    }

    public String getTaskDescription() {
        return task.getDescription();
    }

    public String getTaskNotificationDate() {
        return task.getTimeAlerts();
    }

    public String getTaskContacts() {
        return task.getContacts();
    }
    

}
