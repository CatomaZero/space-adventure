package model.map;

public class Planet extends Enviroment {

    public Planet(int key, int x, int y) {
        super(key, x, y);
    }

    @Override
    public String getName() {
        return "Planet" + getKey();
    }
}