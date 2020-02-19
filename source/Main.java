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
			String target = detailsPanel.targetStringField.getText();
			int populationNumber = Integer.parseInt(detailsPanel.populationField.getText());
			double mutationRate = Double.parseDouble(detailsPanel.mutationRateField.getText());
			mutationRate = mutationRate / 100;
			
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
				((MainFrame) frame).setText (
						"generation: " + totalGenerations
						+ "  |  best string: " + bestResult
						+ "  |  average fitness: " + averageFitness
						+ " / " + target.length()
				);
			}
			//((MainFrame) frame).setText("total generations: " + totalGenerations);
			((MainFrame) frame).showText();
			btn = false;
		}
	}
}
