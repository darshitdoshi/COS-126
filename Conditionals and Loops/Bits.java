class Bits
{
    public static void main(String args[])
    {
        int N = Integer.parseInt(args[0]);  //Tpye conversion from string to integer
        int count = 0;
        //Check if N is negative
        if(N<0)
        {
            System.out.println("Illegal Input");
        }
        else
        {   //Count no. of times N can be divided by 2
            while(N!=0)
            {
                N = N/2;
                count+=1;
            }
            System.out.println(count);
        }
    }
}