package model.items;

import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for staffs
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class StaffTest extends AbstractTestItem {
  private Staff wrongStaff;
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
    expectedName = "Common staff";
    expectedPower = 5;
    expectedMinRange = 1;
    expectedMaxRange = 1;
    staff = new Staff(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongStaff = new Staff("Wrong staff", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(10, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongStaff;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return staff;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public AbstractUnit getTestUnit() {
    return cleric;
  }

  @Test
  public void healTest() {
    fighter=new Fighter(100, 5, new Location(0, 0));
    alpaca=new Alpaca(100, 5, new Location(0, 0));
    archer=new Archer(100, 5, new Location(0, 0));
    cleric=new Cleric(100, 5, new Location(0, 0));
    hero=new Hero(100, 5, new Location(0, 0));
    swordMaster=new SwordMaster(100, 5, new Location(0, 0));
    sorcererLuz=new Sorcerer(100, 5, new Location(0, 0));
    sorcererAnima=new Sorcerer(100, 5, new Location(0, 0));
    sorcererOscuridad=new Sorcerer(100, 5, new Location(0, 0));
    axes = new Axe("Axe", 20, 1, 2);
    sword = new Sword("Sword", 20, 1, 2);
    spear = new Spear("Spear", 20, 1, 2);
    staff = new Staff("Staff", 20, 1, 2);
    bow = new Bow("Bow", 20, 2, 3);
    anima = new Anima("Anima", 20, 1, 2);
    luz = new Luz("Luz", 20, 1, 2);
    oscuridad = new Oscuridad("Oscuridad", 20, 1, 2);
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
    assertEquals(100, alpaca.getCurrentHitPoints());
    assertEquals(100, archer.getCurrentHitPoints());
    assertEquals(100, cleric.getCurrentHitPoints());
    assertEquals(100, fighter.getCurrentHitPoints());
    assertEquals(100, hero.getCurrentHitPoints());
    assertEquals(100, sorcererAnima.getCurrentHitPoints());
    assertEquals(100, sorcererLuz.getCurrentHitPoints());
    assertEquals(100, sorcererOscuridad.getCurrentHitPoints());
    assertEquals(100, swordMaster.getCurrentHitPoints());
    axes.attack(alpaca);
    assertEquals(80, alpaca.getCurrentHitPoints());
    staff.heal(alpaca);
    assertEquals(100, alpaca.getCurrentHitPoints());
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
    staff.luzVS(axes);
    staff.oscuridadVS(axes);
    staff.animaVS(axes);
    assertEquals(axes.getOwner(),fighter);
    assertEquals(100, fighter.getCurrentHitPoints());

  }

  @Test void combatAlpacaTest() {
    Alpaca alpaca = new Alpaca(50, 2, field.getCell(1, 1));
    cleric.addItem(staff);
    cleric.equipItem(staff);
    assertEquals(alpaca.getCurrentHitPoints(),50);
  }

}
