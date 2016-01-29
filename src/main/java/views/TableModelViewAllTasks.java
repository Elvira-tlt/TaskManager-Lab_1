package views;

import models.Task;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TableModelViewAllTasks implements TableModel {
    private List<Task> tasks;
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    TableModelViewAllTasks(List<Task> tasks) {
        super();
        this.tasks = tasks;
        //Для проверки, потом убрать:
        for (int i=0; i<4; i++) {
            Task task = new Task();
            task.setName("Task#" + i);
            task.setDescription("" + i);
            task.setContactsPhone("" + i + i + 3 + i + 6 + 7);
            task.setContactsName("name#" + i);
            task.setTimeAlerts(new Date());
            tasks.add(task);
        }

    }
    public Set<TableModelListener> getListeners() {
        return listeners;

    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class ;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Name task";
            case 1:
                return "Description task";
            case 2:
                return "Time alert";
            case 3:
                return "Contacts";
        }
        return "";
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = tasks.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return task.getName();
            case 1:
                return task.getDescription();
            case 2:
            	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                ////////////////////
                if (task.getTimeAlerts() == null ) {
                    return " ";
                }
                /////

            	return dateFormat.format(task.getTimeAlerts());
         
            case 3:
            	String gettingContacts = getContacts(task.getContactsPhone(), task.getContactsName());
            	return gettingContacts;
        }
        return " ";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }
    
    
    private String getContacts(String contactsPhone, String contactsName) {
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
    	return contactsData;
    }


}
