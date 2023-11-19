package model.entities;

public class Spacecraft {
    private static Spacecraft instance;
    private int fuel;

    private Spacecraft() {
        this.fuel = 100;
    }

    public static Spacecraft getInstance() {
        if (instance == null) {
            instance = new Spacecraft();
        }
        return instance;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
}
