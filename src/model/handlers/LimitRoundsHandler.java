package model.handlers;

import model.Tactician;
import controller.GameController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.map.Location;
import model.units.AbstractUnit;

public class LimitRoundsHandler implements PropertyChangeListener{

    private GameController controller;

    public LimitRoundsHandler(GameController controller){
        this.controller = controller;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt
     *     A PropertyChangeEvent object describing the event source
     *     and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        controller.getWinners();
    }
}
