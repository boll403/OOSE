import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class ChessGame extends java.util.Observable implements MouseListener, ActionListener{
	private static final int SIZEX = 4;
	private static final int SIZEY = 8;
	private RuleOfChineseChess rule ;
	private int prei=-1;
	public Chess chess;
	private ChessBoard ChineseChessBoard;
	private RandomBoard randomboard;
	private GridLayout layout;
	private Lattice lattice[][]= new Lattice[SIZEX][SIZEY];
	private int recordBoard[][]=new int[32][2];
	private int recordBoardForRetry[][]=new int[32][2];
	private String changewho="";
	private String image="changeRight.png";
	
	public ChessGame(){
		ChineseChessBoard = new ChessBoard();
		randomboard = new RandomBoard();
		chess = new Chess();
		layout = new GridLayout(SIZEX,SIZEY);
		ChineseChessBoard.getBoard().setLayout(layout);
		rule= new RuleOfChineseChess(randomboard.getRandboard());
		
		for (int i = 0; i < SIZEX; i++) {
			for (int j = 0; j < SIZEY; j++) {
				lattice[i][j] = new Lattice(i,j);
				lattice[i][j].addMouseListener(this);
				ChineseChessBoard.getBoard().add(lattice[i][j]);
				lattice[i][j].setIcon(new ImageIcon("back.png"));
				lattice[i][j].setBorder(null);
			}
		}

		//將棋子初始化，狀態設為反面
		for(int count=0;count!=32;count++){
			recordBoard[count][1]=-1;
			recordBoard[count][0]=-1;
		}
		this.addObserver(rule);
		ChineseChessBoard.getRetry().addActionListener(this);
		ChineseChessBoard.getRestart().addActionListener(this);
		ChineseChessBoard.getExit().addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(ChineseChessBoard.getRetry())){
			setRetry();
		}else if(arg0.getSource().equals(ChineseChessBoard.getRestart())){
			setRestart();
		}else if(arg0.getSource().equals(ChineseChessBoard.getExit())){
			System.exit(0);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Lattice l = (Lattice) arg0.getComponent();
		int board[]=new int[32];
		int i = l.getChessBoardX()*8 + l.getChessBoardY();
		setChanged();
		notifyObservers(i);
		if(rule.move(recordBoard)){
			if( recordBoard[i][1]==-1){
				lattice[ l.getChessBoardX()][l.getChessBoardY()].setIcon(
						new ImageIcon( chess.getImage(randomboard.getRandboard()[i][0],randomboard.getRandboard()[i][1])));
				recordBoard[i][0]=randomboard.getRandboard()[i][0];
				recordBoard[i][1]=randomboard.getRandboard()[i][1];
				changeLableColor();
			}else if(rule.getState().equals("redReady") || rule.getState().equals("blackReady")){
				for(int count=0;count<32;count++){
					recordBoardForRetry[count][0]=recordBoard[count][0];
					recordBoardForRetry[count][1]=recordBoard[count][1];
				}
				
				lattice[l.getChessBoardX()][l.getChessBoardY()].setBorder(BorderFactory.createLineBorder(Color.MAGENTA,3));
				prei=i;
			}else if(rule.getState().equals("redReadyToEat") || rule.getState().equals("blackReadyToEat")){
				lattice[ l.getChessBoardX()][l.getChessBoardY()].setIcon(
						new ImageIcon( chess.getImage(recordBoard[prei][0], recordBoard[prei][1])));
				lattice[prei/8][prei%8].setIcon(new ImageIcon(""));
				lattice[l.getChessBoardX()][l.getChessBoardY()].removeMouseListener(this);
				lattice[l.getChessBoardX()][l.getChessBoardY()].addMouseListener(this);
				lattice[prei/8][prei%8].removeMouseListener(this);
				lattice[prei/8][prei%8].addMouseListener(this);
				recordBoard[i][0]=recordBoard[prei][0];
				recordBoard[i][1]=recordBoard[prei][1];
				recordBoard[prei][0]=-2;
				recordBoard[prei][1]=-2;
				setChanged();
				notifyObservers(-1);
				changeLableColor();
			}
			for(int count=0;count!=32;count++){
				board[count]=recordBoard[count][1];
			}
			rule.checkWin(board);
		}else changeLableColor();
		if(changewho.equals("")){
			ChineseChessBoard.getVs().setIcon(new ImageIcon("changeRight.png"));
			changewho=rule.getState();
		}else if(!changewho.substring(0,3).equals(rule.getState().substring(0,3))){
			if(image.equals("changeRight.png")){
				image="changeLeft.png";
			}else
				image="changeRight.png";
			changewho=rule.getState();
			ChineseChessBoard.getVs().setIcon(new ImageIcon(image));
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
	public void setRestart(){
		ChineseChessBoard.dispose();
		new ChessGame();
	}
	public void setRetry(){
		if(prei==-10)return ;
		rule.changeState(recordBoard);
		for(int count=0;count<32;count++){
			if(recordBoardForRetry[count][1]==-2)lattice[ count/8][count%8].setIcon(new ImageIcon(""));
			else if(recordBoard[count][1]!=recordBoardForRetry[count][1]){
				lattice[ count/8][count%8].setIcon(
						new ImageIcon( chess.getImage(recordBoardForRetry[count][0],recordBoardForRetry[count][1])));
			}
			lattice[ count/8][count%8].removeMouseListener(this);
			lattice[ count/8][count%8].addMouseListener(this);
		}
		for(int count=0;count<32;count++){
			recordBoard[count][0]=recordBoardForRetry[count][0];
			recordBoard[count][1]=recordBoardForRetry[count][1];
		}
		if(!changewho.substring(0,3).equals(rule.getState().substring(0,3))){
			if(image.equals("changeRight.png")){
				image="changeLeft.png";
			}else
				image="changeRight.png";
			changewho=rule.getState();
			ChineseChessBoard.getVs().setIcon(new ImageIcon(image));
		}
		changeLableColor();
		//換變數   紀錄已毀棋
		//prei=-10;
		
	}
	public void changeLableColor(){
		for(int count =0;count!=32;count++){
			lattice[count/8][count%8].setBorder(null);
		}
	}
}
