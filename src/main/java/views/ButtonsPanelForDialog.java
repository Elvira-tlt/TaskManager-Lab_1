package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonsPanelForDialog extends JPanel {
	private JButton ok;
	private JButton cancel;
	public ButtonsPanelForDialog() {
		ok = new JButton("OK");
        cancel = new JButton("Cancel");
        
        JPanel informationPanel = new JPanel();
        JLabel informationLabel = new JLabel("Звездочкой * помечены поля, "
        		+ " обязательные для заполнения");
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(ok);
        buttonsPanel.add(cancel);
        
        informationPanel.add(informationLabel);
		add(new BorderLayout().NORTH, informationPanel);
		add(buttonsPanel);
        
        setLayout(new GridLayout(2,1));
        
       
	}
	
	public void setListenerTo(JButton buttonName, ActionListener listener) {
		buttonName.addActionListener(listener);
	}
	
	public JButton getButtonOk () {
		return ok;
	}
	
	public JButton getButtonCancel () {
		return cancel;
	}
		
	
	
}
	/*private void createButtonsAndListenerForDialog(JDialog dialogWindow) {
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        dialogWindow.add(new BorderLayout().SOUTH, buttonsPanel);

       
        

        ok.addActionListener(checkDoNameAndTimeAlertsIsEmpty) ;


        cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String taskName = dialogCreatingTask.getNameTask();
                    String taskDescription = dialogCreatingTask.getDescriptionTask();
                    String taskTimeAlerts = dialogCreatingTask.getTimeAlertsTask();
                    String taskContactsPhone = dialogCreatingTask.getContactsPhone();
                    String taskContactsName = dialogCreatingTask.getContactsName();

                    // переделать взаимодействие через массив!!!!!!!!!!
                    String[] textFields = new String[]{
                            taskName,
                            taskDescription,
                            taskTimeAlerts,
                            taskContactsPhone,
                            taskContactsName};

                  //  Boolean fieldDontHaveTexts = taskName.isEmpty() || taskName == null;

                    //while (fieldDontHaveTexts==true) {
                        for (int i=0; i<textFields.length; i++) {
                            if (textFields[i].isEmpty()){
                                if (i== textFields.length-1) {
                                   dialogCreatingTask.dispose();

                                } else continue;

                            } else {
                                createDialogExit(dialogWindow);
                                return;
                            }
                        }

                        // добавить звездочки в название полей

            }
        });

        
        
}*/
