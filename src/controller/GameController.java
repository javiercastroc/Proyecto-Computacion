package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.*;

import model.Tactician;
import model.factory.*;
import model.handlers.DeathHandler;
import model.handlers.HeroHandler;
import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.AbstractUnit;
import model.handlers.SelectHandler;
import model.handlers.PassTurnHandler;
import model.units.IUnit;
import model.units.Sorcerer;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController {

    private List<Tactician> users = new ArrayList<Tactician>();
  private List<Tactician> tacticians = new ArrayList<Tactician>();
  private Field map=new Field();
  private Tactician turn;
  private int roundNumber=0;
  private int maxRounds;
  private int randomNumbers=0;
  private IUnit selectedUnit;
  private List<Tactician> turns = new ArrayList<Tactician>();
  private IEquipableItem item;
  private Random randomSeedGenerator;
  private Boolean initedGame=false;
  private long randomSeed;
  private int indexTurn;
  private List<String> winners = new ArrayList<>();
  public AlpacaFactory alpacaFactory=new AlpacaFactory();
  public ArcherFactory archerFactory=new ArcherFactory();
  public ClericFactory clericFactory=new ClericFactory();
  public FighterFactory fighterFactory=new FighterFactory();
  public HeroFactory heroFactory=new HeroFactory(this);
  public SorcererFactory sorcererFactory=new SorcererFactory();
  public SwordMasterFactory swordMasterFactory=new SwordMasterFactory();
  public AnimaFactory  animaFactory=new AnimaFactory();
  public AxeFactory axeFactory=new AxeFactory();
  public BowFactory bowFactory=new BowFactory();
  public LuzFactory luzFactory=new LuzFactory();
  protected OscuridadFactory oscuridadFactory=new OscuridadFactory();
  protected SpearFactory  spearFactory=new SpearFactory();
  protected SwordFactory swordFactory=new SwordFactory();

  public PropertyChangeSupport
          limitRoundsNotification = new PropertyChangeSupport(this);


  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers the number of players for this game
   * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
   */
  GameController(int numberOfPlayers, int mapSize) {
    this.limitRoundsNotification.addPropertyChangeListener(new SelectHandler(this));
    this.map.generateMap(mapSize);
    randomSeedGenerator=new Random(mapSize);
    this.randomSeed = randomSeedGenerator.nextLong();
    String name;
    for (int i = 0; numberOfPlayers > i; i++) {
        name = ("Player " + (i));
        tacticians.add(new Tactician(name,this.map));
        tacticians.get(i).selectNotification.addPropertyChangeListener(new SelectHandler(this));
        tacticians.get(i).passTurnNotification.addPropertyChangeListener(new PassTurnHandler(this));
    }
    users=tacticians;
    this.turn = tacticians.get(0);
    tacticians.get(0).setTurn();
    turns=tacticians;
    createFactories();
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
    return this.map;
  }

  /**
   * @return the tactician that's currently playing
   */
  Tactician getTurnOwner() {
    return turns.get(indexTurn);
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

    if (indexTurn+1!=turns.size()) {
      indexTurn++;
      this.turn = turns.get(indexTurn);
      this.turn.setTurn();
    }
    else{
      this.roundNumber += 1;
      if(getRoundNumber()!=getMaxRounds()+1){
        setTurns();
        this.indexTurn=0;
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
      if(getTurnOwner().getName().equals(tactician)){
        this.indexTurn--;
          endTurn();
      }
    tacticians.remove(searchTactician(tactician));
      for(int i=0;turns.size()>i;i++){
        if(turns.get(i).getName()==tactician){
          turns.remove(i);
        }
  }}

  public Tactician searchTactician(String name){
      int size =tacticians.size();
      for(int i=0; i<size; i++) {
        if(name.equals(tacticians.get(i).getName())){
          return tacticians.get(i);
        }
    }return null;}


  public void killUnit(AbstractUnit unit) {
     unit.died();
  }

  /**
   * Starts the game.
   *
   * @param rounds the maximum number of turns the game can last
   */
  public void initGame(final int rounds) {
      winners.clear();
      tacticians= users;
      turns=tacticians;
    this.indexTurn=0;
    if(rounds==-1) { initEndlessGame();}
    if(rounds>0){
      this.initedGame=true;
      this.maxRounds = rounds;
      setTurns();
      this.turn=turns.get(0);
      turns.get(0).setTurn();
      this.roundNumber=1; } }

  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
      winners.clear();
      tacticians= users;
      turns=tacticians;
    this.initedGame=true;
    this.maxRounds = -1;
    setTurns();
    this.roundNumber=1;
    this.turn=turns.get(0);
    turns.get(0).setTurn();
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {

      if (getMaxRounds()!=-1 &&getMaxRounds()>getRoundNumber()-1 && tacticians.size()>1){
          return null;
      }
      if(getMaxRounds()==-1 && tacticians.size()>1){
          return null;
      }
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
    return selectedUnit;
  }

  /**
   * Selects a unit in the game map
   *
   * @param x horizontal position of the unit
   * @param y vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {
    if (getGameMap().getCell(y, x).getUnit() != null) {

    if(getTurnOwner()==getOwner(getGameMap().getCell(y,x).getUnit())) {
      selectedUnit =this.map.getCell(y, x).getUnit();
      getTurnOwner().selectUnit((AbstractUnit) selectedUnit);
    }
  }}

  public void selectUnit(AbstractUnit unit) {
    if(getTurnOwner()==getOwner(unit)) {
      selectedUnit =unit;
      if(getTurnOwner().getSelectedUnit()==null){getTurnOwner().selectUnit((AbstractUnit) selectedUnit);}
      if(getTurnOwner().getSelectedUnit()!=unit){
      getTurnOwner().selectUnit((AbstractUnit) selectedUnit);
    }}
  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    if(getSelectedUnit()==null){return null;}
    return getTurnOwner().getInventory();
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index the location of the item in the inventory.
   */
  public void equipItem(int index) {
    if(getSelectedUnit()!=null ){
    getSelectedUnit().equipItem(getItems().get(index));
  }}

  public AbstractItem getEquipItem() {
    if(getSelectedUnit()!=null){ return (AbstractItem) getSelectedUnit().getEquippedItem();}
    else return null;
  }

  /**
   * Uses the equipped item on a target
   *
   * @param x horizontal position of the target
   * @param y vertical position of the target
   */
  public void useItemOn(int x, int y) {
    if(getSelectedUnit()!=null && getEquipItem()!=null && this.map.getCell(y, x).getUnit()!=null){
    getEquipItem().use(map.getCell(y, x).getUnit()); }}

  public void move(int x, int y) {
    if (map.getCell(y, x).getUnit() == null) {
      getSelectedUnit().moveTo(this.map.getCell(y, x)); }}

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index the location of the item in the inventory.
   */
  public void selectItem(int index) {
    if(getItems()!=null){
    this.item = getTurnOwner().getSelectedUnit().getItems().get(index);
  }}

  /**
   * Gives the selected item to a target unit.
   *
   * @param x horizontal position of the target
   * @param y vertical position of the target
   */
  public void giveItemTo(int x, int y) {
    getSelectedUnit().giveItem(this.item, this.map.getCell(y, x).getUnit());
  }



public void setRandomSeed(){
    this.randomSeed=this.randomSeedGenerator.nextLong();
    this.randomNumbers++;
}

  public void setTurns() {
    Tactician nonrep=getTurnOwner();
    setRandomSeed();
    if(getTurnOwner()!=null){
      Collections.shuffle(turns,new Random(randomSeed));
      while(getTurns().get(0)==nonrep && this.roundNumber!=0){
        setRandomSeed();
        Collections.shuffle(turns,new Random(randomSeed));;}}
    this.turn=turns.get(0);}

  public List<Tactician> getTurns() {
    return List.copyOf(turns);
  }

  public Tactician getOwner(AbstractUnit unit){
    int length = tacticians.size();
      for(int i=0;length > i;i++){
        if(tacticians.get(i).getUnits().contains(unit)){
          return tacticians.get(i); } }
      return null;
    }

    public void asignUnit(AbstractUnit unit,Tactician tactician){
      unit.setOwner(tactician);
    tactician.addUnit(unit);
    tactician.deathNotify(unit);
    }

    public List<AbstractUnit> getUnits(){
    return getTurnOwner().getUnits();
    }

  public void setLocation(int x,int y, AbstractUnit unit){
    if(map.getCell(y,x).getUnit()==null){
      unit.setLocation(map.getCell(y,x));
    }
  }

  public void createFactories(){
    alpacaFactory=new AlpacaFactory();
    archerFactory=new ArcherFactory();
    clericFactory=new ClericFactory();
    fighterFactory=new FighterFactory();
    heroFactory=new HeroFactory(this);
    sorcererFactory=new SorcererFactory();
    swordMasterFactory=new SwordMasterFactory();
    animaFactory=new AnimaFactory();
    axeFactory=new AxeFactory();
    bowFactory=new BowFactory();
    luzFactory=new LuzFactory();
    oscuridadFactory=new OscuridadFactory();
    spearFactory=new SpearFactory();
    swordFactory=new SwordFactory();
  }

}
