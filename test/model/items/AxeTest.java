package model.items;


import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test set for Axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
class AxeTest extends AbstractTestItem {

  private Axe axe;
  private Axe wrongAxe;
  private AbstractUnit fighter;
  private AbstractUnit alpaca;
  private Archer archer;
  private Cleric cleric;
  private Hero hero;
  private Sorcerer sorcererAnima;
  private Sorcerer sorcererLuz;
  private Sorcerer sorcererOscuridad;
  private SwordMaster swordMaster;
  private Bow bow;
  private Axe axes;
  private Sword sword;
  private Staff staff;
  private Spear spear;
  private Anima anima;
  private Luz luz;
  private Oscuridad oscuridad;


  @Override
  public void setTestItem() {
    expectedName = "Common axe";
    expectedPower = 20;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    axe = new Axe(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongAxe = new Axe("Wrong axe", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(100, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongAxe;
  }

  @Override
  public IEquipableItem getTestItem() {
    return axe;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public AbstractUnit getTestUnit() {
    return fighter;
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
    swordMaster.addItem(sword);
    sorcererLuz.addItem(luz);
    sorcererAnima.addItem(anima);
    sorcererOscuridad.addItem(oscuridad);
    fighter.equipItem(axes);
    archer.equipItem(bow);
    hero.equipItem(spear);
    swordMaster.equipItem(sword);
    sorcererLuz.equipItem(luz);
    sorcererAnima.equipItem(anima);
    sorcererOscuridad.equipItem(oscuridad);
    axe.attack(alpaca);
    axe.attack(archer);
    axe.attack(cleric);
    axe.attack(fighter);
    axe.attack(hero);
    axe.attack(sorcererAnima);
    axe.attack(sorcererLuz);
    axe.attack(sorcererOscuridad);
    axe.attack(swordMaster);
    assertEquals(80, alpaca.getCurrentHitPoints());
    assertEquals(80, archer.getCurrentHitPoints());
    assertEquals(80, cleric.getCurrentHitPoints());
    assertEquals(80, fighter.getCurrentHitPoints());
    assertEquals(70, hero.getCurrentHitPoints());
    assertEquals(70, sorcererAnima.getCurrentHitPoints());
    assertEquals(70, sorcererLuz.getCurrentHitPoints());
    assertEquals(70, sorcererOscuridad.getCurrentHitPoints());
    assertEquals(100, swordMaster.getCurrentHitPoints());
    axe.heal(alpaca);
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
    axe.luzVS(axes);
    axe.oscuridadVS(axes);
    axe.animaVS(axes);
    assertEquals(axes.getOwner(),fighter);
    assertEquals(100, fighter.getCurrentHitPoints());

  }
}