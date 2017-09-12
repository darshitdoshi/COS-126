import java.util.Random;
class RandomWalker
{
    public static void main(String args[])
    {
        Random randomGenerator = new Random();
        int N = Integer.parseInt(args[0]);
        int i, sq_dist;
        int x=0, y=0; //Initial position is (0,0)
        for(i=0;i<N;i++)
        {
            /*Randomly generate a no. between 0 and 3.
            0 - East, 1- West, 2- North, 3- South
            */
            int step = randomGenerator.nextInt(4);
            
            if(step==0) 
                x+=1;
            else if(step==1) 
                x-=1;
            else if(step==2) 
                y+=1;
            else
                y-=1;
            
            System.out.println("(" + x + ", " + y + ")");
        }
        sq_dist =  (x*x + y*y);
        System.out.println("squared distance = " + sq_dist);
    }
}