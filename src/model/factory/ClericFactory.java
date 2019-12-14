package model.factory;

import model.map.InvalidLocation;
import model.units.Cleric;

public class ClericFactory {
        public Cleric create() {
            return new Cleric(100, 5,new InvalidLocation()); }
    }

