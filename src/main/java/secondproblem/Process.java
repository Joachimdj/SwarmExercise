package secondproblem;

import firstproblem.*;
import java.util.Random;
import java.util.Vector;

public class Process {

    private int swarmSize = 20;
    private int dimension = 4;
    private Vector<Particle> swarm = new Vector<Particle>();
    private double[] pBest = new double[swarmSize];
    private Vector<Location> pBestLocation = new Vector<Location>();
    private double gBest;
    private Location gBestLocation;
    private double[] fitnessValueList = new double[swarmSize];

    private Random ranGen = new Random();

    void executeSwarm() {
        initSwarm();
        updateFitnessList();

        for (int i = 0; i < swarmSize; i++) {
            pBest[i] = fitnessValueList[i];
            pBestLocation.add(swarm.get(i).getLocation());
        }

        int t = 0;
        double w;
        double err = 9999;

        int maxIteration = 50;
        while (t < maxIteration && err > ProblemB.ERR_TOLERANCE) {
            System.out.println("Problem A: "+ ProblemA.ERR_TOLERANCE);
            for (int i = 0; i < swarmSize; i++) {
                if (fitnessValueList[i] < pBest[i]) {
                    pBest[i] = fitnessValueList[i];
                    pBestLocation.set(i, swarm.get(i).getLocation());
                }
            }

            int bestParticleIndex = Utility.getMinPos(fitnessValueList);
            if (t == 0 || fitnessValueList[bestParticleIndex] < gBest) {
                gBest = fitnessValueList[bestParticleIndex];
                gBestLocation = swarm.get(bestParticleIndex).getLocation();
            }

            double upperbound = 1.0;
            double lowerbound = 0.0;
            w = upperbound - (((double) t) / maxIteration) * (upperbound - lowerbound);

            for (int i = 0; i < swarmSize; i++) {
                double r1 = ranGen.nextDouble();
                double r2 = ranGen.nextDouble();

                Particle p = swarm.get(i);

                double[] newVel = new double[dimension];
                double c1 = 2.0;
                double c2 = 2.0;
                newVel[0] = (w * p.getVelocity().getPosition()[0])
                        + (r1 * c1) * (pBestLocation.get(i).getLocation()[0] - p.getLocation().getLocation()[0])
                        + (r2 * c2) * (gBestLocation.getLocation()[0] - p.getLocation().getLocation()[0]);
                newVel[1] = (w * p.getVelocity().getPosition()[1])
                        + (r1 * c1) * (pBestLocation.get(i).getLocation()[1] - p.getLocation().getLocation()[1])
                        + (r2 * c2) * (gBestLocation.getLocation()[1] - p.getLocation().getLocation()[1]);
                newVel[2] = (w * p.getVelocity().getPosition()[2])
                        + (r1 * c1) * (pBestLocation.get(i).getLocation()[2] - p.getLocation().getLocation()[2])
                        + (r2 * c2) * (gBestLocation.getLocation()[2] - p.getLocation().getLocation()[2]);
                newVel[3] = (w * p.getVelocity().getPosition()[3])
                        + (r1 * c1) * (pBestLocation.get(i).getLocation()[3] - p.getLocation().getLocation()[3])
                        + (r2 * c2) * (gBestLocation.getLocation()[3] - p.getLocation().getLocation()[3]);
                Velocity vel = new Velocity(newVel);
                p.setVelocity(vel);


                double[] newLoc = new double[dimension];
                newLoc[0] = p.getLocation().getLocation()[0] + newVel[0];
                newLoc[1] = p.getLocation().getLocation()[1] + newVel[1];
                newLoc[2] = p.getLocation().getLocation()[2] + newVel[2];
                newLoc[3] = p.getLocation().getLocation()[3] + newVel[3];
                Location loc = new Location(newLoc);
                p.setLocation(loc);
            }

            err = ProblemB.evaluate(gBestLocation) - 0;
            t++;
            updateFitnessList();
        }

        System.out.println("Best Location X: " + gBestLocation.getLocation()[0]);
        System.out.println("Best Location Y: " + gBestLocation.getLocation()[1]);
    }

    private void initSwarm() {
        Particle p;
        for (int i = 0; i < swarmSize; i++) {
            p = new Particle();

            double[] loc = new double[dimension];
            loc[0] = ProblemB.LOC_X_LOW + ranGen.nextDouble() * (ProblemB.LOC_X_HIGH - ProblemB.LOC_X_LOW);
            loc[1] = ProblemB.LOC_Y_LOW + ranGen.nextDouble() * (ProblemB.LOC_Y_HIGH - ProblemB.LOC_Y_LOW);
            loc[2] = ProblemB.LOC_U_LOW + ranGen.nextDouble() * (ProblemB.LOC_U_HIGH - ProblemB.LOC_U_LOW);
            loc[3] = ProblemB.LOC_W_LOW + ranGen.nextDouble() * (ProblemB.LOC_W_HIGH - ProblemB.LOC_W_LOW);
            Location location = new Location(loc);

            double[] vel = new double[dimension];
            vel[0] = ProblemB.VEL_LOW + ranGen.nextDouble() * (ProblemB.VEL_HIGH - ProblemB.VEL_LOW);
            vel[1] = ProblemB.VEL_LOW + ranGen.nextDouble() * (ProblemB.VEL_HIGH - ProblemB.VEL_LOW);
            vel[2] = ProblemB.VEL_LOW + ranGen.nextDouble() * (ProblemB.VEL_HIGH - ProblemB.VEL_LOW);
            vel[3] = ProblemB.VEL_LOW + ranGen.nextDouble() * (ProblemB.VEL_HIGH - ProblemB.VEL_LOW);
            Velocity velocity = new Velocity(vel);

            p.setLocation(location);
            p.setVelocity(velocity);
            swarm.add(p);
        }
    }

    private void updateFitnessList() {
        for (int i = 0; i < swarmSize; i++) {
            fitnessValueList[i] = swarm.get(i).getFitnessValue();
        }
    }
}
