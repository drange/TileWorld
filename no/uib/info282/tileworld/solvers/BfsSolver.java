package no.uib.info282.tileworld.solvers;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;

import no.uib.info282.tileworld.Node;
import no.uib.info282.tileworld.Tile;
import no.uib.info282.tileworld.board.Board;

/**
 * Solves the TileWorld search problem by a Breadth First Search
 * algorithm. This algorithm (when implemented correctly) is both
 * optimal and complete, but uses much memory and, in this problem,
 * way more time than necessary.
 * 
 * @author pdr081
 * 
 */
public class BfsSolver implements Solver {
    
    public List<Tile> getPath(Board board, Tile start, Tile goal) {
	Set<Tile> visited = new HashSet<Tile>();
	Queue<Node> frontier = new LinkedList<Node>();
	frontier.offer(Node.createStart(start));
	while (!frontier.isEmpty()) {
	    Node node = frontier.remove();
	    Tile tile = node.getTile();
	    visited.add(tile);
	    if (tile.equals(goal)) {
		return node.getPath();
	    }
	    for (Tile nt : board.getSuccessors(tile)) {
		if (!visited.contains(nt)) {
		    Node n = node.addChild(nt);
		    visited.add(n.getTile());
		    frontier.offer(n);
		}
	    }
	}
	return null;
    }
}
