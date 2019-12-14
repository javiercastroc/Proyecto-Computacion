package model.factory;

import model.map.InvalidLocation;
import model.units.Sorcerer;

public class SorcererFactory {

    public Sorcerer create() {
        return new Sorcerer(100, 5,new InvalidLocation()); }
}

