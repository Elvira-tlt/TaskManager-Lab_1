import com.sun.xml.internal.ws.api.FeatureListValidatorAnnotation;

import java.util.Date;

/**
 * Created by User on 05.01.2016.
 */
public class TaskView {
    //реализовать методы:
    //      выводящие информацию о задаче
    //      для создания задачи (заполнения полей) ?????????

    public void printTaskDetails (String taskName, String taskDescription,
                                  Date taskNotificationDate, String taskContacts) {
        System.out.println("Task \" " + taskName + " \" :");
        System.out.println("Description: " + taskDescription);
        System.out.println("Notification date: " + taskNotificationDate);
        System.out.println("Contacts: " + taskContacts);
    }



}
