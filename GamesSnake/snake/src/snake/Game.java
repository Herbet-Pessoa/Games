package snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

//min 11:00

public class Game extends Canvas implements Runnable, KeyListener {

    public Node[] nodeSnake = new Node[10];

    public boolean left, right, down, up;

    public Game(){
        this.setPreferredSize(new Dimension(480,480));
        for(int i=0; i < nodeSnake.length; i++){
            nodeSnake[i] = new Node(0,0);
        }
    }

    public void tick(){
        
    }

    public void render(){
    	BufferStrategy bs = this.getBufferStrategy();

        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,480,480);
        for(int i=0; i < nodeSnake.length; i++){
            g.setColor(Color.blue);
            g.fillRect(nodeSnake[i].x, nodeSnake[i].y, 10, 10);
        }
    }

    public static void main(String args[]){
        Game game = new Game();
        JFrame = new JFrame("Snake");
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
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
