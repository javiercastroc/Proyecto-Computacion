package model.units;

import model.Tactician;
import model.items.*;
import model.map.Location;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Javier Castro
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  public Fighter(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  public Fighter(final int hitPoints, final int movement, final Location location, final Tactician owner,
                     IEquipableItem... items) {
    super(hitPoints, movement, location, 3,owner, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param axe
   *     the item to equip
   */


  public void equipAxe(final Axe axe) {
        equippedItem =axe; }

  @Override
  public void receiveSpearAttack(Spear attack) {
      if (getEquippedItem() != null) {
          receiveResistantAttack(attack);
      }
      else{receiveAttack(attack);}
  }
  @Override
  public void receiveSwordAttack(Sword attack) {
      if (getEquippedItem() != null) {
          receiveWeaknessAttack(attack);
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
  public void useItem(AbstractUnit other)  {this.getEquippedItem().use(other); }
}
