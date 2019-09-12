package model.units;

import model.items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 */
public class SorcererAnimaTest extends AbstractTestUnit {

    private Sorcerer sorcererAnima;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        sorcererAnima = new Sorcerer(50, 2, field.getCell(0, 0));
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcererAnima;
    }

    @Override
    @Test
    public void equipAnimaTest() {
        assertNull(sorcererAnima.getEquippedItem());
        sorcererAnima.equipItem(anima);
        assertFalse(sorcererAnima.getItems().contains(anima));
        assertNull(sorcererAnima.getEquippedItem());
        sorcererAnima.addItem(anima);
        sorcererAnima.equipItem(anima);
        assertEquals(anima,sorcererAnima.getEquippedItem());
    }
    @Test
    public void equipTest() {
        Axe axes;
        Sword sword;
        Staff staff;
        Spear spear;
        Luz luz;
        Oscuridad oscuridad;
        Anima anima;
        Bow bow;
        axes = new Axe("Axe", 20, 1, 2);
        sword = new Sword("Sword", 20, 1, 2);
        spear = new Spear("Spear", 20, 1, 2);
        staff = new Staff("Staff", 20, 1, 2);
        bow = new Bow("Bow", 20, 2, 3);
        anima = new Anima("Anima", 20, 1, 2);
        luz = new Luz("Luz", 20, 1, 2);
        oscuridad = new Oscuridad("Oscuridad", 20, 1, 2);
        sorcererAnima.addItem(axes);
        sorcererAnima.equipItem(axes);
        assertNull(sorcererAnima.getEquippedItem());
        sorcererAnima.items.remove(axes);
        sorcererAnima.addItem(sword);
        sorcererAnima.equipItem(sword);
        assertNull(sorcererAnima.getEquippedItem());
        sorcererAnima.items.remove(sword);
        sorcererAnima.addItem(spear);
        sorcererAnima.equipItem(spear);
        assertNull(sorcererAnima.getEquippedItem());
        sorcererAnima.items.remove(spear);
        sorcererAnima.addItem(staff);
        sorcererAnima.equipItem(staff);
        assertNull(sorcererAnima.getEquippedItem());
        sorcererAnima.items.remove(staff);
        sorcererAnima.addItem(bow);
        sorcererAnima.equipItem(bow);
        assertNull(sorcererAnima.getEquippedItem());
        sorcererAnima.items.remove(bow);}

}