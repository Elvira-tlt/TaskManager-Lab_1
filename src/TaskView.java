import com.sun.javafx.collections.MappingChange;
import com.sun.xml.internal.ws.api.FeatureListValidatorAnnotation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class TaskView {
    private Map <JLabel, JTextField> labelToField = new HashMap<>();
    private final String[] LABEL_AND_FIELD_NAMES = new String[]{
            "NameTask", "DescriptionTask", "NotificationDateTask", "ContactsPhone", "ContactsName"
    };

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

    public void createJFrame () {

        JFrame frame = new JFrame("Task Manager");
        JButton createTaskButton = new JButton("Create Task / Создать задачу");
        JButton viewTasksButton = new JButton("View Tasks/ Посмотреть задачи");
        JPanel panelButtons = new JPanel();

       // panelButtons.setSize(10,40);
       // createTask.setSize(10,20);

        createTaskButton.setToolTipText("Создание новой задачи");
        viewTasksButton.setToolTipText("Просмотреть имеющиеся задачи");

        panelButtons.add(createTaskButton);
        panelButtons.add(viewTasksButton);
        panelButtons.setBorder(new TitledBorder(""));

       // panelButtons.setLayout(new BorderLayout());

        createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDialogForNewTask(labelToField);
                //Создание формы/диалога для заполнения полей задачи
            }
        } );

        frame.add(panelButtons);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setVisible(true);

    }


    private void createDialogForNewTask ( Map<JLabel,JTextField> map) {
        JDialog dialogCreateTask = new JDialog();

        dialogCreateTask.setName("Create new task");//НЕ ОТОБРАЖАЕТСЯ ИМЯ

        //Создание надписей и полей диалога для заполнения полей задачи по Map и List
        createFieldFromMap(dialogCreateTask, map);

        dialogCreateTask.setLayout(new FlowLayout());
        dialogCreateTask.setSize(450, 700);
        dialogCreateTask.setVisible(true);
    }

    private void createFieldFromMap(Container container,  Map<JLabel, JTextField> map){
        addingMap(map);
        for(Map.Entry<JLabel, JTextField> entry : map.entrySet()) {
            JLabel label = entry.getKey();
            JTextField text = entry.getValue();
            container.add(label);
            container.add(text);

            /////////////////////////

            //container.setVisible(true);
        }
        container.add(new JLabel("hgughu"));
        container.add(new JTextField("ghughu"));
    }

    private void addingMap ( Map <JLabel, JTextField> labelToField) {
        for (String nameField : LABEL_AND_FIELD_NAMES) {
            labelToField.put(new JLabel(nameField), new JTextField(nameField));
        }

    }







    public static void main(String[] args) {
        TaskView view = new TaskView();
        view.createJFrame();
    }



}
