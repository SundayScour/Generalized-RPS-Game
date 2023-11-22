package Player_class;

// Java imports
import java.util.Random;
import java.util.Scanner;

// Custom imports from other parts of the program
import Player_class.Player_derived_classes.NPC;
import Player_class.Player_derived_classes.PC;

/**
 * This is the base (and abstract) class from which the
 * PC and NPC classes are derived. By declaring Player 1
 * (p1 variable) and Player 2 (p2 variable) as Player
 * class type, then even when they are constructed as
 * PC class and NPC class objects (each player could be
 * either and type is not known until runtime) they can
 * still be passed to all methods expecting objects of
 * Player class type. This is the biggest secret of the
 * whole program, and the heart of its design. This allows
 * the two players to be ANY combination of computer or
 * human controlled objects (NPC or PC classes, 
 * respectively). Some explicit type casting is still
 * needed in places to make the whole thing work.
 *  
 * @author Jonathan Wayne Edwards on Monday 8 May 2023
 *
 */
public abstract class Player
{
  public    boolean isValid;  // Is this a valid Player class type object?
  
  protected Boolean human;    // Is this Player object controlled by a user?
  protected String  name;     // The name to display for this Player object
  protected Integer wins;     // Number of games won in the match
  protected Integer score;    // Number of throws win in a game
  protected Integer myThrow;  // The last object thrown 

  /**
   * Default constructor. Should not be called
   */
  public Player ()
  {
    isValid = false; // An INVALID object
    
    human   = null;
    name    = "_UNINITIALIZED_NAME_";
    wins    = null;
    score   = null;
    myThrow = null;
  }
  
  /**
   * The workhorse constructor for making PC and NPC objects.
   * 
   * @param human Is this object human or not? (True for
   *        making PC objects, false for NPC objects.)
   * @param name The name of the object.
   */
  public Player (boolean human, String name)
  {
    this.human      = human;
    this.name       = name;
    this.wins       = 0;
    this.score      = 0;
    this.myThrow    = null;
    this.isValid    = true;
  }
  
  
  /**
   * Makes a PC object.
   * 
   * @param human Is this Player object controlled by a human? True.
   * @param name The object's display name.
   * @param scnr The only Scanner in the program. Passed in.
   * @return Returns the newly instantiated PC object, cast as Player type.
   */
  public static Player makePCPlayer (boolean human, String name, Scanner scnr)
  {
    Object madePlayer = new PC(human, name, scnr);
    
    return (Player)madePlayer;    
  }
  
  /**
   * Makes an NPC object.
   * 
   * @param human Is this Player object controlled by a human? False.
   * @param name The object's display name.
   * @param rnd The only Random class object in the program. Passed in.
   * @return Returns the newly instantiated NPC object, cast as Player type
   */
  public static Player makeNPCPlayer (boolean human, String name, Random rnd)
  {
    Object madePlayer = new NPC(human, name, rnd);

    return (Player)madePlayer;
  }
  
  /**
   * Gets the last throw generated.
   * 
   * @return int of thrown object
   */
  public int getThrow()
  {
    return (int)myThrow;
  }
  
  /**
   * Gets the humanity of the Player object
   * PC = True
   * NPC = False
   * 
   * @return boolean of humanity
   */
  public boolean getHuman ()
  {
    return (boolean)human;
  }
  
  /**
   * Gets the object's display name
   * 
   * @return String of the name to display for this object
   */
  public String getName ()
  {
    return name;
  }
  
  /**
   * Gets the number of wins
   * 
   * @return int number of wins in the match. One match per program execution.
   */
  public int getWins ()
  {
    return (int)wins;
  }
  
  /**
   * Increases this object's number of wins by 1.
   */
  public void addWin ()
  {
    wins++;
  }
  
  /**
   * Gets this object's score so far in a game
   * 
   * @return int of the score of this object within a Gen_RPS_Game
   */
  public int getScore ()
  {
    return (int)score;
  }
  
  /**
   * Increases score by 1.
   */
  public void incScore ()
  {
    score++;
  }
  
  /**
   * Increases this object's score by a number of points.
   * Not utilized yet, with only 2 Player objects. Will be
   * used when more Player objects are implemented
   * 
   * @param addPoints int number of points to add to this object's score.
   */
  public void incScore (int addPoints)
  {
    score += Math.abs(addPoints);
  }
  
  /**
   * Decreses the score by 1.
   * Not used in a 2 Player game.
   */
  public void decScore ()
  {
    score--;
  }
  
  /**
   * Decreases this object's score by a number of points.
   * Not utilized yet, with only 2 Player objects. Will be
   * used when more Player objects are implemented
   * 
   * @param removePoints int number of points to remove from this object's score.
   */
  public void decScore (int removePoints)
  {
    score -= Math.abs(removePoints);
  }
  
  /**
   * VERY IMPORTANT. Resets this object's score to 0, for a new
   * game. Called when a new instance of Gen_RPS_Game is created.
   */
  public void resetScore ()
  {
    score = 0;
  }
 }