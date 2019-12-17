package model.items;

import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for swords
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordTest extends AbstractTestItem {

  private Sword wrongSword;
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
    expectedName = "Common sword";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 1;
    sword = new Sword(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSword = new Sword("Wrong sword", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(10, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSword;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return sword;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public AbstractUnit getTestUnit() {
    return swordMaster;
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
    sword.attack(alpaca);
    sword.attack(archer);
    sword.attack(cleric);
    sword.attack(fighter);
    sword.attack(hero);
    sword.attack(sorcererAnima);
    sword.attack(sorcererLuz);
    sword.attack(sorcererOscuridad);
    sword.attack(swordMaster);
    assertEquals(80, alpaca.getCurrentHitPoints());
    assertEquals(80, archer.getCurrentHitPoints());
    assertEquals(80, cleric.getCurrentHitPoints());
    assertEquals(70, fighter.getCurrentHitPoints());
    assertEquals(100, hero.getCurrentHitPoints());
    assertEquals(70, sorcererAnima.getCurrentHitPoints());
    assertEquals(70, sorcererLuz.getCurrentHitPoints());
    assertEquals(70, sorcererOscuridad.getCurrentHitPoints());
    assertEquals(80, swordMaster.getCurrentHitPoints());
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
    sword.luzVS(axes);
    sword.oscuridadVS(axes);
    sword.animaVS(axes);
    assertEquals(axes.getOwner(),fighter);
    assertEquals(100, fighter.getCurrentHitPoints());

  }
}
