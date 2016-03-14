import java.awt.*;

/**
 * A square that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Square extends DisplayShape
{
    private int size;

    /**
     * Create a new square at default position with default color.
     */
    public Square(int xPos, int yPos, String col, boolean toDraw, int length)
    {
        super(xPos, yPos, col, toDraw);
        size = length;
    }


    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newSize)
    {
        erase();
        size = newSize;
        draw();
    }


    /**
     * Draw the square with current specifications on screen.
     */
    public void draw()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas(25,25);
            canvas.draw(this, color,
                        new Rectangle(xPosition, yPosition, size, size));
            canvas.wait(10);
        }
    }

}
