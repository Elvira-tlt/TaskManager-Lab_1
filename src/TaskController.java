import java.util.Date;

/**
 * Created by User on 05.01.2016.
 */
public class TaskController {
    private Task model;
    private TaskView view;

    public TaskController(Task model, TaskView taskView) {
        this.model = model;
        this.view = taskView;
    }

    public void setTaskName (String name) {
        model.setName(name);
    }

    public String getTaskName() {
        return model.getName();
    }

    public void setTaskDescription(String description) {
        model.setDescription(description);
    }

    public String getTaskDescription() {
        return model.getDescription();
    }

    public void setTaskNotificationDate(Date notificationDate) {
        model.setNotificationDate(notificationDate);
    }

    public Date getTaskNotificationDate() {
        return model.getNotificationDate();
    }

    public void setTaskContacts(String contacts) {
        model.setContacts(contacts);
    }

    public String getTaskContacts() {
        return model.getContacts();
    }

    public void updateTaskView() {
        view.printTaskDetails(getTaskName(), getTaskDescription(), getTaskNotificationDate(), getTaskContacts());
    }




}
