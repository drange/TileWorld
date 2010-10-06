package no.uib.info282.tileworld.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class BoardController {
	private final BoardContent boardContent;
	private final BoardView view;

	public BoardController(Board board, BoardContent boardContent, int tileSize) {
		this.boardContent = boardContent;
		this.view = new BoardView(board, boardContent, tileSize);
		init();
	}

	public JPanel getView() {
		return view;
	}

	private void init() {
		boardContent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.repaint();
			}
		});
	}

}
