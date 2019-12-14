package model.items;

import model.units.AbstractUnit;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Oscuridad extends AbstractItem {

    /**
     * Creates a new Axe item
     *
     * @param name
     *     the name of the Axe
     * @param power
     *     the damage of the axe
     * @param minRange
     *     the minimum range of the axe
     * @param maxRange
     *     the maximum range of the axe
     */
    public Oscuridad(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void equipTo(final AbstractUnit unit) {
        if (unit.getItems().contains(this) && this.getOwner()==unit){
        unit.equipOscuridad(this);}
    }
    @Override
    public void attack(AbstractUnit other) {
        other.receiveOscuridadAttack(this);
    }

    /**
     * the owner recieves an attack with normal damage
     * oscuridad is normal against oscuridad
     * @param attack
     */
    @Override
    public void oscuridadVS(AbstractItem attack){
        this.getOwner().normal(attack);}

    /**
     * the owner recieves an attack with high damage
     * oscuridad is weak against luz
     * @param attack
     */
    @Override
    public void luzVS(AbstractItem attack){
        this.getOwner().mayor(attack);}

    /**
     * the owner recieves an attack with low damage
     * oscuridad is strong against anima
     * @param attack
     */
    @Override
    public void animaVS(AbstractItem attack){
        (this.getOwner()).menor(attack);}

    @Override
    public void use(AbstractUnit other) { this.attack(other);}

}
