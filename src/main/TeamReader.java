package main;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TeamReader {

	public static List<Team> readTeams(String path) {
		String content = getContent(path);
		List<Team> teams = parseToTeams(content);
		return teams;
	}

	private static List<Team> parseToTeams(String content) {
		String[] rows = content.split("\\n");
		List<Team> teams = new ArrayList<>();
		for (String row : rows) {
			Team team = toTeam(row);
			teams.add(team);
		}
		return teams;
	}

	private static Team toTeam(String row) {
		String[] columns = row.split(",");
		Team team = new Team(columns[0].trim())
				.avgScored(new Double(columns[1]))
				.avgConceded(new Double(columns[2]));
		return team;
	}

	private static String getContent(String path) {
		String content = "";
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			content = new String(encoded, Charset.defaultCharset());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	
	
}
