package views;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DialogForNewTask extends JDialog {
    public JPanel labelAndFieldPanel;

    private JTextField nameTaskField;
    private JTextField descriptionTaskField;
    private JTextField timeAlertsTaskField;
    private JTextField contactsPhoneField;
    private JTextField contactsNameField;


    public DialogForNewTask() {
      //  DateFormat format = new SimpleDateFormat("dd.MMMM.yyyy hh:mm");


        labelAndFieldPanel = new JPanel(new GridLayout(6, 2));
        labelAndFieldPanel.setBorder(new TitledBorder(" Заполните информацию о создаваемой вами задаче")); //ЗАПОЛНИТЬ???
        this.add(new BorderLayout().CENTER, labelAndFieldPanel);

        //Creating Label and Field for Container:
        JLabel nameTaskLabel = new JLabel("Название/имя задачи *");
        nameTaskField = new JTextField();

        JLabel descriptionTaskLabel = new JLabel("Описание");
        descriptionTaskField = new JTextField();

        JLabel timeAlertsTaskLabel = new JLabel("Дата и время напоминания/выполнения *");
        timeAlertsTaskField = new JTextField();

        JLabel contactsLabel = new JLabel("Контактная информация:");
        JLabel nullLabel = new JLabel();

        JLabel contactsPhoneLabel = new JLabel("          - контактный телефон");
        contactsPhoneField = new JTextField();

        JLabel contactsNameLabel = new JLabel("           - контактное лицо");
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



        this.setName("Create new task");
        this.setSize(550, 400);
        this.setVisible(true);
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





    public void setTextFieldDialogView(JTextField nameTask, JTextField descriptionTask,
                                       JFormattedTextField timeAlertsTask, JTextField contactsPhone,
                                       JTextField contactsName) {
        this.nameTaskField = nameTask;
        this.descriptionTaskField = descriptionTask;
        this.timeAlertsTaskField = timeAlertsTask;
        this.contactsPhoneField = contactsPhone;
        this.contactsNameField = contactsName;
    }

    public void setTextForFieldDialog(String nameTask, String descriptionTask,
                                      String timeAlertsTask, String contactsPhone,
                                       String contactsName) {
        this.nameTaskField.setText(nameTask);
        this.descriptionTaskField.setText(descriptionTask);
        this.timeAlertsTaskField.setText(timeAlertsTask);
        this.contactsPhoneField.setText(contactsPhone);
        this.contactsNameField.setText(contactsName);




    }


   /* private void createLabelAndFieldForContainer() {
        //Create Panel For Labels and Fields

*/
}
