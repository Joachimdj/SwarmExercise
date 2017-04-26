package firstproblem;
import static java.lang.Math.*;

public class ProblemA {

    public static final double LOC_X_LOW = -2;
    public static final double LOC_X_HIGH = 2;
    public static final double LOC_Y_LOW = -2;
    public static final double LOC_Y_HIGH = 2;
    public static final double VEL_LOW = -1;
    public static final double VEL_HIGH = 1;

    public static final double ERR_TOLERANCE = 1E-20; 

    public static double evaluate(Location location) {
        double x = location.getLocation()[0];
        double y = location.getLocation()[1];
        return x * exp(-x*x-y*y);
    }
}
