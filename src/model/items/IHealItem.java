package model.items;

import model.units.AbstractUnit;

public interface IHealItem {

    /**
     * heals other unit with this item
     *
     * @param other unit to heal
     */
    void heal(AbstractUnit other);
}
