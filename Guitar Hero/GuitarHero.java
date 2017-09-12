public class GuitarHero {
public static void main(String[] args) {
        
        //using an array of GuitarString objects
        GuitarString[] string = new GuitarString[37];
        double frequency;
        for(int i = 0; i < 37; i++) {
            frequency = 440.0 * Math.pow(2, (i - 24.0)/12.0);
            string[i] = new GuitarString(frequency);
        }

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        int index;
        
        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();
                index = keyboard.indexOf(key);
                
                // pluck the corresponding string within the range of 0 to 37
                if(index>=0 && index<37) {
                    string[index].pluck();
                }
            }
            double sample = 0.0;
            // compute the superposition of the sample
            for(int i = 0; i < 37; i++) {
                sample += string[i].sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for(int i = 0; i < 37; i++) {
                string[i].tic();
            }
        }
    }
}