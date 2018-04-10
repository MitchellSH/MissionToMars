public class U2 extends Rocket {

    public U2(){
        this.rCost = 120000000;
        this.rWeight = 18000;
        this.maxWeight = 29000;
        this.chanceOfLaunchExplode = 4;
        this.chanceOfLandCrash = 8;
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

