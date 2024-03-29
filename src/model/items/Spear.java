package model.items;

import model.units.AbstractUnit;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AttackItem {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(final AbstractUnit unit) {
    if (unit.getItems().contains(this) && this.getOwner()==unit){
        unit.equipSpear(this);}
  }

  @Override
  public void attack(AbstractUnit other) {
      other.receiveSpearAttack(this);
  }

}
