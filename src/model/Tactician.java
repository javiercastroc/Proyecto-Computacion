package model;

import model.handlers.SelectHandler;
import model.units.*;
import model.map.Field;
import model.items.AbstractItem;
import model.items.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import model.handlers.DeathHandler;


public class Tactician {
    protected final List<AbstractUnit> units = new ArrayList<>();
    private final String name;
    private Field map;
    public PropertyChangeSupport
            selectNotification = new PropertyChangeSupport(this);
    public PropertyChangeSupport
            passTurnNotification = new PropertyChangeSupport(this);
    private AbstractUnit selectedUnit;
    private Boolean isMyTurn;
    protected List<AbstractUnit> nonMoved = new ArrayList<>();
    private int selectedIndex;


    public Tactician(String name, Field map) {
        this.name=name;
        this.map=map;
    }

    public String getName() {return this.name;}

    public Boolean isMyTurn(){
        return isMyTurn;
    }

    public void attack(AbstractUnit unit) {
        if(isMyTurn() && (selectedUnit!=null)) {
            getSelectedUnit().useItem(unit);
        }
    }

    public void move(int x, int y) {
        if(isMyTurn() && getNonMoved().contains(getSelectedUnit())){
            units.get(indexSelected()).moveTo(map.getCell(y,x));
            this.selectedUnit=units.get(indexSelected());
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
            selectNotification.firePropertyChange(new PropertyChangeEvent(this, "Select", "", unit));
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

    public List<AbstractUnit> getUnits() {
        return units;}

    public List<IEquipableItem> getInventory() {
        return getSelectedUnit().getItems();
    }

    public List<AbstractUnit> getNonMoved() {
        return List.copyOf(nonMoved);}

    public Field getMap() {
        return map;}

    public void passTurn(){
        if(isMyTurn()){
        selectUnit(null);
        this.isMyTurn=false;
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

    public void killedUnit(AbstractUnit unit){
        if(units.contains(unit) && unit.getCurrentHitPoints()==0){
        this.units.remove(unit);}
    }

    public void addUnit(AbstractUnit unit){
        if (unit.getOwner()==this){
            units.add(unit);
        }
    }

    public void deathNotify(AbstractUnit unit){
        unit.deathNotification.addPropertyChangeListener(new DeathHandler(this));
    }
}
