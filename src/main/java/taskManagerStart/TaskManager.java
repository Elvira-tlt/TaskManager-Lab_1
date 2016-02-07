package taskManagerStart;

import notification.TaskNotification;
import controllers.TaskController;
import models.TaskModel;
import savingAndLoadingTasks.Database;
import views.TaskView;

public class TaskManager {
	private TaskModel model;
	private static TaskView view;
	private static TaskController controller;
	private Thread threadOfTimeTracking;
	private Database tasksPersistence;

	public static void main(String[] args) {
		TaskManager taskManager = new TaskManager();
		taskManager.startingTaskManager();
	}
	
	public TaskManager() {
		model = new TaskModel();

		tasksPersistence = new Database(model.getTaskModel());

		view = new TaskView(tasksPersistence);

		controller = new TaskController(model, view);
        view.setTaskController(controller);
	}
	
	private void startingTaskManager() {
		view.createMainWindow();
		tasksPersistence.loadingTasksFromFile();
		startThreadForNotification();
	}

	private void startThreadForNotification() {
		threadOfTimeTracking = new Thread(new TaskNotification(model, view));
		threadOfTimeTracking.start();
	}
}
