package model.factory;

import model.map.InvalidLocation;
import model.units.Alpaca;
import model.units.Archer;

public class ArcherFactory {
    public Archer create() {
        return new Archer(100, 5,new InvalidLocation()); }
}
