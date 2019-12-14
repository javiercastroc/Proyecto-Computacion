package model.factory;

import model.map.InvalidLocation;
import model.units.Alpaca;

public class AlpacaFactory {
    public Alpaca create() {
        return new Alpaca(100, 5,new InvalidLocation()); }
}
