import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class ChessGame implements MouseListener, ActionListener {
	private static final int SIZE = 13;
	private ChessBoard chessboard;
	private Chess chess;
	private Lattice lattice[][]= new Lattice[SIZE][SIZE];
	private RuleOfGomoku rule;
	private GridLayout layout;
	private int change=0;
	private int recordBoard[]={0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0,
							  0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	public ChessGame(){
		chessboard = new ChessBoard();
		chess = new Chess();
		rule = new RuleOfGomoku();
		layout = new GridLayout(SIZE,SIZE);
		chessboard.getBoard().setLayout(layout);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				lattice[i][j] = new Lattice(i,j);
				lattice[i][j].addMouseListener(this);
				chessboard.getBoard().add(lattice[i][j]);
				lattice[i][j].setBorder(null);
			}
		}
		chessboard.getRetry().addActionListener(this);
		chessboard.getRestart().addActionListener(this);
		chessboard.getExit().addActionListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Lattice l = (Lattice) arg0.getComponent();
		
		if(change ==0){
			lattice[ l.getChessBoardX()][l.getChessBoardY()].setIcon((new ImageIcon("")));  //避免第一個下棋的錯誤
		}
		else if(change % 2 == 1){
			lattice[ l.getChessBoardX()][l.getChessBoardY()].setIcon((new ImageIcon(chess.getBlack())));
		    chessboard.getVs().setIcon(new ImageIcon("changeRight.png"));
		    lattice[ l.getChessBoardX()][l.getChessBoardY()].removeMouseListener(this);
		}
		else{
			lattice[ l.getChessBoardX()][l.getChessBoardY()].setIcon((new ImageIcon(chess.getWhite())));
			chessboard.getVs().setIcon(new ImageIcon("changeLeft.png"));
			lattice[ l.getChessBoardX()][l.getChessBoardY()].removeMouseListener(this);
		}
		recordBoard[l.getChessBoardX()*SIZE + l.getChessBoardY()]=change;
		rule.checkWin(recordBoard);
		change++;

		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(chessboard.getRetry())){
			setRetry();
		}else if(arg0.getSource().equals(chessboard.getRestart())){
			setRestart();
		}else if(arg0.getSource().equals(chessboard.getExit())){
			System.exit(0);
		}
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
	
	public void setRetry(){
		for(int count=0;count!=168 && change >1;count++){
			if(recordBoard[count]==change-1){
				lattice[count/13][count%13].removeMouseListener(this);
				lattice[count/13][count%13].setIcon(new ImageIcon(""));
				lattice[count/13][count%13].addMouseListener(this);
				recordBoard[count]=0;
			}
		}
		if(change >1)change--;
		if(change % 2 == 1){
			chessboard.getVs().setIcon(new ImageIcon("changeLeft.png"));
		}
		else{
			chessboard.getVs().setIcon(new ImageIcon("changeRight.png"));
		}
	}

	public void setRestart(){
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				lattice[i][j].setIcon((new ImageIcon("")));
				lattice[i][j].removeMouseListener(this);
				lattice[i][j].addMouseListener(this);
				recordBoard[i*13+j]=0;
			}
		}
		chessboard.getVs().setIcon(new ImageIcon("changeLeft.png"));
		change=1;
	}
}
