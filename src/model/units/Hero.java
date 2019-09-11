package model.units;

import model.items.*;
import model.map.Location;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Hero extends AbstractUnit {

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

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param axe
   *     the item to equip
   */
  @Override
  public void equipAxe(final Axe axe) {
    return; }

  @Override
  public void equipBow(final Bow bow) {
    return; }

  @Override
  public void equipSpear(final Spear spear) {
    equippedItem=spear; }

  @Override
  public void equipStaff(final Staff staff) {
    return; }

  @Override
  public void equipSword(final Sword sword) {
    return; }
}
