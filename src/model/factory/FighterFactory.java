package model.factory;

import model.map.InvalidLocation;
import model.units.Fighter;

public class FighterFactory {
        public Fighter create() {
            return new Fighter(100, 5,new InvalidLocation()); }
    }

