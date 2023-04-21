package Main_Menu_class;

import Player_class.Player;
import Gen_RPS_Game_class.Gen_RPS_Game;
import java.util.Scanner;
import java.io.PrintStream;
import java.util.Random;

public final class Main_Menu
{
  // Input variables
  private static Scanner     scnr      = new Scanner(System.in);
  private static PrintStream o         = System.out;
  private static Random      rnd       = null;
  private static String      input     = "";
  private static String      inputCaps = "";
  private static boolean     correct   = false;
  private static long        inLong    = 0;
  private static int         inInt     = -1;
  
  // Player and game setup variables
  private static Player       p1        = null;
  private static Player       p2        = null;
  private static Player       winner    = null;
  private static Gen_RPS_Game game      = null;
  private static String       name      = "";
  private static boolean      isHuman   = false;
  private static int          gameType  = -1;
  private static int          winScore  = -1;
  private static long         seed      = 0;
  
  private static final  int  NUM_GAMES = 4;
  private static String[] gameTitles = {"Rock, Paper, Scissors",
                                        "Rock, Paper, Scissors, Lizard, Spock",
                                        "Rock, Paper, Scissors, Lizard, Spock,\n"
                                      + "Gun, Pacifist",
                                        "Rock, Paper, Scissors, Lizard, Spock,\n"
                                      + "Gun, Pacifist, Lightning, Laptop"};
    
  private Main_Menu ()
  {
    // The Main_Menu class is never instantiated into an object, thus this
    // constructor is never called.
  }
  
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
    
    // Secret seed setting (for repeatability of randomized elements)
    o.println("Secret seed (y or n)?");
    input = scnr.next();
    inputCaps = input.toUpperCase();
    while ((inputCaps.intern() != "Y") && (inputCaps.intern() != "N"))
    {
      o.println("Try again. Use secret seed? Type \"y\" or \"n\" and "
              + "press <Enter>");
      input = scnr.next();
      inputCaps = input.toUpperCase();
      o.println(inputCaps.intern() == "N");
    }
    if (input.toUpperCase() == "Y")
    {
      inInt = 0;
      while (inInt != 1)
      {
        o.println("Enter an integer (long int) number:");
        inLong = scnr.nextLong();
        seed = inLong;
        o.println("Secret seed will be: " + seed);
        o.println("Is this okay? If it is, type \"1\" and press <Enter>:");
        inInt = scnr.nextInt();
      }
      rnd = new Random(seed);  // Use given seed . . . 
    }
    else
    {
      rnd = new Random();      // . . . or use a random seed
    }
     
    // ******************
    // * Player 1 block *
    // ******************
    correct = false;
    while (!correct)
    {
      o.println();
      o.println("What will Player 1's name be?");
      o.println("Type a name, or type a lowercase \"r\" for a random name, then "
              + "press <Enter>:");
      name = scnr.nextLine();
      name = scnr.nextLine();
      if (name.intern() == "r")
      {
        name = makeRandomName();
      }
      o.println("Will Player 1 be a human or the computer?");
      o.println("Press <H> for human, or <C> for computer, then press <Enter>:");
      input = scnr.next();
      inputCaps = input.toUpperCase();
      while ((inputCaps.intern() != "H") && (inputCaps.intern() != "C"))
      {
        o.println("Please try again. Player 1: <H> for Human, <C> for computer:");
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
      
      o.println("Verify:");
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
        o.println("Is this correct? (Type <Y> or <N>, then <Enter>)");
        input = scnr.next();
        inputCaps = input.toUpperCase();
      }
      if (inputCaps.intern() == "N")
      {
        correct = false;
      }
      else
      {
        p1 = (Player) Player.makePlayer(isHuman, name);
        correct = true;
      }
    }
    // END Player 1 block
   
    // ******************
    // * Player 2 block *
    // ******************
    correct = false;
    while (!correct)
    {
      o.println();
      o.println("What will Player 2's name be?");
      o.println("Type a name, or type a lowercase \"r\" for a random name, then "
              + "press <Enter>:");
      name = scnr.nextLine();
      name = scnr.nextLine();
      if (name.intern() == "r")
      {
        name = makeRandomName();
      }
      o.println("Will Player 2 be a human or the computer?");
      o.println("Press <H> for human, or <C> for computer, then press <Enter>:");
      input = scnr.next();
      inputCaps = input.toUpperCase();
      while ((inputCaps.intern() != "H") && (inputCaps.intern() != "C"))
      {
        o.println("Please try again. Player 2: <H> for Human, <C> for computer:");
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
      
      o.println("Verify:");
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
        o.println("Is this correct? (Type <Y> or <N>, then <Enter>)");
        input = scnr.next();
        inputCaps = input.toUpperCase();
      }
      if (inputCaps.intern() == "N")
      {
        correct = false;
      }
      else
      {
        p2 = (Player) Player.makePlayer(isHuman, name);
        correct = true;
      }
    }
    // END Player 2 block
    
    // ******************
    // * MAIN PLAY LOOP *
    // ******************

    while (true)
    {
      // Get game type
      inInt = 0;
      while ((inInt < 1) || (inInt > (NUM_GAMES + 1)))
      {
        o.println("Choose a game by typing the number, then pressing <Enter>:");
        int i = 0;
        for (String game : gameTitles)
        {
          i++;
          o.println(i + ") " + game);
        }
        i++;
        o.println(i + ") or <" + i + "> to quit the program.");
        inInt = scnr.nextInt();
      }
      if (inInt == (NUM_GAMES + 1))
      {
        break; // Exit the Main Play Loop
      }
      else
      {
        gameType = inInt - 1;
      }
      
      // Get winning score
      o.println("How many victorious throws to win the game?");
      o.println("(Type a number between 1 and 100, then press <Enter>:");
      inInt = scnr.nextInt();
      while ((inInt < 1) || (inInt > 100))
      {
        o.println("Please try again. Type a number between 1 and 15, then press "
                + "<Enter>:");
        inInt = scnr.nextInt();
      }
      winScore = inInt;
      
      // ******************
      // * Run that game! *
      // ******************
      game = new Gen_RPS_Game (p1, p2, gameType, winScore, scnr, rnd);
      winner = game.mainLoop();
      winner.addWin();
      o.println(winner.getName() + " wins that game!");
      o.println("The match stands at:");
      o.println(p1.getName() + " has " + p1.getWins() + "wins, and");
      o.println(p2.getName() + " has " + p2.getWins() + "wins.");
      o.println("Let's move on to the next game!");
      o.println();
      o.println();
    }
    
    // ***************************************
    // * Output results and exit the program *
    // ***************************************
    o.println();
    o.println("***************************************************");
    if (p1.getWins() == p2.getWins())
    {
      o.println("The matchup was a TIE!");
      o.println(p1.getName() + " had " + p1.getWins() + "wins, and");
      o.println(p2.getName() + " had " + p2.getWins() + "wins.");
      o.println("Congratulations to both of you!");
    }
    else if (p1.getWins() > p2.getWins())
    {
      o.println(p1.getName() + " WINS!");
      o.println(p1.getName() + " had " + p1.getWins() + "wins, and");
      o.println(p2.getName() + " had " + p2.getWins() + "wins.");
      o.println("Congratulations, " + p1.getName() + "!");
    }
    else if (p2.getWins() > p1.getWins())
    {
      o.println(p2.getName() + " WINS!");
      o.println(p1.getName() + " had " + p1.getWins() + "wins, and");
      o.println(p2.getName() + " had " + p2.getWins() + "wins.");
      o.println("Congratulations, " + p2.getName() + "!");
    }
    return 0; // Return to main() and exit the program
  }
  
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
}
































