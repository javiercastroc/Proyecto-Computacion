package model.units;

import static java.lang.Math.min;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.items.*;
import model.map.Location;
import model.Tactician;

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
  private Tactician owner;
  public PropertyChangeSupport
            deathNotification = new PropertyChangeSupport(this);

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
      location.setUnit(this);
    this.maxItems = maxItems;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }

    protected AbstractUnit(final int hitPoints, final int movement,
                           final Location location, final int maxItems, final Tactician owner, final IEquipableItem... items) {
        this.maxHitPoints =hitPoints;
        this.currentHitPoints = hitPoints;
        this.movement = movement;
        this.location = location;
        location.setUnit(this);
        this.maxItems = maxItems;
        this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
        this.owner =owner;
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

  public void giveItem(final IEquipableItem item, AbstractUnit other){
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

    /**
     * Unit add to his inventory item, if possible (max container)
     * @param item object to add
     */
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
      targetLocation.setUnit(this);
    }
  }
  @Override
  public boolean isInRange(AbstractUnit other) {
      if(getEquippedItem()!=null){
      return (this.getEquippedItem().getMinRange() <= this.getLocation().distanceTo(other.getLocation()))
              && (this.getLocation().distanceTo(other.getLocation()) <= this.getEquippedItem().getMaxRange());}
      else return false; }
  @Override
  public void attack(AbstractUnit other,AttackItem item) {
      int vidainicial = other.getCurrentHitPoints();
      if(getCurrentHitPoints()>0 && other.getCurrentHitPoints()>0){
      if(getEquippedItem()==item && isInRange(other)){
        item.attack(other);
        if(other.getEquippedItem()!=null && other.getCurrentHitPoints()>0 && vidainicial>other.getCurrentHitPoints()){other.counterAttack(this);}}
  }}

  @Override
  public void counterAttack(AbstractUnit other) {
      if(this.getEquippedItem()!=null && other.isInRange(this)){
          AttackItem attackItem=new AttackItem(this.getEquippedItem().getName(),getEquippedItem().getPower(),getEquippedItem().getMinRange(),getEquippedItem().getMaxRange());
          attackItem.attack(other);
  }}
  @Override
  public void heal(AbstractUnit other,HealItem item) {
      if(getCurrentHitPoints()>0 && other.getCurrentHitPoints()>0){
      if(getEquippedItem()==item && isInRange(other)){
          item.heal(other); }}
  }
    /**
     * Receives damage from a normal attack.
     *
     * @param attack
     *     Received attack.
     */
  protected void receiveAttack(IEquipableItem attack) {
      if(getCurrentHitPoints()-attack.getPower()>=0){
        this.currentHitPoints -= attack.getPower();}
      else{ this.currentHitPoints=0;
          this.died();
      }
    }

    @Override
    public void equipAxe(final Axe axe) {}

    @Override
    public void equipBow(final Bow bow) {}

    @Override
    public void equipSpear(final Spear spear) {}

    @Override
    public void equipStaff(final Staff staff) {}

    @Override
    public void equipSword(final Sword sword) {}

    @Override
    public void equipAnima(final Anima anima) {}

    @Override
    public void equipOscuridad(final Oscuridad oscuridad) {}

    @Override
    public void equipLuz(final Luz luz) {}

    @Override
    public void normal(IEquipableItem attack) { }

    @Override
    public void mayor(IEquipableItem attack) { }

    @Override
    public void menor(IEquipableItem attack) {}

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


    /**
     * Receives an attack to which this Unit is weak
     * @param attack item, weapon that is attacking this unit
     */
    protected void receiveWeaknessAttack(IEquipableItem attack) {
      if(getCurrentHitPoints()-(attack.getPower()*1.5)>=0){
          this.currentHitPoints -= attack.getPower()*1.5; }
      else{ this.currentHitPoints=0;
        this.died();
    }}


    /**
     * Receives an attack to which this Unit is resistant
     * @param attack item, weapon that is attacking this unit
     */
  protected void receiveResistantAttack(IEquipableItem attack) {
      if(attack.getPower() - 20 >=0 && getCurrentHitPoints()-(attack.getPower()-20)>=0){
          this.currentHitPoints -= attack.getPower() - 20; }
      else {
          this.currentHitPoints = 0;
          this.died();
      }}

    /**
     * Receives heal
     * @param attack item staff
     */
  public void receiveHeal(HealItem attack){
      if (getCurrentHitPoints() + attack.getPower()<=getMaxHitPoints()){
          this.currentHitPoints += attack.getPower();
      }
      else currentHitPoints=getMaxHitPoints();
  }

    public void useItem(AbstractUnit other) { getEquippedItem().use(other);}

    public void died() {this.currentHitPoints=0;
      deathNotification.firePropertyChange(new PropertyChangeEvent(this, "Death", "", this)); }

    public Tactician getOwner(){ return owner;}

    public void setOwner(Tactician owner){this.owner=owner;}


}

