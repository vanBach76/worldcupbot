package main;

import java.util.Random;

public class Stats {

	private Team _team;
	private int _points;
	private int _goalsScored;
	private int _goalsConceded;
	
	public Stats(Team team) {
		_team = team;
	}
	
	public int getPoints() {
		return _points;
	}
	
	public void setPoints(int points) {
		this._points = points;
	}
	
	public int getGoalsScored() {
		return _goalsScored;
	}
	
	public void setGoalsScored(int goalsScored) {
		this._goalsScored = goalsScored;
	}
	
	public int getGoalsConceded() {
		return _goalsConceded;
	}
	
	public void setGoalsConceded(int goalsConceded) {
		this._goalsConceded = goalsConceded;
	}
	
	public void addScored(int scored) {
		_goalsScored += scored;
	}
	
	public void addConceded(int conceded) {
		_goalsConceded += conceded;
	}
	
	public void addPoints(int points) {
		_points += points;
	}
	
	public Team getTeam() {
		return _team;
	}

	public void setTeam(Team _team) {
		this._team = _team;
	}
	
	public boolean isBetterThan(Stats stats) {
		boolean isBetterThan = false;
		if(stats == null) {
			isBetterThan = true;
		}
		else if(_points != stats.getPoints()) {
			isBetterThan = _points > stats.getPoints();
		}
		else if(getGoalDifference() != stats.getGoalDifference()) {
			isBetterThan = getGoalDifference() > stats.getGoalDifference();
		}
		else if(_goalsScored != stats.getGoalsScored()) {
			isBetterThan = _goalsScored > stats.getGoalsScored();
		}
		else  {
			isBetterThan = new Random().nextDouble() >= 0.5 ? true : false; 
		}
		return isBetterThan;
	}
	
	public int getGoalDifference() {
		return _goalsScored - _goalsConceded;
	}
	
	@Override
	public String toString() {
		return _goalsConceded + "\t" + _goalsScored + "\t" + _points;
	}
}
