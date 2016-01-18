package models;

public class Task {
    private String name;
    private String description;
    private String timeAlerts; // проверить формат, Прочитать про дату (время)
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
        String contactsData;

        boolean isNotContactPhone = ((contactsPhone==null) || contactsPhone.isEmpty());
        boolean isNotContactName = ((contactsName==null) || contactsName.isEmpty());

        if (!isNotContactPhone && !isNotContactName) {
            contactsData = "phone: " + contactsPhone + "\n contacts name: " + contactsName;
        } else if (!isNotContactPhone && isNotContactName) {
            contactsData = "phone: " + contactsPhone;
        } else if (isNotContactPhone && !isNotContactName) {
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
