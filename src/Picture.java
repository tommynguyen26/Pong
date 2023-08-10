
public class Picture {
	private String background, start, playerOne, playerTwo, background2;
	private int x, y;



	public Picture(int corx, int cory){
		x = corx;
		y = cory;
		background = "background.png";
		background2 = "background2.jpg";
		start = "start.png";
		playerOne = "WinnerOne.png";
		playerTwo = "WinnerTwo.png";
	}

	public String getBackground(){
		return background; 
	}
	public String getBackground2(){
		return background2;
	}

	public String getStart(){
		return start;
	}

	public String getPlayerOne(){
		return playerOne;
	}

	public String getPlayerTwo(){
		return playerTwo;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

}
