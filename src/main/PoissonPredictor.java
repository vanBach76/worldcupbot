package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.apache.commons.math3.distribution.PoissonDistribution;

public class PoissonPredictor implements IPredictor {

	private double _avgScored = 1.21665;
	private int _nrGoalsUpperBound = 1000;

	@Override
	public List<Integer> simulate(Team teamOne, Team teamTwo) {
		List<Integer> goals = new ArrayList<>();
		try {
			double attackStrengthOne = calculateAttackStrength(teamOne);
			double defenceStrengthOne = calculateDefenceStrength(teamOne);
			double attackStrengthTwo = calculateAttackStrength(teamTwo);
			double defenceStrengthTwo = calculateDefenceStrength(teamTwo);

			double expectedNrGoalsOne = calculateExpectedNrGoals(attackStrengthOne, defenceStrengthTwo);
			double expectedNrGoalsTwo = calculateExpectedNrGoals(attackStrengthTwo, defenceStrengthOne);

			Map<Integer,Double> calculateWeightsOne = calculateWeights(expectedNrGoalsOne);
			Map<Integer,Double> calculateWeightsTwo = calculateWeights(expectedNrGoalsTwo);

			int nrScoredOne = calculateScore(calculateWeightsOne);
			int nrScoredTwo = calculateScore(calculateWeightsTwo);
			goals.add(nrScoredOne);
			goals.add(nrScoredTwo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goals;

	}

	private double calculateAttackStrength(Team team) {
		return team.getAvgScored();
	}

	private double calculateDefenceStrength(Team team) {
		return team.getAvgConceded();
	}

	private double calculateExpectedNrGoals(double attackStrength, double defenceStrength) {
		return attackStrength * defenceStrength * _avgScored;
	}

	private Map<Integer, Double> calculateWeights(double expectedNrGoals) {
		PoissonDistribution poissonDistribution = new PoissonDistribution(expectedNrGoals);
		Map<Integer, Double> weights = new TreeMap<>();
		for(int i = 0; i <= _nrGoalsUpperBound; i++) {
			weights.put(i, poissonDistribution.cumulativeProbability(i));
		}
		return weights;
	}

	private int calculateScore(Map<Integer,Double> weights) throws Exception {
		double random = new Random().nextDouble();
		for (Integer score : weights.keySet()) {
			if(random <= weights.get(score)) {
				return score;
			}
		}
		return weights.size();
	}
	
}
