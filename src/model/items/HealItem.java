package model.items;

import model.units.AbstractUnit;

/**
 * Class that define heal items and it methods
 *
 * @author Javier Castro Cuevas
 * @since 1.0
 */
public class HealItem extends AbstractItem implements IHealItem,IEquipableItem{

    /**
     * Constructor for a healing item.
     *
     * @param name
     *     the name of the item
     * @param power
     *     the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange
     *     the minimum range of the item
     * @param maxRange
     *     the maximum range of the item
     */
    public HealItem(final String name, final int power, final int minRange, final int maxRange) {
        super(name,power,Math.max(minRange, 1),maxRange);
    }

    @Override
    public void use(AbstractUnit other) {heal(other);}


    public void heal(AbstractUnit other) {
        if(other!=this.getOwner())other.receiveHeal(this);}

        @Override
    public void counterAttack(AbstractUnit other) {}
}
