package model.units;

import model.items.*;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Javier Castro Cuevas
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public AbstractUnit getTestUnit() {
    return cleric;
  }
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setCombat();
  }

  /**
   *
   * checks how a staff is equipped
   */
  @Test
  @Override
  public void equipStaffTest() {
    assertNull(cleric.getEquippedItem());
    cleric.equipItem(staff);
    assertFalse(cleric.getItems().contains(staff));
    assertNull(cleric.getEquippedItem());
    cleric.addItem(staff);
    cleric.equipItem(staff);
    assertEquals(staff,cleric.getEquippedItem());
  }
  /** check how method equipItem works with all kind of items
   * (for clerics)
   *
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
    cleric.addItem(axes);
    cleric.equipItem(axes);
    assertNull(cleric.getEquippedItem());
    cleric.items.remove(axes);
    cleric.addItem(sword);
    cleric.equipItem(sword);
    assertNull(cleric.getEquippedItem());
    cleric.items.remove(sword);
    cleric.addItem(spear);
    cleric.equipItem(spear);
    assertNull(cleric.getEquippedItem());
    cleric.items.remove(spear);
    cleric.addItem(bow);
    cleric.equipItem(bow);
    assertNull(cleric.getEquippedItem());
    cleric.items.remove(bow);
    cleric.addItem(anima);
    cleric.equipItem(anima);
    assertNull(cleric.getEquippedItem());
    cleric.items.remove(anima);
    cleric.addItem(luz);
    cleric.equipItem(luz);
    assertNull(cleric.getEquippedItem());
    cleric.items.remove(luz);
    cleric.addItem(oscuridad);
    cleric.equipItem(oscuridad);
    assertNull(cleric.getEquippedItem());
    cleric.items.remove(oscuridad);
    cleric.addItem(staff);
    cleric.equipItem(staff);
    assertEquals(cleric.getEquippedItem(),staff);
    cleric.items.remove(staff);}

  /**
   * checks general behavior of healing in all classes of units
   */
  @Test
  public void healTest() {
    Fighter fighter = new Fighter(100, 5, new Location(0, 1));
    Alpaca alpaca=new Alpaca(100, 5, new Location(0, 1));
    Archer archer=new Archer(100, 5, new Location(0, 1));
    Cleric clerico=new Cleric(100, 5, new Location(0, 1));
    Hero hero=new Hero(100, 5, new Location(0, 1));
    SwordMaster swordMaster=new SwordMaster(100, 5, new Location(0, 1));
    Sorcerer sorcerer=new Sorcerer(100, 5, new Location(0, 1));
    Bow bow = new Bow("Axe", 20, 1, 2);
    bow.attack(fighter);
  assertEquals(80,fighter.getCurrentHitPoints());
    bow.attack(alpaca);
    assertEquals(80,alpaca.getCurrentHitPoints());
    bow.attack(archer);
    assertEquals(80,archer.getCurrentHitPoints());
    bow.attack(clerico);
    assertEquals(80,clerico.getCurrentHitPoints());
    bow.attack(hero);
    assertEquals(80,hero.getCurrentHitPoints());
    bow.attack(swordMaster);
    assertEquals(80,swordMaster.getCurrentHitPoints());
    bow.attack(sorcerer);
    assertEquals(80,sorcerer.getCurrentHitPoints());
    cleric.addItem(staff);
    cleric.equipItem(staff);
    cleric.useItem(fighter);
    cleric.useItem(alpaca);
    cleric.useItem(archer);
    cleric.useItem(clerico);
    cleric.useItem(hero);
    cleric.useItem(swordMaster);
    cleric.useItem(sorcerer);
    assertEquals(100,fighter.getCurrentHitPoints());
    assertEquals(100,alpaca.getCurrentHitPoints());
    assertEquals(100,archer.getCurrentHitPoints());
    assertEquals(100,clerico.getCurrentHitPoints());
    assertEquals(100,hero.getCurrentHitPoints());
    assertEquals(100,swordMaster.getCurrentHitPoints());
    assertEquals(100,sorcerer.getCurrentHitPoints());
  }

  /**
   * checks healing behavior
   * checks if a unit with currentHitpints = 0 then a healing doesn't affect the unit
   * checks if healing cannot heal more life than the unit maxhitpoints
   */
  @Test
  public void subOverHealTest() {
    Fighter fighter = new Fighter(0, 5, new Location(0, 1));
    cleric.addItem(staff);
    cleric.equipItem(staff);
    cleric.useItem(fighter);
    assertEquals(0,fighter.getCurrentHitPoints());
    Bow bow = new Bow("Bow", 10, 1, 2);
    Fighter fighter1 = new Fighter(30, 5, new Location(1, 0));
    assertEquals(fighter1.getMaxHitPoints(),30);
    bow.attack(fighter1);
    assertEquals(20,fighter1.getCurrentHitPoints());
    assertTrue(cleric.isInRange(fighter1));
    cleric.useItem(fighter1);
    assertEquals(30,fighter1.getCurrentHitPoints());
  }

  /**
   * Checks that the methods mayor, normal, menor don't produce bugs
   */
  @Test
  public void mmmTest(){
    Bow bow = new Bow("Bow", 20, 2, 3);
    cleric.mayor(bow);
    cleric.menor(bow);
    cleric.normal(bow);
    assertEquals(cleric.getCurrentHitPoints(),cleric.getMaxHitPoints());

  }
}
