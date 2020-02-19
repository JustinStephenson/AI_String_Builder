package source;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main implements ActionListener{
	
	static boolean btn = false;
	static JFrame frame = new MainFrame("String Building via Genetic Algorithm");
	static DetailsPanel detailsPanel = ((MainFrame) frame).detailsPanel;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		btn = true;
	}
	
	public static void main(String[] args) throws InterruptedException {
		//Frame
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				frame.setSize(1000, 750);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
		while (frame.isEnabled()) {
			while (!btn) {
				try {
					Thread.sleep(200);
				}
				catch (InterruptedException e) {
					
				}
			}
			((MainFrame) frame).clearText();
			((MainFrame) frame).showText();
			
			//user input.
			String target = "Test";
			int populationNumber = 200;
			double mutationRate = 1;
			
			try {
				target = detailsPanel.targetStringField.getText();
				populationNumber = Integer.parseInt(detailsPanel.populationField.getText());
				mutationRate = Double.parseDouble(detailsPanel.mutationRateField.getText());
				mutationRate = mutationRate / 100;
			} catch (Exception e) {
				System.out.println("Wrong data type");
				btn = false;
				continue;
			}
			
			//statistics.
			int totalGenerations = 0;
			int averageFitness = 0;
			
			String bestResult = "";
			
			Population pop = new Population(target, populationNumber);
			pop.calculateFitness(target);
			while(!bestResult.equals(target)) {
				pop.selection();
				pop.generate(mutationRate);
				pop.calculateFitness(target);
				bestResult = pop.evalute();
				totalGenerations++;
				averageFitness = pop.averageFitness();
				
				StringBuilder myString = new StringBuilder();
				myString.append("generation: " + totalGenerations);
				myString.insert(myString.length(), "  |  best string: " + bestResult);
				myString.insert(myString.length(), "  |  average fitness: " + averageFitness);
				myString.insert(myString.length(), " / " + target.length());
				((MainFrame) frame).setText (myString.toString());
			}
			//((MainFrame) frame).setText("total generations: " + totalGenerations);
			((MainFrame) frame).showText();
			btn = false;
		}
	}
}
