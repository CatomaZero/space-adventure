package model.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Station extends Enviroment {

    public Station(int key, int x, int y, Image image) {
        super(key, x, y, image);
    }

    @Override
    public String getName() {
        return "Station" + getKey();
    }

    @Override
    public void drawEnviroment(GraphicsContext gc) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setColor(javafx.scene.paint.Color.rgb(255, 255, 255, 0.5));
        gc.setEffect(dropShadow);
        gc.drawImage(getImage(), getX() - 20, getY() - 20, 40, 40);
        gc.setEffect(null);

        if(isNaranjita()){
            int radiusN = 5;
            gc.setFill(Color.ORANGE);
            gc.fillOval(getX()-radiusN, getY()-radiusN, 2*radiusN, 2*radiusN);
        }
    }
}