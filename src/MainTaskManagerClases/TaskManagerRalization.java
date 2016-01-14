package MainTaskManagerClases;

import controllers.TaskController;
import models.Task;
import models.TaskModel;
import views.TaskView;

public class TaskManagerRalization {
	private static TaskModel model; //м.б. сделать static
	private static TaskView view;
	private static TaskController controller;

	public TaskManagerRalization() {
		model = new TaskModel();
		view = new TaskView();
		controller = new TaskController(model, view);
        view.setTaskController(controller);
	}

	public static void main(String[] args) {
		TaskManagerRalization taskManager = new TaskManagerRalization();
		taskManager.startingTaskManager();




		/*
		 * models.Task task = retrieveTaskFromDatabase(); views.TaskView view = new
		 * views.TaskView(); otherMainTaskClases.TaskController controller = new otherMainTaskClases.TaskController(task,
		 * view);
		 * 
		 * controller.updateTaskView();
		 * 
		 * controller.setTaskName("Переименованная задача");
		 * 
		 * controller.updateTaskView();
		 */

	}

	private void startingTaskManager() {
		view.createJFrame();
	}

	// Job with models.Task (передача models.Task контроллеру):

	// Завершение перечисления работы с models.Task

	private static Task getNewTask() {
		return new Task();
	}

	// возвращает models.Task из базы данных, ...
	private static Task retrieveTaskFromDatabase() {
		// ToDo

		Task task = new Task(); // сделать не создание, а загрузку из файла(базы
								// данных)
		return task;
	}

}
