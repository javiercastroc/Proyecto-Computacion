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
import model.handlers.LimitRoundsHandler;
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
   HeroFactory heroFactory=new HeroFactory(this);
  public SorcererFactory sorcererFactory=new SorcererFactory();
  public SwordMasterFactory swordMasterFactory=new SwordMasterFactory();
  public AnimaFactory  animaFactory=new AnimaFactory();
  public AxeFactory axeFactory=new AxeFactory();
  public BowFactory bowFactory=new BowFactory();
  public LuzFactory luzFactory=new LuzFactory();
  protected OscuridadFactory oscuridadFactory=new OscuridadFactory();
  protected SpearFactory  spearFactory=new SpearFactory();
  protected StaffFactory  staffFactory=new StaffFactory();
  protected SwordFactory swordFactory=new SwordFactory();

  public PropertyChangeSupport
          limitRoundsNotification = new PropertyChangeSupport(this);


  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers the number of players for this game
   * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    this.limitRoundsNotification.addPropertyChangeListener(new LimitRoundsHandler(this));
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
    turn=tacticians.get(0);
    tacticians.get(0).setTurn();
    createFactories();
  }


  /**
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() {
    return List.copyOf(tacticians);
  }


  /**
   * @return the map of the current game
   */
  public Field getGameMap() {
    return this.map;
  }

  /**
   * @return the tactician that's currently playing
   */
  public Tactician getTurnOwner() {
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

  /**
   * Selects a unit
   * @param unit to select
   */
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


  /**
   * @return the equipped item of the selected unit (on the actual Tactician turn)
   *
   */
  public AbstractItem getEquipItem() {
    if(getSelectedUnit()!=null){ return (AbstractItem) getSelectedUnit().getEquippedItem();}
    else return null;
  }

  /**
   * Uses the equipped item on a target
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


  /**
   * Set a random seed and save it (and increase n° of random numbers)
   * This is to get random turns each round
   */
public void setRandomSeed(){
    this.randomSeed=this.randomSeedGenerator.nextLong();
    this.randomNumbers++;
}


  /**
   * Set the turns of this round without repeating Tacticians turns
   */
  public void setTurns() {
    Tactician nonrep=getTurnOwner();
    setRandomSeed();
    if(getTurnOwner()!=null){
      Collections.shuffle(turns,new Random(randomSeed));
      while(getTurns().get(0)==nonrep && this.roundNumber!=0){
        setRandomSeed();
        Collections.shuffle(turns,new Random(randomSeed));}}
    this.turn=turns.get(0);
    turn.setTurn();}



  /**
   * @return a list of Tacticians, that the Tactician position on the list refers
   * the position of his turn in this round
   */
  public List<Tactician> getTurns() {
    return turns;
  }


  /**
   * @return the Tactician that owns this turn
   */
  public Tactician getOwner(AbstractUnit unit){
    int length = tacticians.size();
    for (Tactician tactician : tacticians) {
      if (tactician.getUnits().contains(unit)) {
        return tactician;
      }
    }
      return null;
    }

  /**
   * Assing unit to tactician
   * add death notify of the unit to the tactician
   * @param unit to be assigned
   * @param tactician to assign unit
   */
    public void asignUnit(AbstractUnit unit,Tactician tactician){
      unit.setOwner(tactician);
      tactician.addUnit(unit);
      tactician.deathNotify(unit);
    }

  /**
   * @return a list of units that the actual turn Tactician has
   */
    public List<AbstractUnit> getUnits(){
    return getTurnOwner().getUnits();
    }

  /**
   * Sets the location to a unit if the location cell is not filled
   * @param unit to set in location
   * @param x horizontal position of the target location
   * @param y vertical position of the target location
   */
  public void setLocation(int x,int y, AbstractUnit unit){
    if(map.getCell(y,x).getUnit()==null){
      unit.setLocation(map.getCell(y,x));
    }
  }


/**
 * Creates the factories of all units types and item types
 */
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
    staffFactory=new StaffFactory();
  }


  /**
   * @return the tactician of index in the tacticians list
   * @param index of the Tactician in tacticians list
   */
  public Tactician tactician(int index){
    return getTacticians().get(index);
  }



  /**
   * Add item to unit if the game is not inited
   * @param item to be added
   * @param unit to add item
   */
  public void addItemTo(AbstractItem item, AbstractUnit unit){
    if(initedGame==false) {
    unit.addItem(item);
  }}

}
