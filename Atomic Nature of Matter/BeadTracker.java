public class BeadTracker {
    
    public static void main(String args[]) {  
        
        //Read the command-line arguments
        int P = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);
        String[] filename = new String[args.length-3];
        for(int i = 0; i < args.length-3; i++) {
            filename[i] = args[i+3];
        }
        
        Picture pic1;
        Picture pic2;
        BeadFinder bead1;
        BeadFinder bead2;
        Blob[] bead_arr1;
        Blob[] bead_arr2;
        
        for(int i = 0; i < filename.length-1; i++) {
            //Find the closest bead and thus track the movement of each bead. Print the distance moved by each bead in successive frames.
            pic1 = new Picture(filename[i]);
            pic2 = new Picture(filename[i+1]);
            
            bead1 = new BeadFinder(pic1, tau);
            bead2 = new BeadFinder(pic2, tau);
            
            bead_arr1 = bead1.getBeads(P);
            bead_arr2 = bead2.getBeads(P);
            
            double distance;
            for(int j = 0; j < bead_arr2.length; j++) {
                double least_distance = Double.POSITIVE_INFINITY;
                for(int k = 0; k < bead_arr1.length; k++) {
                    distance = bead_arr2[j].distanceTo(bead_arr1[k]);
                    if(distance < least_distance && distance <= delta)
                        least_distance = distance;
                }
                if(least_distance > 0.0 && least_distance < Double.POSITIVE_INFINITY)
                    StdOut.println(String.format("%.4f", least_distance));
            }
            
            
        }
    }
}