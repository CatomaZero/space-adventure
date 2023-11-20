package model.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Station extends Enviroment {

    public Station(int key, int x, int y) {
        super(key, x, y);
    }

    @Override
    public String getName() {
        return "Station" + getKey();
    }

    @Override
    public void drawEnviroment(GraphicsContext gc) {
        int radius = 10;
        gc.setFill(Color.BLUEVIOLET);
        gc.fillOval(getX()-radius, getY()-radius, 2*radius, 2*radius);

        if(isNaranjita()){
            int radiusN = 5;
            gc.setFill(Color.ORANGE);
            gc.fillOval(getX()-radiusN, getY()-radiusN, 2*radiusN, 2*radiusN);
        }
    }
}