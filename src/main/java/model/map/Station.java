package model.map;

public class Station extends Enviroment {

    public Station(int key, int x, int y) {
        super(key, x, y);
    }

    @Override
    public String getName() {
        return "Station" + getKey();
    }
}