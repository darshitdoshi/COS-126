public class Blob {
    
    private int mass;
    private int x_sum;
    private int y_sum;
    
    public        Blob() {                   //  creates an empty blob
    }
    
    
    public   void add(int x, int y) {        //  adds pixel (x, y) to this blob
        mass++;
        x_sum += x;
        y_sum += y;
    }    
        
    public   int  mass() {                     //  returns the number of pixels added to this blob
        return mass;
    }    
    
    public double distanceTo(Blob that) {      //  returns the Euclidean distance between the center of masses of the two blobs
        double x1, y1, x2, y2;
        
        x1 = (double) x_sum / mass;
        y1 = (double) y_sum / mass;
        
        x2 = (double) that.x_sum / that.mass;
        y2 = (double) that.y_sum / that.mass;
        
        double euclidean = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        return euclidean;
    }
    
    public String toString() {                //  returns a string representation of this blob
        double x = (double) x_sum / mass;
        double y = (double) y_sum / mass;
        
        return String.format("%2d (%8.4f, %8.4f)", mass, x, y);
    }
    
    public static void main(String[] args) {  //  unit tests all methods in the Blob data type
        Blob b = new Blob();
        b.add(2, 3);
        b.add(4, 5);
        b.add(7, 8);
        
        Blob a = new Blob();
        a.add(10, 11);
        a.add(30, 40);
        
        StdOut.println(a.distanceTo(b));
        StdOut.println(b.toString());
    
    }
}