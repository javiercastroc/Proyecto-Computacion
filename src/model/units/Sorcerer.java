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
public class Sorcerer extends AbstractUnit {

    public Sorcerer(final int hitPoints, final int movement, final Location location,
                       IEquipableItem... items) {
        super(hitPoints, movement, location, 3, items);
    }

    /**
     * Sets the currently equipped item of this unit.
     *
     * @param luz
     *     the item to equip
     */

    @Override
    public void equipLuz(final Luz luz) {
        equippedItem=luz;}

    @Override
    public void equipOscuridad(final Oscuridad oscuridad) {
        equippedItem=oscuridad;}

    @Override
    public void equipAnima(final Anima anima) {
        equippedItem=anima;}

    /**
     * receives an oscuridad attack
     * and check whether this sorcerer has equipped an item
     * and what type is that item
     * @param attack oscuridad spellbook
     */
    @Override
    public void receiveOscuridadAttack(Oscuridad attack){
        if (getEquippedItem() != null) {
            this.getEquippedItem().oscuridadVS(attack);}
        else receiveAttack(attack);
    }

    /**
     * receives a luz attack
     * and check whether this sorcerer has equipped an item
     * and what type is that item
     * @param attack luz spellbook
     */
    @Override
    public void receiveLuzAttack(Luz attack){
        if (getEquippedItem() != null) {
            this.getEquippedItem().luzVS(attack);}
        else receiveAttack(attack);
    }

    /**
     * receives an anima attack
     * and check whether this sorcerer has equipped an item
     * and what type is that item
     * @param attack anima spellbook
     */
    @Override
    public void receiveAnimaAttack(Anima attack){
        if (getEquippedItem() != null) {
            this.getEquippedItem().animaVS(attack);}
        else receiveAttack(attack);
    }

    @Override
    public void receiveAxeAttack(Axe attack) {
        if (getEquippedItem() != null) {
            receiveWeaknessAttack(attack);
        }
        else receiveAttack(attack);
    }
    @Override
    public void receiveSpearAttack(Spear attack) {
        if (getEquippedItem() != null) {
            receiveWeaknessAttack(attack);
        }
        else receiveAttack(attack);
    }
    @Override
    public void receiveSwordAttack(Sword attack) {
        if (getEquippedItem() != null) {
            receiveWeaknessAttack(attack);
        }
        else receiveAttack(attack);
    }

    @Override
    public void receiveBowAttack(Bow attack){
        if (getEquippedItem() != null) {
            receiveWeaknessAttack(attack);
        }
        else receiveAttack(attack);
    }

    /**
     *recieves an attack with normal damage
     * @param attack
     */
    public void normal(IEquipableItem attack){
        receiveAttack(attack);
    }

    /**
     * recieves an attack with high damage
     * @param attack
     */
    public void mayor(IEquipableItem attack){
        receiveWeaknessAttack(attack);
    }
    /**
     * recieves an attack with low damage
     * @param attack
     */
    public void menor(IEquipableItem attack){
        receiveResistantAttack(attack);
    }

    @Override
    public void useItem(AbstractUnit other)  {this.getEquippedItem().use(other); }
}
