package source;

public final class Random {
	
	private Random() {}
	
	public static int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int)(Math.random() * range) + min;
	}
	
	public static double randomWithRange(double min, double max)
	{
	   double range = (max - min);     
	   return (Math.random() * range) + min;
	}
}
