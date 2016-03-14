import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;


import javax.swing.JFrame;
/**
 * This Class combine the Grapic module and Game modol module together, and the shape can go down automatically, based
 * on the timer. Players can play this game by Keyboard.
 * 
 * @author Qinrui Yan & Shiying 
 * @version 12/12/2013
 */
public class Game2 extends JFrame implements KeyListener
{
    // instance variables - replace the example below with your own
    private static GameModel game1;
    private static GameView view;
    public static Canvas currentcanvas;
    public Timer timer;

     /**
     * Constructor for objects of class Game2
     */
    public Game2(int speed,int width, int high)
    {
        iniGame(width,high);
        Timer timer = new Timer(speed, new TimerListener());
        timer.start();
        currentcanvas.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
     /**
     * Constructor2 for objects of class Game2
     */
    public Game2()
    {
        iniGame(12,12);
        Timer timer = new Timer(1000, new TimerListener());
        timer.start();
        currentcanvas.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Initialize the game
     */
    public void iniGame(int width, int high)
    {        
        game1 = new GameModel(width,high);
        view  = new GameView();
        currentcanvas = Canvas.getCanvas((game1.Board_width+game1.Boarderwidth)*25,(game1.Board_high+game1.Boarderwidth)*25);
        currentcanvas.frame.addKeyListener(this);
        //game1.iniGameModel();
        view.drawBoarder(game1.board,game1.window);
        view.drawBoard(game1.board,game1.window);
        
    }

    /**
     * Generate a new shape and display it.
     */
    public void giveShape()
    {
        game1.newShape();
        view.drawShape(game1.currentshape,game1.shapewindow,game1.xPos,game1.yPos);
    }
    
    /**
     * Refresh data after a shape can not move down.
     */
    public void view()
    {
        game1.putOnBoard();
        view.eraseShape(game1.currentshape,game1.shapewindow);
        view.eraseBoard(game1.board,game1.window);
        game1.delRow();
        view.drawBoard(game1.board,game1.window);
    }
    
    /**
     * Move down on the screen.
     */
    public void down()
    {
        view.eraseShape(game1.currentshape,game1.shapewindow);
        game1.downSquareMatrix();
        view.drawShape(game1.currentshape,game1.shapewindow,game1.xPos,game1.yPos);
    }

    /**
     * Move left on the screen.
     */
    public void left()
    {
        view.eraseShape(game1.currentshape,game1.shapewindow);
        game1.leftSquareMatrix();
        view.drawShape(game1.currentshape,game1.shapewindow,game1.xPos,game1.yPos);
    }

    /**
     * Move right on the screen.
     */
    public void right()
    {
        view.eraseShape(game1.currentshape,game1.shapewindow);
        game1.rightSquareMatrix();
        view.drawShape(game1.currentshape,game1.shapewindow,game1.xPos,game1.yPos);
    }
    
     /**
     * Rotate on the screen.
     */
    public void rotate()
    {
        view.eraseShape(game1.currentshape,game1.shapewindow);
        if(game1.rotateMoveAllow())
        {
            game1.mov.rotateSquareMatrix(game1.currentshape);
        }
        view.drawShape(game1.currentshape,game1.shapewindow,game1.xPos,game1.yPos);
    }
    
    /**
     * Keylistener events
     */
    public void keyPressed(KeyEvent e)
    {
    
        
    } 

    public void keyReleased(KeyEvent e) {
       
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:     rotate(); break;
            case KeyEvent.VK_RIGHT:  right();  break;
            case KeyEvent.VK_LEFT:   left();   break;
            case KeyEvent.VK_DOWN:   down();   break;
            case KeyEvent.VK_P:      timer.stop();   break;
        }
    }

    public void keyTyped(KeyEvent e) {

        
    }
    
     /**
     * Actionlistener events
     */
    
    class TimerListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e) 
        { 
            if(!game1.gameover())
            {
                currentcanvas.updateLabel( Integer.toString(game1.score));
                if(game1.downMoveAllow())
            {
                down();
            }
            else
            {
                view();
                giveShape();
            };
        }
        else
        {
            currentcanvas.updateLabel("Game");
        }
        }
    }
}
