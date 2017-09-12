import java.util.Random;
class TenDice
{
    public static void main(String args[])
    {
        Random randomGenerator = new Random();
        int N = Integer.parseInt(args[0]);
        int i,j,total;
        int[] array = new int[61];
        
        /* Performing N independent experiments 
         * Randomly generating result between 1 and 6 inclusive for 10 die,
         * calculating total and storing in an array
         */
        for(i=0;i<N;i++)
        {
            total = 0;
            for(j=0;j<10;j++)
            {
                int result = 1 + randomGenerator.nextInt(6);
                total += result;
            }
          array[total] += 1;  
        }
        
        //Generating and printing the histogram of the result array
        for(i=10;i<=60;i++)
        {
            String pattern = "";
            for(j=0;j<array[i];j++)
            {
                pattern = pattern + '*';
            }
            System.out.println(i + " " + pattern);
        }
    }
}