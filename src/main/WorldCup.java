package main;

import java.util.List;

public class WorldCup implements ITournament{

	private List<Group> _groups;
	private Team _winner;

	public WorldCup(List<Group> groups){
		_groups = groups;
	}
	
	public void go() throws Exception {
		
		playGroups();
		
		Game roundOfSixteen1 = new Game(_groups.get(0).getWinner(), _groups.get(1).getRunnerUp(), new PoissonPredictor());
		Game roundOfSixteen2 = new Game(_groups.get(1).getWinner(), _groups.get(0).getRunnerUp(), new PoissonPredictor());
		Game roundOfSixteen3 = new Game(_groups.get(2).getWinner(), _groups.get(3).getRunnerUp(), new PoissonPredictor());
		Game roundOfSixteen4 = new Game(_groups.get(3).getWinner(), _groups.get(2).getRunnerUp(), new PoissonPredictor());
		Game roundOfSixteen5 = new Game(_groups.get(4).getWinner(), _groups.get(5).getRunnerUp(), new PoissonPredictor());
		Game roundOfSixteen6 = new Game(_groups.get(5).getWinner(), _groups.get(4).getRunnerUp(), new PoissonPredictor());
		Game roundOfSixteen7 = new Game(_groups.get(6).getWinner(), _groups.get(7).getRunnerUp(), new PoissonPredictor());
		Game roundOfSixteen8 = new Game(_groups.get(7).getWinner(), _groups.get(6).getRunnerUp(), new PoissonPredictor());
	
		roundOfSixteen1.play();
		roundOfSixteen2.play();
		roundOfSixteen3.play();
		roundOfSixteen4.play();
		roundOfSixteen5.play();
		roundOfSixteen6.play();
		roundOfSixteen7.play();
		roundOfSixteen8.play();
		
		System.out.println("\n------------- Round of sixteen ---------------\n");
		
		System.out.println("Round of sixteen one: " + roundOfSixteen1);
		System.out.println("Round of sixteen two: " + roundOfSixteen2);
		System.out.println("Round of sixteen three: " + roundOfSixteen3);
		System.out.println("Round of sixteen four: " + roundOfSixteen4);
		System.out.println("Round of sixteen five: " + roundOfSixteen5);
		System.out.println("Round of sixteen six: " + roundOfSixteen6);
		System.out.println("Round of sixteen seven: " + roundOfSixteen7);
		System.out.println("Round of sixteen eight: " + roundOfSixteen8);
		
		Game quarterFinal1 = new Game(roundOfSixteen1.getWinnerNoDraw(), roundOfSixteen3.getWinnerNoDraw(), new PoissonPredictor());
		Game quarterFinal2 = new Game(roundOfSixteen2.getWinnerNoDraw(), roundOfSixteen4.getWinnerNoDraw(), new PoissonPredictor());
		Game quarterFinal3 = new Game(roundOfSixteen5.getWinnerNoDraw(), roundOfSixteen7.getWinnerNoDraw(), new PoissonPredictor());
		Game quarterFinal4 = new Game(roundOfSixteen6.getWinnerNoDraw(), roundOfSixteen8.getWinnerNoDraw(), new PoissonPredictor());
		
		quarterFinal1.play();
		quarterFinal2.play();
		quarterFinal3.play();
		quarterFinal4.play();
		
		System.out.println("\n------------- Quarter finals ---------------\n");
		
		System.out.println("Quarter final one: " + quarterFinal1);
		System.out.println("Quarter final two: " + quarterFinal2);
		System.out.println("Quarter final three: " + quarterFinal3);
		System.out.println("Quarter final four: " + quarterFinal4);
		
		Game semiFinal1 = new Game(quarterFinal1.getWinnerNoDraw(), quarterFinal3.getWinnerNoDraw(), new PoissonPredictor());
		Game semiFinal2 = new Game(quarterFinal2.getWinnerNoDraw(), quarterFinal4.getWinnerNoDraw(), new PoissonPredictor());
		
		semiFinal1.play();
		semiFinal2.play();
		
		System.out.println("\n------------- Semi finals ---------------\n");
		
		System.out.println("Semi final one: " + semiFinal1);
		System.out.println("Semi final two: " + semiFinal2);
	
		Game finals = new Game(semiFinal1.getWinnerNoDraw(), semiFinal2.getWinnerNoDraw(), new PoissonPredictor());
		
		finals.play();
		
		System.out.println("\n------------- Final -------------\n");
		
		System.out.println("Final: " + finals);
		
		_winner = finals.getWinnerNoDraw();
		
		System.out.println("\n\n WORLD CUP WINNER IS \n **********" + _winner + "*************");
	}

	private void playGroups() throws Exception {
		for (Group group : _groups) {
			group.simulate(new PoissonPredictor());
			System.out.println("Group results: \n" + group.getResultsForPrint());
			System.out.println(System.lineSeparator() + "-------------------------------------" + System.lineSeparator());
		}
	}

	public Team getWinner() {
		return _winner;
	}
	
}
