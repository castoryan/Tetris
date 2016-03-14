import java.util.Arrays;
/**
 * This Class define the fundamental shape of tetris, and the method of shape rotation.
 * 
 * @author Qinrui Yan & Shiying 
 * @version 12/12/2013
 */
public class Shape
{
    // instance variables
    private static int[][] temp = null;
    public int[][][] shape = 
        {
            {{ 0, 1, 0, 0},{ 0, 1, 0, 0},{ 0, 1, 1, 0},{ 0, 0, 0, 0}},  //L initial  shape[0][][]
            {{ 0, 0, 1, 0},{ 0, 0, 1, 0},{ 0, 1, 1, 0},{ 0, 0, 0, 0}},  //J initial  shape[1][][]
            {{ 0, 0, 0, 0},{ 0, 1, 1, 0},{ 0, 1, 1, 0},{ 0, 0, 0, 0}},  //O initial  shape[2][][]
            {{ 0, 0, 1, 0},{ 0, 1, 1, 0},{ 0, 1, 0, 0},{ 0, 0, 0, 0}},  //Z initial  shape[3][][]
            {{ 0, 1, 0, 0},{ 0, 1, 1, 0},{ 0, 0, 1, 0},{ 0, 0, 0, 0}},  //S initial  shape[4][][]
            {{ 0, 1, 0, 0},{ 0, 1, 0, 0},{ 0, 1, 0, 0},{ 0, 1, 0, 0}},  //I initial  shape[5][][]
            {{ 1, 1, 1, 0},{ 0, 1, 0, 0},{ 0, 0, 0, 0},{ 0, 0, 0, 0}}   //T initial  shape[6][][]
        };

    /** 
     * Constructor 
     */
    public Shape()
    {   

    }

    /**
     * load one of shape from the three dimension matrix shape 
     */

    public int[][] loadShape(int style)
    {
        int len = 4;
        int[][] dat = new int[4][4];
        for (int i = 0; i < len; ++i) 
        {
            for (int j = 0; j < len; ++j) 
            {
                dat[i][j] = shape[style][i][j];
            }
        }
        return dat;
    }

    /**
     * rotate the square matrix 
     */
    public int[][] rotateSquareMatrix(int[][] data) 
    {
        int len = data.length;
        int last = len - 1;
        temp = createTempSquareMatrix(len);

        for (int i = 0; i < len; ++i) 
        {
            for (int j = 0; j < len; ++j) 
            {
                temp[j][last - i] = data[i][j];
            }
        }

        // copy the data after rotation
        for (int i = 0; i < len; ++i) 
        {
            for (int j = 0; j < len; ++j) 
            {
                data[i][j] = temp[i][j];
            }
        }

        return data;
    }

    /**
     * create a temporary square matrix for the rotateSquareMatrix(int[][] data) method
     */ 
    private int[][] createTempSquareMatrix(int size) 
    {
        if (temp == null || temp.length != size) 
        {
            temp = new int[size][size];
        }

        return temp;
    } 
}
