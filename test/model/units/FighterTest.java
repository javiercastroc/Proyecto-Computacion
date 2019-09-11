package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 2, field.getCell(0, 0));
  }



  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }


  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipAxeTest() {
    assertNull(fighter.getEquippedItem());
    fighter.equipItem(axe);
    assertFalse(fighter.getItems().contains(axe));
    assertNull(fighter.getEquippedItem());
    fighter.addItem(axe);
    fighter.equipItem(axe);
    assertEquals(axe,fighter.getEquippedItem());
  }

  @Test
  public void giveTest() {
    fighter.addItem(axe);
    fighter.equipItem(axe);
    fighter.giveItem(axe,targetAlpaca);
    assertFalse(fighter.getItems().contains(axe));
    assertTrue(targetAlpaca.getItems().contains(axe));
    targetAlpaca.addItem(sword);
    targetAlpaca.addItem(bow);
    targetAlpaca.addItem(staff);
    targetAlpaca.giveItem(axe,fighter);
    targetAlpaca.giveItem(sword,fighter);
    targetAlpaca.giveItem(bow,fighter);
    targetAlpaca.giveItem(staff,fighter);
    assertFalse(fighter.getItems().contains(staff));
    fighter.giveItem(null,targetAlpaca);
  }
}