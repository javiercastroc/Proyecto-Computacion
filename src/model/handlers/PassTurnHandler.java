package model.handlers;

import controller.GameController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Tactician;
import model.units.AbstractUnit;

public class PassTurnHandler implements PropertyChangeListener{

    private GameController controller;

    public PassTurnHandler(GameController controller){
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
    public void propertyChange(final PropertyChangeEvent evt){
    if(evt.getNewValue()==controller.getTurnOwner()){
        controller.endTurn();
    }
}}



