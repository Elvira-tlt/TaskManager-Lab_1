package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
		add(BorderLayout.NORTH, informationPanel);
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