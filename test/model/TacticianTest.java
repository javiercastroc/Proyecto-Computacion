package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import controller.GameController;
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
 * @author Javier Castro
 * @since v2.0
 */
public class TacticianTest {
    private GameController controller;

    @BeforeEach
    void setUp() {
        // Se define la semilla como un número aleatorio para generar variedad en los tests
        controller = new GameController(2, 4);
    }

    @Test
    void tacticianCreationTest(){
        Tactician tactician0 = new Tactician("Player 0", controller.getGameMap());
        Tactician tactician1 = new Tactician("Player 1", controller.getGameMap());
        assertEquals(tactician0.getName(),controller.getTacticians().get(0).getName());
        assertEquals(tactician1.getName(),controller.getTacticians().get(1).getName());
        assertEquals(tactician0.getMap(),controller.getTacticians().get(0).getMap());
        assertEquals(tactician1.getMap(),controller.getTacticians().get(1).getMap());
        assertEquals(tactician1.getUnits(),controller.getTacticians().get(1).getUnits());
    }

    @Test
    void asignUnitTest(){
        Alpaca alpaca=controller.alpacaFactory.create();
        Tactician tactician=controller.getTacticians().get(0);
        controller.asignUnit(alpaca,tactician);
        assertEquals(alpaca.getOwner(),controller.getTacticians().get(0));
        assertTrue(tactician.getUnits().contains(alpaca));
        assertTrue(tactician.isMyTurn());
        controller.endTurn();
        assertTrue(controller.getTacticians().get(1).isMyTurn());
    }


    @Test
    void moveTest(){
        Alpaca alpaca=controller.alpacaFactory.create();
        controller.setLocation(0,1,alpaca);
        assertEquals(alpaca.getLocation(),controller.getGameMap().getCell(1,0));
        controller.asignUnit(alpaca,controller.tactician(0));
        controller.tactician(0).setTurn();
        controller.getTurns().get(0).selectUnit(alpaca);
        assertTrue(controller.getTurns().get(0).getNonMoved().contains(alpaca));
        controller.getTurns().get(0).move(0,0);
        assertEquals(alpaca.getLocation(),controller.getGameMap().getCell(0,0));
    }

@Test
    void giveItemTest(){
        Alpaca alpaca=controller.alpacaFactory.create();
        Archer archer=controller.archerFactory.create();
        Cleric cleric=controller.clericFactory.create();
        Axe axe=controller.axeFactory.create();
        controller.addItemTo(axe,alpaca);
        controller.asignUnit(cleric,controller.tactician(0));
        controller.asignUnit(alpaca,controller.tactician(0));
        controller.asignUnit(archer,controller.tactician(1));
        controller.setLocation(0,0,alpaca);
        controller.setLocation(1,0,archer);
        assertTrue(alpaca.getItems().contains(axe));
        assertTrue(controller.tactician(0)==alpaca.getOwner());
        controller.tactician(0).selectUnit(alpaca);
        assertEquals(controller.tactician(0).indexSelected(),1);
        assertTrue(controller.tactician(0).getSelectedUnit()==alpaca);
        controller.tactician(0).giveItem(axe,archer);
        assertTrue(archer.getItems().contains(axe));

    }
    @Test
    void equipItemTest(){
        Archer archer=controller.archerFactory.create();
        Bow bow=controller.bowFactory.create();
        controller.addItemTo(bow,archer);
        controller.asignUnit(archer,controller.tactician(0));
        controller.tactician(0).selectUnit(controller.tactician(0).getUnits().get(0));
        assertTrue(controller.tactician(0).getInventory().contains(bow));
        controller.tactician(0).equip(bow);
        assertEquals(bow,archer.getEquippedItem());
    }

    @Test
 void getInfoTest(){
        Archer archer=controller.archerFactory.create();
        Bow bow=controller.bowFactory.create();
        controller.addItemTo(bow,archer);
        controller.asignUnit(archer,controller.tactician(0));
        controller.tactician(0).selectUnit(archer);
        assertEquals(controller.tactician(0).getMaxHitPoints(),archer.getMaxHitPoints());
        assertEquals(controller.tactician(0).getCurrentHitPoints(),archer.getCurrentHitPoints());
        assertEquals(controller.tactician(0).getPower(bow),bow.getPower());

    }

    @Test
    void passTurnTest(){
        controller.tactician(0).passTurn();
        assertEquals(controller.getTurnOwner(),controller.tactician(1));
    }


    @Test
    void combatTest(){
        Archer archer=controller.archerFactory.create();
        Bow bow=controller.bowFactory.create();
        controller.addItemTo(bow,archer);
        controller.asignUnit(archer,controller.tactician(0));
        controller.tactician(0).selectUnit(controller.tactician(0).getUnits().get(0));
        assertTrue(controller.tactician(0).getInventory().contains(bow));
        controller.tactician(0).equip(bow);
        controller.setLocation(0,0,archer);
        controller.tactician(0).passTurn();
        Fighter fighter=controller.fighterFactory.create();
        Axe axe=controller.axeFactory.create();
        controller.addItemTo(axe,fighter);
        controller.asignUnit(fighter,controller.getTurns().get(1));
        controller.getTurns().get(1).selectUnit(fighter);
        assertTrue(controller.getTurns().get(1).getInventory().contains(axe));
        controller.getTurns().get(1).equip(axe);
        controller.setLocation(1,1,fighter);
        controller.getTurns().get(1).useItemOn(archer);
        assertEquals(fighter.getCurrentHitPoints(),fighter.getMaxHitPoints());
        assertEquals(archer.getCurrentHitPoints(),archer.getMaxHitPoints());
        controller.getTurns().get(1).passTurn();
        controller.initGame(2);
        controller.getTurnOwner().selectUnit(controller.getTurnOwner().getUnits().get(0));
        assertTrue(controller.getTurnOwner().getSelectedUnit()!=null);
        controller.getTurnOwner().useItemOn(archer);
        assertEquals(80,archer.getCurrentHitPoints());
    }

}

