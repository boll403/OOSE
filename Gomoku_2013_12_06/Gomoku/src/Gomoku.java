
import java.awt.GridLayout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;



public class Gomoku implements MouseListener{
	ChessBoard chessboard;
	GridLayout layout;
	Piece piece;
	JLabel[][] a= new JLabel[13][13];
	
	JButton[][] button = new JButton[13][13];
	private static final int SIZE = 13;
	int count=0;
	public Gomoku(){
		chessboard = new ChessBoard();
		piece = new Piece();
		layout = new GridLayout(SIZE,SIZE);
		chessboard.getBoard().setLayout(layout);
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				a[i][j] = new JLabel(new ImageIcon("black.png"));
				a[i][j].addMouseListener(this);
				chessboard.getBoard().add(a[i][j]);
				a[i][j].setBorder(null);
				
			}
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		//Chess chess = (Chess) e.getComponent();
		//button[arg0.getX()][arg0.getY()].setIcon((new ImageIcon("black.gif")));
		System.out.println("HAPPY~~~~");

		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		 new Gomoku();
	}
	
}
