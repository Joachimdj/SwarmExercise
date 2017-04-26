package secondproblem;

import firstproblem.*;
import static java.lang.Math.*;

public class ProblemB {

    public static final double LOC_X_LOW = -2;
    public static final double LOC_X_HIGH = 2;
    public static final double LOC_Y_LOW = -2;
    public static final double LOC_Y_HIGH = 2;
    public static final double VEL_LOW = -1;
    public static final double VEL_HIGH = 1;
    public static final double LOC_U_LOW = -2;
    public static final double LOC_U_HIGH = 2;
    public static final double LOC_W_LOW = -2;
    public static final double LOC_W_HIGH = 2;
    
    public static final double ERR_TOLERANCE = 1E-20; 

    public static double evaluate(Location location) {
        double x = location.getLocation()[0];
        double y = location.getLocation()[1];
        double u = location.getLocation()[2];
        double w = location.getLocation()[3];
        return 2 * x * exp(-x*x-y*y-(u-1)*(u-1)-w*w);
    }
}
