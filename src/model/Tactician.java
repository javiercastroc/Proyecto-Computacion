package model;

import model.units.AbstractUnit;
import model.map.Field;
import model.items.AbstractItem;
import model.units.IUnit;
import model.items.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;


public class Tactician {
    protected final List<AbstractUnit> units = new ArrayList<>();
    private final String name;
    private Field map;
    public PropertyChangeSupport
            attackNotification = new PropertyChangeSupport(this);
    public PropertyChangeSupport
            passTurnNotification = new PropertyChangeSupport(this);
    private AbstractUnit selectedUnit;
    private Boolean isMyTurn;
    protected List<IUnit> nonMoved = new ArrayList<>();


    public Tactician(String name, Field map) {
        this.name=name;
        this.map=map;
    }

    public String getName() {return this.name;}

    public Boolean isMyTurn(){
        return isMyTurn;
    }

    public void attack(IUnit unit) {
        if(isMyTurn() && (selectedUnit!=null)) {
            attackNotification.firePropertyChange(new PropertyChangeEvent(this, "Atack", "", unit));
            units.get(indexSelected()).useItem(unit);
        }
    }

    public void move(int x, int y) {
        if(isMyTurn() && getNonMoved().contains(getSelectedUnit())){
            units.get(indexSelected()).moveTo(map.getCell(y,x));
        }

    }

    public void giveItem(AbstractItem item, AbstractUnit unit) {
        if(isMyTurn() && getSelectedUnit().getItems().contains(item)){
            units.get(indexSelected()).giveItem(item,unit);
        }
    }

    public int indexSelected(){
        return units.indexOf(getSelectedUnit());
    }

    public void selectUnit(AbstractUnit unit){
        if (isMyTurn() && units.contains(unit)){
            this.selectedUnit=unit;
        }
    }

    public void equip(AbstractItem item){
        if (isMyTurn() && units.get(indexSelected()).getItems().contains(item)){
            units.get(indexSelected()).equipItem(item);
        }
    }

    public AbstractUnit getSelectedUnit(){
        return selectedUnit;
    }

    public List<IUnit> getUnits() {
        return List.copyOf(units);}

    public List<IEquipableItem> getInventory() {
        return getSelectedUnit().getItems();
    }

    public List<IUnit> getNonMoved() {
        return List.copyOf(nonMoved);}

    public Field getMap() {
        return map;}

    public void passTurn(){
        if(isMyTurn()){
        this.isMyTurn=false;
        selectUnit(null);
        passTurnNotification.firePropertyChange(new PropertyChangeEvent(this, "PassTurn", "", ""));
    }}

    public int getPower(IEquipableItem item){
        if(getInventory().contains(item)) {
            return item.getPower();
        } else return 0;}

    public int getCurrentHitPoints(){
        return units.get(indexSelected()).getCurrentHitPoints();
    }

    public int getMaxHitPoints(){
        return units.get(indexSelected()).getMaxHitPoints();
    }
    public void setTurn(){
        this.isMyTurn=true;
        this.nonMoved=List.copyOf(getUnits());
    }

    public void killedUnit(IUnit unit){
        if(units.contains(unit)){
        this.units.remove(unit);}
    }

}
