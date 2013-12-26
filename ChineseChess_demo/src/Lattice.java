import javax.swing.JLabel;

public class Lattice extends JLabel{
	private int chessBoardX=0,chessBoardY=0;
	
	public Lattice(int x,int y){
		this.chessBoardX = x;
		this.chessBoardY = y;
	}

	public int getChessBoardX() {
		return chessBoardX;
	}

	public void setChessBoardX(int chessBoardX) {
		this.chessBoardX = chessBoardX;
	}

	public int getChessBoardY() {
		return chessBoardY;
	}

	public void setChessBoardY(int chessBoardY) {
		this.chessBoardY = chessBoardY;
	}
}
