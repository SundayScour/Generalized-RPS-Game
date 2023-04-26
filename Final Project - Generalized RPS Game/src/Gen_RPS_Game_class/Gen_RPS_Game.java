package Gen_RPS_Game_class;

import Main_Menu_class.Main_Menu;
import Player_class.Player;
import Player_class.Player_derived_classes.PC;
import Player_class.Player_derived_classes.NPC;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintStream;

public class Gen_RPS_Game
{
  /////////////////////////////////////////////////////
  // Declarations of variables and object references //
  /////////////////////////////////////////////////////
  
  // I/O and utility variables section
  //////////////////////////////////////
  private Random      rnd;            // THE RNG for the game
  private Scanner     scnr;           // A scanner (...darkly)
  private PrintStream o;              // Shortcut handle for System.out
  private int         numAstr   = -1; // Number of asterisks to print (passed to Main_Menu astrisk methods)
  private String      strAstr   = ""; // Output of Main_Menu asterisk methods
  
  // Game type creation specification variables section
  //////////////////////////////////////
  public  boolean     validGame;      // Flag = Is this a validly constructed game?  
  private int         gameType;       // What type of game to construct
  private String      gameTitle;      // The tilte of the game type being played (for displaying)
  private int         nMax;           // Number of throwable objects in game type being played
  private String[]    pieceNames;     // An array of the names of the objects thrown in the game type being played
  private String[][]  verbMatrix;     // The verbs to display between piece names on a throw win in the game type being played
  
  // Player objects and associated variables section
  //////////////////////////////////////
  private Player      p1;             // The Player object representing "Player 1"
  private Player      p2;             // The Player object representing "Player 2"
  private Player      throwWinner;    // The Player object that won the throw (references either p1 or p2, ONLY)
  private Player      throwLoser;     // THe Player object that won the throw (references either p1 or p2, ONLY)
  private int         winScore;       // The number of winning throws a Player object needs to win the game being played
  
  //////////////////
  // Constructors //
  //////////////////
  
  // Constructs an INVALID GEN_RPS_Game object. Should NOT EVER be called. But...
  //
  // TODO add error checking to Main_Menu.mainMenuLoop() in case this constructor IS called!
  //////////////////////
  public Gen_RPS_Game ()
  {
    validGame = false;
    gameType = -1;
    gameTitle = "UNDECLARED_TITLE";
    nMax = -1;
    pieceNames = new String[] {"UNDECLARED_NAMES"};
    verbMatrix = new String[][] {{"UNDECLARED"}, {"VERBS"}};
    scnr = null;
    p1 = null;
    p2 = null;
    winScore = -1;
    o = null;
  }
  
  // Constructs a VALID Gen_RPS_Game object. This should be the ONLY constructor called
  ////////////////////////////////////////////////////////////////////////////////////////////////
  public Gen_RPS_Game (Player p1, Player p2, int gameType, int winScore, Scanner scnr, Random rnd)
  {
    this.gameType = gameType;
    this.winScore = winScore;
    this.scnr = scnr;
    this.rnd = rnd;
    o = System.out;

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
    p1.resetScore();
    p2.resetScore();
    
    gameSetup();
  }

  ///////////////
  // Main Loop //
  ///////////////
  
  // The ONLY non-constructor public method of the Gen_RPS_Game class. 
  // Essentially, it is a sub-menu of Main_Menu.mainMenuLoop(). This is the 
  // method that ACTUALLY plays whichever game the user selects from the 
  // program's main menu.
  //
  // TODO Add error-checking, and throw an Exception if run on an invalid
  //      Gen_RPS_Game object instantiation.
  /////////////////////////
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
        ((PC)p1).rpsThrow(scnr, nMax, pieceNames);
      }
      else
      {
        ((NPC)p1).rpsThrow(rnd, nMax, pieceNames);
      }
      p1T = p1.getThrow();
      
      // Get "Player 2"'s throw.
      if (p2.getHuman())
      {
        ((PC)p2).rpsThrow(scnr, nMax, pieceNames);
      }
      else
      {
        ((NPC)p2).rpsThrow(rnd, nMax, pieceNames);
      }
      p2T = p2.getThrow();
     
//      o.println(p1.toString());   // For debugging during early development phase of the project.
//      o.println(p2.toString());   // For debugging during early development phase of the project.
      if (p1T == p2T)
      {
        numAstr = 14 + pieceNames[p1T].length() + pieceNames[p2T].length();
        strAstr = Main_Menu.makeAsterisks(numAstr);
        o.println(strAstr);
        o.println("* " + pieceNames[p1T] + " vs " + pieceNames[p2T] + ": TIE! *");
        o.println(strAstr);
        o.println();
      }
      else
      { 
        o.print   ("Player 1, " + p1.getName() + ", throws " + pieceNames[p1T] + " and ");
        o.println ("Player 2, " + p2.getName() + ", throws " + pieceNames[p2T] + ".");       
        evalThrows();
        printThrowWin();
      }
    }
    
    if (p1.getScore() > p2.getScore())
    {
      return p1;
    }
    else
    {
      return p2;
    }
  }
  
  
  // A private helper method that takes care of the nitty-gritty value
  // assignments after Gen_RPS_Game.gameType is specified in the constructor of
  // the class. Called from the constructor.
  /////////////////////////
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
                                     {"disassembles", "grounds", "ignores", "befriends"},
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
  
  // A private helper method to determine BOTH the winning and the losing
  // Player objects after each player has thrown. Called from 
  // Gen_RPS_Game.mainLoop().
  //////////////////////////
  private void evalThrows ()
  {
    // Local varibles to help evaluate the throws
    int     p1T     = (int)p1.getThrow();  // Shorter handle for p1's throw.
    int     p2T     = (int)p2.getThrow();  // Shorter handle for p2's throw.
    boolean p1Wins  = false;          // Did player 1 win? If not player 2 MUST have won.

    // The main logic for evaluating throws [NOTE: Several versions made throughout development. (See comments below.)]
    
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
  
  // A private helper method for displaying the results of the throw. Called
  // from Gen_RPS_Game.mainLoop().
  ////////////////////////////
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
//    o.println("verbCol = " + verbCol);    // Output for debugging the calculation for verbCol.
    
    // The verb to join the piece names of the thrown objects when displaying the results
    String winVerb   = verbMatrix[verbRow][verbCol];

    // Display the results of the throw, including each player's resilting score. 
    numAstr = 14 + winPiece.length() + winVerb.length() + losePiece.length() + winName.length();
    strAstr = Main_Menu.makeAsterisks(numAstr);
    o.println(strAstr);
    o.println("* " + winPiece + " " + winVerb + " " + losePiece + "! " + winName + " wins! *");
    o.println(strAstr);
    o.print  ("Score: Player 1, " + p1.getName() + ", has " + p1.getScore() + " points, and ");
    o.println       ("Player 2, " + p2.getName() + ", has " + p2.getScore() + " points.");
    o.println();
  }
}


















































































