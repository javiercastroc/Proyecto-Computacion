package model.factory;

import model.items.Anima;

public class AnimaFactory {
    public Anima create() {
        return new Anima("Anima",20,1,3);
    }
}
