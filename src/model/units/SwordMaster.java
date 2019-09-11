package model.units;

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

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param axe
   *     the item to equip
   */
  @Override
  public void equipAxe(final Axe axe) {}

  @Override
  public void equipBow(final Bow bow) {}

  @Override
  public void equipSpear(final Spear spear) {}

  @Override
  public void equipStaff(final Staff staff) {}

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
