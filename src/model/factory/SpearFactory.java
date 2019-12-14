package model.factory;

import model.items.Spear;

public class SpearFactory {

    public Spear create() {
        return new Spear("Spear",20,1,3);
    }
}
