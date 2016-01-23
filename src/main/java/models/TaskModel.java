package models;

import java.util.ArrayList;
import java.util.List;

public class TaskModel {
    private List<Task> taskModel = new ArrayList<>();

    public List<Task> getTaskModel() {
        return taskModel;
    }

    public void addTaskToModel(Task task) {
        taskModel.add(task);
    }
    
    public void deleteTaskFromModel (int numberTaskInListTasks) {
    	taskModel.remove(numberTaskInListTasks);
	}

    
    
    
    
    

}
