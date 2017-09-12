public class Avogadro {
    
    //calculate Boltzmann constant and Avogadro's number
    static final double DELTA_T = 0.5;
    static final double ABSOLUTE_TEMP = 297;
    static final double VISCOSITY = 9.135e-4;
    static final double RADIUS = 0.5e-6;
    static final double R = 8.31446;
    static final double PIXEL_TO_METER = 0.175e-6;
    public static void main(String args[]) {
        
        double total_displacement = 0.0;
        double[] displacement = StdIn.readAllDoubles(); 
        
        for(int i = 0; i < displacement.length; i++) {
            total_displacement += Math.pow(displacement[i] * PIXEL_TO_METER, 2); //calculating total displacement in meter
        }
        double variance =  total_displacement/(2 * displacement.length);
        
        double self_diffusion_constant = variance/(2 * DELTA_T); 
        
        double boltzmann_constant = (self_diffusion_constant * 6 * Math.PI * VISCOSITY * RADIUS) / (ABSOLUTE_TEMP); 
        
        double avogadro_number = R / boltzmann_constant;
        
        
        StdOut.println(String.format("Boltzmann = %.4e", boltzmann_constant));
        StdOut.println(String.format("Avogadro = %.4e", avogadro_number));
    }
}