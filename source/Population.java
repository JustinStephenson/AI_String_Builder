package source;

public class Population {
	private Chromosome[] population;
	private Chromosome[] matingPool;
	private int[] fitnessScores;
	
	public Population(String target, int populationNumber) {
		population = new Chromosome[populationNumber];
		//add random Chromosomes into the population.
		for (int i = 0; i < populationNumber; i++) {
			population[i] = new Chromosome(target.length()); 
		}
		matingPool = new Chromosome[populationNumber];
		fitnessScores = new int[populationNumber];
	}
	
	public void calculateFitness(String target) {
		for (int i = 0; i < population.length; i++) {
			fitnessScores[i] = population[i].calculateFitness(target); 
		}
	}
	
	public void selection() {
		int totalFitness = 0;
		//find total fitness to be used later.
		for (int i = 0; i < population.length; i++) {
			totalFitness += fitnessScores[i];
		}
		//loop through population subtracting fitness with random number until <= 0.
		//this will effectively choose the chromosomes randomly based on fitness.
		//Use this to fill up the mating pool to create new population later.
		for (int i = 0; i < matingPool.length; i++) {
			//find random number between 0 and totalFitness.
			int randomFitness = Random.randomWithRange(0, totalFitness);
			for (int j = 0; j < fitnessScores.length; j++) {
				randomFitness -= fitnessScores[j];
				if (randomFitness <= 0) {
					matingPool[i] = population[j];
					break;
				}
			}
		}
	}
	
	public void generate(double mutationRate) {
		//generate a new population using Crossover and Mutation.
		for (int i = 0; i < population.length; i++) {
			int randomIndex1 = Random.randomWithRange(0, matingPool.length - 1);
			int randomIndex2 = Random.randomWithRange(0, matingPool.length - 1);
			Chromosome parent1 = matingPool[randomIndex1];
			Chromosome parent2 = matingPool[randomIndex2];
			Chromosome child = parent1.crossover(parent2);
			child.mutate(mutationRate);
			population[i] = child; 
		}
	}
	
	public String evalute() {
		//check to see if a member of the population meets required fitness level.
		
		int bestFitness = 0;
		String bestString = "";
		for (int i = 0; i < population.length; i++) {
			if (fitnessScores[i] > bestFitness) {
				bestFitness = fitnessScores[i];
				bestString = population[i].toString();
			}
		}
		return bestString;
	}
	
	public int averageFitness() {
		int totalFitness = 0;
		for (int i = 0; i < fitnessScores.length; i++) {
			totalFitness += fitnessScores[i];
		}
		return totalFitness / fitnessScores.length;
	}
}
