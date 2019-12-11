package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;
import model.handlers.AttackHandler;
import model.handlers.PassTurnHandler;
import java.util.Collections;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController {

  private List<Tactician> tacticians = new ArrayList<>();
  private Tactician[] users;
  private Field map;
  private Tactician turn;
  private int roundNumber;
  private int maxRounds;
  private IUnit selectedUnit;
  private List<Tactician> turns = new ArrayList<>();
  private IEquipableItem item;
  private Boolean initedGame;
  private List<String> winners = new ArrayList<>();
  public PropertyChangeSupport
          limitRoundsNotification = new PropertyChangeSupport(this);


  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers the number of players for this game
   * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
   */
  GameController(int numberOfPlayers, int mapSize) {
    this.limitRoundsNotification.addPropertyChangeListener(new AttackHandler(this));
    this.map = this.map.generateMap(mapSize);
    this.initedGame = false;
        Tactician[] users = new Tactician[numberOfPlayers];
        String name;
        for (int i = 0; numberOfPlayers > i; i++) {
          name = ("Player " + i);
          users[i] = new Tactician(name, this.map);
          users[i].attackNotification.addPropertyChangeListener(new AttackHandler(this));
          users[i].passTurnNotification.addPropertyChangeListener(new PassTurnHandler(this));
          tacticians.add(users[i]);

    }
    this.turn = tacticians.get(0);

  }


  /**
   * @return the list of all the tacticians participating in the game.
   */
  List<Tactician> getTacticians() {
    return List.copyOf(tacticians);
  }


  /**
   * @return the map of the current game
   */
  Field getGameMap() {
    return map;
  }

  /**
   * @return the tactician that's currently playing
   */
  Tactician getTurnOwner() {
    return turn;
  }

  /**
   * @return the number of rounds since the start of the game.
   */
  int getRoundNumber() {
    return roundNumber;
  }

  /**
   * @return the maximum number of rounds a match can last
   */
  int getMaxRounds() {
    return maxRounds;
  }

  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    int index = turns.indexOf(getTurnOwner());
    if (turns.get(index + 1) != null) {
      this.turn = turns.get(index + 1);
      this.turn.setTurn();
    }
    else{
      if(getRoundNumber()!=getMaxRounds()){
        this.roundNumber += 1;
        setTurns();
        this.turn.setTurn(); }
      else{limitRoundsNotification.firePropertyChange(new PropertyChangeEvent(this, "LimitRounds", "", ""));}
    }
  }

  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician the player to be removed
   */
  public void removeTactician(String tactician) {
    tacticians.remove(searchTactician(tactician));
  }

  public Tactician searchTactician(String name){
      int size =tacticians.size();
      for(int i=0; i<size; i++) {
        if(name.equals(tacticians.get(i).getName())){
          return tacticians.get(i);
        }
    }return null;}

  public void removeUnit(IUnit unit) {
    getOwner(unit).killedUnit(unit);
  }

  /**
   * Starts the game.
   *
   * @param maxRounds the maximum number of turns the game can last
   */
  public void initGame(final int maxRounds) {

    if(maxRounds==-1) {
      initEndlessGame();}
    if(maxRounds>0){
      this.initedGame=true;
      this.maxRounds = maxRounds;
      setTurns();
      this.turn=turns.get(0);
      turns.get(0).setTurn();

    }

  }

  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
    this.initedGame=true;
    this.maxRounds = Integer.MAX_VALUE;
    setTurns();
    this.turn=turns.get(0);
    turns.get(0).setTurn();
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    this.initedGame=false;
    int length=getTacticians().size();
    for(int i=0;length > i;i++){
      this.winners.add(getTacticians().get(i).getName());
    }
    return this.winners;
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return getTurnOwner().getSelectedUnit();
  }

  /**
   * Selects a unit in the game map
   *
   * @param x horizontal position of the unit
   * @param y vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {
    selectedUnit = this.map.getCell(y, x).getUnit();
  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return getTurnOwner().getSelectedUnit().getItems();
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index the location of the item in the inventory.
   */
  public void equipItem(int index) {
    getSelectedUnit().equipItem(getItems().get(index));

  }

  /**
   * Uses the equipped item on a target
   *
   * @param x horizontal position of the target
   * @param y vertical position of the target
   */
  public void useItemOn(int x, int y) {
    getSelectedUnit().useItem(this.map.getCell(y, x).getUnit());
  }

  public void move(int x, int y) {
    if (map.getCell(y, x).getUnit() == null) {
      getSelectedUnit().moveTo(this.map.getCell(y, x));
    }
  }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index the location of the item in the inventory.
   */
  public void selectItem(int index) {
    this.item = getTurnOwner().getSelectedUnit().getItems().get(index);
  }

  /**
   * Gives the selected item to a target unit.
   *
   * @param x horizontal position of the target
   * @param y vertical position of the target
   */
  public void giveItemTo(int x, int y) {
    getSelectedUnit().giveItem(this.item, this.map.getCell(y, x).getUnit());
  }




  public void setTurns() {
    if(getTurnOwner()!=null){
      Tactician nonrep=getTurnOwner();
      Collections.shuffle(this.tacticians);
      while(getTurns().get(0)==nonrep){
        Collections.shuffle(this.tacticians);}}
    else{
      Collections.shuffle(this.tacticians); }
    this.turn=tacticians.get(0);}


  public List<Tactician> getTurns() {
    return List.copyOf(turns);
  }

  public Tactician getOwner(IUnit unit){
    int length = tacticians.size();
      for(int i=0;length > i;i++){
        if(tacticians.get(i).getUnits().contains(unit)){
          return tacticians.get(i);
        }
      }
      return null;
    }
}
