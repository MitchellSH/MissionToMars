import java.util.ArrayList;
import java.util.List;

public class Rocket {

    protected int cost;
    protected int baseWeight;
    protected int maxWeight;
    protected int chanceOfLaunchExplode;
    protected int chanceOfLandCrash;
    protected int cargoWeight = 0;
    protected List<Cargo> cargo = new ArrayList<Cargo>();

    public boolean launch(){
        double probability = ((chanceOfLaunchExplode/100) * (cargoWeight/maxWeight));
        double random = Math.random();
        return random > probability;
    }

    public boolean land(){
        double probability = ((chanceOfLandCrash/100) * (cargoWeight/maxWeight));
        double random = Math.random();
        return random > probability;
    }

    public boolean canCarry(Cargo item) {
        int weight = baseWeight + cargoWeight + item.weight;
        return weight < maxWeight;
    }

    public void carry(Cargo item) {
        cargo.add(item);
        cargoWeight += item.weight;
    }
}
