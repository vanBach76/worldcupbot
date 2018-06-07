package main;
import java.util.HashMap;
import java.util.List;

public class Game {

	private Team _teamOne;
	private Team _teamTwo;
	private HashMap<Team, Integer> _result;
	private IPredictor _predictor;
	
	public Game(Team teamOne, Team teamTwo, IPredictor predictor) {
		_teamOne = teamOne;
		_teamTwo = teamTwo;
		_result = new HashMap<>();
		_predictor = predictor;
	} 
	
	public void play() {
		 List<Integer> result = _predictor.simulate(getTeamOne(), getTeamTwo());
		 _result.put(_teamOne, result.get(0));
		 _result.put(_teamTwo, result.get(1));
	}
	
	public int getResultTeamOne() {
			return _result.get(_teamOne);
	}
	
	public int getResultTeamTwo() {
		return _result.get(_teamTwo);
	}

	public Team getTeamOne() {
		return _teamOne;
	}

	public void setTeamOne(Team teamOne) {
		this._teamOne = teamOne;
	}

	public Team getTeamTwo() {
		return _teamTwo;
	}

	public void setTeamTwo(Team teamTwo) {
		this._teamTwo = teamTwo;
	}
	
	public Team getWinnerNoDraw() throws Exception {
		int scoreOne = _result.get(_teamOne);
		int scoreTwo = _result.get(_teamTwo);
		if(scoreOne != scoreTwo) {
			return scoreOne > scoreTwo ? _teamOne : _teamTwo;
		}
		int counter = 0;
		while(scoreOne == scoreTwo) {
			List<Integer> result = _predictor.simulate(_teamOne, _teamTwo);
			scoreOne = result.get(0);
			scoreTwo = result.get(1);
			if(counter > 1000) {
				throw new Exception("This game seem to be an infinte draw.");
			}
			counter++;
		}
		return scoreOne > scoreTwo ? _teamOne : _teamTwo;
	}
	
	@Override
	public String toString() {
		return _teamOne.getName() + " " + getResultTeamOne() + ", " + _teamTwo.getName() + " " + getResultTeamTwo();
	}
}
