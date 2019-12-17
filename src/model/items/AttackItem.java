package model.items;

import model.units.AbstractUnit;

/**
 * Class that define attack items and it methods
 *
 * @author Javier Castro Cuevas
 * @since 1.0
 */
public class AttackItem extends AbstractItem implements IAttackItem,IEquipableItem{



    /**
     * Constructor for an attack item.
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
    public AttackItem(final String name, final int power, final int minRange, final int maxRange) {
        super(name,power,Math.max(minRange, 1),maxRange);
    }



    @Override
    public void attack(AbstractUnit other) { }

    @Override
    public void use(AbstractUnit other) {if(other!=this.getOwner())attack(other);}

    @Override
    public void counterAttack(AbstractUnit other){attack(other);}



}
