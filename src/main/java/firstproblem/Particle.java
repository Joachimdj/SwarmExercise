package firstproblem;

public class Particle {

    private Velocity velocity;
    private Location location;
    private double fitnessValue;

    public Particle() {
        super();
    }

    public Particle(double fitnessValue, Velocity velocity, Location location) {
        this.fitnessValue = fitnessValue;
        this.velocity = velocity;
        this.location = location;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getFitnessValue() {
        fitnessValue = ProblemA.evaluate(location);
        return fitnessValue;
    }
}
