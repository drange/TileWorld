package no.uib.info282.tileworld.solvers;

import java.util.List;

import no.uib.info282.tileworld.Tile;
import no.uib.info282.tileworld.board.Board;

/**
 * A interface for a solver of the mapsearch. The solver is given a board, a
 * start and goal {@link Tile}. It have to return a list of Tiles that is the
 * path between start and goal. If there is no path return null or a empty list.
 * 
 * It searches on board using the method {@link Board#getSuccessors(Tile)} that
 * gives all the legal successortiles of tile.
 * 
 * 
 * @author epa095, pdr081
 * 
 */
public interface Solver {
	/**
	 * Finds and returns a path between start and goal in board. For it to
	 * considered a path it must include start as the first node and goal as the
	 * last node.
	 * 
	 * @param board
	 *            The board to search on
	 * @param start
	 *            the Tile to start the search from
	 * @param goal
	 *            the Tile to search for a path to
	 * @return A list of the tiles that must be traversed to get from start to
	 *         goal or null if no path is found
	 */
	List<Tile> getPath(Board board, Tile start, Tile goal);
}
