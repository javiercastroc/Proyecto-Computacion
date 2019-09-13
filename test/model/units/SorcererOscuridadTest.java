package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 */
public class SorcererOscuridadTest extends AbstractTestUnit {

    private Sorcerer sorcererOscuridad;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        sorcererOscuridad = new Sorcerer(50, 2, field.getCell(0, 0));
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcererOscuridad;
    }

    /**
     * checks how a oscuridad spellbook is equipped
     */
    @Override
    @Test
    public void equipOscuridadTest() {
        assertNull(sorcererOscuridad.getEquippedItem());
        sorcererOscuridad.equipItem(spear);
        assertFalse(sorcererOscuridad.getItems().contains(spear));
        assertNull(sorcererOscuridad.getEquippedItem());
        sorcererOscuridad.addItem(spear);
        sorcererOscuridad.equipItem(spear);
        assertNotEquals(spear,sorcererOscuridad.getEquippedItem());
    }
    @Override
    @Test
    public void equipTest() {}
}