package source;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DetailsPanel extends JPanel{
	
	public JTextField targetStringField;
	public JTextField populationField;
	public JTextField mutationRateField;
	
	public DetailsPanel(ActionListener listener) {
		Dimension size = getPreferredSize();
		size.width = 300;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Controls"));
		
		JLabel targetStringLabel = new JLabel("Target String: ");
		JLabel populationLabel = new JLabel("Population: ");
		JLabel mutationRateLabel = new JLabel("Mutation Rate: ");
		
		targetStringField = new JTextField(10);
		populationField = new JTextField(10);
		mutationRateField = new JTextField(10);
		
		JButton button = new JButton("Run");
		button.addActionListener(listener);
		Dimension buttonSize = new Dimension(200, 50);
		button.setPreferredSize(buttonSize);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//first column
		gc.anchor = GridBagConstraints.LINE_END;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		gc.gridx = 0;
		gc.gridy = 0;
		add(targetStringLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		add(populationLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		add(mutationRateLabel, gc);
		
		//second column
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		add(targetStringField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(populationField, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		add(mutationRateField, gc);
		
		//final row
		//dummy row
		gc.gridx = 0;
		gc.gridy = 3;
		add(new JPanel(), gc);
		
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.BASELINE;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		
		gc.gridx = 0;
		gc.gridy = 4;
		add(button, gc);
	}
	
}
