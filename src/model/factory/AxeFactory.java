package model.factory;

import model.items.Axe;

public class AxeFactory {
    public Axe create() {
        return new Axe("Axe",20,1,3);
    }
}
