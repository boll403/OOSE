public class Chess {
	private String ChessSet[][] ;
	private int num = 0;

	public Chess(){
		ChessSet=new String[14][3];
		this.addNewChess("¶Â±N.png", 6, 0);
		this.addNewChess("¶Â¤h.png", 5, 0);
		this.addNewChess("¶Â¶H.png", 4, 0);
		this.addNewChess("¶Â¨®.png", 3, 0);
		this.addNewChess("¶Â°¨.png", 2, 0);
		this.addNewChess("¶Â¥].png", 1, 0);
		this.addNewChess("¶Â¨ò.png", -1, 0);
		
		this.addNewChess("¬õ«Ó.png", 6, 1);
		this.addNewChess("¬õ¥K.png", 5, 1);
		this.addNewChess("¬õ¬Û.png", 4, 1);
		this.addNewChess("¬õ¨®.png", 3, 1);
		this.addNewChess("¬õ°¨.png", 2, 1);
		this.addNewChess("¬õ¬¶.png", 1, 1);
		this.addNewChess("¬õ§L.png", -1, 1);
		
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