import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage; 
import java.awt.event.*;


public class Game  extends JPanel implements Runnable, KeyListener{ 


	private BufferedImage back; 
	private int key, score1, score2; 
	private Paddle playerOne, playerTwo; 
	private Ball ball; 
	private ImageIcon background, start, player1Win, player2Win, background2;
	private Picture pic;
	private boolean activateStart, playerOneWin, playerTwoWin, background02, playing;
	private Player play;

	public Game() {
		back = null;
		new Thread(this).start();
		this.addKeyListener(this); 	

		key =-1; score1 = 0; score2 = 0;
		playerOne = new Paddle(20, 300, 20, 100); 
		playerTwo = new Paddle(945, 300, 20, 100);
		ball = new Ball(480, 330, 40, 40);

		pic = new Picture(0,0);
		background = new ImageIcon(pic.getBackground());
		background2 = new ImageIcon(pic.getBackground2());
		start = new ImageIcon (pic.getStart());
		player1Win = new ImageIcon (pic.getPlayerOne());
		player2Win = new ImageIcon (pic.getPlayerTwo());

		activateStart = true; 
		playerOneWin = false; 
		playerTwoWin= false;
		background02 = false;
		playing = false;

		play = new Player();
	}


	public void run()
	{
		try
		{
			while(true)
			{
				Thread.currentThread().sleep(1);
				repaint();
			}
		}
		catch(Exception e)
		{
		}
	}





	public void paint(Graphics g){

		Graphics2D twoDgraph = (Graphics2D) g; 
		if( back ==null)
			back=(BufferedImage)( (createImage(getWidth(), getHeight()))); 

		Graphics g2d = back.createGraphics();

		if(activateStart){
			g2d.clearRect(0,0,getSize().width, getSize().height);
			g2d.drawImage(start.getImage(), 0, 0, 1000, 700,this); 
		}
		else {
			g2d.clearRect(0,0,getSize().width, getSize().height);
			if(background02){
				g2d.drawImage(background2.getImage(),0, 0, 1000, 700,this);
			}
			else {
				g2d.drawImage(background.getImage(), 0, 0, 1000, 700,this); 
			}


			move();
			Color color3 = new Color(255, 0, 0);
			g2d.setColor(color3);
			g2d.fillRect(playerOne.getX(), playerOne.getY(), playerOne.getWidth(), playerOne.getHeight());
			g2d.setFont(new Font("Garamond",Font.BOLD, 50));
			g2d.drawString("" + score1, 415, 90);

			Color color2 = new Color(0, 0, 255);
			g2d.setColor(color2);
			g2d.fillRect(playerTwo.getX(), playerTwo.getY(), playerTwo.getWidth(), playerTwo.getHeight());
			g2d.setFont(new Font("Garamond",Font.BOLD, 50));
			g2d.drawString("" + score2, 550, 90);

			Color color1 = new Color(0, 215, 0);
			g2d.setColor(color1);
			g2d.fillOval(ball.getX(), ball.getY(),ball.getWidth(), ball.getHeight());
			for (int x = 495,  y = 10,  n = 10; y < 1000; y = y + 20){
				g2d.fillRect(x, y, n, n);
			}



			if (playerOneWin){
				g2d.drawImage(player1Win.getImage(),  0, 0, 1000, 700,this);
			}

			if (playerTwoWin){
				g2d.drawImage(player2Win.getImage(),  0, 0, 1000, 700,this);
			}
		}
		twoDgraph.drawImage(back, null, 0, 0);
	}


	public void move(){
		ball.move();
		ball.collide(playerOne);
		ball.collide(playerTwo);
		collision();
		playerOne.move();
		playerTwo.move();
		winner();
	}
	public void collision(){
		if(ball.getX()<0) {
			ball.restartMatch();
			score2++;
		}
		if(ball.getX_Width()>980) {
			ball.restartMatch();
			score1++;
		}
	}


	public void winner(){
		if (score1>=6){
			playerOneWin = true;
			if(playing == false){
				play.playmusic("stop");
				play.playmusic("theme.wav");
				playing = true;
			}
		}
		if (score2>=6){
			playerTwoWin = true;
			if(playing == false){
				play.playmusic("stop");
				play.playmusic("theme.wavf");
				playing = true;
			}
		}
	}

	// all three method required in keyListener, some may be blank
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void keyPressed(KeyEvent e) { // Key is pressed, pass through e. 
		// TODO Auto-generated method stub

		key= e.getKeyCode();
		System.out.println(key); 

		if(key==87){
			playerOne.setdy(-3);
		}
		if(key==83){
			playerOne.setdy(3);
		}

		if(key==38){
			playerTwo.setdy(-3);
		}

		if(key==40){
			playerTwo.setdy(3);
		}

		if(key == 70){
			activateStart = false;
		}
		if (key == 37) { //65
			score1++;
		}
		if (key==32){
			background02 = !background02;
		}

		if (key == 68) { //39
			ball.setdx(5);
			ball.setdy(5);
		}

		if(key == 65) { //37 
			ball.setdx(3);
			ball.setdy(3);
		}

		if (key==39) { //68 
			score2++;
		}

		if(key ==88){
			playerOne.setY(10); 
		}

		if(key==67){
			playerTwo.setY(10);
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 87){
			playerOne.setdy(0);
		}
		if (e.getKeyCode() == 83){
			playerOne.setdy(0);
		}

		if (e.getKeyCode() == 38){
			playerTwo.setdy(0);
		}
		if (e.getKeyCode() == 40){
			playerTwo.setdy(0);
		}

	}

}
