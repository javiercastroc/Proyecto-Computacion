package model.items;

import model.units.AbstractUnit;

public class HealItem extends AbstractItem implements IHealItem,IEquipableItem{

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
