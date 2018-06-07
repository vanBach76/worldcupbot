package main;

public class Team {
	
	private String _name;
	private double _avgScored;
	private double _avgConceded;
	private int _ranking;
	
	public Team(String name) {
		_name = name;
	}
	
	public Team(double avgScored, double avgConceded) {
		_avgScored = avgScored;
		_avgConceded = avgConceded;
	}
	
	
	public int getRanking() {
		return _ranking;
	}
	
	public Team setRanking(int _ranking) {
		this._ranking = _ranking;
		return this;
	}

	public String getName() {
		return _name;
	}

	public Team setName(String _name) {
		this._name = _name;
		return this;
	}

	public double getAvgConceded() {
		return _avgConceded;
	}

	public Team avgConceded(double _defenceStrength) {
		this._avgConceded = _defenceStrength;
		return this;
	}

	public double getAvgScored() {
		return _avgScored;
	}

	public Team avgScored(double _attackStrength) {
		this._avgScored = _attackStrength;
		return this;
	}
	
	@Override
	public String toString() {
		return _name;
	}
	
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Team)) {
			return false;
		}
		Team otherTeam = (Team) object;
		return otherTeam.getName() != null
				&& otherTeam.getName().equals(_name);
	}

}
