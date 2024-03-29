package model.items;

import model.units.AbstractUnit;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Axe extends AttackItem {

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
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(final AbstractUnit unit) {
    if (unit.getItems().contains(this) && this.getOwner()==unit){
    unit.equipAxe(this);}
  }
  @Override
  public void attack(AbstractUnit other) {
    other.receiveAxeAttack(this);
  }

  @Override
  public void use(AbstractUnit other) { this.attack(other);}
}
