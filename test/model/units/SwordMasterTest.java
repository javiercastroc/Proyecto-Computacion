package model.units;

import model.items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ignacio Slater Muñoz
 */
public class SwordMasterTest extends AbstractTestUnit {

  private SwordMaster swordMaster;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public AbstractUnit getTestUnit() {
    return swordMaster;
  }

  /**
   * checks if a sword is equipped correctly
   */
  @Override
  public void equipSwordTest() {
    assertNull(swordMaster.getEquippedItem());
    swordMaster.equipItem(sword);
    assertEquals(sword, swordMaster.getEquippedItem());
  }

  /** check how method equipItem works with all kind of items
   * for swordmasters
   */
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
    swordMaster.addItem(axes);
    swordMaster.equipItem(axes);
    assertNull(swordMaster.getEquippedItem());
    swordMaster.items.remove(axes);
    swordMaster.addItem(spear);
    swordMaster.equipItem(spear);
    assertNull(swordMaster.getEquippedItem());
    swordMaster.items.remove(spear);
    swordMaster.addItem(staff);
    swordMaster.equipItem(staff);
    assertNull(swordMaster.getEquippedItem());
    swordMaster.items.remove(staff);
    swordMaster.addItem(bow);
    swordMaster.equipItem(bow);
    assertNull(swordMaster.getEquippedItem());
    swordMaster.items.remove(bow);
    swordMaster.addItem(anima);
    swordMaster.equipItem(anima);
    assertNull(swordMaster.getEquippedItem());
    swordMaster.items.remove(anima);
    swordMaster.addItem(luz);
    swordMaster.equipItem(luz);
    assertNull(swordMaster.getEquippedItem());
    swordMaster.items.remove(luz);
    swordMaster.addItem(oscuridad);
    swordMaster.equipItem(oscuridad);
    assertNull(swordMaster.getEquippedItem());
    swordMaster.items.remove(oscuridad);
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);
    assertEquals(swordMaster.getEquippedItem(),sword);
    swordMaster.items.remove(sword);}


  /**
   * Checks that the methods mayor, normal, menor don't produce bugs
   */
  @Test
  public void mmmTest(){
    Bow bow = new Bow("Bow", 20, 2, 3);
    swordMaster.mayor(bow);
    swordMaster.menor(bow);
    swordMaster.normal(bow);
    assertEquals(swordMaster.getCurrentHitPoints(),swordMaster.getMaxHitPoints());

  }
}
