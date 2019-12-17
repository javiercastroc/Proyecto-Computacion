package model;

import model.units.*;
import model.map.Field;
import model.items.AbstractItem;
import model.items.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import model.handlers.DeathHandler;



/**
 * This class represents a Tactician, a user in the game,
 * tactician represent the player in game.
 * <p>
 * The responsibility of this entity will be to handle all user instructions
 * and delegate messages to model objects.
 * A tactician is able to perform all the unctionalities of the items and units.
 *
 *
 *
 * @author Javier Castro Cuevas
 * @since 2.0
 */
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
    protected List<AbstractUnit> nonMoved = new ArrayList<AbstractUnit>();
    private int selectedIndex;
    private int roundNumber=0;



    /**
     * Creates a new Tactician.
     *
     * @param name
     *     the name of the Player/Tactician
     * @param map
     *     the game map Field that will be used for playing
     *
     */
    public Tactician(String name, Field map) {
        this.name=name;
        this.map=map;
    }



    /**
     * @return the name of this Tactician
     */
    public String getName() {return this.name;}


    /**
     * @return whether this turn is of this Tactician
     */
    public Boolean isMyTurn(){
        return isMyTurn;
    }



    /**
     * uses selected item (of actual selected unit) on other unit
     * @param unit other unit to use item on
     */
    public void useItemOn(AbstractUnit unit) {
        if(isMyTurn() && (selectedUnit!=null) && roundNumber!=0) {
            getSelectedUnit().useItem(unit);
        }
    }


    /**
     * moves the actual selected unit on x,y cell
     * @param x horizontal position of the cell
     * @param y vertical position of the cell
     */
    public void move(int x, int y) {
        if(isMyTurn() && nonMoved.contains(getSelectedUnit())){
            units.get(indexSelected()).moveTo(map.getCell(y,x)); } }



    /**
     * gives item from the inventory of the actual selected unit to other unit
     * @param item item to give
     * @param unit the receiver of the item
     */
    public void giveItem(AbstractItem item, AbstractUnit unit) {
        if(isMyTurn() && getSelectedUnit().getItems().contains(item)){
            units.get(indexSelected()).giveItem(item,unit); } }


    /**
     * @return the index of the selected unit (in units list)
     */
    public int indexSelected(){
        return units.indexOf(getSelectedUnit());
    }



    public void selectUnit(AbstractUnit unit){
        if (isMyTurn() && units.contains(unit)){
            this.selectedUnit=unit;
            this.selectedIndex=units.indexOf(unit);
            selectNotification.firePropertyChange(new PropertyChangeEvent(this, "Select", "", unit));
        }
    }

    /**
     * the selected unit equips an item of the selected unit inventory
     * @param item the item to equip
     */
    public void equip(AbstractItem item){
        if (isMyTurn() && units.get(indexSelected()).getItems().contains(item)){
            units.get(indexSelected()).equipItem(item);
        }
    }


    /**
     * @return the selected unit of this Tactician
     */
    public AbstractUnit getSelectedUnit(){
        return selectedUnit;
    }


    /**
     * @return a list of the units of this Tactician
     */
    public List<AbstractUnit> getUnits() {
        return List.copyOf(units);}


    /**
     * @return a list of the items that the selected unit has
     */
    public List<IEquipableItem> getInventory() {
        return getSelectedUnit().getItems();
    }


    /**
     * @return a list of the units of this Tactician
     */
    public List<AbstractUnit> getNonMoved() {
        return List.copyOf(nonMoved);}


    /**
     * @return the Game Map Field
     */
    public Field getMap() {
        return map;}


    /**
     * If is the turn of this Tactician, it ends the actual turn
     * and notify this to the game controller
     */
    public void passTurn(){
        if(isMyTurn()){
        this.isMyTurn=false;
        this.roundNumber++;
        passTurnNotification.firePropertyChange(new PropertyChangeEvent(this, "PassTurn", "", this));
    }}


    /**
     * @return the power of the item (if the item is not in the inventory of the
     * selected unit, it returns 0)
     * @param item to analyze power
     */
    public int getPower(IEquipableItem item){
        if(getInventory().contains(item)) return item.getPower();
        else return 0;
    }


    /**
     * @return the current Hit Points of the selected Unit
     *
     */
    public int getCurrentHitPoints(){
        return units.get(indexSelected()).getCurrentHitPoints();
    }

    /**
     * @return the maximum Hit Points of the selected Unit
     *
     */
    public int getMaxHitPoints(){
        return units.get(indexSelected()).getMaxHitPoints();
    }

    /**
     * Sets the turn to the this Tactician
     * actualize the nonMoved units
     */
    public void setTurn(){
        this.isMyTurn=true;
        nonMoved=List.copyOf(getUnits());
    }

    /**
     * if the unit died(actual hit points=0)
     * and if the unit is of this Tactician:
     * remove the unit from units list
     * @param unit that has been killed
     */
    public void killedUnit(AbstractUnit unit){
        if(units.contains(unit) && unit.getCurrentHitPoints()==0){
        this.units.remove(units.indexOf(unit));
        if(nonMoved.contains(unit)){
        nonMoved.remove(nonMoved.indexOf(unit));}}
    }

    /**
     * if unit's owner is this Tactician, then add to list
     * @param unit to add
     */
    public void addUnit(AbstractUnit unit){
        if (unit.getOwner()==this && !units.contains(unit)){
            units.add(unit);
        }
    }



    /**
     * creates a death handler
     * to notify this Tactician when the unit dies
     * @param unit
     */
    public void deathNotify(AbstractUnit unit){
        unit.deathNotification.addPropertyChangeListener(new DeathHandler(this));
    }
}
