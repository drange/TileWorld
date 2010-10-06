package no.uib.info282.tileworld;

/**
 * Represents a place on the board.
 * 
 * @author epa095, pdr081
 * 
 */
public class Tile {
	private final int x;
	private final int y;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the straigth-line distance between this tile and the Tile t
	 * 
	 * @param t
	 *          The Tile to give the distance to
	 * @return The distance to t
	 */
	public double distance(Tile t) {
		return Math.sqrt(Math.pow(x - t.x, 2) + Math.pow(y - t.y, 2));
	}

	/**
	 * Returns the manhatan distance between this tile and t
	 * 
	 * @param t
	 *          The tile to give the distance to
	 * @return The dinstance to t
	 */
	public int manhattanDistance(Tile t) {
		int xd = Math.abs(x - t.x);
		int yd = Math.abs(y - t.y);
		return xd + yd;
	}

	/**
	 * Returns a new Tile that is this tile with t added. Often used with t beeing
	 * one of the direction-tiles (-1 <=x <=1 and -1 <=y <=1)
	 * 
	 * Addition is normal vectoraddition
	 * 
	 * @param t
	 *          The tile to add
	 * @return A new tile that is this with t added.
	 */
	public Tile add(Tile t) {
		return new Tile(t.x + x, t.y + y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getCost() {
		return 1;
	}

	@Override
	public String toString() {
		return "Tile [" + x + "," + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Tile other = (Tile) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
