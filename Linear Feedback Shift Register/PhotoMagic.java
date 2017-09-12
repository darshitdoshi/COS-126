import java.awt.Color;
public class PhotoMagic {

    public static Picture transform(Picture picture, LFSR lfsr) {
        int width = picture.width();
        int height = picture.height();
        Picture coded_picture = new Picture(width, height);
        
        for(int col = 0; col < width; col++) {
            for(int row = 0; row < height; row++) {
                Color color = picture.get(col, row);
                //extract the 3 different color components
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                
                //Xor each color with a newly generated 8 bit integer
                int r_code = lfsr.generate(8);
                r = r ^ r_code;
                
                int g_code = lfsr.generate(8);
                g = g ^ g_code;
                
                int b_code = lfsr.generate(8);
                b = b ^ b_code;
                
                //set the pixel of new image according to the new values of r,g,b
                coded_picture.set(col, row, new Color(r, g, b));
            }
        }
        return coded_picture;
    }
    
    public static void main(String[] args) {
    //read the picture name, seed and tap from the command-line argument
        Picture picture = new Picture(args[0]);
        String seed = args[1];
        int tap = Integer.parseInt(args[2]);
        
        LFSR lfsr = new LFSR(seed, tap);
        //display the transformed image
        transform(picture, lfsr).show();
    }
}