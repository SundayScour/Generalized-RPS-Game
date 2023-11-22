package Main_Menu_class;

// Java imports
import java.util.Scanner;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Random;

//Custom imports from other parts of the program
import Player_class.Player;
import Player_class.Player_derived_classes.NPC;
import Player_class.Player_derived_classes.PC;
import Gen_RPS_Game_class.Gen_RPS_Game;

/**
 * This is a static class that is never instantiated. It controls
 * the main logic for running the program, through user input that
 * creates the 2 Player objects, selects the games to run, and when
 * to exit the program. Also has helper methods to format output
 * into boxes made from Asterisks.
 * 
 * @author Jonathan Wayne Edwards on Monday 8 May 2023
 *
 */
public final class Main_Menu
{
  // Input variables
  private static Scanner      scnr          = new Scanner(System.in); // THE Scanner (...darkly) for the WHOLE PROGRAM.
  private static PrintStream  o             = System.out;             // A shorter handle for System.out used in output.
  private static Random       rnd           = null;                   // THE Random object for the WHOLE PROGRAM.
  private static String       input         = "";                     // A String for the user's input
  private static String       inputCaps     = "";                     // The capitalized version of above.
  private static boolean      correct       = false;                  // A flag for whether to repeat an input prompt.
  private static long         inLong        = 0;                      // A String for Secret Seed input.
  private static int          inInt         = -1;                     // A int for the user's input.
  private static int          numAstr       = 0;                      // The number of asterisks to pass to asterisk box methods
  private static String       strAstr       = "";                     // A String for the output of asterisk box methods
  private static int          numCenter     = 0;                      // The number of charaters for a centered word in asterisk box methods.
  private static String       strAstrCenter = "";                     // A String fot the output of the centered asterisk box method.
  
  // Player and game setup variables
  private static Player       p1        = null;                       // An object reference for "Player 1".
  private static Player       p2        = null;                       // An object reference for "Player 2".
  private static Player       winner    = null;                       // An object reference for the winner of a game object instance.
  private static Gen_RPS_Game game      = null;                       // An object reference for the game object of the current game to play.
  private static String       name      = "";                         // A String to hold a Player object's name, for display.
  private static boolean      isHuman   = false;                      // A flag for whether a Player object should be instantiated as PC or NPC.
  private static int          gameType  = -1;                         // The type of game object to make.
  private static int          winScore  = -1;                         // The number of winning throws a Player needs to win the current game.
  private static long         seed      = 0;                          // The Secret Seed to use (or not use).
  private static final int    NUM_GAMES = 4;                          // The numnber of game types for the user to choose from.
  private static String[]     gameTitles = {"Rock, Paper, Scissors",  // The titles of the gmae types/
                                            "Rock, Paper, Scissors, Lizard, Spock",
                                            "Rock, Paper, Scissors, Lizard, Spock,\n"
                                          + "Gun, Pacifist",
                                            "Rock, Paper, Scissors, Lizard, Spock,\n"
                                          + "Gun, Pacifist, Lightning, Laptop"};
  
  /**
   * Default constructor. SHOULD NEVER BE CALLED!!
   * 
   * TODO: Throw an exception if this construtor is ever called.
   */
  private Main_Menu ()
  {
    /*
     * The Main_Menu class is never instantiated into an object, thus this
     * constructor is never called. SHOULD never be called
     */
  }
  
  /*
   * MAIN LOOP FOR THE WHOLE PROGRAM
   */
  /**
   * THE MAIN LOOP FOR THE WHOLE PROGRAM
   * 
   * @return int Utilized ONLY to allow explicit return to the
   *         Java standard main() method.
   */
  public static int mainMenuLoop ()
  {
    o.println();
    o.println();
    o.println();
    o.println("*******************************************************");
    o.println("*********  Welcome to G E N E R A L I Z E D  **********");
    o.println("*********      'Rock, Paper, Scissors'!      **********");
    o.println("********   Written by Jonathan Wayne Edwards   ********");
    o.println("*******************************************************");
    o.println();
    o.println();
    o.println();
    
    // Use / get the Secret Seed
    secretSeed();
    
    // Get Player 1 info and make it an object
    makePlayer1();
    
    // Get Player 2 info and make it an object
    makePlayer2();
    
    // Display the title card for the matchup
    displayMatchup();
    
    // Implement the main menu of picking a game type, playing it, and playing another or exiting
    mainMenu();
    
    // Return to main() and exit the program
    return 0;
  }
  
  /*
   * Private helper methods section
   */
  
  /**
   * Gets the Secret Seed from the user. Either typed in or randomly generated.
   */
  private static void secretSeed()
  {
    // The Secrec Seed allows for repeatability of randomized elements. 
    
    o.println("*************************************************************************");
    o.println("* Use a Secret Seed (for repeatability)? Press <Y> or <N> then <Enter>. *");
    o.println("*************************************************************************");
    o.println();
    o.print(": ");
    input = scnr.next();
    inputCaps = input.toUpperCase();
    while ((inputCaps.intern() != "Y") && (inputCaps.intern() != "N"))
    {
      o.println();
      o.println("Try again. Use a Secret Seed? Press <Y> or <N> then <Enter>.");
      o.println();
      o.print(": ");
      input = scnr.next();
      inputCaps = input.toUpperCase();
    }
    if (inputCaps.intern() == "Y")
    {
      inputCaps = "";
      while (inputCaps.intern() != "Y")
      {
        try
        {
          o.println();
          o.println("****************************************************");
          o.println("*      Enter an integer (long int) number          *");
          o.println("*                     OR                           *");
          o.println("* \"r\" for random, which is same as no Secret Seed: *");
          o.println("****************************************************");
          o.println();
          o.print(": ");
          inLong = scnr.nextLong();
          seed = inLong;
          numAstr = 25;
          numAstr += String.valueOf(seed).length();
          strAstr = makeAsterisks(numAstr);
          o.println();
          o.println(strAstr);
          o.println("* Secret Seed will be: " + seed + " *");
          o.println(strAstr);
          o.println();
        }
        catch (InputMismatchException iME)
        {
          input = scnr.next();
          inputCaps = input.toUpperCase();
          if (inputCaps.intern() == "R")
          {
            o.println();
            o.println("*******************************");
            o.println("* Secret Seed will be: random *");
            o.println("*******************************");
            o.println();
          }
          else
          {
            o.println();
            o.println("Try again.");
            continue;
          }
        }
        o.println("***************************************************");
        o.println("* Is this okay? If it is, press <Y> then <Enter>. *");
        o.println("***************************************************");
        o.println();
        o.print(": ");
        input = scnr.next();
        inputCaps = input.toUpperCase();
      }
      rnd = new Random(seed);  // Use a given seed . . . 
    }
    else
    {
      rnd = new Random();      // . . . or use a random seed.
    }
  }
  
  /**
   * Gets user input to make Player 1.
   */
  private static void makePlayer1()
  {
    correct = false;
    while (!correct)
    {
      o.println();
      o.println("*********************************");
      o.println("* What will Player 1's name be? *");
      o.println("*******************************************************************");
      o.println("* Type a name, or type <R> for a random name, then press <Enter>: *");
      o.println("*******************************************************************");
      o.println();
      o.print(": ");
      name = scnr.nextLine();
      name = scnr.nextLine();
      if (name.toUpperCase().intern() == "R")
      {
        name = makeRandomName();
      }
      numAstr = 15 + 4 + name.length();
      strAstr = makeAsterisks(numAstr);
      o.println();
      o.println(strAstr);
      o.println("* Player 1 name: " + name + " *");
      o.println(strAstr);

      o.println();      
      o.println("*********************************************");
      o.println("* Will Player 1 be a human or the computer? *");
      o.println("*****************************************************************");
      o.println("* Press <H> for human, or <C> for computer, then press <Enter>: *");
      o.println("*****************************************************************");
      o.println();
      o.print(": ");
      input = scnr.next();
      inputCaps = input.toUpperCase();
      while ((inputCaps.intern() != "H") && (inputCaps.intern() != "C"))
      {
        o.println("Please try again. Player 1: <H> for Human, <C> for computer:");
        o.println();
        o.print(": ");
        input = scnr.next();
        inputCaps = input.toUpperCase();
      }
      if (inputCaps.intern() == "H")
      {
        isHuman = true;
      }
      else
      {
        isHuman = false;
      }
      
      o.println();
      o.println("***********");
      o.println("* Verify: *");
      o.println("***********");
      o.println();
      o.print("Player 1's name is " + name + " and Player 1 is ");
      if (isHuman)
      {
        o.println("a human.");
      }
      else
      {
        o.println("the computer.");
      }
      input = "x";
      inputCaps = input.toUpperCase();
      while ((inputCaps.intern() != "Y") && (inputCaps.intern() != "N"))
      {
        o.println();
        o.println("***************************************************");
        o.println("* Is this correct? Type <Y> or <N>, then <Enter>. *");
        o.println("***************************************************");
        o.println();
        o.print(": ");
        input = scnr.next();
        inputCaps = input.toUpperCase();
      }
      if (inputCaps.intern() == "N")
      {
        correct = false;
      }
      else
      {
        if (isHuman)
        {
          p1 = Player.makePCPlayer(isHuman, name, scnr);
          correct = true;
        }
        else
        {
          p1 = Player.makeNPCPlayer(isHuman, name, rnd);
          correct = true;
        }
      }
//      o.println();
    }
  }

  /**
   * Gets user input to make Player 2.
   */
  private static void makePlayer2()
  {
     ////////////////// 
    // Player 2 block //
     ////////////////// 
    correct = false;
    while (!correct)
    {
      o.println();
      o.println("*********************************");
      o.println("* What will Player 2's name be? *");
      o.println("*******************************************************************");
      o.println("* Type a name, or type <R> for a random name, then press <Enter>: *");
      o.println("*******************************************************************");
      o.println();
      o.print(": ");
      name = scnr.nextLine();
      name = scnr.nextLine();
      if (name.toUpperCase().intern() == "R")
      {
        name = makeRandomName();
      }
      numAstr = 15 + 4 + name.length();
      strAstr = makeAsterisks(numAstr);
      o.println();
      o.println(strAstr);
      o.println("* Player 2 name: " + name + " *");
      o.println(strAstr);
      
      o.println();
      o.println("*********************************************");
      o.println("* Will Player 2 be a human or the computer? *");
      o.println("*****************************************************************");
      o.println("* Press <H> for human, or <C> for computer, then press <Enter>: *");
      o.println("*****************************************************************");
      o.println();
      o.print(": ");
      input = scnr.next();
      inputCaps = input.toUpperCase();
      while ((inputCaps.intern() != "H") && (inputCaps.intern() != "C"))
      {
        o.println("Please try again. Player 2: <H> for Human, <C> for computer:");
        o.println();
        o.print(": ");
        input = scnr.next();
        inputCaps = input.toUpperCase();
      }
      if (inputCaps.intern() == "H")
      {
        isHuman = true;
      }
      else
      {
        isHuman = false;
      }
      
      o.println();
      o.println("***********");
      o.println("* Verify: *");
      o.println("***********");
      o.println();
      o.print("Player 2's name is " + name + " and Player 2 is ");
      if (isHuman)
      {
        o.println("a human.");
      }
      else
      {
        o.println("the computer.");
      }
      input = "x";
      inputCaps = input.toUpperCase();
      while ((inputCaps.intern() != "Y") && (inputCaps.intern() != "N"))
      {
        o.println();
        o.println("***************************************************");
        o.println("* Is this correct? Type <Y> or <N>, then <Enter>. *");
        o.println("***************************************************");
        o.println();
        o.print(": ");
        input = scnr.next();
        inputCaps = input.toUpperCase();
      }
      if (inputCaps.intern() == "N")
      {
        correct = false;
      }
      else
      {
        if (isHuman)
        {
          p2 = Player.makePCPlayer(isHuman, name, scnr);
          correct = true;
        }
        else
        {
          p2 = Player.makeNPCPlayer(isHuman, name, rnd);
          correct = true;
        }
      }
//      o.println();
    }
  }
  
  /**
   * Displays the introductory "title card" for the match.
   */
  private static void displayMatchup ()
  {
     /////////////////////// 
    // Display the matchup //
     /////////////////////// 
    o.println();
    o.println();
    o.println();
    numAstr = 15 + p1.getName().length() + p2.getName().length();
    strAstr = makeAsterisks(numAstr);
    o.println(strAstr);
    o.println(strAstr);
    o.println("* " + p1.getName() + " versus " + p2.getName() + "!!! *");
    o.println(strAstr);
    o.println(strAstr);
  }

  /**
   * The Main Menu. Pick a game. Pick the score to win it.
   * Display winning game message. Quit? If yes, display the
   * final win totals, declare a winner, and exit the program.
   */
  public static void mainMenu()
  {
     ////////////////// 
    // MAIN PLAY LOOP //
     //////////////////
    while (true)
    {
      // Get game type
      input = "";
      inputCaps = "";
      inInt = 0;
      while ((inInt < 1) || (inInt > (NUM_GAMES + 1)))
      {
        try
        {
          o.println();
          o.println("**************************************");
          o.println("* Choose a game, then press <Enter>: *");
          o.println("**************************************");
          o.println();
          int i = 0;
          for (String game : gameTitles)
          {
            i++;
            o.println(i + ") " + game);
          }
          i++;
          o.println("Q) or \"Q\" to quit the program");
          o.println();
          o.print(":");
          inInt = scnr.nextInt();
        }
        catch (InputMismatchException iME)
        {
          input = scnr.next();
          inputCaps = input.toUpperCase();
          if (inputCaps.intern() == "Q")
          {
            inInt = NUM_GAMES + 1;
          }
          else
          {
            inInt = 0;
            inputCaps = "";
          }
        }
      }
      if (inInt == (NUM_GAMES + 1))
      {
        break; // Exit the Main Play Loop
      }
      else
      {
        gameType = inInt - 1;
      }
      o.println();
      
      // Get winning score
      o.println("***********************************************");
      o.println("* How many victorious throws to win the game? *");
      o.println("*********************************************************");
      o.println("* (Type a number between 1 and 100, then press <Enter>: *");
      o.println("*********************************************************");
      o.println();
      o.print(": ");
      try
      {
        inInt = scnr.nextInt();
      }
      catch (InputMismatchException iME)
      {
        String trash = scnr.next();
        inInt = -1;
      }
      while ((inInt < 1) || (inInt > 100))
      {
        try
        {
          o.println("Please try again. Type a number between 1 and 100, then press <Enter>:");
          o.println();
          o.print(": ");
          inInt = scnr.nextInt();
        }
        catch (InputMismatchException iME)
        {
          String trash = scnr.next(); // Discard invalid input
        }
      }
      o.println();
      o.println();
      o.println();
      winScore = inInt;
      
       //////////////////
      // Run that game! //
       //////////////////
      game = new Gen_RPS_Game (p1, p2, gameType, winScore, scnr);
      winner = game.mainLoop();
      if (winner != null)
      {
        winner.addWin();
        strAstr = Main_Menu.makeAsterisks(17);
        o.println(strAstr);
        o.println("* Game summary: *");
        o.println(strAstr);
        o.println(winner.getName() + " wins that game!");
        o.println("The match stands at:");
        o.print  ("Player 1, " + p1.getName() + ", has " + p1.getWins() + " wins, and ");
        o.println("Player 2, " + p2.getName() + ", has " + p2.getWins() + " wins.");
        o.println();
        o.println("***********************************");
        o.println("* Let's move on to the next game! *");
        o.println("***********************************");
        o.println();
      }
      else
      {
        strAstr = Main_Menu.makeAsterisks(17);
        o.println(strAstr);
        o.println("* Game summary: *");
        o.println(strAstr);
        o.println("* TIE due to early quit *");
        o.println("The match stands at:");
        o.print  ("Player 1, " + p1.getName() + ", has " + p1.getWins() + " wins, and ");
        o.println("Player 2, " + p2.getName() + ", has " + p2.getWins() + " wins.");
        o.println();
        o.println("***********************************");
        o.println("* Let's move on to the next game! *");
        o.println("***********************************");
        o.println();
      }
      
      o.println("*************************************************");
      o.println("* To continue, type anything then press <Enter> *");
      o.println("*************************************************");
      o.println();
      String trash = scnr.next();
      o.println();
    }
    
     ///////////////////////////////////////
    // Output results and exit the program //
     ///////////////////////////////////////
    o.println();
    o.println();
    o.println();
    o.println();
    o.println("***************************************************");
    o.println("*********                               ***********");
    o.println("*********  M A T C H   S U M M A R Y :  ***********");
    o.println("*********                               ***********");
    o.println("***************************************************");
    
    if (p1.getWins() == p2.getWins())       // A TIE!
    {
      numAstr  = 27;
      numAstr += p1.getName().length();
      numAstr += p2.getName().length();
      numAstr += String.valueOf(p1.getWins()).length();
      numAstr += String.valueOf(p2.getWins()).length();
      strAstr  = Main_Menu.makeAsterisks(numAstr);
      o.println(strAstr);
      o.println("The matchup was a TIE!");
      o.print  (p1.getName() + " had " + p1.getWins() + " wins, and");
      o.println(p2.getName() + " had " + p2.getWins() + " wins.");
      o.println("Congratulations to both of you!");
      o.println(strAstr);
    }
    else if (p1.getWins() > p2.getWins())   // "Player 1" WINS!
    {
      numAstr  = 31;
      numAstr += p1.getName().length();
      numAstr += p2.getName().length();
      numAstr += String.valueOf(p1.getWins()).length();
      numAstr += String.valueOf(p2.getWins()).length();
      strAstr  = Main_Menu.makeAsterisks(numAstr);
      o.println(strAstr);      
      o.print  ("* " + p1.getName() + " had " + p1.getWins() + " wins, and ");
      o.println(p2.getName() + " had " + p2.getWins() + " wins. *");
      numCenter = 8 + p1.getName().length();
      strAstrCenter = makeAsterisksCenter(numAstr, numCenter);
      o.println(strAstrCenter + " " + p1.getName() + " WINS! " + strAstrCenter);
      o.println(strAstr);
      numCenter = 22 + p1.getName().length();
      strAstrCenter = Main_Menu.makeAsterisksCenter(numAstr, numCenter);
      o.println(strAstrCenter + "  Congratulations, " + p1.getName() + "!  " + strAstrCenter);
      o.println(strAstr);
    }
    else if (p2.getWins() > p1.getWins())   // "Player 2" WINS!
    {
      numAstr  = 31;
      numAstr += p1.getName().length();
      numAstr += p2.getName().length();
      numAstr += String.valueOf(p1.getWins()).length();
      numAstr += String.valueOf(p2.getWins()).length();
      strAstr  = Main_Menu.makeAsterisks(numAstr);
      o.println(strAstr);      
      o.print  ("* " + p1.getName() + " had " + p1.getWins() + " wins, and ");
      o.println(p2.getName() + " had " + p2.getWins() + " wins. *");
      numCenter = 8 + p2.getName().length();
      strAstrCenter = makeAsterisksCenter(numAstr, numCenter);
      o.println(strAstrCenter + " " + p2.getName() + " WINS! " + strAstrCenter);
      o.println(strAstr);
      numCenter = 22 + p2.getName().length();
      strAstrCenter = Main_Menu.makeAsterisksCenter(numAstr, numCenter);
      o.println(strAstrCenter + "  Congratulations, " + p2.getName() + "!  " + strAstrCenter);
      o.println(strAstr);
    }
  }
  
  
  // A private helper method to pick a random name. Called by 
  // Main_Menu.mainMenuLoop().
  ///////////////////////////////////////
  /**
   * @return String that is a random name from a list of 52
   *         names: 26 Boy names, 26 Girl names, one name each
   *         per letter of the alphabet. Got from
   *         https://babynames.net, and my own mind.
   */
  private static String makeRandomName ()
  {
    int x = -1;
    String[] names = {"Alice",
                      "Andrew",
                      "Barbara",
                      "Bob",
                      "Clara",
                      "Cody",
                      "Denise",
                      "Daniel",
                      "Erica",
                      "Eren",
                      "Frauline",
                      "Franklin",
                      "Gwen",
                      "Gaige",
                      "Hazel",
                      "Henry",
                      "Ingrid",
                      "Ichiro",
                      "Jasmine",
                      "Jon",
                      "Karen",
                      "Keith",
                      "Lauren",
                      "Langston",
                      "Melody",
                      "Maximillion",
                      "Nadia",
                      "Nathan",
                      "Ophalia",
                      "Oberon",
                      "Pixie",
                      "Paul",
                      "Quinn",
                      "Quill",
                      "Robin",
                      "Roger",
                      "Svetlana",
                      "Slade",
                      "Toya",
                      "Tim",
                      "Usha",
                      "Uriel",
                      "Veronica",
                      "Viggo",
                      "Winona",
                      "Wies",
                      "Xochitl",
                      "Xander",
                      "Ylva",
                      "Yoshihiro",
                      "Zelda",
                      "Zenon"};
    x = rnd.nextInt(51);
    return names[x];
  }
  
  /**
   * A private helper method to help create boxes of asterisks. Also called by
   * other classes. This method simply returns a String containing the desired
   * number of asterisks.
   * 
   * @param numAstr int number of asterisks to return in the return String
   * @return String containing numAstr asterisks.
   */
  public static String makeAsterisks (int numAstr)
  {
    String outStr = "";
    
    for (int i = 0; i < numAstr; i++)
    {
      outStr += "*";
    }
    
    return outStr;
  }
  

  /**
   * A private helper method to help create boxes of asterisks. Also called by
   * other classes. This method returns a String containing a row of asterisks
   * to be placed to either side of a String in a line, to center the string
   * within the line within the whole rectangular asterisk box.
   * 
   * TODO Add ability to accept the string itself, then output BOTH left and
   *      right asterisk Strings as an array of type String[], to accommodate
   *      Strings with an ODD NUMBER of characters.
   *       
   * @param totalAstr int total astrisks in each line of the asterisk block
   * @param numCenter int number of spaces to allow for in the middle of this
   *        line.
   * @return String containing the proper number of asterisks on each side of
   *         the String in the middle of this line with numCenter charaters in
   *         it. 
   */
  public static String makeAsterisksCenter (int totalAstr, int numCenter)
  {
    String  outStr  = "";
    int     numAstr = 0;
    
    numAstr = (totalAstr / 2) - (numCenter / 2);
    
    for (int i = 0; i < numAstr; i++)
    {
      outStr += "*";
    }
        
    return outStr;
  }
  
  /**
   * Simply returns a string containing numSpaces space characters.
   * 
   * @param numSpaces int number of spaces to fill the return String with.
   * @return String with numSpaces space characters in it.
   */
  public static String makeSpaces (int numSpaces)
  {
    String outStr = "";
    
    for (int i = 0; i < numSpaces; i++)
    {
      outStr += " ";
    }
    
    return outStr;
  }
}