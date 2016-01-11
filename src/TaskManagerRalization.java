import java.text.DateFormat;
import java.util.Date;

/**
 * Created by User on 05.01.2016.
 */
public class TaskManagerRalization {
    public static void main(String[] args) {
        Task task = retrieveTaskFromDatabase();
        TaskView view = new TaskView();
        TaskController controller = new TaskController(task, view);

        controller.updateTaskView();

        controller.setTaskName("Переименованная задача");

        controller.updateTaskView();


    }

    //возвращает Task из базы данных, ...
    private static Task retrieveTaskFromDatabase() {
        Task task = new Task();
        task.setName("Заказать воду");
        task.setDescription("Заказать в компании Айсберг на утро воду");
        task.setNotificationDate(/*"06.01.2016 09:05"*/ null);
        task.setContacts("76-60-06");
        return  task;
    }
}




