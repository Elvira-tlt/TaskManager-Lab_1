package models;

import java.util.Date;

public class Task {
    private String name;
    private String description;
    private Date timeAlerts;
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
        return  new Date(timeAlerts.getTime());
    }

    public void setTimeAlerts(Date timeAlerts) {
            this.timeAlerts = new Date(timeAlerts.getTime());
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

}
