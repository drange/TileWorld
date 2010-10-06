package no.uib.info282.tileworld.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import no.uib.info282.tileworld.GameSpec;
import no.uib.info282.tileworld.Tile;
import no.uib.info282.tileworld.TileType;

/**
 * The representation of a board and all it content. Contains a "world", and
 * also a register of where the search has "been" as following:
 * 
 * Knows if a tile has been the argument of board.getSuccessors(Tile tile), e.g
 * that it has been expaned. Also know if a Tile has been returned from
 * board.getSuccessors(Tile tile), e.g visited but not expanded.
 * 
 * @author epa095, pdr081
 * 
 */
public class BoardContent {
	private final TileType[][] board;
	private final short[][] visitCounter;
	private final int width;
	private final int height;
	private final Set<ActionListener> listeners;
	private Tile start;
	private Tile goal;

	private BoardContent(int x, int y) {
		visitCounter = new short[x][y];
		board = new TileType[x][y];
		this.width = x;
		this.height = y;
		listeners = new HashSet<ActionListener>();
	}

	public short visitCount(Tile tile) {
		if (!inBounds(tile)) {
			throw new IndexOutOfBoundsException("" + tile);
		}
		return visitCounter[tile.getX()][tile.getY()];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tile getTile(int x, int y) {
		return new Tile(x, y);
	}

	public void visit(Tile tile, int val) {
		int x = tile.getX();
		int y = tile.getY();
		if (visitCounter[x][y] < 2) {
			visitCounter[x][y] = (short) val;
		}
		for (ActionListener listener : listeners) {
			listener.actionPerformed(new ActionEvent(tile, x * width + y, "Visited [" + x + ", " + y + "]:" + val));
		}
	}

	public void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}

	private Tile getRandomDirection(Random r) {
		int x = 0, y = 0;
		do {
			x = r.nextInt(3) - 1;
			y = r.nextInt(3) - 1;
		} while (x == 0 && y == 0);
		return new Tile(x, y);
	}

	private void createWalls(Random r, Tile p, double continuep, double turnp) {
		Tile direction = getRandomDirection(r);
		while (inBounds(p) && getType(p).isWalkable()) {
			if (r.nextDouble() < continuep) {
				board[p.getX()][p.getY()] = TileType.OBSTACLE;
			}
			if (r.nextDouble() < turnp) {
				direction = getRandomDirection(r);
			}
			p = p.add(direction);
		}
	}

	public Tile getStart() {
		return start;
	}

	public Tile getGoal() {
		return goal;
	}

	private void createWalls(Random random, int x, int y, double wallContinue, double wallTurn) {
		createWalls(random, new Tile(x, y), wallContinue, wallTurn);
	}

	public TileType getType(Tile tile) {
		if (!inBounds(tile)) {
			throw new IndexOutOfBoundsException("" + tile);
		}
		return board[tile.getX()][tile.getY()];
	}

	public boolean inBounds(Tile tile) {
		return tile.getX() >= 0 && tile.getY() >= 0 && tile.getX() < width && tile.getY() < height;
	}

	public static BoardContent generateBoard(GameSpec gameSpec) {
		int height = gameSpec.getHeight();
		int width = gameSpec.getWidth();
		BoardContent board = new BoardContent(width, height);
		Random random = new Random();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
					board.board[i][j] = TileType.WALL;
				} else {
					board.board[i][j] = TileType.GROUND;
				}
			}
		}
		for (int i = 1; i < width - 1; i++) {
			for (int j = 1; j < height - 1; j++) {
				if (random.nextDouble() < gameSpec.getWallCreation()) {
					board.createWalls(random, i, j, gameSpec.getWallContinue(), gameSpec.getWallTurn());
				}
			}
		}

		int tilex = 0, tiley = 0;
		do {
			tilex = random.nextInt(width);
			tiley = random.nextInt(height);
		} while (!board.getType(board.getTile(tilex, tiley)).isWalkable());

		board.start = new Tile(tilex, tiley);

		do {
			tilex = random.nextInt(width);
			tiley = random.nextInt(height);
		} while (!board.getType(board.getTile(tilex, tiley)).isWalkable());

		board.goal = new Tile(tilex, tiley);
		return board;
	}

	public void drawPath(List<Tile> path, long milliSleep, int pathNr) {
		TileType typeSpec = TileType.PATH1;
		if (pathNr != 1) {
			typeSpec = TileType.PATH2;
		}
		if (path != null) {
			for (Tile t : path) {
				board[t.getX()][t.getY()] = typeSpec;
				for (ActionListener listener : listeners) {
					listener.actionPerformed(new ActionEvent(getTile(t.getX(), t.getY()), t.getCost(), "Path " + t));
				}
				try {
					TimeUnit.MILLISECONDS.sleep(milliSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String toString() {
		String s = "Board:\n\n";
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				switch (board[x][y]) {
				case GROUND:
					s += " ";
					break;
				case OBSTACLE:
					s += "X";
					break;
				case WALL:
					s += "M";
					break;
				}
			}
			s += "\n";
		}
		return s;
	}
}
