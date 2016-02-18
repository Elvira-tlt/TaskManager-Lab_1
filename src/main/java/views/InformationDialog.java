package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationDialog extends JDialog {
    private JLabel informationLabel;
    private JButton ok;

    public InformationDialog() {
        informationLabel = new JLabel(" ");
        ok = new JButton("OK");
        
        ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
        
        add(informationLabel);
        add(BorderLayout.SOUTH, ok);
        
        setLayout(new FlowLayout());
        setSize(500,100);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void setTextInformationLabel(String textInformation) {
        informationLabel.setText(textInformation);
    }
}
