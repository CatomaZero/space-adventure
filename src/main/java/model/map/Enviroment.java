package model.map;

public abstract class Enviroment {
    private int key;
    private int x;
    private int y;

    public Enviroment(int key, int x, int y) {
        this.key = key;
        this.x = x;
        this.y = y;
    }

    public int getKey() {
        return key;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract String getName();
}
