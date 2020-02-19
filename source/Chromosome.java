package source;

public class Chromosome {
	private char[] genes;
	
	public Chromosome(int genesLength) {
		genes = new char[genesLength];
		//populate genes with random chars
		for (int i = 0; i < genesLength; i++) {
			//add random chars to genes
			genes[i] = randomChar();
		}
	}
	
	private char randomChar() {
		//choose a random int between 32 and 126.
		int randomInt = Random.randomWithRange(32, 126);
		return (char)randomInt;
	}
	
	public int length() {
		return genes.length;
	}
	
	@Override
	public String toString() {
		return new String(genes);
	}
	
	public int calculateFitness(String target) {
		int score = 0;
		//go through every char of the gene comparing them to target String.
		for (int i = 0; i < genes.length; i++) {
			if (genes[i] == target.charAt(i)) {
				score++;
			}
		}
		//return the fitness score between 0 - 1.
		return score;
	}
	
	public Chromosome crossover(Chromosome partner) {
		Chromosome child = new Chromosome(genes.length);
		int midPoint = Random.randomWithRange(0, partner.length());
		for (int i = 0; i < genes.length; i++) {
			if (i < midPoint) {
				child.genes[i] = genes[i]; 
			}
			else {
				child.genes[i] = partner.genes[i];
			}
		}
		return child;
	}
	
	public void mutate(double mutationRate) {
		for (int i = 0; i < genes.length; i++) {
			double randomDouble = Random.randomWithRange(0d, 1d);
			if (randomDouble < mutationRate) {
				genes[i] = randomChar();
			}
		}
	}
}
