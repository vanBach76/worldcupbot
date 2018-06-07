package main;

import java.util.List;
public class Main {

	public static void main(String[] args) {

		Team everton = new Team("Everton")
				.avgConceded(1.263)
				.avgScored(1.315);
		
		Team tottenham = new Team("Tottenham")
				.avgConceded(0.789)
				.avgScored(1.842);
		
		IPredictor predictor = new PoissonPredictor();
		
		
		int maxEverton = 0;
		int maxTottenham = 0;
		for(int i = 0; i < 10000 ; i++) {
			List<Integer> result = predictor.simulate(everton, tottenham);
//			System.out.println(everton + ": " + result.get(0) + ", " + tottenham + ": " + result.get(1));
			maxEverton = maxEverton < result.get(0) ? result.get(0) : maxEverton;
			maxTottenham = maxEverton < result.get(1) ? result.get(1) : maxTottenham;
		}
		System.out.println("maxEverton: " + maxEverton + ", maxTottenham: " + maxTottenham);
	}

}
