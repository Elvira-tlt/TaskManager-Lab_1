package MainTaskManagerClases;

import controllers.TaskController;
import models.Task;
import models.TaskModel;
import views.TaskView;

public class TaskManagerRalization {
	private TaskModel model;
	private static TaskView view;
	private static TaskController controller;

	public static void main(String[] args) {
		TaskManagerRalization taskManager = new TaskManagerRalization();
		taskManager.startingTaskManager();
	}
	
	public TaskManagerRalization() {
		model = new TaskModel();
		view = new TaskView();
		controller = new TaskController(model, view);
        view.setTaskController(controller);
	}

	private void startingTaskManager() {
		view.createMainWindow();
	}

	private static Task getNewTask() {
		return new Task();
	}

	// возвращает models Task из базы данных, ...
	private static Task retrieveTaskFromDatabase() {
		//TODO

		Task task = new Task(); // сделать не создание, а загрузку из файла(базы
								// данных)
		return task;
	}

}
