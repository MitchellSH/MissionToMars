import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    public ArrayList<Item> loadItems(String path) throws Exception {
        ArrayList<Item> cargo = new ArrayList<Item>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String [] data = line.split("=");
            Item item = new Item();
            item.name = data[0];
            item.weight = Integer.parseInt(data[1]);
            cargo.add(item);
        }

        return cargo;
    }

    public ArrayList<U1> loadU1(ArrayList<Item> items) throws Exception {
        ArrayList<U1> rockets = new ArrayList<U1>();

        U1 rU1 = new U1();

        for (Item item: items) {
            if (rU1.canCarry(item)){
                rU1.carry(item);
            } else {
                rockets.add(rU1);
                rU1 = new U1();
            }
        }

        return rockets;
    }

    public ArrayList<U2> loadU2(ArrayList<Item> items) throws Exception {
        ArrayList<U2> rockets = new ArrayList<U2>();

        U2 rU2 = new U2();

        for (Item item: items) {
            if (rU2.canCarry(item)){
                rU2.carry(item);
            } else {
                rockets.add(rU2);
                rU2 = new U2();
            }
        }

        return rockets;
    }

    public void runSimulation(ArrayList<Rocket> rockets, String name) throws Exception {
        int totalBudget = 0;
        int totalTrips = 0;
        int totalFails = 0;

        System.out.println(String.format("+=========================================================="));

        for(int i = 0; i < rockets.size(); i++){
            Rocket rocket = rockets.get(i);
            if (!rocket.launch() || !rocket.land()){
                System.out.println(String.format("|  %s Rocket #%s failed, launching again", name, i));
                totalFails++;
            }
            else {
                System.out.println(String.format("|  %s Rocket #%s launched and landed successfully", name, i));
            }
            totalBudget += rocket.rCost;
            totalTrips++;
        }

        System.out.println(String.format("+=========================================================="));
        System.out.println(String.format("|  Results of %s Rockets for both Phase 1 and Phase 2", name));
        System.out.println(String.format("|--------------------------------"));
        System.out.println(String.format("|  Total Budget: $%s", NumberFormat.getNumberInstance().format(totalBudget)));
        System.out.println(String.format("|  Total Trips: %s", totalTrips));
        System.out.println(String.format("|  Failed %s of %s \n", totalFails, totalTrips));
    }


}
