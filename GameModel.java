import java.util.Arrays;
/**
 * This Class define the game model of tetris, which contains the game rules, shape movement 
 * and size of our game board
 * 
 * @author Qinrui Yan & Shiying 
 * @version 12/12/2013
 */
public class GameModel
{
    // instance variables
    public int[][] board;

    public int[][] currentshape;
    public int xPos,yPos;

    public int score;
    public Square[][] window;
    public Square[][] shapewindow;
    public Shape mov;

    public int Boarderwidth = 6;     // width of boarder
    
    public int Board_width ;     // width of board
    public int Board_high ;     // high of board

    /**
     * Constructor for objects of class GameModel.
     */
    public GameModel(int width,int high)
    {
        iniGameModel(width,high);

    }

    /**
     * initializing the game model.
     */
    public void iniGameModel(int Board_width,int Board_high)
    {
        this.Board_width = Board_width;
        this.Board_high  = Board_high;
        mov         =       new Shape();
        board       =       new int[Board_high+Boarderwidth][Board_width+Boarderwidth];
        window      =       new Square[Board_high+Boarderwidth][Board_width+Boarderwidth];
        shapewindow =       new Square[Board_high+Boarderwidth][Board_width+Boarderwidth];

        //set the boarder of bottom.
        for(int i = 0;i < Board_width + Boarderwidth;i++)
        {

            //from the second row to board_high-1 row is the board range
            board[Board_high+3][i]=10;
            board[Board_high+4][i]=10;
            board[Board_high+5][i]=10;
        }
        //set the boarder of left and right sides.
        for(int i = 0; i < Board_high + Boarderwidth; i++)
        {
            board[i][0]=10;
            board[i][1]=10;
            board[i][2]=10;
            //from the second column to board_width-1 column is the board range
            board[i][Board_width+3]=10;
            board[i][Board_width+4]=10;
            board[i][Board_width+5]=10;
        }

        newShape();
    }

    /**
     * Determine if the game is over.
     */
    public boolean gameover()
    {
        if((yPos == 0) & (downMoveAllow()==false))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Delele the full rows.
     */
    public void delRow()
    {   int time = 0;
        //delete from the third row，until Board_high,in order to avoid deleting the boarder.
        for(int row = 2;row < Board_high + 3;row++)
        {
            if(isFull(board,row))
            {
                time = time + 1;
                delOneRow(board, row);
            }
        }
        
        switch(time)
            {
                case 1: score += 100; break;
                case 2: score += 300; break;
                case 3: score += 600; break;
                case 4: score += 1000; break;
            }
    }

    /**
     * return a boolean value that if a row is full.
     */
    public boolean isFull(int[][] data,int row)
    {   

        int Column = data[0].length;

        for( int col = 3; col < Column - 3; col++ )
        {
            if(data[row][col] != 0) 
            {
                continue;
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Delele one row and let the above ones down, the first row are zeros.
     */
    public int[][] delOneRow(int[][] data, int delRow)
    {
        int Row = data.length;
        int Column = data[0].length;

        if(delRow > 0)
        {
            for(int row = delRow; row > 1; row--)
            {
                for(int col = 3; col < Column - 3; col++)
                {
                    data[row][col] = data[row-1][col];
                }
            }

            for(int col = 3;col < Column -3; col++ )
            {
                data[0][col] = 0;
            }
        }
        else//delRow==0;
        {
            for(int col = 3;col < Column -3 ; col++ )
            {
                data[0][col] = 0;
            }
        }

        return data;
    }

    /**
     * Module of shape generating, randomly choose style and color.
     */
    public int[][] newShape()
    {
        xPos = (int)(Board_width/2);
        yPos = 0;
        int style = (int)(Math.random()*7);
        int color = (int)(Math.random()*5)+1;

        currentshape = mov.loadShape(style);

        for(int row=0;row<4;row++)
        {
            for(int col=0;col<4;col++)
            {
                currentshape[row][col] *= color;
            }
        }
        return currentshape;
    }

    /**
     * Putting the shape matrix on board matrix.
     */
    public int[][] putOnBoard()
    {
        if(downMoveAllow()==false)
        {
            for(int row=0;row<4;row++)
            {
                for(int col=0;col<4;col++)
                {
                    if (currentshape[row][col]!=0)
                    {
                        board[yPos+row][xPos+col] = currentshape[row][col];
                    }
                }
            }
        }
        return board;
    }

   /**
     * Return the boolean value if the shape can go down.
     */
    public boolean downMoveAllow()
    {
        for(int row=3;row>=0;row--)
        {
            for(int col=3;col>=0;col--)
            {
                if((board[row+yPos+1][col+xPos]!=0)&(currentshape[row][col]!=0))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return the boolean value if the shape can go right.
     */
    public boolean rightMoveAllow()
    {
        for(int row=3;row>=0;row--)
        {
            for(int col=0;col<4;col++)
            {
                if((board[row+yPos][col+xPos+1]!=0)&(currentshape[row][col]!=0))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return the boolean value if the shape can go left.
     */
    public boolean leftMoveAllow()
    {
        for(int row=3;row>=0;row--)
        {
            for(int col=3;col>=0;col--)
            {
                if((board[row+yPos][col+xPos-1]!=0)&(currentshape[row][col]!=0))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return the boolean value if the shape can rotate.
     */
    public boolean rotateMoveAllow()
    {
        mov.rotateSquareMatrix(currentshape);

        for(int row = 3; row >= 0; row-- )
        {
            for(int col = 3; col >= 0; col-- )
            {
                if((board[row+yPos][col+xPos]!=0)&(currentshape[row][col]!=0))
                {
                    mov.rotateSquareMatrix(currentshape);
                    mov.rotateSquareMatrix(currentshape);
                    mov.rotateSquareMatrix(currentshape);
                    return false;
                }
            }
        }
        mov.rotateSquareMatrix(currentshape);
        mov.rotateSquareMatrix(currentshape);
        mov.rotateSquareMatrix(currentshape);
        return true;
    }

    
    /**
     * Executing down move on shape matrix.
     */
    public int downSquareMatrix() 
    {
        if((yPos + 4 <= (Board_high + Boarderwidth/2 + 1))&(downMoveAllow()))
        {
            yPos = yPos + 1;
        }
        return yPos;
    }

    /**
     * Executing left move on shape matrix.
     */
    public int leftSquareMatrix() 
    {
        if(( xPos - 1 >= 0)&(leftMoveAllow())) //xPos must bigger than 0,and there is nothing on the left
        {
            xPos -= 1;
        }
        return xPos;
    }

    /**
     * Executing right move on shape matrix.
     */
    public int rightSquareMatrix() 
    {
        if((xPos + 4 <= (Board_width + Boarderwidth/2 + 1))&(rightMoveAllow()))
        {
            xPos += 1;
        }
        return xPos;
    }

    /**
     * Method of delay n ms.
     */
    public void delay(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (Exception e)
        {
            // ignoring exception at the moment
        }
    }
}
