import java.awt.Dimension;

import javax.swing.ImageIcon;

public class ChessBoard extends ChessBoardFrame {
	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();	 
	public ChessBoard(){
		//²£¥Íµøµ¡
		this.setIconImage(new ImageIcon("icon.png").getImage());
		this.setTitle("·t´Ñ");
		this.getBoard().setIcon(new ImageIcon("board.png"));
		this.setSize(790, 570);
		this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) /2);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);	
		this.setResizable(false);
		this.setVisible(true);
	}
}
