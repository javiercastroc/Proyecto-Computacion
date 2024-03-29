package model.items;

import model.units.AbstractUnit;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  void equipTo(AbstractUnit unit);


  /**
   * @return the unit that has currently equipped this item
   */
  AbstractUnit getOwner();

  /**
   * Sets the owner of this item. (sets parameter owner of this item)
   *
   * @param unit
   *     the unit that will be the owner of this item
   */
 void setOwner(AbstractUnit unit);

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();

    void use(AbstractUnit unit);

    void counterAttack(AbstractUnit unit);


    /**
     * send a message to this item; that is being attacked with an oscuridad item
     *
     * @param attack, item that is attacking
     */
    void oscuridadVS(AbstractItem attack);

    /**
     * send a message to this item; that is being attacked with a luz item
     *
     * @param attack, item that is attacking
     */
    void luzVS(AbstractItem attack);

    /**
     * send a message to this item; that is being attacked with a anima item
     *
     * @param attack, item that is attacking
     */
    void animaVS(AbstractItem attack);



}
