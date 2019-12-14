package model.factory;

import model.items.Oscuridad;

public class OscuridadFactory {
    public Oscuridad create() {
        return new Oscuridad("Oscuridad",20,1,3);
    }
}
