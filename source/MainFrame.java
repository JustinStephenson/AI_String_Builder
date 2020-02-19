package source;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener{
	
	public DetailsPanel detailsPanel;
	
	private StringBuilder myString;
	private JLabel area;
	
	public MainFrame(String title) {
		super(title);
		
		myString = new StringBuilder();
		
		//Set layout manager.
		setLayout(new BorderLayout());
		
		//Create Swing component.
		area = new JLabel();
		area.setOpaque(true);
		area.setVerticalAlignment(1);
		area.setBorder(new EmptyBorder(5, 5, 0, 0));
		area.setBackground(Color.white);
		JScrollPane scroller = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		detailsPanel = new DetailsPanel(new Main());
		
		//Add Swing components to content pane.
		Container container = getContentPane();
		container.add(scroller, BorderLayout.CENTER);
		container.add(detailsPanel, BorderLayout.WEST);
	}
	
	public void setText(String s) {
		myString.insert(myString.length(), s);
		myString.insert(myString.length(), "\n");
		area.setText(s);
	}
	
	public void showText() {
		area.setText("<html>" + myString.toString().replaceAll(" ", "&nbsp;").replaceAll("\n", "<br/>") + "<html>");
	}
	
	public void clearText() {
		myString.setLength(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
