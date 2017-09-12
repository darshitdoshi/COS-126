import java.util.Random;
class RandomWalkers
{
    public static void main(String args[])
    {
        Random randomGenerator = new Random();
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        int i,j;
        int total_sq_dist=0;
        double mean_sq_dist;
        
        for(j=0;j<T;j++) //Loop over T independent experiments
        {
            int sq_dist=0;
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
                
            }
            sq_dist =  (x*x + y*y);
            total_sq_dist += sq_dist; // total of all mean sq. dist. over j independent experiments
        }
        mean_sq_dist = (double)total_sq_dist/(double)T;
        System.out.println("mean squared distance = " + mean_sq_dist);
    }
}