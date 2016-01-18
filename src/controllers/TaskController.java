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
    
}
