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
  public void equipAxe(final Axe axe) {
    return; }

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
    equippedItem=sword; }
}
