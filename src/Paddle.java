
public class Paddle {
	private int x, y, dy, x1, y1;


	public Paddle(int posx, int posy, int posx1, int posy1){
		x = posx;
		y = posy;
		x1 = posx1;
		y1 = posy1;
		dy = 0;
	}


	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setY(int setY){
		y = setY; 
	}

	public int getWidth(){ 
		return x1;
	}

	public int getHeight(){ 
		return y1;
	}

	public int getX_Width(){
		return x + x1;
	}

	public int getY_Height(){
		return y + y1;
	}


	public void setdy(int yy){
		dy = yy; 
	}

	public void move(){
		y += dy;
	}

}
