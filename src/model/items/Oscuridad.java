package model.items;

import model.units.AbstractUnit;
import model.units.IUnit;

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
    public void equipTo(final IUnit unit) {
        unit.equipOscuridad(this);
        this.changeOwner(unit);
    }
    @Override
    public void attack(IUnit other) {
        other.receiveOscuridadAttack(this);
    }

    @Override
    public void oscuridadVS(AbstractItem attack){
        this.getOwner().normal(attack);}

    @Override
    public void luzVS(AbstractItem attack){
        this.getOwner().mayor(attack);}

    @Override
    public void animaVS(AbstractItem attack){
        (this.getOwner()).menor(attack);}

    @Override
    public void heal(IUnit other) {}
}
