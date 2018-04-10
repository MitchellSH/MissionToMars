public class Rocket implements SpaceShip {

    protected int rCost;
    protected int rWeight;
    protected int maxWeight;
    protected int chanceOfLaunchExplode;
    protected int chanceOfLandCrash;
    protected int cWeight = 0;

    public boolean launch() {
        return true;
    }

    public boolean land() {
        return true;
    }

    public boolean canCarry(Item item) {
        int weight = rWeight + (cWeight + item.weight);
        return weight < maxWeight;
    }

    public void carry(Item item) {
        int weight = rWeight + (cWeight + item.weight);
        cWeight = weight;
    }
}
