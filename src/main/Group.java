package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {

	List<Game> _games;
	List<Stats> _standings;
	
	public Group(List<Game> games) {
		_games = games;
		_standings = initializeStandings(games);
	}

	private List<Stats> initializeStandings(List<Game> games) {
		List<Stats> standings = new ArrayList<>();
		Set<Team> teams = getTeams(games);
		for (Team team : teams) {
			standings.add(new Stats(team));
		}
		return standings;
	}

	private Set<Team> getTeams(List<Game> games) {
		Set<Team> teams = new HashSet<>();
		for (Game game : games) {
			teams.add(game.getTeamOne());
			teams.add(game.getTeamTwo());
		}
		return teams;
	}
	
	public void simulate(IPredictor predictor) throws Exception {
		for (Game game : _games) {
			game.play();
			updateStandings(game);
		}
		sortStandings();
	}
	
	private void updateStandings(Game game) throws Exception {
		int resultTeamOne = game.getResultTeamOne();
		int resultTeamTwo = game.getResultTeamTwo();
		
		Stats statsTeamOne = _standings.stream()
				.filter(s -> s.getTeam().equals(game.getTeamOne()))
				.findFirst()
				.get();
		Stats statsTeamTwo = _standings.stream()
				.filter(s -> s.getTeam().equals(game.getTeamTwo()))
				.findFirst()
				.get();
		
		statsTeamOne.addScored(resultTeamOne);
		statsTeamTwo.addScored(resultTeamTwo);
		statsTeamOne.addConceded(resultTeamTwo);
		statsTeamTwo.addConceded(resultTeamOne);
		
		if(resultTeamOne == resultTeamTwo) {
			statsTeamOne.addPoints(1);
			statsTeamTwo.addPoints(1);
		}
		else if(resultTeamOne > resultTeamTwo) {
			statsTeamOne.addPoints(3);
		}
		else {
			statsTeamTwo.addPoints(3);
		}
	}
	
	private void sortStandings() {
		_standings.sort(new Comparator<Stats>() {
			@Override
			public int compare(Stats stats1, Stats stats2) {
				return stats1.isBetterThan(stats2) ? -1 : 1;
			}
		});
	}
	
	public Team getWinner()  {
		return _standings.get(0).getTeam();
	}
	
	public Team getRunnerUp()  {
		return _standings.get(1).getTeam();
	}
	
	public String getResultsForPrint() {
		StringBuilder stringBuilder = new StringBuilder();
		int gameNr = 1;
		for (Game game : _games) {
			stringBuilder.append("Game " + gameNr + ", " + game.toString() + System.lineSeparator());
			gameNr++;
		}
		stringBuilder.append( System.lineSeparator());
		stringBuilder.append("Final standings: " + System.lineSeparator());
		stringBuilder.append("Team \t  GA \tGS \tP" + System.lineSeparator());
		stringBuilder.append("-------------------------" + System.lineSeparator());
		for (Stats stats : _standings) {
			Team team = stats.getTeam();
			String name = team.getName().length() > 6 ? team.getName().substring(0, 6) : team.getName(); 
			stringBuilder.append(name + "\t| " + stats + System.lineSeparator());
		}
		return stringBuilder.toString();
	}
	
}
