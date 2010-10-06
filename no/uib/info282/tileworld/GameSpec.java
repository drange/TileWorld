package no.uib.info282.tileworld;

public class GameSpec {
	// size of world
	public final static int WIDTH = 100;
	public final static int HEIGHT = 80;
	public final static int TILE_SIZE = 10;

	// wall creation
	public final static double WALL_CREATION = .005;
	public final static double WALL_CONTINUE = .8;
	public final static double WALL_TURN = .05;

	// sleeping
	public final static long EXPANSION_MILLI_SLEEP = 100;
	public final static long PATH_DRAW_MILLI_SLEEP = 50;

	private final int width;
	private final int height;
	private final int tileSize;

	private final double wallCreation;
	private final double wallContinue;
	private final double wallTurn;

	private final long expansionMilliSleep;
	private final long pathDrawMilliSleep;

	/**
	 * Given all needed parameters it starts a search in a random world with two
	 * different search algorithms and draws the searchs and the paths in the
	 * world.
	 * 
	 * 
	 * @param width
	 *          The horizontal number of tiles
	 * @param height
	 *          The vertical number of tiles
	 * @param tileSize
	 *          The size of each tile in the GUI
	 * @param wallCreation
	 *          The probability that it will create a wall per tile. A larger
	 *          number creates more walls
	 * @param wallContinue
	 *          The probability that it will continue to create a wall. A larger
	 *          number creates longer walls
	 * @param wallTurn
	 *          The probability that it create a turn in a wall. A lower number
	 *          creates straight walls
	 * @param expansionMilliSleep
	 *          The time of millisecons it will sleep each tile in the
	 *          expansionface
	 * @param pathDrawMilliSleep
	 *          The time of millisecons it will sleep each tile in the
	 *          pathdrawingface
	 */
	public GameSpec(int width, int height, int tileSize, double wallCreation, double wallContinue, double wallTurn,
			long expansionMilliSleep, long pathDrawMilliSleep) {
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;
		this.wallCreation = wallCreation;
		this.wallContinue = wallContinue;
		this.wallTurn = wallTurn;
		this.expansionMilliSleep = expansionMilliSleep;
		this.pathDrawMilliSleep = pathDrawMilliSleep;
	}

	public static GameSpec defaultSpec() {
		return new GameSpec(WIDTH, HEIGHT, TILE_SIZE, WALL_CREATION, WALL_CONTINUE, WALL_TURN, EXPANSION_MILLI_SLEEP,
				PATH_DRAW_MILLI_SLEEP);
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static int getTILE_SIZE() {
		return TILE_SIZE;
	}

	public static double getWALL_CREATION() {
		return WALL_CREATION;
	}

	public static double getWALL_CONTINUE() {
		return WALL_CONTINUE;
	}

	public static double getWALL_TURN() {
		return WALL_TURN;
	}

	public static long getEXPANSION_MILLI_SLEEP() {
		return EXPANSION_MILLI_SLEEP;
	}

	public static long getPATH_DRAW_MILLI_SLEEP() {
		return PATH_DRAW_MILLI_SLEEP;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getTileSize() {
		return tileSize;
	}

	public double getWallCreation() {
		return wallCreation;
	}

	public double getWallContinue() {
		return wallContinue;
	}

	public double getWallTurn() {
		return wallTurn;
	}

	public long getExpansionMilliSleep() {
		return expansionMilliSleep;
	}

	public long getPathDrawMilliSleep() {
		return pathDrawMilliSleep;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (expansionMilliSleep ^ (expansionMilliSleep >>> 32));
		result = prime * result + height;
		result = prime * result + (int) (pathDrawMilliSleep ^ (pathDrawMilliSleep >>> 32));
		result = prime * result + tileSize;
		long temp;
		temp = Double.doubleToLongBits(wallContinue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(wallCreation);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(wallTurn);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + width;
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
		GameSpec other = (GameSpec) obj;
		if (expansionMilliSleep != other.expansionMilliSleep)
			return false;
		if (height != other.height)
			return false;
		if (pathDrawMilliSleep != other.pathDrawMilliSleep)
			return false;
		if (tileSize != other.tileSize)
			return false;
		if (Double.doubleToLongBits(wallContinue) != Double.doubleToLongBits(other.wallContinue))
			return false;
		if (Double.doubleToLongBits(wallCreation) != Double.doubleToLongBits(other.wallCreation))
			return false;
		if (Double.doubleToLongBits(wallTurn) != Double.doubleToLongBits(other.wallTurn))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

}
