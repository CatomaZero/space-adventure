package model.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Planet extends Enviroment {

    public Planet(int key, int x, int y) {
        super(key, x, y);
    }

    @Override
    public String getName() {
        return "Planet" + getKey();
    }

    @Override
    public void drawEnviroment(GraphicsContext gc) {
        int radius = 20;
        gc.setFill(Color.LIGHTPINK);
        gc.fillOval(getX()-radius, getY()-radius, 2*radius, 2*radius);

        if(isNaranjita()){
            int radiusN = 5;
            gc.setFill(Color.PURPLE);
            gc.fillOval(getX()-radiusN, getY()-radiusN, 2*radiusN, 2*radiusN);
        }
    }
}