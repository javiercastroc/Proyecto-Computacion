package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.items.*;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  protected final List<IEquipableItem> items = new ArrayList<>();
  private final int maxHitPoints;
  private int currentHitPoints;
  private final int movement;
  private final int maxItems;
  protected IEquipableItem equippedItem;
  private Location location;

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   */
  protected AbstractUnit(final int hitPoints, final int movement,
      final Location location, final int maxItems, final IEquipableItem... items) {
    this.maxHitPoints =hitPoints;
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.maxItems = maxItems;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }

  @Override
  public int getMaxItems(){return maxItems;}

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public int getMaxHitPoints(){return maxHitPoints;}

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  public void giveItem(final IEquipableItem item, IUnit other){
      if(this.getLocation().isNeighbour(other.getLocation())){
          if(item==this.getEquippedItem() && other.getItems().size()<other.getMaxItems() && item!=null) {
              this.equipItem(null);
              this.items.remove(item);
              other.addItem(item);

          }
          if(this.items.contains(item) && other.getItems().size()<other.getMaxItems() && item!=null){
              this.items.remove(item);
              other.addItem(item);
          }}

  }


    @Override
    public void equipItem(final IEquipableItem item) {
      if(item==null){equippedItem=null;}
      else {item.equipTo(this);}
    }

  public void addItem(IEquipableItem item) {
    if(this.items.size()<this.maxItems && item!=null){
      this.items.add(item);
      item.setOwner(this);}}

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }
  @Override
  public boolean isInRange(IUnit other) {
      if(getEquippedItem()!=null){
      return (this.getEquippedItem().getMinRange() <= this.getLocation().distanceTo(other.getLocation()))
              && (this.getLocation().distanceTo(other.getLocation()) <= this.getEquippedItem().getMaxRange());}
      else return false; }
  @Override
  public void attack(IUnit other) {
      int vidainicial = other.getCurrentHitPoints();
      if(getCurrentHitPoints()>0 && other.getCurrentHitPoints()>0){
      if(getEquippedItem()!=null && isInRange(other)){
        getEquippedItem().attack(other);
        if(other.getEquippedItem()!=null && other.getCurrentHitPoints()>0 && vidainicial>other.getCurrentHitPoints()){other.counterAttack(this);}}
  }}

  @Override
  public void counterAttack(IUnit other) {
      if(other.getEquippedItem()!=null && other.isInRange(this)){
          getEquippedItem().attack(other);
  }}
  @Override
  public void heal(IUnit other) {
      if(getCurrentHitPoints()>0 && other.getCurrentHitPoints()>0){
      if(getEquippedItem()!=null && isInRange(other)){
          getEquippedItem().heal(other); }}
  }

  protected void receiveAttack(IEquipableItem attack) {
      if(getCurrentHitPoints()-attack.getPower()>=0){
        this.currentHitPoints -= attack.getPower();}
      else this.currentHitPoints=0;
    }

    @Override
    public void receiveAxeAttack(Axe attack){
        receiveAttack(attack);
    }

    @Override
    public void receiveBowAttack(Bow attack){
        receiveAttack(attack);
    }

    @Override
    public void receiveSpearAttack(Spear attack){
        receiveAttack(attack);
    }

    @Override
    public void receiveStaffHeal(Staff attack){
        receiveAttack(attack);
    }

    @Override
    public void receiveSwordAttack(Sword attack){
        receiveAttack(attack);
    }

    @Override
    public void receiveAnimaAttack(Anima attack){
        receiveAttack(attack);
    }

    @Override
    public void receiveOscuridadAttack(Oscuridad attack){
        receiveAttack(attack);
    }

    @Override
    public void receiveLuzAttack(Luz attack){
        receiveAttack(attack);
    }


    protected void receiveWeaknessAttack(IEquipableItem attack) {
      if(getCurrentHitPoints()-(attack.getPower()*1.5)>=0){
          this.currentHitPoints -= attack.getPower()*1.5; }
      else this.currentHitPoints=0;}


  protected void receiveResistantAttack(IEquipableItem attack) {
      if(attack.getPower() - 20 >=0 && getCurrentHitPoints()-(attack.getPower()-20)>=0){
          this.currentHitPoints -= attack.getPower() - 20; }
      else this.currentHitPoints=0; }

  protected void receiveHeal(IEquipableItem attack){
      if (getCurrentHitPoints() + attack.getPower()<=getMaxHitPoints()){
          this.currentHitPoints += attack.getPower();
      }
      else currentHitPoints=getMaxHitPoints();
  }


}

