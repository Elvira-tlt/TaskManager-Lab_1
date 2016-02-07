package savingAndLoadingTasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Task;
import models.TaskModel;

import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;


public class Database {
	private static final String FILENAME = "Tasks.xml";
	private Document document;
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	private FileOutputStream fileOutputStream;
	private List<Task> allTasks = new ArrayList<Task>();

	public List<Task> loadingTasksFromFile() {
		SAXBuilder saxBuilder = new SAXBuilder();
		File xmlFile = new File(FILENAME);
		if (xmlFile.exists()){
			try {
				document = saxBuilder.build(xmlFile);
				Element tasksModel = document.getRootElement();
				List<Element> taskList = tasksModel.getChildren("task");
				for (Element nextTask : taskList) {
					String name = nextTask.getChild("name").getValue();
					String description = nextTask.getChild("description").getValue();
					String timeAlertsString = nextTask.getChild("timeAlerts").getValue();
					String contactsPhone = nextTask.getChild("contactsPhone").getValue();
					String contactsName = nextTask.getChild("contactsName").getValue();

					Date timeAlerts = format.parse(timeAlertsString);

					Task taskForLoading = new Task();
					taskForLoading.setName(name);
					taskForLoading.setDescription(description);
					taskForLoading.setTimeAlerts(timeAlerts);
					taskForLoading.setContactsPhone(contactsPhone);
					taskForLoading.setContactsName(contactsName);

					synchronized (allTasks) {
					allTasks.add(taskForLoading);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allTasks;
	}
	
	public void savingTasksToFile (List<Task> tasksForSaving) {
		File xmlFile = new File(FILENAME);
        Element tasksModel = new Element("tasksModel");
        document = new Document(tasksModel);
        XMLOutputter xmlOutputter = new XMLOutputter();
    	xmlOutputter.setFormat(Format.getPrettyFormat());

		synchronized (tasksForSaving) {
			if (!tasksForSaving.isEmpty()) {
				for (Task task : tasksForSaving) {
					Element taskElement = new Element("task");
					taskElement.addContent(new Element("name").setText(task.getName()));
					taskElement.addContent(new Element("description").setText(task.getDescription()));
					taskElement.addContent(new Element("timeAlerts").setText(format.format(task.getTimeAlerts())));
					taskElement.addContent(new Element("contactsPhone").setText(task.getContactsPhone()));
					taskElement.addContent(new Element("contactsName").setText(task.getContactsName()));
					tasksModel.addContent(taskElement);
				}
			}
		}
        try {
    		fileOutputStream = new FileOutputStream(xmlFile);
			xmlOutputter.output(document, fileOutputStream);
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
   