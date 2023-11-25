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
    private String[] travel;
    private boolean move;
    private boolean close=false;

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

    public void setTravel(String[] travel) {
        this.travel = travel;
    }

    public String[] getTravel() {
        return travel;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.close=true;
        this.move = move;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public boolean isClose() {
        return close;
    }
}
