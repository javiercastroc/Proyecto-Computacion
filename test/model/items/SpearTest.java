package model.items;

import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test set for spears
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SpearTest extends AbstractTestItem {

  private Spear javelin;
  private Spear wrongSpear;
  private Bow bow;
  private AbstractUnit fighter;
  private AbstractUnit alpaca;
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
    expectedName = "Javelin";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 3;
    javelin = new Spear(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSpear = new Spear("Wrong spear", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(10, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSpear;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return javelin;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public AbstractUnit getTestUnit() {
    return hero;
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
    spear.attack(alpaca);
    spear.attack(archer);
    spear.attack(cleric);
    spear.attack(fighter);
    spear.attack(hero);
    spear.attack(sorcererAnima);
    spear.attack(sorcererLuz);
    spear.attack(sorcererOscuridad);
    spear.attack(swordMaster);
    assertEquals(80, alpaca.getCurrentHitPoints());
    assertEquals(80, archer.getCurrentHitPoints());
    assertEquals(80, cleric.getCurrentHitPoints());
    assertEquals(100, fighter.getCurrentHitPoints());
    assertEquals(80, hero.getCurrentHitPoints());
    assertEquals(70, sorcererAnima.getCurrentHitPoints());
    assertEquals(70, sorcererLuz.getCurrentHitPoints());
    assertEquals(70, sorcererOscuridad.getCurrentHitPoints());
    assertEquals(70, swordMaster.getCurrentHitPoints());
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
    javelin.luzVS(axes);
    javelin.oscuridadVS(axes);
    javelin.animaVS(axes);
    assertEquals(axes.getOwner(),fighter);
    assertEquals(100, fighter.getCurrentHitPoints());

  }
  /**
   * check attack behavior with combat/counterattack against all classes of units (equipped)
   */
  @Test
  public void combatTest() {
    Location location1 = new Location(0, 0);
    Location location2 = new Location(0, 1);
    location1.addNeighbour(location2);
    fighter=new Fighter(100, 5, location1);
    alpaca=new Alpaca(100, 5, location1);
    archer=new Archer(100, 5, location1);
    cleric=new Cleric(100, 5, location1);
    hero=new Hero(200, 5, location2);
    swordMaster=new SwordMaster(100, 5, location1);
    sorcererLuz=new Sorcerer(100, 5, location1);
    sorcererAnima=new Sorcerer(100, 5, location1);
    sorcererOscuridad=new Sorcerer(100, 5, location1);
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
    hero.useItem(alpaca);
    assertEquals(hero.getLocation().distanceTo(alpaca.getLocation()),1);
    assertTrue(hero.isInRange(alpaca));
    assertEquals(80, alpaca.getCurrentHitPoints());
    assertEquals(200, hero.getCurrentHitPoints());
    hero.useItem(archer);
    assertEquals(80, archer.getCurrentHitPoints());
    assertEquals(200, hero.getCurrentHitPoints());
    hero.useItem(cleric);
    assertEquals(80, cleric.getCurrentHitPoints());
    assertEquals(200, hero.getCurrentHitPoints());
    hero.useItem(fighter);
    assertEquals(100, fighter.getCurrentHitPoints());
    assertEquals(200, hero.getCurrentHitPoints());
    hero.useItem(hero);
    assertEquals(200, hero.getCurrentHitPoints());
    hero.useItem(sorcererAnima);
    assertEquals(70, sorcererAnima.getCurrentHitPoints());
    assertEquals(200, hero.getCurrentHitPoints());
    hero.useItem(sorcererLuz);
    assertEquals(70, sorcererLuz.getCurrentHitPoints());
    assertEquals(200, hero.getCurrentHitPoints());
    hero.useItem(sorcererOscuridad);
    assertEquals(70, sorcererOscuridad.getCurrentHitPoints());
    assertEquals(200, hero.getCurrentHitPoints());
    hero.useItem(swordMaster);
    assertEquals(70, swordMaster.getCurrentHitPoints());
    assertEquals(200, hero.getCurrentHitPoints());
  }

}
