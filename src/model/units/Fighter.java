package model.units;

import model.items.*;
import model.map.Location;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  public Fighter(final int hitPoints, final int movement, final Location location,
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
  public void equipAxe(final Axe axe) {
        equippedItem =axe; }

  @Override
  public void equipBow(final Bow bow) {
        return; }

  @Override
  public void equipSpear(final Spear spear) {
        return; }

  @Override
  public void equipStaff(final Staff staff) {
        return; }

  @Override
    public void equipSword(final Sword sword) {
        return;
  }
  @Override
  public void receiveSpearAttack(Spear attack) {
      if (getEquippedItem() != null) {
          receiveResistantAttack(attack);
      }
  }
  @Override
  public void receiveSwordAttack(Sword attack) {
      if (getEquippedItem() != null) {
          receiveWeaknessAttack(attack);
      }
  }
  @Override
  public void receiveStaffHeal(Staff attack) {
      receiveHeal(attack);
    }


}
