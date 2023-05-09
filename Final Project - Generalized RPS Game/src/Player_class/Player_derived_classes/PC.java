package Player_class.Player_derived_classes;

// Java imports
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

// Custom imports from other parts of the program 
import Player_class.Player;
import Player_class.Player_interfaces.Throw_interface;

/**
 * This is the class for Player objects controlled by the user(s).
 * Derived from, and a type of, the Player class.
 * 
 * @author Jonathan Wayne Edwards on Monday 8 May 2023
 *
 */
public class PC extends Player implements Throw_interface
{
  private Scanner scnr = null;
  
  /**
   * Default constructor. Should not be called.
   */
  public PC ()
  {
    super(); // Makes an INVALID Player object
  }
  
  /**
   * The proper constructor for making a PC Player object
   * 
   * @param human Is this Player object a human? True.
   * @param name The PC object's name.
   * @param scnr The SINGLE scanner for the whole program.
   */
  public PC (boolean human, String name, Scanner scnr)
  {
    super ((Boolean)human, name); // Make the a base Player class object . . .
    this.scnr = scnr;             // . . . then add in a Scanner for PC class
  }
  
  
  /**
   * Generates a throw for the PC object. It asks the user for
   * input.
   * 
   * @param nMax The number of throwable pieces for a particular
   *        generalized game. 
   * @param pieceNames The String names of the pieces for a given
   *        generalized game.
   */
  public void rpsThrow (int nMax, String[] pieceNames)
  {
    String input = "";
    String inputCaps = "";
    PrintStream o = System.out;
    
    int pcThrow = -1;
    while ((inputCaps.intern() != "Q") && ((pcThrow < 1) || (pcThrow > nMax)))
    {
      try
      {
        o.println("What do you throw?:");
        
        int i = 0;
        for (String name : pieceNames)
        {
          i++;
          o.println(i + ") " + name);
        }
        o.println("***********************");
        o.println("Q) Quit this game early");
        o.println();
        o.print(":");
        input = scnr.next();
        inputCaps = input.toUpperCase();
        try
        {
          pcThrow = Integer.parseInt(input);
        }
        catch (NumberFormatException nFE)
        {
          pcThrow = -1; // Was not a number, so keep pcThrow an invalid throw number
        }
      }
      catch (InputMismatchException iME) // Not really needed since switching to scnr.next() from scnr.nextInt()
      {
        String trash = scnr.next(); // Throw away the bad input
      }
    }
    
    if (inputCaps.intern() == "Q")
    {
      myThrow = -13;
    }
    else
    {
      this.myThrow = pcThrow - 1;
    }
    o.println();
    //o.println(pieceNames[myThrow]); // Consistency verification
  }
}