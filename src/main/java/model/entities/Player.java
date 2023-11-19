package model.entities;

public class Player {
    private static Player instance;
    private Spacecraft vehicle;
    private int life;
    private int oxygen;
    private int coin;

    public Player() {
        this.vehicle = Spacecraft.getInstance();
        this.life = 100;
        this.oxygen = 100;
        this.coin = 100;
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }
}
