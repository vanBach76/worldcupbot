package main;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Program {

	
	public static void main(String[] args) throws Exception {
		
		ClassLoader classLoader = Program.class.getClassLoader();
		File file = new File(classLoader.getResource("resources/teams.csv").getFile());
		
		List<Team> allTeams = TeamReader.readTeams(file.getAbsolutePath());
		List<Group> groups = new ArrayList<>();
		
		List<Team> teamsA = getTeams(allTeams, Arrays.asList("Russia", "Saudi Arabia", "Egypt", "Uruguay"));
		groups.add(getGroup(teamsA));
		
		List<Team> teamsB = getTeams(allTeams, Arrays.asList("Portugal", "Spain", "Morocco", "Iran"));;
		groups.add(getGroup(teamsB));
		
		List<Team> teamsC = getTeams(allTeams, Arrays.asList("France", "Australia", "Peru", "Denmark"));;
		groups.add(getGroup(teamsC));
		
		List<Team> teamsD = getTeams(allTeams, Arrays.asList("Argentina", "Iceland", "Croatia", "Nigeria"));
		groups.add(getGroup(teamsD));
		
		List<Team> teamsE = getTeams(allTeams, Arrays.asList("Brazil", "Switzerland", "Costa Rica", "Serbia"));
		groups.add(getGroup(teamsE));
		
		List<Team> teamsF = getTeams(allTeams, Arrays.asList("Germany", "Mexico", "Sweden", "Korea"));
		groups.add(getGroup(teamsF));
		
		List<Team> teamsG = getTeams(allTeams, Arrays.asList("Belgium", "Panama", "Tunisia", "England"));
		groups.add(getGroup(teamsG));
		
		List<Team> teamsH = getTeams(allTeams, Arrays.asList("Poland", "Senegal", "Colombia", "Japan"));
		groups.add(getGroup(teamsH));
		
		int nrRuns = 1;
		HashMap<Team, Integer> nrWins = new HashMap<>();
		WorldCup worldCup = new WorldCup(groups);
		for (int i = 0; i < nrRuns; i++) {
			worldCup.go();
			Team winner = worldCup.getWinner();
			if(nrWins.containsKey(winner)) {
				nrWins.put(winner, nrWins.get(winner) + 1);
			} else {
				nrWins.put(winner, 1);
			}
		}
		
//		System.out.println(nrWins);
		
	}

	private static List<Team> getTeams(List<Team> readTeams, List<String> teamNames) {
		List<Team> teams = new ArrayList<>();
		for (String teamName : teamNames) {
			teams.add(readTeams.stream().filter(t -> t.getName().equalsIgnoreCase(teamName)).findFirst().get());
			
		}
		return teams;
	}

	private static Group getGroup(List<Team> teams) {
		List<Game> games = getGroupGames(teams);
		return new Group(games);
	}
	
	private static List<Game> getGroupGames(List<Team> team) {
		List<Game> games = new ArrayList<>();
		games.add(new Game(team.get(0),team.get(1), new PoissonPredictor()));
		games.add(new Game(team.get(0),team.get(2), new PoissonPredictor()));
		games.add(new Game(team.get(0),team.get(3), new PoissonPredictor()));
		games.add(new Game(team.get(1),team.get(2), new PoissonPredictor()));
		games.add(new Game(team.get(1),team.get(3), new PoissonPredictor()));
		games.add(new Game(team.get(2),team.get(3), new PoissonPredictor()));
		return games;
	}
	
	
}
