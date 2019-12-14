package model.handlers;

import model.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.units.AbstractUnit;

public class DeathHandler implements PropertyChangeListener{

    private Tactician tactician;

    public DeathHandler(Tactician tactician){
        this.tactician = tactician;
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
        tactician.killedUnit(((AbstractUnit)evt.getNewValue()));
    }
}