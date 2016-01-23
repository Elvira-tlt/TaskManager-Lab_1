package controllers;

import models.Task;
import models.TaskModel;
import views.TaskView;

import java.util.List;

public class TaskController {
	
    //private Task task;
    private TaskModel model;
    private TaskView view;
    private TaskOperation taskOperation;
    
    public TaskController(TaskModel taskModel, TaskView taskView) {
    	this.model = taskModel;
        this.view = taskView;
        taskOperation = new TaskOperation(this);
    }
    
    public List<Task> getAllTasksModel() {
        return model.getTaskModel();
    }

    public void saveNewTask (String nameTask, String descriptionTask, String timeAlertsTask,
                                String contactsPhone, String contactsName) {
        taskOperation.createNewTaskFullFields(nameTask, descriptionTask, timeAlertsTask, contactsPhone, contactsName);
        
        model.addTaskToModel(taskOperation.getTaskForSave());
    }
    
    public void deleteSelectedTask (int numberTaskInListTasks) {
    	/*List<Task> tasks = model.getTaskModel();
    	Task taskForDelete = tasks.get(numberTaskInListTasks);*/
    	model.deleteTaskFromModel(numberTaskInListTasks);
    	
    }
    
    public void setFieldsTaskFromModel(int countSelectedRows,String nameTask, String descriptionTask, String timeAlertsTask,
            String contactsPhone, String contactsName) {
    	taskOperation.setTask(model.getTaskModel().get(countSelectedRows)); 
    	
    	taskOperation.setFieldsTask(nameTask, descriptionTask, timeAlertsTask, contactsPhone, contactsName);
}

    //for editing task's datas:
   
    
   public String getTasknameTaskField(Task task) {
       taskOperation.setTask(task);
       return taskOperation.getTaskName();
   }
    public String getDescriptionTaskField() {
        return taskOperation.getTaskDescription();
    }
    public String getTimeAlertsTaskField() {
        return taskOperation.getTaskTimeAlerts();
    }
   public String getContactsPhoneField() {
        return taskOperation.getTaskContactsPhone();
    }
    public String getContactsNameField() {
        return taskOperation.getTaskContactsName();
    }


    
}
