class NBody
{
    static final Double G = 6.67*Math.pow(10,-11);
    static final String Background = "starfield.jpg";
    
    static Double[] pos_x;
    static Double[] pos_y;
    static Double[] vel_x;
    static Double[] vel_y;
    static Double[] mass;
    static String[] name;
    static int N;
    static Double delta_t;
    static Double radius;
    
    static Double[] force_x;
    static Double[] force_y;
    
    public static void main(String args[])
    {
        //Read the information of the current universe
        Double T = java.lang.Double.parseDouble(args[0]);
        delta_t = java.lang.Double.parseDouble(args[1]);
        N = StdIn.readInt();
        radius = StdIn.readDouble();
                
        pos_x = new Double[N];
        pos_y = new Double[N];
        vel_x = new Double[N];
        vel_y = new Double[N];
        mass = new Double[N];
        name = new String[N];
        
        force_x = new Double[N];
        force_y = new Double[N];
        
        Double time = 0.0;
        
        for(int i = 0; i < N; i++)
            {
                pos_x[i] = StdIn.readDouble();
                pos_y[i] = StdIn.readDouble();
                vel_x[i] = StdIn.readDouble();
                vel_y[i] = StdIn.readDouble();
                mass[i] = StdIn.readDouble();
                name[i] = StdIn.readString();
            }
        
        StdAudio.play("2001.mid"); //Play the audio
        
        /*Repeat the process of drawing the current universe, calculating net force, calculating new position of planets
        till the current time is less than the max time(T).*/
        while(time < T)
        {
            draw();          
            calculateForce();
            newPosition();
            time += delta_t;
        }
        
        //Print the end state
        printState();
    }
    
    //calculate the net force exerted on each planet 
    public static void calculateForce()
    {
        
        
        Double f_x, f_y;
        Double r, force;
        Double dx, dy;
        for(int i = 0; i < N; i++)
        {
            f_x = 0.0;
            f_y = 0.0;
            
            for(int j=0; j<N; j++)
            {
                if(j!=i)
                {
                    dx = (pos_x[j]-pos_x[i]);
                    
                    dy = (pos_y[j]-pos_y[i]);
                    
                    r = Math.sqrt((dx*dx) + (dy*dy));
                    force = (G*mass[i]*mass[j]) / (r*r);
                    f_x += (force*dx / r);
                    f_y += (force*dy / r);
                    
                }
            }
            
            force_x[i] = f_x;
            force_y[i] = f_y;
        }
    }
    
    //calculate the new position of the planets
    public static void newPosition()
    {
        Double[] acc_x = new Double[N];
        Double[] acc_y = new Double[N];
        
        for(int i = 0; i < N; i++)
        {
            acc_x[i] = force_x[i] / mass[i];
            acc_y[i] = force_y[i] / mass[i];
            
            vel_x[i] = vel_x[i] + (delta_t*acc_x[i]);
            vel_y[i] = vel_y[i] + (delta_t*acc_y[i]);
            
            pos_x[i] = pos_x[i] + (delta_t*vel_x[i]);
            pos_y[i] = pos_y[i] + (delta_t*vel_y[i]);
        }
    }
    
    //draw the background and the planets
    public static void draw()
    {
        StdDraw.setScale(-1*radius, radius);
        StdDraw.clear();
        StdDraw.picture(0.5, 0.5, Background);
        for(int i = 0; i < N; i++)
        {
            StdDraw.picture(pos_x[i], pos_y[i], name[i]);
        }
        
        StdDraw.show(25);
    }
    
    //Print the values of the current state in the required format
    public static void printState()
    {
        StdOut.println(N);
        StdOut.println(radius);
        for(int i = 0; i < N; i++)
        {
            StdOut.printf("%.4e  ", pos_x[i]);
            StdOut.printf("  %.4e  ", pos_y[i]);
            StdOut.printf("  %.4e  ", vel_x[i]);
            StdOut.printf("  %.4e  ", vel_y[i]);
            StdOut.printf("  %.4e  ", mass[i]);
            StdOut.printf("  %s    ", name[i]);
            StdOut.println();
        }
    }
}