
/**
 * This Class define the fundamental shape of tetris, and the method of shape rotation.
 * 
 * @author Qinrui Yan & Shiying 
 * @version 12/12/2013
 */
public class GameView
{
    // instance variables - replace the example below with your own
    public static Square[][] window;
    
    /**
     * Constructor for objects of class GameView
     */
    public GameView()
    {
        
    }
    
    /**
     * Method of drawing shape on canvas
     */
    public void drawShape(int[][] shape,Square[][] window,int xPosition,int yPosition)
    {
        int Row = shape.length;
        int Col = shape[0].length;
        for(int i=0;i<Row;i++)
        {
            for(int j=0;j<Col;j++)
            {
                if(shape[i][j] != 0)
                {
                    window[i][j] = new Square((xPosition+j)*25,(yPosition+i)*25,Color(shape[i][j]),true ,24);
                    window[i][j].draw();
                }
            }
        }
    }
    
    /**
     * Method of erasing shape on canvas
     */
    public void eraseShape(int[][] reference,Square[][] window)
    {
        int Row = reference.length;
        int Col = reference[0].length;
        
        for(int i=0;i<Row;i++)
        {
            for(int j=0;j<Col;j++)
            {
                if((reference[i][j]!=0)&(window[i][j]!=null))
                {   
                    window[i][j].erase();
                }
            }
        }
    }
    
    /**
     * Method of drawing board on canvas
     */
    public void drawBoard(int[][] board,Square[][] window)
    {
        
        int Row = board.length;
        int Col = board[0].length;
        for(int i=0;i<Row-3;i++)
        {
            for(int j=3;j<Col-3;j++)
            {
                if(board[i][j] != 0)
                {
                    window[i][j] = new Square(j*25,i*25,
                                        Color(board[i][j])
                                        ,true ,24);
                    window[i][j].draw();
                }
            }
        }
    }
    
    /**
     * Method of erasing board on canvas
     */
    public void eraseBoard(int[][] reference,Square[][] window)
    {
        int Row = reference.length;
        int Col = reference[0].length;
        
        for(int i=0;i < Row-3;i++)
        {
            for(int j=3;j < Col-3;j++)
            {
                if((reference[i][j]!=0)&(window[i][j]!=null))
                {   
                    window[i][j].erase();
                }
            }
        }
    }
    
    /**
     * Method of drawing boarder on canvas
     */
    public void drawBoarder(int[][] board,Square[][] window)
    {
        
        int Row = board.length;
        int Col = board[0].length;
        for(int i=0;i<Row-1;i++)
        {
           window[i][0] = new Square(0*25,i*25,"black",true ,25);      
           window[i][1] = new Square(1*25,i*25,"black",true ,25);
           window[i][2] = new Square(2*25,i*25,"black",true ,25);
           window[i][Col-1] = new Square((Col-1)*25,i*25,"black",true ,25);   
           window[i][Col-2] = new Square((Col-2)*25,i*25,"black",true ,25);         
           window[i][Col-3] = new Square((Col-3)*25,i*25,"black",true ,25);
           
           window[i][0].draw();
           window[i][1].draw();
           window[i][2].draw();
           window[i][Col-1].draw();
           window[i][Col-2].draw();
           window[i][Col-3].draw();
        }
        
        for(int j=0;j<Col;j++)
        {
            window[Row-1][j] = new Square(j*25,(Row-1)*25,"black",true ,25);
            window[Row-2][j] = new Square(j*25,(Row-2)*25,"black",true ,25);
            window[Row-3][j] = new Square(j*25,(Row-3)*25,"black",true ,25);
            
            window[Row-1][j].draw();
            window[Row-2][j].draw();
            window[Row-3][j].draw();
        }
    }
    
    /**
     * Method of chosing color according to the index.
     */
    public String Color(int colornumber)
    {
        switch(colornumber)
        {
            case 1:return "red";   
            case 2:return "blue"; 
            case 3:return "green"; 
            case 4:return "yellow";
            case 5:return "magenta";
        }
        return "black";
    }
    
}
