package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 */
public class SorcererLuzTest extends AbstractTestUnit {

    private Sorcerer sorcererLuz;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        sorcererLuz = new Sorcerer(50, 2, field.getCell(0, 0));
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcererLuz;
    }

    @Override
    @Test
    public void equipLuzTest() {
        assertNull(sorcererLuz.getEquippedItem());
        sorcererLuz.equipItem(luz);
        assertFalse(sorcererLuz.getItems().contains(luz));
        assertNull(sorcererLuz.getEquippedItem());
        sorcererLuz.addItem(luz);
        sorcererLuz.equipItem(luz);
        assertEquals(luz,sorcererLuz.getEquippedItem());
    }
}