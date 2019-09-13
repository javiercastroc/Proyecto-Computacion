package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.Test;

/**
 * Test set for bows
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class BowTest extends AbstractTestItem {

  private Bow wrongBow;
  private Bow bow;
  private IUnit fighter;
  private IUnit alpaca;
  private Archer archer;
  private Cleric cleric;
  private Hero hero;
  private Sorcerer sorcererAnima;
  private Sorcerer sorcererLuz;
  private Sorcerer sorcererOscuridad;
  private SwordMaster swordMaster;
  private Axe axes;
  private Sword sword;
  private Staff staff;
  private Spear spear;
  private Luz luz;
  private Oscuridad oscuridad;
  private Anima anima;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Common bow";
    expectedPower = 20;
    expectedMinRange = 2;
    expectedMaxRange = 4;
    bow = new Bow(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongBow = new Bow("Wrong bow", 10, 1, 1);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(10, 5, new Location(0, 0));
  }

  /**
   * Checks that the minimum range for a bow is greater than 1
   */
  @Override
  @Test
  public void incorrectRangeTest() {
    assertTrue(getWrongTestItem().getMinRange() > 1);
    assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongBow;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return bow;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * check attack behavior (without combat or counterattack) against all classes of units
   *
   */
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
    bow.attack(alpaca);
    bow.attack(archer);
    bow.attack(cleric);
    bow.attack(fighter);
    bow.attack(hero);
    bow.attack(sorcererAnima);
    bow.attack(sorcererLuz);
    bow.attack(sorcererOscuridad);
    bow.attack(swordMaster);
    assertEquals(80, alpaca.getCurrentHitPoints());
    assertEquals(80, archer.getCurrentHitPoints());
    assertEquals(80, cleric.getCurrentHitPoints());
    assertEquals(80, fighter.getCurrentHitPoints());
    assertEquals(80, hero.getCurrentHitPoints());
    assertEquals(70, sorcererAnima.getCurrentHitPoints());
    assertEquals(70, sorcererLuz.getCurrentHitPoints());
    assertEquals(70, sorcererOscuridad.getCurrentHitPoints());
    assertEquals(80, swordMaster.getCurrentHitPoints());
    bow.heal(alpaca);
    assertEquals(80, alpaca.getCurrentHitPoints());
  }

  /**
   * check if methods luzVS, oscuridadVS, animaVS don't change things/parameters
   * in corresponding units (all - sorcerer)
   */
  @Test
  public void VSTest(){
    fighter=new Fighter(100, 5, new Location(0, 0));
    axes = new Axe("Axe", 20, 1, 2);
    fighter.addItem(axes);
    fighter.equipItem(axes);
    bow.luzVS(axes);
    bow.oscuridadVS(axes);
    bow.animaVS(axes);
    assertEquals(axes.getOwner(),fighter);
    assertEquals(100, fighter.getCurrentHitPoints());

  }
}
