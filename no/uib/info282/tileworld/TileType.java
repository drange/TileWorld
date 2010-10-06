package no.uib.info282.tileworld;

public enum TileType {
	WALL(false), OBSTACLE(false), GROUND(true), PATH1(true), PATH2(true);
	final private boolean walkable;

	TileType(boolean walk) {
		this.walkable = walk;
	}

	public boolean isWalkable() {
		return walkable;
	}
}
