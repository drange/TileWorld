package no.uib.info282.tileworld.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import no.uib.info282.tileworld.Tile;
import no.uib.info282.tileworld.TileType;

public class BoardView extends JPanel {
	private static final long serialVersionUID = 1L;
	private final int tileSize;
	private final BoardContent boardContent;
	private final Board board;

	public BoardView(Board board, BoardContent content, int tileSize) {
		this.board = board;
		this.tileSize = tileSize;
		setPreferredSize(new Dimension(content.getWidth() * tileSize, content.getHeight() * tileSize));
		this.boardContent = content;
	}

	public void drawColor(Color color, Tile tile, Graphics2D g2) {
		drawColor(color, tile.getX(), tile.getY(), g2);
	}

	public void drawColor(Color color, int x, int y, Graphics2D g2) {
		if (g2 == null) {
			g2 = (Graphics2D) getGraphics();
		}
		if (g2 != null) {
			g2.setPaint(color);
			g2.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
		}
		Tile goal = boardContent.getGoal();
		if (goal != null) {
			g2.setPaint(Color.RED);
			g2.fillOval(goal.getX() * tileSize - 5, goal.getY() * tileSize - 5, tileSize + 10, tileSize + 10);
			g2.setPaint(Color.CYAN);
			g2.fillOval(goal.getX() * tileSize, goal.getY() * tileSize, tileSize, tileSize);
		}
		Tile prev = board.getLastExpandedTile();
		if (prev != null) {
			g2.setPaint(Color.YELLOW);
			g2.fillOval(prev.getX() * tileSize, prev.getY() * tileSize, tileSize, tileSize);
		}
	}

	public void drawTile(Tile tile, Graphics2D g2) {
		TileType type = boardContent.getType(tile);
		Color color = Color.WHITE;
		if (tile.equals(boardContent.getStart()) || tile.equals(boardContent.getGoal())) {
			color = Color.RED;
		} else if (type == TileType.GROUND) {
			short visitCount = boardContent.visitCount(tile);
			if (visitCount == 2) {
				color = Color.MAGENTA;
			} else if (visitCount == 1) {
				color = Color.GREEN;
			}
		} else if (type == TileType.WALL || type == TileType.OBSTACLE) {
			color = Color.BLACK;
		} else if (type == TileType.PATH1) {
			color = Color.BLUE;
		} else if (type == TileType.PATH2) {
			color = Color.CYAN;
		}
		drawColor(color, tile, g2);
	}

	@Override
	protected void paintComponent(Graphics g) {
		setBackground(Color.WHITE);
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (int x = 0; x < boardContent.getWidth(); x++) {
			for (int y = 0; y < boardContent.getHeight(); y++) {
				drawTile(boardContent.getTile(x, y), g2);
			}
		}
	}
}
