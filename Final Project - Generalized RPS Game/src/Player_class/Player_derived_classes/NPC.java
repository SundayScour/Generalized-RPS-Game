package Player_class.Player_derived_classes;

// Java imports
import java.util.Random;
import java.util.Scanner;

// Custom imports from other parts of the program
import Player_class.Player;
import Player_class.Player_interfaces.Throw_interface;

/**
 * This is the class for Player objects controlled by the
 * computer. Derived from, and a type of, the Player class
 * 
 * @author Jonathan Wayne Edwards on Monday 8 May 2023
 *
 */
public class NPC extends Player implements Throw_interface
{
  private Random rnd;
  
  /**
   * Default constructor. Should not be called.
   */
  public NPC ()
  {
    super(); // Makes an INVALID Player object
  }
  
  /**
   * The proper constructor for making an NPC object
   * 
   * @param human Is this Player object a human? False.
   * @param name The NPC object's name.
   * @param rnd The SINGLE Random class object for the
   *        whole program
   */
  public NPC (boolean human, String name, Random rnd)
  {
    super((Boolean)false, name);
    this.rnd = rnd;
  }
    
  /**
   * Generates a throw for the NPC object. Simply returns a
   * random value between 0 and nMax - 1.
   * 
   * @param nMax The number of throwable pieces for a particular
   *        generalized game. 
   * @param pieceNames The String names of the pieces for a given
   *        generalized game.
   */
  @Override
  public void rpsThrow (int nMax, String[] pieceNames)
  {
    myThrow =  rnd.nextInt(nMax);
  }
}  
