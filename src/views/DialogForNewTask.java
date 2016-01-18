package views;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class DialogForNewTask extends JDialog {
    private JTextField nameTaskField;
    private JTextField descriptionTaskField;
    private JTextField timeAlertsTaskField;
    private JTextField contactsPhoneField;
    private JTextField contactsNameField;


    public DialogForNewTask() {
        createLabelAndFieldForContainer();

        this.setName("Create new task");
        this.setSize(550, 400);
        this.setVisible(true);
    }

    public void setTextFieldDialogView(JTextField nameTask, JTextField descriptionTask,
                                       JTextField timeAlertsTask, JTextField contactsPhone,
                                       JTextField contactsName) {
        this.nameTaskField = nameTask;
        this.descriptionTaskField = descriptionTask;
        this.timeAlertsTaskField = timeAlertsTask;
        this.contactsPhoneField = contactsPhone;
        this.contactsNameField = contactsName;
    }


    private void createLabelAndFieldForContainer() {
        //Create Panel For Labels and Fields
        JPanel labelAndFieldPanel = new JPanel(new GridLayout(6, 2));
        labelAndFieldPanel.setBorder(new TitledBorder(" Fill out the information on the task to be created")); //ЗАПОЛНИТЬ???
        this.add(new BorderLayout().CENTER, labelAndFieldPanel);

        //Creating Label and Field for Container:
        JLabel nameTaskLabel = new JLabel("Name Task");
       nameTaskField = new JTextField();

        JLabel descriptionTaskLabel = new JLabel("Description Task");
        descriptionTaskField = new JTextField();

        JLabel timeAlertsTaskLabel = new JLabel("Time Alerts's Task");
        timeAlertsTaskField = new JTextField();

        JLabel contactsLabel = new JLabel("Contacts");
        JLabel nullLabel = new JLabel();

        JLabel contactsPhoneLabel = new JLabel("          - contacts Phone");
        contactsPhoneField = new JTextField();

        JLabel contactsNameLabel = new JLabel("           - contacts Name");
        contactsNameField = new JTextField();

        //Adding components:
        labelAndFieldPanel.add(nameTaskLabel);
        labelAndFieldPanel.add(nameTaskField);
        labelAndFieldPanel.add(descriptionTaskLabel);
        labelAndFieldPanel.add(descriptionTaskField);
        labelAndFieldPanel.add(timeAlertsTaskLabel);
        labelAndFieldPanel.add(timeAlertsTaskField);
        labelAndFieldPanel.add(contactsLabel);
        labelAndFieldPanel.add(nullLabel);
        labelAndFieldPanel.add(contactsPhoneLabel);
        labelAndFieldPanel.add(contactsPhoneField);
        labelAndFieldPanel.add(contactsNameLabel);
        labelAndFieldPanel.add(contactsNameField);
    }


    public String getNameTask() {
        return nameTaskField.getText();
    }

    public String getDescriptionTask() {
        return descriptionTaskField.getText();
    }

    public String getTimeAlertsTask() {
        return timeAlertsTaskField.getText();
    }

    public String getContactsPhone() {
        return contactsPhoneField.getText();
    }

    public String getContactsName() {
        return contactsNameField.getText();
    }

}
