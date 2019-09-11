package model.items;

import model.units.IUnit;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Sword extends AbstractItem {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(final IUnit unit) {
    if (unit.getItems().contains(this) && this.getOwner()==unit){
    unit.equipSword(this);}
  }

  @Override
  public void attack(IUnit other) {
    other.receiveSwordAttack(this);
  }
  @Override
  public void heal(IUnit other) {}

  @Override
  public void oscuridadVS(AbstractItem attack) {}

  @Override
  public void luzVS(AbstractItem attack) {}

  @Override
  public void animaVS(AbstractItem attack) {}
}
