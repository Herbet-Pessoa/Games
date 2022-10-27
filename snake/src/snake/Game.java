package snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public Game(){
        this.setPreferredSize(new Dimension(480,480));
    }

    public void tick(){

    }

    public void render(){
        BufferStrategy bs = this.BufferStrategy();

        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,480,480);
    }

    public static void main(String args[]){
        Game game = new Game();
        JFrame = new JFrame("Snake");
        frame.add(game);
        frame.setResizable(false);
        
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

}
