package no.uib.info282.tileworld.solvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import no.uib.info282.tileworld.Node;
import no.uib.info282.tileworld.Tile;
import no.uib.info282.tileworld.board.Board;

/**
 * Chooses a random tile from the fringe to expand. Probably same average memory
 * and time as breadth first search, complete, but not optimal.
 * 
 * @author epa095, pdr081
 * 
 */
public class RandomSolver implements Solver {

	public List<Tile> getPath(Board board, Tile start, Tile goal) {
		Random random = new Random();
		Set<Tile> visited = new HashSet<Tile>();
		List<Node> fringe = new ArrayList<Node>();
		fringe.add(Node.createStart(start));
		while (!fringe.isEmpty()) {
			Node node = fringe.remove(random.nextInt(fringe.size()));
			Tile tile = node.getTile();
			visited.add(tile);
			if (tile.equals(goal)) {
				return node.getPath();
			}
			for (Tile nt : board.getSuccessors(tile)) {
				if (!visited.contains(nt)) {
					Node n = node.addChild(nt);
					visited.add(n.getTile());
					fringe.add(n);
				}
			}
		}
		return null;
	}
}
