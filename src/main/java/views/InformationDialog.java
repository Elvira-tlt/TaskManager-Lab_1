package views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.LongBuffer;

import javax.swing.*;

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
        add(new BorderLayout().SOUTH, ok);
        
        setLayout(new FlowLayout());
        setSize(500,100);
        setVisible(true);
    }

    public void setTextInformationLabel(String textInformation) {
        informationLabel.setText(textInformation);
    }
}
