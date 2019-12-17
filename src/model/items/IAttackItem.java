package model.items;

import model.units.AbstractUnit;

public interface IAttackItem {
    /**
     * attack other unit with this item
     *
     * @param other unit to attack
     */
    void attack(AbstractUnit other);

}
