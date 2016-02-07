package views;

import models.Task;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class DialogForNewTask extends JDialog {
    private TaskView taskView;

    public JPanel labelAndFieldPanel;

    private JTextField nameTaskField = new JTextField();
    private JTextField descriptionTaskField = new JTextField();
    private JTextField contactsPhoneField = new JTextField();
    private JTextField contactsNameField = new JTextField();
    private JDatePickerImpl datePicker;
    private JSpinner spinner;
    private Date timeAlertsTaskValue;

    private JLabel nameTaskLabel = new JLabel("Название/имя задачи *");
    private JLabel descriptionTaskLabel = new JLabel("Описание");
    private JLabel timeAlertsTaskLabel = new JLabel("Дата и время напоминания/выполнения *");
    private JLabel contactsLabel = new JLabel("Контактная информация:");
    private JLabel nullLabel = new JLabel();
    private JLabel contactsPhoneLabel = new JLabel("          - контактный телефон");
    private JLabel contactsNameLabel = new JLabel("           - контактное лицо");

    Task taskForEditing;

    private JSpinner.DateEditor dateEditor;

    private JDatePanelImpl datePanel;

    public DialogForNewTask(Task taskForEditing) {
        this.taskForEditing = taskForEditing;
    }

    public void createDialogForTask(TaskView taskView) {
        this.taskView = taskView;

        labelAndFieldPanel = new JPanel(new GridLayout(6, 2));
        labelAndFieldPanel.setBorder(new TitledBorder(" Заполните информацию о создаваемой вами задаче")); //ЗАПОЛНИТЬ???
        this.add(new BorderLayout().CENTER, labelAndFieldPanel);

        JPanel panelForCalendar = new JPanel();

        if (taskForEditing != null)  {
            String nameTaskField = taskView.taskController.getTasknameTaskField(taskForEditing);
            String descriptionTaskField = taskView.taskController.getDescriptionTaskField();
            timeAlertsTaskValue = taskView.taskController.getTimeAlertsTaskField();
            String contactsPhoneField = taskView.taskController.getContactsPhoneField();
            String contactsNameField = taskView.taskController.getContactsNameField();

            setTextForFieldDialog(nameTaskField, descriptionTaskField, contactsPhoneField, contactsNameField);

            setTitle("Редактирование задачи: " + nameTaskField);

        } else {
           setTitle("Создание новой задачи");
        }

        setSpinnerAndJDatePickerValue(panelForCalendar, timeAlertsTaskValue);

        //Adding components:
        labelAndFieldPanel.add(nameTaskLabel);
        labelAndFieldPanel.add(nameTaskField);
        labelAndFieldPanel.add(descriptionTaskLabel);
        labelAndFieldPanel.add(descriptionTaskField);
        labelAndFieldPanel.add(timeAlertsTaskLabel);
        labelAndFieldPanel.add(panelForCalendar);
        labelAndFieldPanel.add(contactsLabel);
        labelAndFieldPanel.add(nullLabel);
        labelAndFieldPanel.add(contactsPhoneLabel);
        labelAndFieldPanel.add(contactsPhoneField);
        labelAndFieldPanel.add(contactsNameLabel);
        labelAndFieldPanel.add(contactsNameField);

        this.setName("Create new task");
        this.setSize(600, 450);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public String getNameTask() {
        return nameTaskField.getText();
    }

    public String getDescriptionTask() {
        return descriptionTaskField.getText();
    }

    public Date getTimeAlertsTask() {
        return timeAlertsTaskValue;
    }

    public String getContactsPhone() {
        return contactsPhoneField.getText();
    }

    public String getContactsName() {
        return contactsNameField.getText();
    }

    public void setTextForFieldDialog(String nameTask, String descriptionTask,
                                      String contactsPhone,
                                      String contactsName) {
        this.nameTaskField.setText(nameTask);
        this.descriptionTaskField.setText(descriptionTask);
        this.contactsPhoneField.setText(contactsPhone);
        this.contactsNameField.setText(contactsName);
    }

    //метод, отображающий значение даты для редактирования:
    private void  setSpinnerAndJDatePickerValue(JPanel panel, Date dateValue) {

        UtilDateModel modelImp= new UtilDateModel();
        datePanel = new JDatePanelImpl(modelImp, new Properties());
        datePicker = new JDatePickerImpl(datePanel, new views.DateFormatter());

        SpinnerDateModel model = new SpinnerDateModel();
        spinner = new JSpinner(model);
        dateEditor = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(dateEditor);

        if(dateValue != null) {
            UtilDateModel dataModel = (UtilDateModel) datePicker.getModel();
            dataModel.setValue(dateValue);
        }
        panel.add(datePicker);
        panel.add(spinner);
    }

    private ActionListener readingDateValueForCreating = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            Calendar calendarValue = new GregorianCalendar();
            datePicker.getModel().setSelected(true);
            calendarValue.setTime((Date) datePicker.getModel().getValue());

            Calendar timeValue = new GregorianCalendar();
            timeValue.setTime((Date) spinner.getValue());

            calendarValue.set(Calendar.HOUR_OF_DAY, timeValue.get(Calendar.HOUR_OF_DAY));
            calendarValue.set(Calendar.MINUTE, timeValue.get(Calendar.MINUTE));
            
            String taskName = getNameTask();
            String taskDescription = getDescriptionTask();
            Date taskTimeAlerts = calendarValue.getTime();
            String taskContactsPhone = getContactsPhone();
            String taskContactsName = getContactsName();

            Boolean nameDontHaveTextsInField = taskName.isEmpty();
            Date currentValueDate = new Date();
            Boolean timeAlertsBeforeCurrentValue = taskTimeAlerts.before(currentValueDate);

            if ((nameDontHaveTextsInField) && timeAlertsBeforeCurrentValue) {
                    InformationDialog informationDialog = new InformationDialog();
                    informationDialog.setTextInformationLabel("<html> <br> Полe \"Name Task\""
                            + "обязательно должно быть заполнено! <br>"+ "\n Значение \"Time Alert's\""
                            + "должно быть позже текущего времени!<br></html>");
            } else if(timeAlertsBeforeCurrentValue) {
                InformationDialog informationDialog = new InformationDialog();
                informationDialog.setTextInformationLabel("Значение \"Time Alert's\""
                        + "должно быть позже текущего времени!");
            } else if (nameDontHaveTextsInField) {
                InformationDialog informationDialog = new InformationDialog();
                informationDialog.setTextInformationLabel("Полe \"Name Task\""
                        + "обязательно должно быть заполнено!");

            //save task's datas:
            } else {

                if (taskForEditing == null) {
                    taskView.taskController.saveNewTask(taskName, taskDescription,
                            taskTimeAlerts, taskContactsPhone,
                            taskContactsName);
                } else {
                    taskView.taskController.setValueTaskFromModel(taskForEditing, taskName,
                            taskDescription, taskTimeAlerts, taskContactsPhone,
                            taskContactsName);
                }
                taskView.updateTable();
                dispose();
            }
        }
    };


    public ActionListener getReadingDateValueForCreating() {
        return readingDateValueForCreating;
    }

    private ActionListener cancelSaveValueFieldDialog = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String taskName = getNameTask();
            String taskDescription = getDescriptionTask();
            Date taskTimeAlerts = getTimeAlertsTask();
            String taskContactsPhone = getContactsPhone();
            String taskContactsName = getContactsName();

            String[] textFields = new String[]{
                    taskName,
                    taskDescription,
                    taskContactsPhone,
                    taskContactsName};

            for (int i = 0; i < textFields.length; i++) {
                if (textFields[i].isEmpty()) {
                    if (i == textFields.length - 1) {
                        dispose();

                    } else continue;

                } else {
                    new DialogForExit().exitingFromOtherDialog(DialogForNewTask.this);
                    return;
                }
            }
        }
    }


            ;

    public ActionListener getCancelSaveValueFieldDialog () {
        return cancelSaveValueFieldDialog;
    }
}
