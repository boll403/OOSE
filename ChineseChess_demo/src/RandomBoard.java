
public class RandomBoard {
	private int randomboard[][]=new int[32][2];
	
	public RandomBoard(){
		random();
	}
	
	public int[][] getRandboard() {
		return randomboard;
	}
	
	public void setRandboard(int[][] random) {
		this.randomboard = random;
	}
	
	public void random(){
		int array[]={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,
				17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		int tmp;
		int color=0;
		for(int count=31; count!=-1;count--){
			tmp=(int)((Math.random()*(count+1)));
			if(count>29){
				if(count==30)color=1;
				randomboard[array[tmp]][0]=6;
				randomboard[array[tmp]][1]=color;
			}else if(count>25){
				if(count<28)color=1;
				randomboard[array[tmp]][0]=5;
				randomboard[array[tmp]][1]=color;
			}else if(count>21){
				if(count<24)color=1;
				randomboard[array[tmp]][0]=4;
				randomboard[array[tmp]][1]=color;
			}else if(count>17){
				if(count<20)color=1;
				randomboard[array[tmp]][0]=3;
				randomboard[array[tmp]][1]=color;
			}else if(count>13){
				if(count<16)color=1;
				randomboard[array[tmp]][0]=2;
				randomboard[array[tmp]][1]=color;
			}else if(count>9){
				if(count<12)color=1;
				randomboard[array[tmp]][0]=1;
				randomboard[array[tmp]][1]=color;
			}else{
				if(count<5)color=1;
				randomboard[array[tmp]][0]=-1;
				randomboard[array[tmp]][1]=color;
			}
			array[tmp]=array[count];
			color=0;
		}
	}
}
