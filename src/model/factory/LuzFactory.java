package model.factory;

import model.items.Luz;

public class LuzFactory {
    public Luz create() {
        return new Luz("Luz",20,1,3);
    }
}
