import javax.swing.JOptionPane;

public class RuleOfGomoku implements Rule {

	public void checkWin(int board[]) {
		int num=0;
		int i=0;
		int x[]={13,1,12,14};
		int y=0;
		String color="";
		while(y!=4){	
			for ( int count = 0; count < board.length && num!=5 ; count++ ){
				num=0;
				i=count;
				if(board[count]%2==0 && board[count]!=0){
					color="White Win";
					if(y==2 && i%13<4);						
					else if((y==1||y==3) && i%13>8);
					else{
						while(board[i]%2==0 && board[i]!=0 && num!=5){
							i=i+x[y];
							num++;
							if(i>168)break;
						}
					}
				}else if(board[count]%2==1 ){
					color="Black Win";
					if(y==2 && i%13<4);						
					else if((y==1||y==3) && i%13>8);
					else{
						while(board[i]%2==1 && num!=5){
							i=i+x[y];
							num++;
							if(i>168)break;
						}
					}
				}
			}
			y++;
		}
		if(num==5){
			JOptionPane.showMessageDialog(null, color, "", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}
