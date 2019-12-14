package model.factory;

import model.map.InvalidLocation;
import model.units.Fighter;
import model.units.SwordMaster;

public class SwordMasterFactory {

    public SwordMaster create() {
        return new SwordMaster(100, 5,new InvalidLocation()); }
}

