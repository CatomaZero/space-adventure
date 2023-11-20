package model.map;

import javafx.scene.canvas.GraphicsContext;

public abstract class Enviroment {
    private int key;
    private int x;
    private int y;

    private boolean naranjita;

    public Enviroment(int key, int x, int y) {
        this.key = key;
        this.x = x;
        this.y = y;
        this.naranjita = false;
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

    public boolean isNaranjita() {
        return naranjita;
    }

    public void setNaranjita(boolean naranjita) {
        this.naranjita = naranjita;
    }

    public abstract void drawEnviroment(GraphicsContext gc);
}
