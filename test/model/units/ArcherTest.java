package model.units;

import model.items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public AbstractUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipBowTest() {
    assertNull(archer.getEquippedItem());
    archer.equipItem(bow);
    assertFalse(archer.getItems().contains(bow));
    assertNull(archer.getEquippedItem());
    archer.addItem(axe);
    archer.addItem(bow);
    archer.equipItem(axe);
    assertNull(archer.getEquippedItem());
    archer.equipItem(bow);
    assertEquals(bow,archer.getEquippedItem());
  }

  /**
   * checks minimum range of bow
   */
  @Test
  public void lowRangeTest() {
    archer.addItem(bow);
    archer.equipItem(bow);
    assertFalse(archer.isInRange(getTargetAlpaca()));
  }

  /** check how method equipItem works with all kind of items
   * for archers
   */
  @Override
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
    archer.addItem(axes);
    archer.equipItem(axes);
    assertNull(archer.getEquippedItem());
    archer.items.remove(axes);
    archer.addItem(sword);
    archer.equipItem(sword);
    assertNull(archer.getEquippedItem());
    archer.items.remove(sword);
    archer.addItem(spear);
    archer.equipItem(spear);
    assertNull(archer.getEquippedItem());
    archer.items.remove(spear);
    archer.addItem(staff);
    archer.equipItem(staff);
    assertNull(archer.getEquippedItem());
    archer.items.remove(staff);
    archer.addItem(bow);
    archer.equipItem(bow);
    assertEquals(archer.getEquippedItem(),bow);
    archer.items.remove(bow);
    archer.equippedItem=null;
    archer.addItem(anima);
    archer.equipItem(anima);
    assertNull(archer.getEquippedItem());
    archer.items.remove(anima);
    archer.addItem(luz);
    archer.equipItem(luz);
    assertNull(archer.getEquippedItem());
    archer.items.remove(luz);
    archer.addItem(oscuridad);
    archer.equipItem(oscuridad);
    assertNull(archer.getEquippedItem());
    archer.items.remove(oscuridad);}

  /**
   * Checks that the methods mayor, normal, menor don't produce bugs
   */
  @Test
  public void mmmTest(){
    Bow bow = new Bow("Bow", 20, 2, 3);
    archer.mayor(bow);
    archer.menor(bow);
    archer.normal(bow);
    assertEquals(archer.getCurrentHitPoints(),archer.getMaxHitPoints());

  }
}