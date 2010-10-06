package no.uib.info282.tileworld.board;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import no.uib.info282.tileworld.Tile;

/**
 * The public gateway to a board. Contains boardContent, and makes sure that
 * only legal stuff are done with that.
 * 
 * @author epa095, pdr081
 * 
 */
public class Board {
	private final BoardContent boardContent;
	private final long expansionMilliSleep;
	private long numberOfVisits;
	private long numberOfExpansions;
	private Tile prev;

	/**
	 * Creates a new Board with the given {@link BoardContent} and a instruction
	 * for how long the GUI will sleep when it shows the path expansiontime.
	 * 
	 * @param content
	 * @param expansionMilliSleep
	 */
	public Board(BoardContent content, long expansionMilliSleep) {
		this.boardContent = content;
		this.expansionMilliSleep = expansionMilliSleep;
	}

	public Tile getLastExpandedTile() {
		return prev;
	}

	/**
	 * Given a tile, it returns all the tiles one can move to from that tile. Will
	 * not return tiles that contains walls or other objects we cant walk through.
	 * 
	 * tile must be a tile on the board
	 * 
	 * @param tile
	 *          The tile to get the successors of.
	 * @return A list of successortiles
	 */
	public List<Tile> getSuccessors(Tile tile) {
		prev = tile;
		numberOfExpansions++;
		if (!boardContent.inBounds(tile)) {
			throw new IndexOutOfBoundsException("Tile out of board: " + tile);
		}
		List<Tile> successors = new ArrayList<Tile>(8);
		if (!boardContent.getType(tile).isWalkable()) {
			return successors;
		}

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				Tile t = tile.add(new Tile(x, y));
				if ((x != 0 || y != 0) && (x == 0 || y == 0) && boardContent.inBounds(t)
						&& boardContent.getType(t).isWalkable()) {
					numberOfVisits++;
					successors.add(t);
					boardContent.visit(t, 1);
				}
			}
		}
		boardContent.visit(tile, 2);
		try {
			TimeUnit.MILLISECONDS.sleep(expansionMilliSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return successors;
	}

	public long getNumberOfExpansions() {
		return numberOfExpansions;
	}

	public long getNumberOfVisits() {
		return numberOfVisits;
	}
}
