package model.units;

import model.items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Javier Castro Cuevas
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  /**
   * checks if a spear is equipped correctly
   */
  @Override
  @Test
  public void equipSpearTest() {
    assertNull(hero.getEquippedItem());
    hero.equipItem(spear);
    assertFalse(hero.getItems().contains(spear));
    assertNull(hero.getEquippedItem());
    hero.addItem(spear);
    hero.equipItem(spear);
    assertEquals(spear,hero.getEquippedItem());
  }

  /** check how method equipItem works with all kind of items
   * for heroes
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
    hero.addItem(axes);
    hero.equipItem(axes);
    assertNull(hero.getEquippedItem());
    hero.items.remove(axes);
    hero.addItem(sword);
    hero.equipItem(sword);
    assertNull(hero.getEquippedItem());
    hero.items.remove(sword);
    hero.addItem(staff);
    hero.equipItem(staff);
    assertNull(hero.getEquippedItem());
    hero.items.remove(staff);
    hero.addItem(bow);
    hero.equipItem(bow);
    assertNull(hero.getEquippedItem());
    hero.items.remove(bow);
    hero.addItem(anima);
    hero.equipItem(anima);
    assertNull(hero.getEquippedItem());
    hero.items.remove(anima);
    hero.addItem(luz);
    hero.equipItem(luz);
    assertNull(hero.getEquippedItem());
    hero.items.remove(luz);
    hero.addItem(oscuridad);
    hero.equipItem(oscuridad);
    assertNull(hero.getEquippedItem());
    hero.items.remove(oscuridad);
    hero.addItem(spear);
    hero.equipItem(spear);
    assertEquals(hero.getEquippedItem(),spear);
    hero.items.remove(spear);}

  /**
   * Checks that the methods mayor, normal, menor don't produce bugs
   */
  @Test
  public void mmmTest(){
    Bow bow = new Bow("Bow", 20, 2, 3);
    hero.mayor(bow);
    hero.menor(bow);
    hero.normal(bow);
    assertEquals(hero.getCurrentHitPoints(),hero.getMaxHitPoints());

  }
}
