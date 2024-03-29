package model.units;

import model.items.*;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Alpaca chargerAlpaca;
  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected Anima anima;
  protected Luz luz;
  protected Oscuridad oscuridad;
  protected Fighter fighter;
  protected Alpaca alpaca;
  protected Archer archer;
  protected Cleric cleric;
  protected Hero hero;
  protected SwordMaster swordMaster;
  protected Sorcerer sorcererLuz;
  protected Sorcerer sorcererAnima;
  protected Sorcerer sorcererOscuridad;

  /**
   * set up alpaca to test
   */
  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setCombat();
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();


  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setCombat() {
    Location location1 = new Location(0, 0);
    Location location2 = new Location(0, 1);
    location1.addNeighbour(location2);
    fighter = new Fighter(100, 5, location1);
    alpaca = new Alpaca(100, 5, location1);
    archer = new Archer(100, 5, location1);
    cleric = new Cleric(100, 5, location1);
    hero = new Hero(100, 5, location1);
    swordMaster = new SwordMaster(100, 5, location1);
    sorcererLuz = new Sorcerer(100, 5, location1);
    sorcererAnima = new Sorcerer(100, 5, location2);
    sorcererOscuridad = new Sorcerer(100, 5, location1);
    this.axe = new Axe("Axe", 20, 1, 2);
    this.sword = new Sword("Sword", 20, 1, 2);
    this.spear = new Spear("Spear", 20, 1, 2);
    this.staff = new Staff("Staff", 20, 1, 2);
    this.bow = new Bow("Bow", 20, 2, 3);
    this.anima = new Anima("Anima", 20, 1, 2);
    this.luz = new Luz("Luz", 20, 1, 2);
    this.oscuridad = new Oscuridad("Oscuridad", 20, 1, 2);
  }
  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(50, getTestUnit().getMaxHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract AbstractUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAxeTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAxe());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItem(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  /**
   * Checks if the sword is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipSwordTest() {
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  /**
   * Checks if the spear is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipSpearTest() {
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  /**
   * Checks if the staff is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipStaffTest() {
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipBowTest() {
    checkEquippedItem(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }



  /**
   * Checks if the anima spellbook is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAnimaTest() {
    checkEquippedItem(getAnima());
  }

  /**
   * @return the test anima
   */
  @Override
  public Anima getAnima() {
    return anima;
  }

  /**
   * Checks if the luz spellbook is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipLuzTest() {
    checkEquippedItem(getLuz());
  }

  /**
   * @return the test luz
   */
  @Override
  public Luz getLuz() {
    return luz;
  }

  /**
   * Checks if the oscuridad spellbook is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipOscuridadTest() {
    checkEquippedItem(getOscuridad());
  }

  /**
   * @return the test anima
   */
  @Override
  public Oscuridad getOscuridad() {
    return oscuridad;
  }



  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }

  /**
   * Checks if Unit without equipped weapon an attack doesn't make changes
   */
  @Test
  public void attackWithoutWeaponTest(){
    getTestUnit().addItem(null);
    int vidainicial=getTargetAlpaca().getCurrentHitPoints();
    getTestUnit().attack(getTargetAlpaca(),null);
    assertEquals(getTargetAlpaca().getCurrentHitPoints(),vidainicial);
  }

}
