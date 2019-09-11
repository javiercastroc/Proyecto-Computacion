package model.units;

import model.items.*;
import model.map.Location;

/**
 * This class represents an <i>Alpaca</i> type unit.
 * <p>
 * This are a special kind of unit that can carry an unlimited amount of items but can't use any of
 * them.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Alpaca extends AbstractUnit {

  /**
   * Creates a new Alpaca.
   *
   * @param hitPoints
   *     the amount of damage this unit can receive
   * @param movement
   *     number of cells the unit can move
   * @param location
   *     current position of the unit
   */
  public Alpaca(final int hitPoints, final int movement, final Location location,
      final IEquipableItem... items) {
    super(hitPoints, movement, location, Integer.MAX_VALUE, items);
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Alpaca</i> cannot equip any item.
   */
  @Override
  public void equipAxe(final Axe axe) {}

  @Override
  public void equipBow(final Bow bow) { }

  @Override
  public void equipSpear(final Spear spear) { }

  @Override
  public void equipStaff(final Staff staff) {}

  @Override
  public void equipSword(final Sword sword) {}

  @Override
  public void receiveStaffHeal(Staff attack) {
    receiveHeal(attack);
  }


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
}
