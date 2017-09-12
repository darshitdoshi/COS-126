import java.util.Random;

public class GuitarString {

    int SAMPLING_RATE = 44100;
    double ENERGY_DECAY_FACTOR = 0.996;
    RingBuffer rb;
    int capacity;
    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        capacity = (int)(SAMPLING_RATE / frequency);
        
        rb = new RingBuffer(capacity);
        for(int i = 0; i < capacity; i++) {
            rb.enqueue(0);
        }
    }

    /*creates a guitar string whose size and initial values are given by
     the specified array*/
    public GuitarString(double[] init) {
        capacity = init.length;
        rb = new RingBuffer(capacity);
        
        for(int i = 0; i < capacity; i++) {
            rb.enqueue(init[i]);
        }
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
        Random r = new Random();
        double random_value;
        double max = 0.5;
        double min = -0.5;
        for(int i = 0; i < capacity; i++) {
            random_value = min + (max - min) * r.nextDouble();
            rb.dequeue();
            rb.enqueue(random_value);
        }
    }

    // advances the Karplus-String simulation one time step
    public void tic() {
        double val1 = rb.dequeue();
        double val2 = rb.peek();
        double new_sample = ENERGY_DECAY_FACTOR * 0.5 * (val1 + val2);
        rb.enqueue(new_sample);
    }

    // returns the current sample
    public double sample() {
        return (rb.peek());
    }


    // unit tests this class
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
      double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };  
      GuitarString testString = new GuitarString(samples);
      for (int i = 0; i < N; i++) {
          double sample = testString.sample();
          System.out.printf("%6d %8.4f\n", i, sample);
          testString.tic();
      }
    }

}
