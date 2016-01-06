import java.util.Date;

/**
 * Created by User on 05.01.2016.
 */
public class Task {
    private String name;
    private String description;
    private Date notificationDate; // проверить формат, Прочитать про дату (время) (time alerts??)
    private String contacts; //возможно переделать на класс Contacts (name, telephone number)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date dateOfNotification) {
        this.notificationDate = dateOfNotification;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
