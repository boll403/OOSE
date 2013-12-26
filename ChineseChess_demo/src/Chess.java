public class Chess {
	private String ChessSet[][] ;
	private int num = 0;

	public Chess(){
		ChessSet=new String[14][3];
		this.addNewChess("�±N.png", 6, 0);
		this.addNewChess("�¤h.png", 5, 0);
		this.addNewChess("�¶H.png", 4, 0);
		this.addNewChess("�¨�.png", 3, 0);
		this.addNewChess("�°�.png", 2, 0);
		this.addNewChess("�¥].png", 1, 0);
		this.addNewChess("�¨�.png", -1, 0);
		
		this.addNewChess("����.png", 6, 1);
		this.addNewChess("���K.png", 5, 1);
		this.addNewChess("����.png", 4, 1);
		this.addNewChess("����.png", 3, 1);
		this.addNewChess("����.png", 2, 1);
		this.addNewChess("����.png", 1, 1);
		this.addNewChess("���L.png", -1, 1);
		
	}
	public void addNewChess(String image,int code,int color){
		ChessSet[num][0] = image;
		ChessSet[num][1] = Integer.toString(code);
		ChessSet[num][2] = Integer.toString(color);
		num++;	
	}
	
	public String getImage(int code,int color){
		for(int count=0;count!=num+1;count++){
			if(ChessSet[count][1].equals( Integer.toString(code)) && 
					ChessSet[count][2].equals(Integer.toString(color))){
				return ChessSet[count][0];
			}
		}
		return "";
	}
}