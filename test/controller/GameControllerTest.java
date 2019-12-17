package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import model.Tactician;
import model.map.Field;
import model.map.InvalidLocation;
import model.map.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.items.*;
import model.units.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {

  private GameController controller;
  private long randomSeed;
  private List<String> testTacticians;
  private  Random randomSeedGenerator;
  private Hero hero;
  private SwordMaster swordMaster;
  private Sword sword;
  private Spear spear;


  /**
   * Sets up the controller and seed
   * Sets up the units, weapons and map to be tested
   */
  @BeforeEach
  void setUp() {
    // Se define la semilla como un número aleatorio para generar variedad en los tests
    Location location1 =new Location(0, 0);
    controller = new GameController(4, 4);
      randomSeedGenerator=new Random(controller.getGameMap().getSize());
      randomSeed = randomSeedGenerator.nextLong();
    testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
    this.sword = new Sword("Sword", 20, 0, 3);
    this.spear = new Spear("Spear", 20, 0, 3);
      hero = new Hero(100, 5, controller.getGameMap().getCell(0, 0),controller.getTurnOwner());
      swordMaster = new SwordMaster(100, 5, controller.getGameMap().getCell(1, 1),controller.getTacticians().get(1));
  }

  /**
   * Checks if all tacticians are on the list of controller
   */
  @Test
  void getTacticians() {
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(4, tacticians.size());
    for (int i = 0; i < tacticians.size(); i++) {
      assertEquals("Player " + (i), tacticians.get(i).getName());
    }
  }


  /**
   * Checks the map and its creation
   */
  @Test
  void getGameMap() {
    Field gameMap = controller.getGameMap();
    assertEquals(4, gameMap.getSize());
    assertTrue(controller.getGameMap().isConnected());
    Field testMap =new Field();
    testMap.generateMap(gameMap.getSize());
    assertEquals(testMap.getSize(),gameMap.getSize());
  }


  /**
   * Checks how the turn owner works
   * Checks how the setting of turns work
   */
  @Test
  void getTurnOwner() {
    assertEquals(controller.getTurnOwner(),controller.getTurns().get(0));
      assertEquals(controller.getTurnOwner().getName(),testTacticians.get(0));
      ArrayList<Tactician> turnos = new ArrayList<>(controller.getTurns().size());
      turnos.addAll(controller.getTurns());
      controller.initGame(2);
      this.randomSeed=this.randomSeedGenerator.nextLong();
      Collections.shuffle(turnos,new Random(randomSeed));
      assertEquals(turnos,controller.getTurns());
      Tactician nonRep=turnos.get(3);
      for(int i=0; i<turnos.size(); i++){
          assertTrue(controller.getTurnOwner()==turnos.get(i));
          controller.endTurn();
      }
      assertTrue(nonRep!=controller.getTurnOwner());
      this.randomSeed=this.randomSeedGenerator.nextLong();
      Collections.shuffle(turnos,new Random(randomSeed));
      while(turnos.get(0)==nonRep){
        this.randomSeed=this.randomSeedGenerator.nextLong();
        Collections.shuffle(turnos,new Random(randomSeed));
      }
      assertEquals(turnos,controller.getTurns());
  }

  /**
   * Checks how the rounds and their number workds
   */
  @Test
  void getRoundNumber() {
    controller.initGame(10);
    for (int i = 1; i < 10; i++) {
      assertEquals(i, controller.getRoundNumber());
      for (int j = 0; j < 4; j++) {
        controller.endTurn();
      }
    }
  }


  /**
   * Checks the maxRounds number
   * and it winners condition
   */
  @Test
  void getMaxRounds() {
    Random randomTurnSequence = new Random();
    IntStream.range(0, 50).forEach(i -> {
      int m=Math.abs(randomTurnSequence.nextInt());
      controller.initGame(m);
      assertEquals(m, controller.getMaxRounds());
    });
    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }


  /**
   * Checks how the endTurn method work
   */
  @Test
  void endTurn() {
      Tactician firstPlayer = controller.getTurnOwner();
    Tactician secondPlayer = new Tactician("Player 1", controller.getGameMap()); // <- Deben cambiar esto (!)
    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

    controller.endTurn();
    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
  }


  /**
   * Checks the removeTactician method and checks
   * what changes when it applies
   */
  @Test
  void removeTactician() {
    assertEquals(4, controller.getTacticians().size());
    assertEquals(4, controller.getTacticians().size());
    for (int i=0;i<4;i++) {
      assertTrue(testTacticians.contains(controller.getTacticians().get(i).getName()));
    }

    controller.removeTactician("Player 0");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians().forEach(tactician -> assertNotEquals("Player 1", tactician));
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 5");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
  }

  /**
   * Checks the winners conditions
   */
  @Test
  void getWinners() {
    controller.initGame(2);
    IntStream.range(0, 8).forEach(i -> controller.endTurn());
    assertEquals(4, controller.getWinners().size());
    controller.getWinners()
        .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

    controller.initGame(2);
    IntStream.range(0, 4).forEach(i -> controller.endTurn());
    assertNull(controller.getWinners());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

    controller.initGame(-1);
    for (int i = 0; i < 2; i++) {
      assertNull(controller.getWinners());
      controller.removeTactician("Player " + i);
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
  }

  /**
   * Checks how to select units
   * and how the getSelectedUnit works
   */
  @Test
  void getSelectedUnit() {
      assertNull(controller.getSelectedUnit());
      controller.selectUnit(hero);
      assertNull(controller.getSelectedUnit());
      controller.asignUnit(swordMaster,controller.getTacticians().get(1));
      assertTrue(controller.getUnits().isEmpty());
    assertNull(controller.getOwner(hero));
      controller.asignUnit(hero,controller.getTurnOwner());
      controller.selectUnit(hero);
      assertEquals(controller.getSelectedUnit(),hero);
      controller.selectUnit(swordMaster);
      assertNotEquals(controller.getSelectedUnit(),swordMaster);
      controller.endTurn();
    controller.selectUnit(swordMaster);
    assertEquals(controller.getSelectedUnit(),swordMaster);
    assertEquals(controller.getTurnOwner(),controller.getOwner(swordMaster));

  }


  /**
   * Checks how select unit in location works
   */
  @Test
  void selectUnitIn() {
    controller.selectUnitIn(0,0);
    assertNull(controller.getSelectedUnit());
    controller.asignUnit(swordMaster,controller.getTurnOwner());
    controller.selectUnitIn(1,1);

    controller.asignUnit(hero,controller.getTacticians().get(0));
    controller.selectUnitIn(0,0);
    assertEquals(controller.getSelectedUnit(),hero);


  }
  /**
   * Checks the items work (add items and get items of unit)
   */
  @Test
  void getItems() {
    assertNull(controller.getItems());
    controller.endTurn();
    swordMaster.addItem(sword);
    controller.asignUnit(swordMaster,controller.getTurnOwner());
    controller.getItems();
    assertNull(controller.getItems());
    controller.selectUnit(swordMaster);
    assertTrue(controller.getItems().contains(sword));
    controller.asignUnit(hero,controller.getTurnOwner());
    assertFalse(controller.getItems().contains(spear));


  }
  /**
   * Checks how to equip items and it correct operation
   */
  @Test
  void equipItem() {
    controller.asignUnit(hero,controller.getTurnOwner());
    hero.addItem(sword);
    hero.addItem(spear);
    assertEquals(controller.getTurnOwner(),controller.getTacticians().get(0));
    assertNull(controller.getEquipItem());
    controller.selectUnit(hero);
    assertNull(controller.getEquipItem());
    controller.equipItem(0);
    assertNull(hero.getEquippedItem());
    assertEquals(2,controller.getItems().size());
    controller.equipItem(1);
    assertEquals(spear,controller.getEquipItem());
  }


  /**
   * Checks how use item on cell location works
   */
  @Test
  void useItemOn() {
    endTurn();
    controller.asignUnit(swordMaster,controller.getTurnOwner());
    swordMaster.addItem(sword);
    controller.selectUnit(swordMaster);
    controller.equipItem(0);
    assertEquals(sword,controller.getEquipItem());
    assertEquals(swordMaster.getEquippedItem(),controller.getEquipItem());
    assertEquals(controller.getGameMap().getCell(0,0).getUnit(),hero);
    controller.useItemOn(0,0);
    assertEquals(hero.getCurrentHitPoints(),hero.getMaxHitPoints()-sword.getPower());
  }


  /**
   * Checks how to give items and it correct perfomance
   */
  @Test
  void giveItemTo() {
    controller.asignUnit(swordMaster,controller.getTurnOwner());
    controller.asignUnit(hero,controller.getTurnOwner());
    assertTrue(controller.getUnits().contains(hero));
    hero.addItem(spear);
    controller.selectUnit(hero);
    controller.selectItem(0);
    controller.giveItemTo(1,1);
    assertFalse(swordMaster.getItems().contains(spear));
    controller.move(1,0);
    controller.giveItemTo(1,1);
    assertTrue(swordMaster.getItems().contains(spear));


  }

  /**
   * Checks how to kill unit and it correct perfomance (remove from units list)
   */
  @Test
  void killTest() {
    controller.endTurn();
    controller.asignUnit(swordMaster,controller.getTurnOwner());
    assertEquals(swordMaster.getCurrentHitPoints(),100);
    assertTrue(controller.getUnits().contains(swordMaster));
    controller.killUnit(swordMaster);
    assertEquals(0,swordMaster.getCurrentHitPoints());
    assertTrue(controller.getUnits().isEmpty());
  }

  /**
   * Checks how to move unit to target cell works
   */
  @Test
  void moveTest() {
    controller.asignUnit(hero,controller.getTurnOwner());
    controller.selectUnit(hero);
    assertSame(controller.getTurnOwner().getSelectedUnit(), hero);
    controller.move(1,1);
    assertSame(controller.getGameMap().getCell(1, 1).getUnit(), swordMaster);
    controller.move(1,0);
    assertSame(controller.getGameMap().getCell(0, 1).getUnit(), hero);
  }

  @Test
  void deathHeroTest(){
    controller.asignUnit(controller.heroFactory.create(),controller.getTurnOwner());
    controller.killUnit(controller.getUnits().get(0));
    assertFalse(controller.getUnits().contains(hero));
    assertEquals(controller.getTacticians().size(),3);
    assertEquals(controller.getTurns().get(0),controller.getTurnOwner());

  }

  /**
   * Checks how the maxRound number activate winners (win condition)
   */
  @Test
  void maxRoundTest(){
    controller.initGame(1);
    controller.endTurn();
    controller.endTurn();
    controller.endTurn();
    controller.endTurn();
    assertTrue(controller.getWinners().containsAll(testTacticians));
    controller.initGame(1);
    controller.removeTactician("Player 0");
    assertEquals(controller.getTacticians().size(),3);
    assertEquals(controller.getTurns().size(),3);
    controller.removeTactician("Player 1");
    controller.endTurn();
    controller.endTurn();
    //assertEquals(null,controller.getWinners());
    assertTrue(controller.getWinners().contains("Player 2"));
    assertTrue(controller.getWinners().contains("Player 3"));
  }


  /**
   * Checks how unit factories work and it correct performance
   */
  @Test
  void unitFactoryTest(){
    Alpaca alpacaFactory =controller.alpacaFactory.create();
    Archer archerFactory =controller.archerFactory.create();
    Cleric clericFactory =controller.clericFactory.create();
    Fighter fighterFactory =controller.fighterFactory.create();
    Hero heroFactory =controller.heroFactory.create();
    Sorcerer sorcererFactory =controller.sorcererFactory.create();
    SwordMaster swordMasterFactory =controller.swordMasterFactory.create();
    Alpaca alpacaTest =new Alpaca(100, 5,new InvalidLocation());
    Archer archerTest =new Archer(100, 5,new InvalidLocation());
    Cleric clericTest =new Cleric(100, 5,new InvalidLocation());
    Fighter fighterTest =new Fighter(100, 5,new InvalidLocation());
    Hero heroTest =new Hero(200, 5,new InvalidLocation());
    Sorcerer sorcererTest =new Sorcerer(100, 5,new InvalidLocation());
    SwordMaster swordMasterTest =new SwordMaster(100, 5,new InvalidLocation());
    unitTest(alpacaFactory,alpacaTest);
    unitTest(archerFactory,archerTest);
    unitTest(clericFactory,clericTest);
    unitTest(fighterFactory,fighterTest);
    unitTest(heroFactory,heroTest);
    unitTest(sorcererFactory,sorcererTest);
    unitTest(swordMasterFactory,swordMasterTest);
  }

  /**
   * it compare the fields of 2 units
   * @param unit1 to compare
   * @param unit2 to compare
   */
  void unitTest(AbstractUnit unit1,AbstractUnit unit2){
    assertEquals(unit1.getLocation().toString(),unit2.getLocation().toString());
    assertEquals(unit1.getMaxHitPoints(),unit2.getMaxHitPoints());
    assertEquals(unit1.getMovement(),unit2.getMovement());
    assertEquals(unit1.getClass(),unit2.getClass());
  }

  /**
   * Checks how item factories work and it correct performance
   */
  @Test
  void itemFactoryTest(){
    Anima animaFactory =controller.animaFactory.create();
    Axe axeFactory =controller.axeFactory.create();
    Bow bowFactory =controller.bowFactory.create();
    Luz luzFactory =controller.luzFactory.create();
    Oscuridad oscuridadFactory =controller.oscuridadFactory.create();
    Spear spearFactory =controller.spearFactory.create();
    Staff staffFactory =controller.staffFactory.create();
    Sword swordFactory =controller.swordFactory.create();
    Anima animaTest =new Anima("Anima", 20,1,3);
    Axe axeTest =new Axe("Axe", 20,1,3);
    Bow bowTest =new Bow("Bow", 20,2,4);
    Luz luzTest =new Luz("Luz", 20,1,3);
    Oscuridad oscuridadTest =new Oscuridad("Oscuridad", 20,1,3);
    Spear spearTest =new Spear("Spear", 20,1,3);
    Staff staffTest =new Staff("Staff", 20,1,3);
    Sword swordTest =new Sword("Sword", 20,1,3);

    itemTest(animaFactory,animaTest);
    itemTest(axeFactory,axeTest);
    itemTest(bowFactory,bowTest);
    itemTest(luzFactory,luzTest);
    itemTest(oscuridadFactory,oscuridadTest);
    itemTest(spearFactory,spearTest);
    itemTest(staffFactory,staffTest);
    itemTest(swordFactory,swordTest);
  }


  /**
   * it compare the fields of 2 items
   * @param item1 to compare
   * @param item2 to compare
   */
  void itemTest(AbstractItem item1, AbstractItem item2){
    assertEquals(item1.getName(),item2.getName());
    assertEquals(item1.getOwner(),item1.getOwner());
    assertEquals(item1.getMinRange(),item2.getMinRange());
    assertEquals(item1.getMaxRange(),item2.getMaxRange());
    assertEquals(item1.getPower(),item2.getPower());
  }

}