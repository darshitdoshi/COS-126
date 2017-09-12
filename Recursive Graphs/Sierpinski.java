public class Sierpinski
{
    static double LENGTH = 1.0;
    static double ORIGIN_X = 0.0;
    static double ORIGIN_Y = 0.0;
    
    //Calculates the height of an equilateral triangle with side each of 'length'. 
    public static double height(double length)
    {
        double height = (length * Math.sqrt(3)) / 2;
        return height;
    }
    
    //Draws a filled equilateral triangle of 'length' and bottom vertex at (x,y).
    //The length of each side of a filled triangle will be half the bigger equilateral triangle. 
    public static void filledTriangle(double x, double y, double length)
    {
        double height = height(length); 
        double[] x_coordinate = {x, x + (length/2.0), x - (length/2.0)};
        double[] y_coordinate = {y, y + height, y + height};
        StdDraw.filledPolygon(x_coordinate, y_coordinate);
    }
    
    //Draws a sierpinski triangle of order n of the specified length and bottom vertex of largest filled triangle at (x,y).
    //A filled triangle divides the original triangle into 3 smaller equilateral triangles.
    public static void sierpinski(int n, double x, double y, double length)
    {
        while(n > 1)
        {
            n--;
            sierpinski(n, (x - length/2.0), y, length/2.0);
            sierpinski(n, (x + length/2.0), y, length/2.0);
            sierpinski(n, x, (y+height(length)), length/2.0);
        }
        filledTriangle(x, y, length);
    }
    
    public static void main(String args[])
    {
        int n = Integer.parseInt(args[0]);
        StdDraw.setScale(-1, 2);
        
        double[] x = {ORIGIN_X, LENGTH, LENGTH/2.0};
        double[] y = {ORIGIN_Y, ORIGIN_Y, height(LENGTH)};
        StdDraw.polygon(x, y);
        sierpinski(n, LENGTH/2.0, ORIGIN_Y, LENGTH/2.0);
    }
}