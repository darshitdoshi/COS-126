public class MarkovModel extends RuntimeException {
    private static final int ASCII = 128;
    int k;
    ST<String, int[]> st;
    
    // creates a Markov model of order k for the specified text
    public MarkovModel(String text, int k) {
        if(k < 0 || k > text.length())
            throw new RuntimeException("k must be non-negative integer which cannot be greater than length of text");
        st = new ST<String, int[]>();  /*Symbol table for storing kgram as key and an integer array of 128 elements corresponding to each character, 
                                         which stores its frequency as value*/ 
        this.k = k;
        char next_char;
        int[] value_array = new int[ASCII];
        int text_length = text.length();
        text += text.substring(0,k); // appending the first k characters to the end of the text to make it circular.
        
        for(int i = 0; i < text_length; i++) { //O(N)
            String kgram = text.substring(i, i+k);
            if(!st.contains(kgram))            //O(log N)
                st.put(kgram, new int[ASCII]); //O(log N)
            
            next_char = text.charAt(i + k);
            value_array = st.get(kgram);       //O(log N)
            value_array[(int) next_char]++;
            st.put(kgram, value_array);        //O(log N)
        }
        //Time complexity of loop = O(N * log(N))
        
    }

    // returns the order k of this Markov model
    public int order() {
        return k;
    }

    // returns the number of times the specified kgram appears in the text
    public int freq(String kgram) {
        if(kgram.length() != k)
            throw new RuntimeException("The string is not of length k");
        int count = 0;
        if(st.contains(kgram)) {
            int[] frequency = st.get(kgram); //O(log N)
            for(int i = 0; i < ASCII; i++ ) { //constant time
                count += frequency[i];
            }
        }
        return count;
    }

    // returns the number of times the character c follows the specified
    // kgram in the text
    public int freq(String kgram, char c) {
        if(kgram.length() != k)
            throw new RuntimeException("The string is not of length k");
        int[] value_array = st.get(kgram);  //O(log N)
        return value_array[(int) c];
    }

    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
    public char random(String kgram) {
        if(kgram.length() != k)
            throw new RuntimeException("The string is not of length k");
        int[] value_array = st.get(kgram);  //O(log N)
        int random_value = StdRandom.discrete(value_array); //selecting a random integer from value_array propotional to frequencies
        return((char) random_value); //Type-casting the integer to its corresponding character
        
    }

    // unit tests this class
    public static void main(String[] args) {
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();

        String text2 = "one fish two fish red fish blue fish"; 
        MarkovModel model2 = new MarkovModel(text2, 4);
        StdOut.println("freq(\"ish \", 'r') = " + model2.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model2.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model2.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model2.freq("tuna"));
        StdOut.println(model2.random("ish "));
    }
}