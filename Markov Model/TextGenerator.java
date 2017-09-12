public class TextGenerator extends RuntimeException{
    
    public static void main(String args[]) {
        int k = Integer.parseInt(args[0]);
        int length = Integer.parseInt(args[1]);
        if(k > length)
            throw new RuntimeException("Length of text must be atleast the value of k");
        
        String text = "";
        while(StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            text += line;
        }
        
        MarkovModel model = new MarkovModel(text, k);
        
        String generated_text = text.substring(0, k);
        for(int i = 0; i < length-k; i++) {
            generated_text += String.valueOf(model.random(generated_text.substring(i, i+k)));/*1. select the substring of length k and generate the 
              next random char. 2. Convert this char into string  3. Append this string to generated_text*/
   
        }
        StdOut.println(generated_text);
    }
}