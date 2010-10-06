package no.uib.info282.tileworld;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import no.uib.info282.tileworld.board.Board;
import no.uib.info282.tileworld.board.BoardContent;
import no.uib.info282.tileworld.board.BoardController;
import no.uib.info282.tileworld.solvers.Solver;

public class Game {
	/**
	 * Given all needed parameters it starts a search in a random world with two
	 * different searchalgorithms and draws the searchs and the paths in the
	 * world.
	 * 
	 * @param solver
	 *          The first algorithm to try it
	 * @param solver2List
	 *          A list of other solvers to try out
	 * @param gameSpec
	 *          the game spec
	 */
	public Game(Solver solver, List<Solver> solver2List, GameSpec gameSpec) {
		// Setting up the world
		BoardContent boardContent = BoardContent.generateBoard(gameSpec);
		Board board = new Board(boardContent, gameSpec.getExpansionMilliSleep());
		BoardController controller = new BoardController(board, boardContent, gameSpec.getTileSize());
		Tile start = boardContent.getStart();
		Tile goal = boardContent.getGoal();

		// Setting up the GUI
		JPanel view = controller.getView();
		JFrame frame = new JFrame("Tile World");
		frame.getContentPane().add(view);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Doing search 1.
		List<Tile> path = solver.getPath(board, start, goal);
		boardContent.drawPath(path, gameSpec.getPathDrawMilliSleep(), 1);
		System.out.println("Depth of path is  " + (path == null ? "infinite" : path.size()));
		System.out.println("Number of expansions is " + board.getNumberOfExpansions());
		System.out.println("Number of visits is " + board.getNumberOfVisits());
		System.out.println("Done with the search 1.");

		System.out.print("Does the path start and end with start and goal?: ");
		System.out.println(checkPath(path, start, goal));

		// If we have a second solver we repeat the process with that one.
		if (solver2List != null) {
			int solverNr = 2;
			for (Solver nextSolver : solver2List) {
				long preNumberOfExpansions = board.getNumberOfExpansions();
				long preNumberOfVisits = board.getNumberOfVisits();
				System.out.println("Starting search " + solverNr);
				path = nextSolver.getPath(board, start, goal);
				boardContent.drawPath(path, gameSpec.getPathDrawMilliSleep(), 2);
				System.out.println("Depth of path is  " + (path == null ? "infinite" : path.size()));
				System.out.println("Number of expansions is " + (board.getNumberOfExpansions() - preNumberOfExpansions));
				System.out.println("Number of visits is " + (board.getNumberOfVisits() - preNumberOfVisits));
				System.out.println("Done with the search " + solverNr++ + " .");

				System.out.print("Does the path start and end with start and goal?: ");
				System.out.println(checkPath(path, start, goal));
			}
		}
	}

	private boolean checkPath(List<Tile> path, Tile start, Tile goal) {
		return path != null && start.equals(path.get(0)) && goal.equals(path.get(path.size() - 1));
	}

	public Game(Solver solver, GameSpec gameSpec) {
		this(solver, null, gameSpec);
	}
}
