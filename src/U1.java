public class U1 extends Rocket {

    public U1(){
        this.rCost = 100000000;
        this.rWeight = 10000;
        this.maxWeight = 18000;
        this.chanceOfLaunchExplode = 5;
        this.chanceOfLandCrash = 1;
    }

    @Override
    public boolean launch(){
        double probability = ((chanceOfLaunchExplode/100) * (cWeight/maxWeight));
        double random = Math.random();
        return random > probability;
    }

    @Override
    public boolean land(){
        double probability = ((chanceOfLandCrash/100) * (cWeight/maxWeight));
        double random = Math.random();
        return random > probability;
    }

}
