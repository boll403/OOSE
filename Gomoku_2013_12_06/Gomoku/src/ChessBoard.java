import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class ChessBoard extends ChessBoardFrame {
	
	public ChessBoard(){
		//���͵���
		this.setIconImage(new ImageIcon("icon.png").getImage());
		this.setTitle("���l��");
		this.getBoard().setIcon(new ImageIcon("board.png"));
		this.setSize(615, 800);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);	
	//	this.setResizable(false);
		this.setVisible(true);
	}

	
	public void RecordBoard() {
		int a=0;
		
	}

}
