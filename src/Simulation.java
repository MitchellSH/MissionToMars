import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {

    public ArrayList<Rocket> rockets = new ArrayList<Rocket>();
    public Class rocketType;

    public Simulation(Class rocketType){
        this.rocketType = rocketType;
    }

    public void prepareRockets(File f1, File f2) throws Exception {
        ArrayList<Cargo> phase1Manifest = loadCargo(f1);
        ArrayList<Cargo> phase2Manifest = loadCargo(f2);

        rockets.addAll(createLoadedRockets(phase1Manifest));
        rockets.addAll(createLoadedRockets(phase2Manifest));
    }

    public ArrayList<Cargo> loadCargo(File path) throws Exception {
        ArrayList<Cargo> cargo = new ArrayList<Cargo>();

        File file = new File(String.valueOf(path));
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String [] data = line.split("=");
            Cargo item = new Cargo();
            item.name = data[0];
            item.weight = Integer.parseInt(data[1]);
            cargo.add(item);
        }

        return cargo;
    }

    public List<Rocket> createLoadedRockets(ArrayList<Cargo> item) throws Exception {

        ArrayList<Rocket> fleet = new ArrayList<Rocket>();

        Rocket r = (Rocket) rocketType.getConstructor().newInstance();

        fleet.add(r);

        for (Cargo i : item) {
            if (r.canCarry(i)){
                r.carry(i);
            } else {
                r = (Rocket) rocketType.getConstructor().newInstance();
                rockets.add(r);
            }
        }

        return fleet;

    }

    public void run() throws Exception {
        int totalBudget = 0;
        int totalTrips = 0;
        int totalFails = 0;

        System.out.println(String.format("+=========================================================="));

        for(int i = 0; i < rockets.size(); i++){
            Rocket rocket = rockets.get(i);
            if (!rocket.launch() || !rocket.land()){
                System.out.println(String.format("|  %s Rocket #%s failed, launching again", rocketType.getSimpleName(), i));
                totalFails++;
            }
            else {
                System.out.println(String.format("|  %s Rocket #%s launched and landed successfully", rocketType.getSimpleName(), i));
            }
            totalBudget += rocket.cost;
            totalTrips++;
        }

        System.out.println(String.format("+=========================================================="));
        System.out.println(String.format("|  Results of %s Rockets for both Phase 1 and Phase 2", rocketType.getSimpleName()));
        System.out.println(String.format("|--------------------------------"));
        System.out.println(String.format("|  Total Budget: $%s", NumberFormat.getNumberInstance().format(totalBudget)));
        System.out.println(String.format("|  Total Trips: %s", totalTrips));
        System.out.println(String.format("|  Failed %s of %s \n", totalFails, totalTrips));
    }


}
