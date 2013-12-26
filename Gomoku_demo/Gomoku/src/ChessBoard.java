import java.awt.Dimension;

import javax.swing.ImageIcon;


public class ChessBoard extends ChessBoardFrame {
	
	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();	 
	public ChessBoard(){
		//產生視窗
		this.setIconImage(new ImageIcon("icon.png").getImage());
		this.setTitle("五子棋");
		this.getBoard().setIcon(new ImageIcon("board.png"));
		this.setSize(615, 790);
		this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) /2);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);	
		this.setResizable(false);
		this.setVisible(true);
	}
}
