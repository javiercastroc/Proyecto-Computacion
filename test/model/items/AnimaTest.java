package model.items;

import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test set for Axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
class AnimaTest extends AbstractTestItem {

    private Anima anima;
    private Anima wrongAnima;
    private IUnit fighter;
    private IUnit alpaca;
    private Archer archer;
    private Cleric cleric;
    private Hero hero;
    private Sorcerer sorcererAnima;
    private Sorcerer sorcererLuz;
    private Sorcerer sorcererOscuridad;
    private SwordMaster swordMaster;
    private Bow bow;
    private Axe axes;
    private Sword sword;
    private Staff staff;
    private Spear spear;
    private Luz luz;
    private Oscuridad oscuridad;

    @Override
    public void setTestItem() {
        expectedName = "Common anima";
        expectedPower = 20;
        expectedMinRange = 1;
        expectedMaxRange = 5;
        anima = new Anima(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongAnima = new Anima("Wrong anima", 0, -1, -2);
    }

    /**
     * Sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit() {
        sorcererAnima = new Sorcerer(10, 5, new Location(0, 0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongAnima;
    }

    @Override
    public IEquipableItem getTestItem() {
        return anima;
    }

    /**
     * @return a unit that can equip the item being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcererAnima;
    }

    @Test
    public void attackTest() {

        fighter=new Fighter(100, 5, new Location(0, 0));
        alpaca=new Alpaca(100, 5, new Location(0, 0));
        archer=new Archer(100, 5, new Location(0, 0));
        cleric=new Cleric(100, 5, new Location(0, 0));
        hero=new Hero(100, 5, new Location(0, 0));
        swordMaster=new SwordMaster(100, 5, new Location(0, 0));
        sorcererLuz=new Sorcerer(100, 5, new Location(0, 0));
        sorcererAnima=new Sorcerer(100, 5, new Location(0, 0));
        sorcererOscuridad=new Sorcerer(100, 5, new Location(0, 0));
        this.axes = new Axe("Axe", 20, 1, 2);
        this.sword = new Sword("Sword", 20, 1, 2);
        this.spear = new Spear("Spear", 20, 1, 2);
        this.staff = new Staff("Staff", 20, 1, 2);
        this.bow = new Bow("Bow", 20, 2, 3);
        this.anima = new Anima("Anima", 20, 1, 2);
        this.luz = new Luz("Luz", 20, 1, 2);
        this.oscuridad = new Oscuridad("Oscuridad", 20, 1, 2);
        fighter.addItem(axes);
        archer.addItem(bow);
        hero.addItem(spear);
        cleric.addItem(staff);
        swordMaster.addItem(sword);
        sorcererLuz.addItem(luz);
        sorcererAnima.addItem(anima);
        sorcererOscuridad.addItem(oscuridad);
        fighter.equipItem(axes);
        archer.equipItem(bow);
        cleric.equipItem(staff);
        hero.equipItem(spear);
        swordMaster.equipItem(sword);
        sorcererLuz.equipItem(luz);
        sorcererAnima.equipItem(anima);
        sorcererOscuridad.equipItem(oscuridad);
        anima.attack(alpaca);
        anima.attack(archer);
        anima.attack(cleric);
        anima.attack(fighter);
        anima.attack(hero);
        anima.attack(sorcererAnima);
        anima.attack(sorcererLuz);
        anima.attack(sorcererOscuridad);
        anima.attack(swordMaster);
        assertEquals(80, alpaca.getCurrentHitPoints());
        assertEquals(70, archer.getCurrentHitPoints());
        assertEquals(70, cleric.getCurrentHitPoints());
        assertEquals(70, fighter.getCurrentHitPoints());
        assertEquals(70, hero.getCurrentHitPoints());
        assertEquals(80, sorcererAnima.getCurrentHitPoints());
        assertEquals(70, sorcererLuz.getCurrentHitPoints());
        assertEquals(100, sorcererOscuridad.getCurrentHitPoints());
        assertEquals(70, swordMaster.getCurrentHitPoints());
        anima.heal(alpaca);
        assertEquals(80, alpaca.getCurrentHitPoints());
    }


    @Test void combatAlpacaTest() {
        Alpaca alpacaa = new Alpaca(50, 2, field.getCell(0, 1));
        Sorcerer sorc  = new Sorcerer(50, 2, field.getCell(0, 0));
        Anima animas = new Anima("Animas", 20, 1, 5);
        sorc.addItem(animas);
        sorc.equipItem(animas);
        sorc.attack(alpacaa);
        assertEquals(sorc.getEquippedItem(),animas);
        assertTrue(sorc.getLocation().isNeighbour(alpacaa.getLocation()));
        assertEquals(alpacaa.getMaxHitPoints()-getExpectedBasePower(),alpacaa.getCurrentHitPoints());
    }
}
