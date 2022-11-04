package snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

//min 11:00

public class Game extends Canvas implements Runnable, KeyListener {

    final int HIGH_SCREW = 230;
    final int WIDTH_SCREW = 200;
    final int LENGTH_SNAKE = 20;

    public Node[] nodeSnake = new Node[LENGTH_SNAKE];

    public boolean left, right, down, up;

    public int score = 0;

    public int appleX = 0, appleY = 0;

    public Game(){
        this.setPreferredSize(new Dimension(WIDTH_SCREW, HIGH_SCREW));
        for(int i=0; i < nodeSnake.length; i++){
            nodeSnake[i] = new Node(0,0);
        }
        this.addKeyListener(this);
    }

    public void tick(){

        //movimento cobrinha
        for(int i = nodeSnake.length-1; i>0; i--){
            nodeSnake[i].x = nodeSnake[i-1].x;
            nodeSnake[i].y = nodeSnake[i-1].y;
        }

        //Trazendo a cobrinha de volta do outro lado da parede
        if(nodeSnake[0].x + LENGTH_SNAKE < 0){
            nodeSnake[0].x = WIDTH_SCREW;
        }else if(nodeSnake[0].x >= WIDTH_SCREW){
            nodeSnake[0].x = -10;
        }
        if(nodeSnake[0].y + LENGTH_SNAKE < 0){
            nodeSnake[0].y = HIGH_SCREW;
        }else if(nodeSnake[0].y >= HIGH_SCREW){
            nodeSnake[0].y = -10;
        }

        //movimento
        if(right){
            nodeSnake[0].x++;
        }else if(left){
            nodeSnake[0].x--;
        }else if(up){
            nodeSnake[0].y--;
        }else if(down){
            nodeSnake[0].y++;
        }

        //colisão com maça
        if (new Rectangle(nodeSnake[0].x, nodeSnake[0].y, 10, 10).intersects(new Rectangle(appleX, appleY, 10, 10))){
            appleX = new Random().nextInt(WIDTH_SCREW-10);
            appleY = new Random().nextInt(HIGH_SCREW-10);
            score++;
            System.out.println("Pontos: " + score);
        }
    }

    public void render(){
    	BufferStrategy bs = this.getBufferStrategy();

        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0, WIDTH_SCREW, HIGH_SCREW);
        for(int i=0; i < nodeSnake.length; i++){
            g.setColor(Color.blue);
            g.fillRect(nodeSnake[i].x, nodeSnake[i].y, 10, 10);
        }

        g.setColor(Color.RED);
        g.fillRect(appleX, appleY, 10, 10);

        g.dispose();
        bs.show();
    }

    public static void main(String args[]){
        Game game = new Game();
        JFrame frame = new JFrame("Snake");
        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        
        frame.setVisible(true);
        new Thread(game).start();
    }

    @Override
    public void run(){

        while (true){
            tick();
            render();
            try{
                Thread.sleep(1000/60);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }


    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
            left = false;
            up = false;
            down = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
            right = false;
            up = false;
            down = false;
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
            right = false;
            left = false;
            down = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
            right = false;
            left = false;
            up = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
