package model.factory;

import model.items.Bow;

public class BowFactory {
    public Bow create() {
        return new Bow("Bow",20,2,4);
    }
}
