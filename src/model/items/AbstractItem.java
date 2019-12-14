package model.items;

import model.units.AbstractUnit;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private final String name;
  private final int power;
  protected int maxRange;
  protected int minRange;
  private AbstractUnit owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  public AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  @Override
  public void equipTo(final AbstractUnit unit) {
      if(this.getOwner()==unit && unit.getItems().contains(this)){
    unit.equipItem(this);}
  }

  @Override
  public AbstractUnit getOwner() {
    return owner;
  }

  @Override
  public void setOwner(final AbstractUnit unit) {
    owner=unit;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public int getMinRange() {
    return minRange;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }
  @Override
  public void heal(AbstractUnit other) {}

  @Override
  public void oscuridadVS(AbstractItem attack) { }

  @Override
  public void luzVS(AbstractItem attack) { }

  @Override
  public void animaVS(AbstractItem attack) {}

  @Override
  public void attack(AbstractUnit other) { }

  @Override
  public void use(AbstractUnit other) {}
}
