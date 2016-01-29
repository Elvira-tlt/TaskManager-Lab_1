package views;

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

    private JTextField nameTaskField;
    private JTextField descriptionTaskField;
    private JDatePickerImpl datePicker;
    private JSpinner spinner;
    private Date timeAlertsTaskValue;
    private JTextField contactsPhoneField;
    private JTextField contactsNameField;


    ////////
    private JSpinner.DateEditor dateEditor;

   // private UtilDateModel modelImp;
    private JDatePanelImpl datePanel;
    //
    private DialogType dialogType;

    public DialogForNewTask(DialogType dialogType) {
        this.dialogType = dialogType;
    }

    public void createDialogForTask(TaskView taskView) {
        this.taskView = taskView;


        labelAndFieldPanel = new JPanel(new GridLayout(6, 2));
        labelAndFieldPanel.setBorder(new TitledBorder(" Заполните информацию о создаваемой вами задаче")); //ЗАПОЛНИТЬ???
        this.add(new BorderLayout().CENTER, labelAndFieldPanel);

        // modelImp = new UtilDateModel();

        //datePanel = new JDatePanelImpl(modelImp);

        //Creating Label and Field for Container:
        JLabel nameTaskLabel = new JLabel("Название/имя задачи *");


        JLabel descriptionTaskLabel = new JLabel("Описание");
        descriptionTaskField = new JTextField();

        JLabel timeAlertsTaskLabel = new JLabel("Дата и время напоминания/выполнения *");
        JPanel panelForCalendar = new JPanel();
        setSpinnerAndJDatePickerValue(panelForCalendar, timeAlertsTaskValue);

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
        labelAndFieldPanel.add(panelForCalendar);
        labelAndFieldPanel.add(contactsLabel);
        labelAndFieldPanel.add(nullLabel);
        labelAndFieldPanel.add(contactsPhoneLabel);
        labelAndFieldPanel.add(contactsPhoneField);
        labelAndFieldPanel.add(contactsNameLabel);
        labelAndFieldPanel.add(contactsNameField);


        this.setName("Create new task");
        this.setSize(550, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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


    public void setTextFieldDialogView(JTextField nameTask, JTextField descriptionTask,
                                       /*JFormattedTextField timeAlertsTask,*/ JTextField contactsPhone,
                                       JTextField contactsName) {
        this.nameTaskField = nameTask;
        this.descriptionTaskField = descriptionTask;
        // this.timeAlertsTaskField = timeAlertsTask; м.б удалить (сделать автоматическое присвоение при ок)
        this.contactsPhoneField = contactsPhone;
        this.contactsNameField = contactsName;
    }

    public void setTextForFieldDialog(String nameTask, String descriptionTask,
                                      /*Date timeAlertsTask, */String contactsPhone,
                                      String contactsName) {
        this.nameTaskField.setText(nameTask);
        this.descriptionTaskField.setText(descriptionTask);
        // setTimeAlertsTaskValue(timeAlertsTask); //// ПРОВЕРИТЬ НА РАБОТУ
        this.contactsPhoneField.setText(contactsPhone);
        this.contactsNameField.setText(contactsName);
    }

    public void setTimeAlertsTaskValue(Date dateValue) {
        timeAlertsTaskValue = dateValue;
    }


    //метод, отображающий значение даты для редактирования:
    private void setSpinnerAndJDatePickerValue(JPanel panel, Date dateValue) {

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

            ///потом удалить:
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            DateFormat taskDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            //

            Calendar calendarValue = new GregorianCalendar();
            datePicker.getModel().setSelected(true);
            calendarValue.setTime((Date) datePicker.getModel().getValue());
           // datePicker.getModel().setMonth(calendarValue.get(Calendar.MONTH));

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
            Boolean timeAlertsDontHaveValue = taskTimeAlerts == null;

            if ((nameDontHaveTextsInField) || (timeAlertsDontHaveValue)) {
                // содание информационного окна
                InformationDialog informationDialog = new InformationDialog();
                informationDialog.setTextInformationLabel("Поля \"Name Task\" и \"Time Alerts's Task\""
                        + "обязательно должны быть заполнены!");


                // добавить звездочки в название полей

            } else {
                taskView.taskController.saveNewTask(taskName, taskDescription,
                        taskTimeAlerts, taskContactsPhone,
                        taskContactsName);
                taskView.updateTable();

                dispose();
            }
        }


    };

    private ActionListener readingDateValueForEditing = new ActionListener() {

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

            if ((nameDontHaveTextsInField)) {
                // содание информационного окна
                InformationDialog informationDialog = new InformationDialog();
                informationDialog.setTextInformationLabel("Полe \"Name Task\""
                        + "обязательно должно быть заполнено!");


                // добавить звездочки в название полей

            } else {
                taskView.taskController.saveNewTask(taskName, taskDescription,
                        taskTimeAlerts, taskContactsPhone,
                        taskContactsName);


                //////////////
                taskController.setFieldsTaskFromModel(countSelectedRows, taskName,
                        taskDescription, taskTimeAlerts, taskContactsPhone,
                        taskContactsName);

                ////





                taskView.updateTable();

                dispose();
            }
        }


    };

   /* new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            String taskName = dialogForEditingTask.getNameTask();
            String taskDescription = dialogForEditingTask.getDescriptionTask();
            Date taskTimeAlerts = dialogForEditingTask.getTimeAlertsTask();
            String taskContactsPhone = dialogForEditingTask.getContactsPhone();
            String taskContactsName = dialogForEditingTask.getContactsName();

            taskController.setFieldsTaskFromModel(countSelectedRows, taskName,
                    taskDescription, taskTimeAlerts, taskContactsPhone,
                    taskContactsName);

            updateTable();
            dialogForEditingTask.dispose();

        }
    }*/



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

            // переделать взаимодействие через массив!!!!!!!!!!
            String[] textFields = new String[]{
                    taskName,
                    taskDescription,
                    //taskTimeAlerts,
                    taskContactsPhone,
                    taskContactsName};

            //  Boolean fieldDontHaveTexts = taskName.isEmpty() || taskName == null;

            //while (fieldDontHaveTexts==true) {
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










    ////// Потом убрать
    public static void main(String[] args) {
        new DialogForNewTask(DialogType.FOR_CREATING);
    }
    //////////


}
