public class Rocket implements SpaceShip {
    int cost;
    int weight;
    int max_weight;
    double launchExplosion;
    double landingCrash;
    int currentWeight;

    public boolean launch() {
        return true;
    }
    public boolean land() {
        return true;
    }
    public boolean canCarry(Item item){
        return this.currentWeight + item.weight <= max_weight;
    }
    public int carry(Item item){
        this.currentWeight += item.weight;
        return this.currentWeight;
    }
}