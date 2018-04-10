public interface SpaceShip {
    boolean launch();
    boolean land();
    boolean canCarry(Item items);
    void carry(Item weight);
}
