package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogForExit extends JDialog {

	public void exitingFromOtherDialog(JDialog otherDialogForExit) {
		JDialog dialogExitFromCreatingTask = new JDialog();
        JLabel questionAboutExit = new JLabel("Are you sure you want to exit the task creation? All unsaved data will be lost.");
        JPanel exitButtonsPanel = new JPanel();
        JButton okExit = new JButton("Yes, exit");
        JButton cancelExit = new JButton("Cancel exit");
        
        okExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogExitFromCreatingTask.dispose();
				otherDialogForExit.dispose();
			}
		});
        
       	cancelExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogExitFromCreatingTask.dispose();
			}
		});	
        

        exitButtonsPanel.add(okExit);
        exitButtonsPanel.add(cancelExit);

        dialogExitFromCreatingTask.add(questionAboutExit);
        dialogExitFromCreatingTask.add(new BorderLayout().SOUTH, exitButtonsPanel);

        dialogExitFromCreatingTask.setLayout(new FlowLayout());
        dialogExitFromCreatingTask.setSize(600,120);
        dialogExitFromCreatingTask.setVisible(true);
	}
   


}