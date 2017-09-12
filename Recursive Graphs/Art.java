//This program recursively replaces a straight line segment with a sine wave
class Art
{
    public static void main(String args[])
    {
        int N = Integer.parseInt(args[0]);
        StdDraw.setScale(-1,1);
        double x1 = -1.0;
        double x2 = 1.0;
        double y1 = 0.0;
        double y2 = 0.0;
        StdDraw.line(x1, y1, x2, y2); //Initial line segment
        pattern(N, x1, y1, x2, y2);
    }
    
    //When the pattern is drawn on a line, 2 new line segments are generated of 1/4th the original length. 
    //Using recursion, the pattern is drawn on these new lines. 'n' denotes the depth of recursion.
    public static void pattern(int n, double x1, double y1, double x2, double y2)
    {
        while(n > 0)
        {
            n--;
            pattern(n, x1, y1, x1 + (x2-x1)/4, y1 + (y2-y1)/4);
            pattern(n, x1 + 3*(x2-x1)/4, y1 + 3*(y2-y1)/4, x2, y2);
            
            draw(x1, y1, x2, y2);
        }
        
    }
    
    //This method plots the the sine wave from a line segment by dividing the segment into 4 parts and replacing the 2 middle segments
    //with two arcs.
    public static void draw(double x1, double y1, double x2, double y2)
    {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.line(x1 + (x2-x1)/4, y1 + (y2-y1)/4, x1 + 3*(x2-x1)/4, y1 + 3*(y2-y1)/4); //Draws a white line over the 2 middle line segment
        
        StdDraw.setPenColor(StdDraw.BLACK);
        double radius = (x2-x1)/8;
        StdDraw.arc(x1 + 3*(x2-x1)/8, y1 + 3*(y2-y1)/8, radius, 0.0, 180.0);
        StdDraw.arc(x1 + 5*(x2-x1)/8, y1 + 5*(y2-y1)/8, radius, 180.0, 0.0);
    }
}
