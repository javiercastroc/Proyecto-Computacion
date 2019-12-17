package model.units;

import model.Tactician;
import model.items.*;
import model.map.Location;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  public SwordMaster(final int hitPoints, final int movement, final Location location,
                        IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }
  public SwordMaster(final int hitPoints, final int movement, final Location location, final Tactician owner,
                     IEquipableItem... items) {
    super(hitPoints, movement, location, 3,owner, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param sword
   *     the item to equip
   */
  @Override
  public void equipSword(final Sword sword) {
    equippedItem=sword; }

  @Override
  public void receiveAxeAttack(Axe attack) {
    if (getEquippedItem() != null) {
      receiveResistantAttack(attack);
    }
  }
  @Override
  public void receiveSpearAttack(Spear attack) {
    if (getEquippedItem() != null) {
      receiveWeaknessAttack(attack);
    }
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
}
