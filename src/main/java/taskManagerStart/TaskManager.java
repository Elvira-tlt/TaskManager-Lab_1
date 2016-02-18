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
		new TaskManager().startingTaskManager();
	}
	
	public TaskManager() {

	}

	private void startingTaskManager() {
		tasksPersistence = new Database();
		model = new TaskModel();
		model.setTasksToModel(tasksPersistence.loadingTasksFromFile());
		view = new TaskView();
		controller = new TaskController(model);
		view.setTaskController(controller);
		view.createMainWindow();
		startThreadForNotification();
	}

	private void startThreadForNotification() {
		threadOfTimeTracking = new Thread(new TaskNotification(model, view));
		threadOfTimeTracking.start();
	}
}
