import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ChessBoardFrame extends JFrame {
	//private static final int SIZE = 15;
	private GridBagLayout layout;
	private GridBagConstraints constraint;
	private JLabel player1;
	private JLabel vs;
	private JLabel player2;
	private JLabel board;
	private JButton retry;
	private JButton restart;
	private JButton exit;
	
	public ChessBoardFrame() {	
		layout = new GridBagLayout();	
		constraint = new GridBagConstraints();
		player1 = new JLabel(new ImageIcon("player1.png"));
		vs = new JLabel(new ImageIcon("changeRight.png"));
		player2 = new JLabel(new ImageIcon("player2.png"));
		board = new JLabel();
		retry = new JButton("悔棋");
		restart = new JButton("重新開始");
		exit = new JButton("離開");
		
		//設定layout
		constraint.weightx=1.0 ;
		constraint.fill = GridBagConstraints.BOTH;
		this.setLayout(layout);
		

		layout.setConstraints(player1, constraint);
		
		this.add(player1);
		
		layout.setConstraints(vs, constraint);
		this.add(vs);
		
		constraint.gridwidth = GridBagConstraints.REMAINDER;
		layout.setConstraints(player2, constraint);
		this.add(player2); 
		
		constraint.weighty=1.0 ;
		layout.setConstraints(board, constraint);
		this.add(board);
		board.setBackground(Color.black);
		
		constraint.weighty=0.0 ;
		constraint.gridwidth = 1;
		layout.setConstraints(retry, constraint);
		this.add(retry);
		
		layout.setConstraints(restart, constraint);
		this.add(restart);
		
		layout.setConstraints(exit, constraint);
		this.add(exit);
		
	    
	}
	
	public JLabel getPlayer1() {
		return player1;
	}

	public void setPlayer1(JLabel player1) {
		this.player1 = player1;
	}

	public JLabel getPlayer2() {
		return player2;
	}

	public void setPlayer2(JLabel player2) {
		this.player2 = player2;
	}

	public JLabel getBoard() {
		return board;
	}

	public void setBoard(JLabel board) {
		this.board = board;
	}

	public JButton getRetry() {
		return retry;
	}

	public void setRetry(JButton retry) {
		this.retry = retry;
	}

	public JButton getRestart() {
		return restart;
	}

	public void setRestart(JButton restart) {
		this.restart = restart;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	public abstract void RecordBoard();

}
