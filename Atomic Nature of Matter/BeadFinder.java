import java.util.ArrayList;
public class BeadFinder {
    
    boolean[][] visited; //keeps track of pixels visited
    ArrayList<Blob> blob;
    public static double tau;
    public static Picture picture;
    int width;
    int height;
    
    //  finds all blobs in the specified picture using luminance threshold tau
    public BeadFinder(Picture picture, double tau) {
        this.picture = picture;
        this.tau = tau;
        blob = new ArrayList<>(); //arraylist of all blobs in picture
        width = picture.width();
        height = picture.height();

        visited = new boolean[width][height];
        
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(!visited[i][j]) {
                        double luminance = Luminance.lum(picture.get(i, j));
                        if(luminance >= tau) {       
                            Blob b = new Blob();
                            blob.add(b);
                            dfs(b, i, j);
                        }
                }
            }
        }
    }
    
    //performing depth-first search with recurssion
    public void dfs(Blob b, int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height || visited[x][y]==true)
            return;

        visited[x][y] = true;
        double luminance = Luminance.lum(picture.get(x, y));
        if(luminance >= tau) {
            
            b.add(x, y);
            dfs(b, x+1, y);
            dfs(b, x-1, y);
            dfs(b, x, y+1);
            dfs(b, x, y-1);
        }
        return;
        
    }
    //  returns all beads (blobs with >= P pixels)
    public Blob[] getBeads(int P) {
        ArrayList<Blob> beads = new ArrayList<>(); //arraylist of all beads in picture
        for(int i = 0; i < blob.size(); i++) {
            if(blob.get(i).mass() >= P)
                beads.add(blob.get(i));
        }
        Blob[] beads_array = new Blob[beads.size()];
        beads_array = beads.toArray(beads_array);  //converting arraylist to array of beads
        return beads_array;
    }

    //  unit tests the BeadFinder data type, as described below
    public static void main(String[] args) {
        int P = Integer.parseInt(args[0]);
        tau = Double.parseDouble(args[1]);
        String picture_name = args[2];
        
        picture = new Picture(picture_name);
        BeadFinder bf = new BeadFinder(picture, tau);
        
        Blob[] beads_array = bf.getBeads(P);
        //StdOut.println(beads_array.length);
        for(int i = 0; i < beads_array.length; i++) {
            StdOut.println(beads_array[i].toString());
        }
    }

}