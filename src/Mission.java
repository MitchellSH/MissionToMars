import java.io.File;

public class Mission {

    public static void main(String [] args) throws Exception {
        Simulation sim = new Simulation(U1.class);
        sim.prepareRockets(new File("PhaseOne.txt"), new File("PhaseTwo.txt"));
        sim.run();

        sim = new Simulation(U2.class);
        sim.prepareRockets(new File("PhaseOne.txt"), new File("PhaseTwo.txt"));
        sim.run();
    }
}
