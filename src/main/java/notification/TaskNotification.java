package notification;


import models.Task;
import models.TaskModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import views.TaskView;

public class TaskNotification implements Runnable {
    private List<Task> tasksForNotificationQueue = new ArrayList<>();
    private TaskModel taskModel;
    private TaskView taskView;
    private NotificationWindow notificationWindow;

    public TaskNotification(TaskModel model, TaskView view) {
        taskModel = model;
        taskView = view;
    }

    private void setModelAndViewForFrame(NotificationWindow notificationWindow) {
        notificationWindow.setTaskModel(taskModel);
        notificationWindow.setTaskView(taskView);
    }

    @Override
    public void run() {
        try {
            while (true) {
                tasksForNotificationQueue = taskModel.getTaskModel();

                    Date currentValueDate = new Date();

                    for (int i = 0; i < tasksForNotificationQueue.size(); i++) {
                        Date dateTask = tasksForNotificationQueue.get(i).getTimeAlerts();
                        if (dateTask.getTime() < currentValueDate.getTime()) {
                            Task taskForNotification = tasksForNotificationQueue.get(i);

                            taskModel.getTaskModel().remove(taskForNotification);
                            notificationWindow = new NotificationWindow(taskForNotification);
                            setModelAndViewForFrame(notificationWindow);
                        }
                }
                TimeUnit.SECONDS.sleep(20);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
