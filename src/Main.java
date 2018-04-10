import java.util.ArrayList;

public class Main {

    public static void main(String [] args) throws Exception {
        Simulation sim = new Simulation();

        try {
            ArrayList<Item> phase1Items = sim.loadItems("PhaseOne.txt");
            ArrayList<Item> phase2Items = sim.loadItems("PhaseTwo.txt");

            ArrayList<Rocket> rocketsU1 = new ArrayList<Rocket>();
            rocketsU1.addAll(sim.loadU1(phase1Items));
            rocketsU1.addAll(sim.loadU1(phase2Items));
            sim.runSimulation(rocketsU1, "U1");

            ArrayList<Rocket> rocketsU2 = new ArrayList<Rocket>();
            rocketsU2.addAll(sim.loadU2(phase1Items));
            rocketsU2.addAll(sim.loadU2(phase2Items));
            sim.runSimulation(rocketsU2, "U2");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
