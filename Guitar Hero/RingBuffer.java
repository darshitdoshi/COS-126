
public class RingBuffer extends RuntimeException{
    double[] buffer;
    int capacity; //length of the buffer
    int first; //stores the index of the least recently inserted item 
    int last; //stores the index one beyond the most recently inserted item
    int count; //number of items in the buffer

    // creates an empty ring buffer with the specified capacity
    public RingBuffer(int capacity) {
        buffer = new double[capacity];
        this.capacity = capacity;
        //the buffer is intially empty
        first = 0;
        last = 0;
        count = 0;
        
    }

    // return number of items currently in this ring buffer
    public int size() {
      return count;
    }

    // is this ring buffer empty (size equals zero)?
    public boolean isEmpty() {
            return(count == 0);
    }

    // is this ring buffer full (size equals capacity)?
    public boolean isFull() {
        return(count == capacity);
    }

    // adds item x to the end of this ring buffer
    public void enqueue(double x) throws RuntimeException{
        if(isFull()) {
            throw new RuntimeException("The buffer is full");
        }
        else {
            buffer[last] = x;
            last = (last + 1) % capacity; //Since its a circular buffer
            count++;
        }
    }

    // deletes and returns the item at the front of this ring buffer
    public double dequeue() throws RuntimeException{
        double result;
        if(isEmpty()) {
            throw new RuntimeException("The buffer is empty");
        }
        else {
            result = buffer[first];
            first = (first + 1) % capacity;
            count--;
        }
        return result;
    }

    // returns the item at the front of this ring buffer
    public double peek() {
         if(isEmpty()) {
            throw new RuntimeException("The buffer is empty");
        }
        else {
            return(buffer[first]);
        }
    }

    // unit tests this class
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
      RingBuffer buffer = new RingBuffer(N);  
      for (int i = 1; i <= N; i++) {
          buffer.enqueue(i);
      }
      double t = buffer.dequeue();
      buffer.enqueue(t);
      System.out.println("Size after wrap-around is " + buffer.size());
      while (buffer.size() >= 2) {
          double x = buffer.dequeue();
          double y = buffer.dequeue();
          buffer.enqueue(x + y);
      }
      System.out.println(buffer.peek());
    }

}