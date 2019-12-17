package model.units;

import model.Tactician;
import model.items.*;
import model.map.Location;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Hero extends AbstractUnit {


  public PropertyChangeSupport
          heroNotification = new PropertyChangeSupport(this);
  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Hero(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  public Hero(final int hitPoints, final int movement, final Location location, final Tactician owner,
              IEquipableItem... items) {
    super(hitPoints, movement, location, 3, owner , items);
  }
  /**
   * Sets the currently equipped item of this unit.
   *
   * @param spear
   *     the item to equip
   */
  @Override
  public void equipSpear(final Spear spear) {
    equippedItem=spear; }


  @Override
  public void receiveAxeAttack(Axe attack) {
    if (getEquippedItem() != null) {
      receiveWeaknessAttack(attack);
    }
    else{receiveAttack(attack);}
  }
  @Override
  public void receiveSwordAttack(Sword attack) {
    if (getEquippedItem() != null) {
      receiveResistantAttack(attack);
    }
    else{receiveAttack(attack);}
  }

  @Override
  public void receiveAnimaAttack(Anima attack) {
    if (getEquippedItem() != null) {
      receiveWeaknessAttack(attack);
    }
    else receiveAttack(attack);
  }

  @Override
  public void receiveLuzAttack(Luz attack) {
    if (getEquippedItem() != null) {
      receiveWeaknessAttack(attack);
    }
    else receiveAttack(attack);
  }
  @Override
  public void receiveOscuridadAttack(Oscuridad attack) {
    if (getEquippedItem() != null) {
      receiveWeaknessAttack(attack);
    }
    else receiveAttack(attack);
  }

  @Override
  public void useItem(AbstractUnit other) {this.getEquippedItem().use(other); }

  @Override
  public void died() {
    heroNotification.firePropertyChange(new PropertyChangeEvent(this, "HeroDeath", "", this));}
}
