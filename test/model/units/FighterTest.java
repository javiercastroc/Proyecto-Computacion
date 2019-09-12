package model.units;

import model.items.*;
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
    chargerAlpaca=new Alpaca(50, 2, field.getCell(0, 1));
    fighter.addItem(axe);
    fighter.equipItem(axe);
    fighter.giveItem(axe,chargerAlpaca);
    assertFalse(fighter.getItems().contains(axe));
    assertTrue(chargerAlpaca.getItems().contains(axe));
    chargerAlpaca.addItem(sword);
    chargerAlpaca.addItem(bow);
    chargerAlpaca.addItem(staff);
    chargerAlpaca.giveItem(axe,fighter);
    chargerAlpaca.giveItem(sword,fighter);
    chargerAlpaca.giveItem(bow,fighter);
    chargerAlpaca.giveItem(staff,fighter);
    assertFalse(fighter.getItems().contains(staff));
    fighter.giveItem(null,chargerAlpaca);
  }

  @Test
  public void isInRange() {
    fighter.addItem(axe);
    fighter.equipItem(axe);
    assertTrue(fighter.isInRange(getTargetAlpaca()));
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
    fighter.addItem(sword);
    fighter.equipItem(sword);
    assertNull(fighter.getEquippedItem());
    fighter.items.remove(sword);
    fighter.addItem(spear);
    fighter.equipItem(spear);
    assertNull(fighter.getEquippedItem());
    fighter.items.remove(spear);
    fighter.addItem(staff);
    fighter.equipItem(staff);
    assertNull(fighter.getEquippedItem());
    fighter.items.remove(staff);
    fighter.addItem(bow);
    fighter.equipItem(bow);
    assertNull(fighter.getEquippedItem());
    fighter.items.remove(bow);
    fighter.addItem(anima);
    fighter.equipItem(anima);
    assertNull(fighter.getEquippedItem());
    fighter.items.remove(anima);
    fighter.addItem(luz);
    fighter.equipItem(luz);
    assertNull(fighter.getEquippedItem());
    fighter.items.remove(luz);
    fighter.addItem(oscuridad);
    fighter.equipItem(oscuridad);
    assertNull(fighter.getEquippedItem());
    fighter.items.remove(oscuridad);
    fighter.addItem(axes);
    fighter.equipItem(axes);
    assertEquals(fighter.getEquippedItem(),axes);
    fighter.items.remove(axes);}

  @Test
  public void mmmTest(){
    Bow bow = new Bow("Bow", 20, 2, 3);
    fighter.mayor(bow);
    fighter.menor(bow);
    fighter.normal(bow);
    assertEquals(fighter.getCurrentHitPoints(),fighter.getMaxHitPoints());

  }
}
