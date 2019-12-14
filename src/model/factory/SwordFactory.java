package model.factory;

import model.items.Sword;

public class SwordFactory {


    public Sword create() {
        return new Sword("Sword",20,1,3);
    }
}
