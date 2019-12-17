package model.items;

import model.units.AbstractUnit;

public class AttackItem extends AbstractItem implements IAttackItem,IEquipableItem{




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
