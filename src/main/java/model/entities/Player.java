package model.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player {
    private static Player instance;
    private int life;
    private int oxygen;
    private int coin;
    private double x;
    private double y;

    public Player() {
        this.life = 100;
        this.oxygen = 100;
        this.coin = 100;
        this.x = 0;
        this.y = 0;
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void drawPlayer(GraphicsContext gc){
        int radius = 10;
        gc.setFill(Color.GREENYELLOW);
        gc.fillOval(x-radius, y-radius, 2*radius, 2*radius);
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
