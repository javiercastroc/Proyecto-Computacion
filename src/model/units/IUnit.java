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

  int getMaxItems();

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

  /**This unit equip an axe(if possible)
   * @param axe, item axe to equip
   */
    void equipAxe(final Axe axe);

  /**This unit equip an axe(if possible)
   * @param bow, item axe to equip
   */
   void equipBow(final Bow bow);

  /**This unit equip an axe(if possible)
   * @param spear, item axe to equip
   */
   void equipSpear(final Spear spear);

  /**This unit equip an axe(if possible)
   * @param staff, item axe to equip
   */
   void equipStaff(final Staff staff);

  /**This unit equip an axe(if possible)
   * @param sword, item axe to equip
   */
   void equipSword(final Sword sword);

  /**This unit equips anima spellbook(if possible)
   * @param anima, item anima spellbook to equip
   */
   void equipAnima(final Anima anima);

  /**This unit equips an oscuridad spellbook(if possible)
   * @param oscuridad, item oscuridad spellbook to equip
   */
   void equipOscuridad(final Oscuridad oscuridad);

  /**This unit equips a luz spellbook (if possible)
   * @param luz, item luz spellbook to equip
   */
    void equipLuz(final Luz luz);


  /**Verifies if other unit is in range according to this unit equipped item
   * @param other, other unit who
   */
   boolean isInRange(IUnit other);

  /**
   * Attack other and causes a combat(counterAttack)
   * @param other Unit that receives the attack.
   */
  void attack(IUnit other);

  /**
   * Counterattack if possible (item equipped)
   * @param other Unit that receives the attack.
   */
  void counterAttack(IUnit other);

  /**
   * Heal other's Hitpoints if possible (staff equipped)
   * @param other Unit that receives healing.
   */
  void heal(IUnit other);

  /**
   * This unit receives an axe attack
   * @param attack item: Axe
   */
  void receiveAxeAttack(Axe attack);

  /**
   * This unit receives a bow attack
   * @param attack item: Bow
   */
  void receiveBowAttack(Bow attack);

  /**
   * This unit receives a spear attack
   * @param attack item: Spear
   */
  void receiveSpearAttack(Spear attack);

  /**
   * This unit receives a staff Healing
   * @param attack item: Staff
   */
  void receiveStaffHeal(Staff attack);

  /**
   * This unit receives a sword attack
   * @param attack item: Sword
   */
  void receiveSwordAttack(Sword attack);

  /**
   * This unit receives a anima attack
   * @param attack item: Anima spellbook
   */
  void receiveAnimaAttack(Anima attack);

  /**
   * This unit receives a luz attack
   * @param attack item: Luz spellbook
   */
  void receiveLuzAttack(Luz attack);

  /**
   * This unit receives an oscuridad attack
   * @param attack item: Oscuridad spellbook
   */
  void receiveOscuridadAttack(Oscuridad attack);

  /**
   * This unit receives a normal attack
   * @param attack item: Oscuridad spellbook
   */
  void normal(IEquipableItem attack);

  /**
   * This unit receives a strong attack
   * @param attack item: Oscuridad spellbook
   */
  void mayor(IEquipableItem attack);

  /**
   * This unit receives a low attack
   * @param attack item: Oscuridad spellbook
   */
  void menor(IEquipableItem attack);

  void useItem(IUnit other);
}
