package model.map;

import model.entities.Enemy;

import java.util.ArrayList;

public class Planet extends Enviroment {
    private ArrayList<Items> items;

    public ArrayList<Enemy> enemies;

    public Planet(int key){
        super(key);
    }
}
