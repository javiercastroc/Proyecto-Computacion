package model.items;

import model.units.AbstractUnit;
import model.units.Alpaca;
import model.units.IUnit;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Defines some common methods for all the items tests
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestItem {

  protected Alpaca targetAlpaca;
  protected Field field;
  protected String expectedName;
  protected int expectedPower;
  protected short expectedMinRange;
  protected short expectedMaxRange;

  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the items to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestItem();
    setWrongRangeItem();
    setTestUnit();
    setTargetAlpaca();
  }

  /**
   * Sets up the the space/field to test
   */
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
            new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
            new Location(2, 1), new Location(2, 2));
  }
  /**
   * Sets up a correctly implemented item that's going to be tested
   */
  public abstract void setTestItem();

  /**
   * Sets up an item with wrong ranges setted.
   */
  public abstract void setWrongRangeItem();

  /**
   * Sets the unit that will be equipped with the test item
   */
  public abstract void setTestUnit();

  /**
   * Checks that the tested item cannot have ranges outside of certain bounds.
   */
  @Test
  public void incorrectRangeTest() {
    assertTrue(getWrongTestItem().getMinRange() >= 0);
    assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
  }

  public abstract IEquipableItem getWrongTestItem();

  /**
   * Tests that the constructor for the tested item works properly
   */
  @Test
  public void constructorTest() {
    assertEquals(getExpectedName(), getTestItem().getName());
    assertEquals(getExpectedBasePower(), getTestItem().getPower());
    assertEquals(getExpectedMinRange(), getTestItem().getMinRange());
    assertEquals(getExpectedMaxRange(), getTestItem().getMaxRange());
  }

  /**
   * @return the expected name of the item
   */
  public String getExpectedName() {
    return expectedName;
  }

  /**
   * @return the item being tested
   */
  public abstract IEquipableItem getTestItem();

  /**
   * @return the expected power of the Item
   */
  public int getExpectedBasePower() {
    return expectedPower;
  }

  /**
   * @return the expected minimum range of the item
   */
  public int getExpectedMinRange() {
    return expectedMinRange;
  }

  /**
   * @return the expected maximum range of the item
   */
  public int getExpectedMaxRange() {
    return expectedMaxRange;
  }

  /**
   * Checks that the Item can be correctly equipped to a unit
   */
  @Test
  public void equippedToTest() {
    assertNull(getTestItem().getOwner());
    IUnit unit = getTestUnit();
    unit.addItem(getTestItem());
    getTestItem().equipTo(unit);
    assertEquals(unit, getTestItem().getOwner());
  }

  /**
   * Checks that the Item null not causes a bug
   */
  @Test
  public void equipNullTest(){
    assertNull(getTestItem().getOwner());
    IUnit unit = getTestUnit();
    unit.addItem(getTestItem());
    getTestItem().equipTo(unit);
    unit.equipItem(null);
    assertNull(unit.getEquippedItem());
    assertTrue(unit.getItems().contains(getTestItem()));
  }

  /**
   * Checks that the Item cannot be equipped to other iwner
   */
  @Test
  public void equipOtherTest() {
    assertNull(getTestItem().getOwner());
    IUnit unit = getTestUnit();
    targetAlpaca.addItem(getTestItem());
    getTestItem().equipTo(unit);
    assertNotEquals(unit, getTestItem().getOwner());
  }

  /**
   * Checks that the Item can equip correctly to a unit
   */
  @Test
  public void equipToTest() {
    assertNull(getTestItem().getOwner());
    IUnit unit = getTestUnit();
    unit.addItem(getTestItem());
    assertEquals(getTestItem().getOwner(),unit);
    assertTrue(unit.getItems().contains(getTestItem()));
    assertNull(unit.getEquippedItem());
    getTestItem().equipTo(getTestUnit());
    assertEquals(unit.getEquippedItem(),getTestItem());}


  /**
   * @return a unit that can equip the item being tested
   */
  public abstract IUnit getTestUnit();
}
