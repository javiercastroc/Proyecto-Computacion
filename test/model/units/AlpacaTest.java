package model.units;

import model.items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {


  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0));
  }

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

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
    alpaca.addItem(axes);
    alpaca.equipItem(axes);
    assertNull(alpaca.getEquippedItem());
    alpaca.items.remove(axes);
    alpaca.addItem(sword);
    alpaca.equipItem(sword);
    assertNull(alpaca.getEquippedItem());
    alpaca.items.remove(sword);
    alpaca.addItem(spear);
    alpaca.equipItem(spear);
    assertNull(alpaca.getEquippedItem());
    alpaca.items.remove(spear);
    alpaca.addItem(staff);
    alpaca.equipItem(staff);
    assertNull(alpaca.getEquippedItem());
    alpaca.items.remove(staff);
    alpaca.addItem(bow);
    alpaca.equipItem(bow);
    assertNull(alpaca.getEquippedItem());
    alpaca.items.remove(bow);
    alpaca.addItem(anima);
    alpaca.equipItem(anima);
    assertNull(alpaca.getEquippedItem());
    alpaca.items.remove(anima);
    alpaca.addItem(luz);
    alpaca.equipItem(luz);
    assertNull(alpaca.getEquippedItem());
    alpaca.items.remove(luz);
    alpaca.addItem(oscuridad);
    alpaca.equipItem(oscuridad);
    assertNull(alpaca.getEquippedItem());
    alpaca.items.remove(oscuridad); }

    @Test
    public void mmmTest(){
    Bow bow = new Bow("Bow", 20, 2, 3);
    alpaca.mayor(bow);
    alpaca.menor(bow);
    alpaca.normal(bow);
    assertEquals(alpaca.getCurrentHitPoints(),alpaca.getMaxHitPoints());

    }







}