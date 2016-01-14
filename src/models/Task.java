package models;

/**
 * Created by User on 05.01.2016.
 */
public class Task {
    private String name;
    private String description;
    private String timeAlerts; // проверить формат, Прочитать про дату (время) (time alerts??)
    private String contacts;

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

    public String getTimeAlerts() {
        return timeAlerts;
    }

    public void setTimeAlerts(String timeAlerts) {
        this.timeAlerts = timeAlerts;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contactsPhone, String contactsName) {
        //доработать, неверно отображается!!!!
        String contactsData;
         if ((contactsPhone != null)&& (contactsName != null)) {
            contactsData = "phone: " + contactsPhone + "\ncontacts name: " + contactsName;
        } else if ((contactsPhone != null)&& (contactsName == null)) {
            contactsData = "phone: " + contactsPhone;
        } else if ((contactsPhone == null)&& (contactsName != null)) {
            contactsData = "contacts name: " + contactsName;
        } else {
            contactsData = " ";
        }
    	this.contacts = contactsData;
    }

    //ПОЗЖЕ УДАЛИТЬ:
    public String toString () {
        return "Task:"+ name + " " + description+ " " + timeAlerts + " " + contacts;
    }
}
