package model.factory;

import controller.GameController;
import model.handlers.HeroHandler;
import model.map.InvalidLocation;
import model.units.Fighter;
import model.units.Hero;

public class HeroFactory {
    private GameController controller;
    public HeroFactory(GameController controller){
        this.controller=controller;
    }

    public Hero create() {
        Hero hiro =new Hero(200, 5,new InvalidLocation());
        hiro.heroNotification.addPropertyChangeListener(new HeroHandler(controller));
        return hiro; }
}
