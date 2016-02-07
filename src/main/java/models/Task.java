package models;

import java.util.Date;

public class Task {
    private String name;
    private String description;
    private Date timeAlerts; // проверить формат, Прочитать про дату (время)
    private String contactsPhone;
    private String contactsName;

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

    public  Date getTimeAlerts() {
        return timeAlerts;
    }

    public void setTimeAlerts(Date timeAlerts) {
        this.timeAlerts = timeAlerts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }


    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }
    
    public String getContactsName() {
        return contactsName;
    }
    
    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    //ПОЗЖЕ УДАЛИТЬ:
    public String toString () {
        return "Task:"+ name + " " + description+ " " + timeAlerts + " " + contactsPhone + " " + contactsName;
    }
}
