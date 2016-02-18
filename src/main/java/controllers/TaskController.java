package controllers;

import models.Task;
import models.TaskModel;
import views.TaskView;

import java.util.Date;
import java.util.List;

public class TaskController {
	
    private TaskModel model;
    private TaskOperation taskOperation;
    
    public TaskController(TaskModel taskModel) {
    	this.model = taskModel;
        taskOperation = new TaskOperation();
    }
    
    public List<Task> getAllTasksModel() {
        return model.getTaskModel();
    }

    public void saveNewTask (String nameTask, String descriptionTask, Date timeAlertsTask,
                                String contactsPhone, String contactsName) {
        taskOperation.createNewTaskFullFields(nameTask, descriptionTask, timeAlertsTask, contactsPhone, contactsName);
        
        model.addTaskToModel(taskOperation.getTaskForSave());
    }
    
    public void deleteSelectedTask (int numberTaskInListTasks) {
    	model.deleteTaskFromModel(numberTaskInListTasks);
    	
    }

    public void setValueTaskFromModel(Task task, String nameTask, String descriptionTask, Date timeAlertsTask,
                                      String contactsPhone, String contactsName) {
        taskOperation.setTask(task);

        taskOperation.setFieldsTask(nameTask, descriptionTask, timeAlertsTask, contactsPhone, contactsName);
    }

   public String getTasknameTaskField(Task task) {
       taskOperation.setTask(task);
       return taskOperation.getTaskName();
   }
    public String getDescriptionTaskField() {
        return taskOperation.getTaskDescription();
    }
    public Date getTimeAlertsTaskField() {
        return taskOperation.getTaskTimeAlerts();
    }
   public String getContactsPhoneField() {
        return taskOperation.getTaskContactsPhone();
    }
    public String getContactsNameField() {
        return taskOperation.getTaskContactsName();
    }
}
