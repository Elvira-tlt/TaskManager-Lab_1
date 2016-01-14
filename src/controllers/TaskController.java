package controllers;

import models.Task;
import models.TaskModel;
import views.TaskView;

import java.util.List;

public class TaskController {
	
    //private Task task;
    private TaskModel model; // ПЕРЕСМОТРЕТЬ, ВЕЗДЕ ЛИ ПРАВИЛЬНО НАПИСАНЫ В АРГУМЕНТАХ МОДЕЛЬ И ЗАДАЧА
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
        
        model.addTaskToModel(taskOperation.getTaskForSave()); // организовать передачу task



        //удалить в будущем, чтобы не отображалось
        //сделать отдельным методом, не требующим обращения к полю модель
        System.out.println("Model: " + model.getTaskModel());
    }
    
    




    
//СНЯТЬ КОММЕНТАРИИ
   /* public void updateTaskView() {
        view.printTaskDetails(getTaskName(), getTaskDescription(), getTasktimeAlerts(), getTaskContacts());
    }

*/
public static void main(String[] args) {
   /* TaskModel model = new TaskModel();
    TaskController taskController = new TaskController(model, new TaskView());


    for (int i=0; i<10; i++) {
        Task task = new Task();
        String name = i+"# task";
        task.setName(name);
        model.addTaskToModel(task);
    }


    System.out.println(taskController.getAllTasksModel());*/
}




}
