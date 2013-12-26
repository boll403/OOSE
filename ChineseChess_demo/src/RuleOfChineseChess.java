import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class RuleOfChineseChess implements Rule, Observer{
	private String state="";
	private int recordBoard[][]=new int[32][2];
	private int rand[][]=new int[32][2];
	private int i=-1;
	private int prei=-1;
	private boolean error=false;
	RuleOfChineseChess(int rand[][]){
		for(int count=0;count!=32;count++)
			recordBoard[count][1]=-1;
		this.rand=rand;
	}
	
	@Override
	public void checkWin(int[] board) {
		// TODO Auto-generated method stub
		int color=-1;
		int count=0;
		while(count!=32){
			if(board[count]==0&& color==-1){
				color=0;
			}else if(board[count]==1 && color==-1){
				color=1;
			}else if(board[count]==0 && color==1){
				color=2;
				break;
			}else if(board[count]==1 && color==0){
				color=2;
				break;
			}else if(board[count]==-1){
				color=2;
				break;
			}
			count++;
		}
		if(color==0){
			JOptionPane.showMessageDialog(null, "黑棋獲勝", "", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}else if(color==1){
			JOptionPane.showMessageDialog(null, "紅棋獲勝", "", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	public boolean move(int recordBoard[][]){
		if(recordBoard[i][1]==-1){
			if(state.equals("") && rand[i][1]==1)state="black";
			else if(state.equals("") && rand[i][1]==0)state="red";
			this.recordBoard=recordBoard;
			return true;
		}
		if(!error){
			if(recordBoard[prei][0]==6 && recordBoard[i][0]==-1){
				happenError();
				return false;
			}else if(recordBoard[prei][0]==-1 && recordBoard[i][0]==6){
				checkXY();
			}else if(recordBoard[prei][0]==1 && recordBoard[i][1]!=recordBoard[prei][1]){
				int count=prei;
				int num=0;
				while(count>i && count!=0 && i/8==prei/8){
					count--;
					if(recordBoard[count][1]!=-2)num++;
					if(num==2 && count==i)return true;
				}
				while(count<i && count!=31 && i/8==prei/8){
					count++;
					if(recordBoard[count][1]!=-2)num++;
					if(num==2 && count==i)return true;
				}
				if(i+16==prei){
					if(recordBoard[i+8][1]!=-2)return true;
				}else if(i-16==prei){
					if(recordBoard[i-8][1]!=-2)return true;
				}else if(i+24==prei){
					if(recordBoard[i+8][1]!=-2 && recordBoard[i+16][1]==-2)return true;
					if(recordBoard[i+8][1]==-2 && recordBoard[i+16][1]!=-2)return true;
				}else if(i-24==prei){
					if(recordBoard[i-8][1]!=-2 && recordBoard[i-16][1]==-2)return true;
					if(recordBoard[i-8][1]==-2 && recordBoard[i-16][1]!=-2)return true;
				}
				if(i-8==prei || i+8==prei || i+1==prei || i-1==prei){
					if(recordBoard[i][1]==-2)return true;
				}
				happenError();
				return false;
			}else if(recordBoard[prei][0]<recordBoard[i][0]){
				happenError();
				return false;
			}else{
				checkXY();
			}
			this.recordBoard=recordBoard;
			return true;
		}
		error=false;
		return false;
	}
	
	//檢查座標
	public void checkXY(){
		int a=0;
			if(recordBoard[prei][0]==1){
				if(i+16==prei || i+24==prei || i-16==prei || i-24==prei);
				else {
					for(int count=i;count<=prei;count++){
						if(recordBoard[count][1]!=-2)a++;
						if(a==2 && recordBoard[count][1]!=-2 && count==prei)break;
						if(a==2 && recordBoard[count][1]!=-2 && count==prei)happenError();
					}
					for(int count=i;count>=prei;count--){
						if(recordBoard[count][1]!=-2)a++;
						if(a==2 && recordBoard[count][1]!=-2 && count==prei)break;
						if(a==2 && recordBoard[count][1]!=-2 && count==prei)happenError();
					}
				}
			}else if(i+1!=prei && i-1!=prei && i+8!=prei && i-8!=prei ){
				System.out.println("~~!!");
				happenError();
			}else if(prei%8!=i%8 && prei/8!=i/8){
				happenError();
			}
	}
	
	public void happenError(){
		if(state.equals("blackReadyToEat")){
			JOptionPane.showMessageDialog(null, "無法吃子，請重下", "", JOptionPane.ERROR_MESSAGE);
			state="black";
		}else if(state.equals("redReadyToEat")){
			JOptionPane.showMessageDialog(null, "無法吃子，請重下", "", JOptionPane.ERROR_MESSAGE);
			state="red";
		}
	}
	
	public String getState(){
		System.out.println(state);
		return state;
	}
	@Override
	//0=black,1=white,-1=未翻開,-2=空
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		//System.out.println(((Integer)arg1).intValue());
		i=((Integer)arg1).intValue();
		if (state.equals("red") && recordBoard[i][1]==-1){
			state="black";
		}else if(state.equals("red") && recordBoard[i][1]==1){
			state="redReady";
			prei=i;
		}else if(state.equals("red") && recordBoard[i][1]==0){
			JOptionPane.showMessageDialog(null, "換紅方下棋", "", JOptionPane.ERROR_MESSAGE);
			error=!error;
			System.out.println("error");
		}else if(state.equals("redReady") && (recordBoard[i][1]==0 || recordBoard[i][1]==-2))state="redReadyToEat";
		else if(state.equals("redReady") && recordBoard[i][1]==-1)state="black";
		else if(state.equals("redReady")){
			state="red";
			error=!error;
			System.out.println("error");
		}else if (state.equals("black") && recordBoard[i][1]==-1){
			state="red";
		}else if(state.equals("black") && recordBoard[i][1]==0){
			state="blackReady";
			prei=i;
		}else if(state.equals("black") && recordBoard[i][1]==1){
			JOptionPane.showMessageDialog(null, "換黑方下棋", "", JOptionPane.ERROR_MESSAGE);
			error=!error;
			System.out.println("error");
		}else if(state.equals("blackReady") && (recordBoard[i][1]==1 || recordBoard[i][1]==-2))state="blackReadyToEat";
		else if(state.equals("blackReady") && recordBoard[i][1]==-1)state="red";
		else if(state.equals("blackReady")){
			state="black";
			error=!error;
			System.out.println("error");
		}
		if(i==-1){
			if(state.equals("blackReadyToEat"))state="red";
			else state="black";
		}

	}
	public void changeState(int recordBoard[][]){
			if(state.equals("black") && prei!=-10)state="red";
			else state="black";	
			this.recordBoard=recordBoard;
			prei=-10;
	}

}
