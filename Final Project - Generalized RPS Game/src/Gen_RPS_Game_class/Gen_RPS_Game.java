package Gen_RPS_Game_class;

// Java imports
import java.util.Scanner;
import java.util.Random;
import java.io.PrintStream;

//Custom imports from other parts of the program
import Main_Menu_class.Main_Menu;
import Player_class.Player;
import Player_class.Player_derived_classes.PC;
import Player_class.Player_derived_classes.NPC;
import Custom_Exception_classes.My_Quit_Exception;

/**
 * This class represents each game played within the whole match.
 * There is only a single match per execution of the program. This
 * class is self-contained in that it diplays handles it's own
 * input and output. Most of the excution time of this program
 * should be spent in this class, playing each game. The main method
 * of this class is mainLoop(), which runs a game; it returns the
 * Player object that won, or null for a tie. A tie can ONLY occur
 * when a game is exited early by a PC player. The gameSetup()
 * method is where the details of a given game type are plugged
 * into a specific instance of this class; it is called from the
 * constructor. The method evalThrows() deterimines the winner of
 * a throw, and printThrowWin() displays the throw's winner.
 * 
 * @author Jonathan Wayne Edwards on Monday 8 May 2023
 *
 */
public class Gen_RPS_Game
{
  
  // I/O and utility variables section
  private Scanner     scnr;             // A scanner (...darkly)
  private PrintStream o;                // Shortcut handle for System.out
  private int         numAstr     = -1; // Number of asterisks to print (passed to Main_Menu astrisk methods)
  private String      strAstr     = ""; // Output of Main_Menu asterisk methods
  private int         numSpacesP1 = -1; // For spacing when outputting scores
  private String      strSpacesP1 = ""; // For spacing when outputting scores
  private int         numSpacesP2 = -1; // For spacing when outputting scores
  private String      strSpacesP2 = ""; // For spacing when outputting scores
  
  // Game type creation specification variables section
  public  boolean     validGame;        // Flag = Is this a validly constructed game?  
  private int         gameType;         // What type of game to construct
  private String      gameTitle;        // The tilte of the game type being played (for displaying)
  private int         nMax;             // Number of throwable objects in game type being played
  private String[]    pieceNames;       // An array of the names of the objects thrown in the game type being played
  private String[][]  verbMatrix;       // The verbs to display between piece names on a throw win in the game type being played
  private int         winScore;         // The number of winning throws a Player object needs to win the game being played

  // Player objects and associated variables section
  private Player      p1;               // The Player object representing "Player 1"
  private Player      p2;               // The Player object representing "Player 2"
  private Player      throwWinner;      // The Player object that won the throw (references either p1 or p2, ONLY)
  private Player      throwLoser;       // The Player object that lost the throw (references either p1 or p2, ONLY)
  private Player      gameWinner;       // The Player object that won the game (references either p1 or p2, ONLY)
  
  /**
   * Constructs an INVALID GEN_RPS_Game object. Should NOT EVER be called. But...
   * 
   * TODO add error checking to Main_Menu.mainMenuLoop() in case this constructor IS called!
   */
  public Gen_RPS_Game ()
  {
    validGame   = false; // INVALID game
    
    scnr        = null;
    o           = null;
    gameType    = -1;
    gameTitle   = "UNDECLARED_TITLE";
    nMax        = -1;
    pieceNames  = new String[] {"UNDECLARED_NAMES"};
    verbMatrix  = new String[][] {{"UNDECLARED"}, {"VERBS"}};
    p1          = null;
    p2          = null;
    throwWinner = null;
    throwLoser  = null;
    gameWinner  = null;
    winScore    = -1;
  }
  
  /**
   * Constructs a VALID Gen_RPS_Game object. This should be the ONLY constructor called
   * 
   * @param p1 Player 1
   * @param p2 Player 2
   * @param gameType int of the type of game to play
   * @param winScore int of the number of points to win the game.
   * @param scnr The only Scanner object in the game. Passed in.
   */
  public Gen_RPS_Game (Player p1, Player p2, int gameType, int winScore, Scanner scnr)
  {
    this.validGame = true;
    
    this.scnr = scnr;
    this.o = System.out;
    this.gameType = gameType;
    this.winScore = winScore;
    this.throwWinner = null;
    this.throwLoser  = null;
    this.gameWinner = null;

    gameSetup();  // Set gameTitle, nMax, pieceNames, verbMatrix
    
    if (p1.getHuman())
    {
      this.p1 = (PC) p1;
    }
    else
    {
      this.p1 = (NPC) p1;
    }
    if (p2.getHuman())
    {
      this.p2 = (PC) p2;
    }
    else
    {
      this.p2 = (NPC) p2;
    }
    
    // Reset the Players' scores to 0
    p1.resetScore();
    p2.resetScore();
  }

  /**
   * The ONLY non-constructor public method of the Gen_RPS_Game class.
   * Essentially, it is a sub-menu of Main_Menu.mainMenuLoop(). This is the
   * method that ACTUALLY plays whichever game the user selects from the
   * program's main menu.
   * 
   * TODO Add error-checking, and throw an Exception if run on an invalid
   * Gen_RPS_Game object instantiation.
   * 
   * @return Player object that won. Null if tie from early exit.
   */
  public Player mainLoop ()
  {
    // Local variables
    int p1T;  // For player 1's throw
    int p2T;  // For player 2's throw
    
    o.println("************************************");
    o.println("* " + gameTitle + " *");
    o.println("************************************");
    o.println();
    o.println("***************");
    o.println("* B E G I N ! *");
    o.println("***************");
    
    while ((p1.getScore() < winScore) && (p2.getScore() < winScore))
    {
      // Get "Player 1"'s throw.
      if (p1.getHuman())
      {
        ((PC)p1).rpsThrow(nMax, pieceNames);
      }
      else
      {
        ((NPC)p1).rpsThrow(nMax, pieceNames);
      }
      p1T = p1.getThrow();
      
      // Get "Player 2"'s throw.
      if (p2.getHuman())
      {
        ((PC)p2).rpsThrow(nMax, pieceNames);
      }
      else
      {
        ((NPC)p2).rpsThrow(nMax, pieceNames);
      }
      p2T = p2.getThrow();
     
//      o.println(p1.toString());   // For debugging during early development phase of the project.
//      o.println(p2.toString());   // For debugging during early development phase of the project.
      try 
      {
        if ((p1T == -13) || (p2T == -13)) // Game was quit from within a throw input
        {
          throw new My_Quit_Exception("* I QUIT! *");          
        }
      }
      catch (My_Quit_Exception mQE)  // DID NOT need to make a custom exception type to quit, but wanted the experience of doing so
      {
        o.println();
        o.println(mQE.getMessage());
        o.println();
        getGameWinner();
        if (gameWinner == null)
        {
          o.println();
          o.println("*********************");
          o.println("Tie due to early quit");
          o.println("*********************");
          o.println();
        }
        return gameWinner;
      }
      if (p1T == p2T)
      {
        numAstr = 14 + pieceNames[p1T].length() + pieceNames[p2T].length();
        strAstr = Main_Menu.makeAsterisks(numAstr);
        o.println();
        o.println(strAstr);
        o.println("* " + pieceNames[p1T] + " vs " + pieceNames[p2T] + ": TIE! *");
        o.println(strAstr);
        o.println();
      }
      else
      { 
        numAstr = 48;
        numAstr += p1.getName().length();
        numAstr += p2.getName().length();
        numAstr += pieceNames[p1T].length();
        numAstr += pieceNames[p2T].length();
        strAstr = Main_Menu.makeAsterisks(numAstr);
        o.println();
        o.println(strAstr);
        o.print   ("* Player 1, " + p1.getName() + ", throws " + pieceNames[p1T] + " and ");
        o.println   ("Player 2, " + p2.getName() + ", throws " + pieceNames[p2T] + ". *");
        o.println(strAstr);
        o.println();
        evalThrows();
        printThrowWin();
      }
    }
    
    getGameWinner();
    return gameWinner;
  }
  
  
  /**
   * A private helper method that takes care of the nitty-gritty value
   * assignments after Gen_RPS_Game.gameType is specified in the constructor
   * of the class. Called from the constructor.
   */
  private void gameSetup ()
  {
    switch (gameType)
    {
      case 0:
      {
        gameTitle = "Rock, Paper, Scissors";
        nMax = 3;
        pieceNames = new String[]    {"Rock",
                                      "Scissors",
                                      "Paper"};
        
        verbMatrix = new String[][] {{"dulls"},
                                     {"cut"},
                                     {"smothers"}};
        validGame = true;
        break;
      }
      case 1:
      {
        gameTitle = "Rock, Paper, Scissors, Lizard, Spock";
        nMax = 5;
        pieceNames = new String[]    {"Rock",
                                      "Lizard",
                                      "Spock",
                                      "Scissors",
                                      "Paper"};
        
        verbMatrix = new String[][] {{"crushes", "dulls"},
                                     {"poisons", "eats"},
                                     {"dismantles", "vaporizes"},
                                     {"cut", "decapitate"},
                                     {"smothers", "disproves"}};
        validGame = true;
        break;
      }
      case 2:
      {
        gameTitle = "Rock, Paper, Scissors, Lizard, Spock, Gun, Pacifist";
        nMax = 7;
        pieceNames = new String[]    {"Rock",
                                      "Lizard",
                                      "Spock",
                                      "Pacifist",
                                      "Gun",
                                      "Scissors",
                                      "Paper"};
        
        verbMatrix = new String[][] {{"crushes", "bludgeons", "dulls"},
                                     {"poisons", "fouls", "eats"},
                                     {"outwits", "dismantles", "vaporizes"},
                                     {"disassembles", "ignores", "befriends"},
                                     {"shatters", "disintegrates", "headshots"},
                                     {"cut", "decapitate", "stab"},
                                     {"smothers", "disproves", "jams"}};
        validGame = true;
        break;
      }
      case 3:
      {
        gameTitle = "Rock, Paper, Scissors, Lizard, Spock, \n"
                  + "Gun, Pacifist, Lightning, Laptop";
        nMax = 9;
        pieceNames = new String[]    {"Rock",
                                      "Lizard",
                                      "Spock",
                                      "Pacifist",
                                      "Gun",
                                      "Laptop",
                                      "Lightning",
                                      "Scissors",
                                      "Paper"};
        
        verbMatrix = new String[][] {{"crushes", "bludgeons", "closes", "dulls"},
                                     {"poisons", "fouls", "absorbs", "eats"},
                                     {"outwits", "hacks", "dismantles", "vaporizes"},
                                     {"disassembles", "uses a copper rod to ground", "ignores", "befriends"},
                                     {"Obliterates", "shatters", "disintegrates", "headshots"},
                                     {"surge protects from", "obviates", "confuses", "doxes"},
                                     {"melts", "crumbles", "inconveniences", "fuses"},
                                     {"cut", "decapitate", "stab", "short circuit"},
                                     {"smothers", "disproves", "jams", "flys away from"}};
        validGame = true;
        break;
      }
    }
  }
  
  /**
   * A private helper method to determine BOTH the winning and the losing
   * Player objects after each player has thrown. Called from
   * Gen_RPS_Game.mainLoop(). 
   */
  private void evalThrows ()
  {
    // Local varibles to help evaluate the throws
    int     p1T     = (int)p1.getThrow();  // Shorter handle for p1's throw.
    int     p2T     = (int)p2.getThrow();  // Shorter handle for p2's throw.
    boolean p1Wins  = false;          // Did player 1 win? If not player 2 MUST have won.

    // The main logic for evaluating throws [NOTE: Several versions made throughout development. (See comments below.)]
     //////////////////////////////
    // <history_of_logic_fails>
     ///////////////////////////
    // "Strike 1"              //
    //                         //
    // (A bad first attempt at //
    // single-step evluation   //
    // of the winner. Kept for //
    // historical significance //
    // and any post-project    //
    // autopsy.)               //
     ///////////////////////////////////////////////////////////////////////////////////////
    // // Strike 1:                                                                        //
    //                                                                                     //
    // if ((((p1.getThrow() + 1) / (nMax - 1)) % 2) == ((p2.getThrow() / (nMax - 1)) % 2)) //
    //                                                                                     //
     ///////////////////////////////////////////////////////////////////////////////////////
    
     //////////////////////////
    // "Strike 2"             //
    //                        //
    // At least it's smaller? //
     ///////////////////////////////////////////////
    // // Strike 2:                                //
    //                                             //
    // if ( (p2T % 2) == (((p1T + 1) % nMax) % 2)) //
    //                                             //
     ///////////////////////////////////////////////
    // </history_of_logic_fails>
    
     ////////////////
    // "Grand Slam" //
     ////////////////
    // Let's eschew elegance and go BRUTE FORCE!: 
    for (int i = 0; i < ((nMax - 1) / 2); i++)
    {
      if (p2T == (((p1T + 1) + (2 * i)) % nMax))
      {
        p1Wins = true;
        break;
      }
    } // YAY! It worked! HOME RUN!!!!
    
    
    if (p1Wins)   // "Player 1" wins.
    {
      throwWinner = p1;
      throwLoser  = p2;
      p1.incScore();
//      o.println(pieceNames[p1T] + " beats " + pieceNames[p2T]); // Output for debugging the logic 
    }
    else          // "Player 2" wins.
    {
      throwWinner = p2;
      throwLoser  = p1;
      p2.incScore();
//      o.println(pieceNames[p2T] + " beats " + pieceNames[p1T]); // Output for debugging the logic
    }
  }
  
  /**
   * A private helper method for displaying the results of the throw. Called
   * from Gen_RPS_Game.mainLoop().
   */
  private void printThrowWin()
  {
    // The winning Player object's properties 
    String  winName   = throwWinner.getName();    // The winning player's name to display.
    int     winThrow  = throwWinner.getThrow();   // The losing player's thrown object.
    String  winPiece  = pieceNames[winThrow];     // The display name of the winning thrown object.
    
    // The losing Player object's properties
    String  loseName  = throwLoser.getName();     // The losing player's name to display. 
    int     loseThrow = throwLoser.getThrow();    // The losing player's thrown object.
    String  losePiece = pieceNames[loseThrow];    // The display name of the losing thrown object.
    
    // Indicies for getting the verb to display from verbMatrix[][].
    int     verbRow   = winThrow;
    int     verbCol   = ((loseThrow - winThrow) / 2) % (gameType + 1);
    
    // A correction for Java's implementation of the % operator [HINT: It does NOT perform a modulus calculation.]
    if (verbCol < 0)
    {
      verbCol = (verbCol + (gameType + 1)); // Convert remainder to modulus. Trick from StackOverflow online forum.
    }
    
    // The verb to join the piece names of the thrown objects when displaying the results
    String winVerb   = verbMatrix[verbRow][verbCol];

    // Display the results of the throw, including each player's resilting score. 
    numAstr = 14 + winPiece.length() + winVerb.length() + losePiece.length() + winName.length();
    strAstr = Main_Menu.makeAsterisks(numAstr);
    o.println();
    o.println(strAstr);
    o.println("* " + winPiece + " " + winVerb + " " + losePiece + "! " + winName + " wins! *");
    o.println(strAstr);
    o.println();
    
    o.println("**********");
    o.println("* Score: *");
    numAstr = 28;
    if (p1.getName().length() > p2.getName().length())
    {
      numAstr += p1.getName().length();
      numAstr += String.valueOf(p1.getScore()).length();
      strAstr = Main_Menu.makeAsterisks(numAstr);
      strSpacesP1 = "";
      numSpacesP2 = 28;
      numSpacesP2 += p2.getName().length();
      numSpacesP2 += String.valueOf(p2.getScore()).length();
      numSpacesP2 = numAstr - numSpacesP2;
      strSpacesP2 = Main_Menu.makeSpaces(numSpacesP2);
    }
    else
    {
      numAstr += p2.getName().length();
      numAstr += String.valueOf(p2.getScore()).length();
      strAstr = Main_Menu.makeAsterisks(numAstr);
      strSpacesP2 = "";
      numSpacesP1 = 28;
      numSpacesP1 += p1.getName().length();
      numSpacesP1 += String.valueOf(p1.getScore()).length();
      numSpacesP1 = numAstr - numSpacesP1;
      strSpacesP1 = Main_Menu.makeSpaces(numSpacesP1);
    }
    o.println(strAstr);
    o.println("* Player 1, " + p1.getName() + ", has " + p1.getScore() + " points." + strSpacesP1 + " *");
    o.println("* Player 2, " + p2.getName() + ", has " + p2.getScore() + " points." + strSpacesP2 + " *");
    o.println(strAstr);
    o.println();
  }
  
  /**
   * Detemines which Player object achieved the winning number of score points.
   * Sets gameWinner reference to refer to the winning Player object.
   */
  private void getGameWinner()
  {
    if (p1.getScore() > p2.getScore())
    {
      gameWinner = p1;
    }
    else if (p1.getScore() < p2.getScore())
    {
      gameWinner = p2;
    }
    else
    {
      gameWinner = null;
    }
  }
}
