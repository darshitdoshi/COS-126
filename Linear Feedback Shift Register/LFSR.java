public class LFSR {
    
    int[] registers;
    int seed_length;
    int tap;
    
    // creates an LFSR with the specified seed and tap
    public LFSR(String seed, int t) {
        seed_length = seed.length();
        tap = t;
        registers = new int[seed_length];
        for(int i = 0; i < seed_length; i++)
        {
            registers[i] = Character.digit(seed.charAt(i), 2); //converts a string(seed) into an array of binary numbers, and each number is stored in registers
        }
        
    }
  
    // simulates one step and returns the new bit (as 0 or 1)
    public int step() {
        /*Note that the indexing of registers in LFSR is the opposite of indexing of arrays in Java. Therefore the new bit is inserted at position 10,
        by xoring bits at index 0 and length-1-t*/
        int new_bit = registers[0] ^ registers[seed_length-1-tap];
        for(int i = 0; i < seed_length-1; i++)
        {
            registers[i] = registers[i+1];
        }
        registers[seed_length-1] = new_bit;
        return new_bit;
    }
  
    // simulates k steps and return the k bits as a k-bit integer
    public int generate(int k) {
        int result = 0;
        int new_bit;
        //generate a k-bit integer
        for(int i = 0; i < k; i++) 
        {
            new_bit = step();
            result = result*2 + new_bit;
        }
        return result;
    }

    // return a string representation of this LFSR
    public String toString()  {
        int len = registers.length;        
        String str = java.util.Arrays.toString(registers).replaceAll(", |\\[|\\]", ""); //converts a integer array(registers) into a string
        return(str);
    }
   
    // unit tests this class
    public static void main(String[] args)  {
       // test code
       LFSR lfsr = new LFSR("01101000010", 8);
       StdOut.println(lfsr);
       for (int i = 0; i < 10; i++) {
       int r = lfsr.generate(5);
       StdOut.println(lfsr + " " + r);
       }
    }

}
