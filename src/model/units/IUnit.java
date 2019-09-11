package model.units;

import java.util.List;

import model.items.*;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);

  /**
   * @return hit points of the unit
   */
  int getCurrentHitPoints();

  int getMaxHitPoints();

  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  void giveItem(IEquipableItem item, IUnit unit);

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  void addItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

    void equipAxe(final Axe axe);

   void equipBow(final Bow bow);

   void equipSpear(final Spear spear);

   void equipStaff(final Staff staff);

   void equipSword(final Sword sword);

   void equipAnima(final Anima anima);

   void equipOscuridad(final Oscuridad oscuridad);

    void equipLuz(final Luz luz);

   boolean isInRange(IUnit other);

  void attack(IUnit other);

  void counterAttack(IUnit other);

  void heal(IUnit other);

  void receiveAxeAttack(Axe attack);

  void receiveBowAttack(Bow attack);

  void receiveSpearAttack(Spear attack);

  void receiveStaffHeal(Staff attack);

  void receiveSwordAttack(Sword attack);

  void receiveAnimaAttack(Anima attack);

  void receiveLuzAttack(Luz attack);

  void receiveOscuridadAttack(Oscuridad attack);

  void normal(IEquipableItem attack);

  void mayor(IEquipableItem attack);

  void menor(IEquipableItem attack);


}
