package no.uib.info282.tileworld;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a Node in a search. Contains a tile, but also knows the pa th
 * followed to get to it and its cost.
 * 
 * @author epa095, pdr081
 * 
 */
public class Node {
	private final Tile tile;
	private final int cost;
	private final Node parent;

	private Node(Tile tile, Node parent) {
		this.tile = tile;
		this.parent = parent;
		int tmpCost = tile.getCost();
		if (parent != null) {
			tmpCost += parent.cost;
		}
		this.cost = tmpCost;
	}

	public String toString() {
		return tile + ": " + cost;
	}

	/**
	 * Creates a startnode (one with no parrents) with Tile tile.
	 * 
	 * @param tile
	 * @return
	 */
	public static Node createStart(Tile tile) {
		return new Node(tile, null);
	}

	/**
	 * Returns a new node that has the Tile tile, and this node as a parrent
	 * 
	 * @param tile
	 * @return
	 */
	public Node addChild(Tile tile) {
		return new Node(tile, this);
	}

	/**
	 * Gives a list of Tile's used to walk to the Tile in this Node.
	 * 
	 * @return A list of Tile
	 */
	public List<Tile> getPath() {
		Node p = this;
		LinkedList<Tile> path = new LinkedList<Tile>();

		while (p != null) {
			path.addFirst(p.tile);
			p = p.parent;
		}
		return path;
	}

	// Add a cache of this hashcode (as all values are final) if needed.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((tile == null) ? 0 : tile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (cost != other.cost)
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (tile == null) {
			if (other.tile != null)
				return false;
		} else if (!tile.equals(other.tile))
			return false;
		return true;
	}

	public Tile getTile() {
		return tile;
	}

	public int getCost() {
		return cost;
	}

	public Node getParent() {
		return parent;
	}

}
