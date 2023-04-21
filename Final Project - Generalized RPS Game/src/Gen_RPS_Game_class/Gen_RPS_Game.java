package Gen_RPS_Game_class;

import Player_class.Player;
import Player_class.Player_derived_classes.PC;
import Player_class.Player_derived_classes.NPC;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintStream;

public class Gen_RPS_Game
{
  public boolean validGame;
  private Random      rnd;
  private Scanner     scnr;
  private PrintStream o;
  private int         gameType;
  private String      gameTitle;
  private int         nMax;
  private String[]    pieceNames;
  private String[][]  verbMatrix;
  private Player      p1;
  private int         p1Throw;
  private Player      p2;
  private int         p2Throw;
  private Player      throwWinner;
  private Player      throwLoser;
  private int         winScore;
  
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
  
  public Gen_RPS_Game (Player p1, Player p2, int gameType, int winScore, Scanner scnr, Random rnd)
  {
    if (p1.getHuman())
    {
      this.p1 = (PC) p1;
    }
    else
    {
      this.p1 = (NPC) p2;
    }
    if (p2.getHuman())
    {
      this.p2 = (PC) p2;
    }
    else
    {
      this.p2 = (NPC) p2;
    }
    this.gameType = gameType;
    this.winScore = winScore;
    this.scnr = scnr;
    this.rnd = rnd;
    o = System.out;
    
    gameSetup();
  }
  
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
                                     {"cut", "decapitate", "stab", "short curcuit"},
                                     {"smothers", "disproves", "jams", "flys away from"}};
        validGame = true;
        break;
      }
    }
  }
  
  public Player mainLoop ()
  {
    o.println("*****************");
    o.println(gameTitle);
    o.println("*****************");
    o.println();
    o.println("B E G I N !");
    
    while ((p1.getScore() < winScore) && (p2.getScore() < winScore))
    {
      o.println(p1.toString());
      if (p1.getHuman())
      {
        p1.PCThrow(scnr, nMax, pieceNames);
      }
      else
      {
        p1.NPCThrow(rnd, nMax, pieceNames);
      }
      o.println(p2.toString());
      if (p2.getHuman())
      {
        p2.PCThrow(scnr, nMax, pieceNames);
      }
      else
      {
        p2.NPCThrow(rnd, nMax, pieceNames);
      }
      if (p1.getThrow() == p2.getThrow())
      {
        o.println("Tie!");
      }
      else
      { 
        evalThrows();
        printWin(throwWinner, throwLoser);
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
  
  private void evalThrows ()
  {
 
    // ***** Swing and a miss on first formula: *****
    //if ((((p1.getThrow() + 1) / (nMax - 1)) % 2) == ((p2.getThrow() / (nMax - 1)) % 2))
    // * End Strike 1 *
    
    int     p1T     = p1.getThrow();
    int     p2T     = p2.getThrow();
    Boolean p1Wins  = false;
    
    // ***** Strike 2 on second formula: *****
    //if ( (p2T % 2) == (((p1T + 1) % nMax) % 2) )
    // * End Strike 2 *
    
    // Let's eschew elegance and go BRUTE FORCE!: 
    for (int i = 0; i < ((nMax - 1) / 2); i++)
    {
      if (p2T == (((p1T + 1) + (2 * i)) % nMax))
      {
        p1Wins = true;
        break;
      }
    }
    // YAY! It worked! HOME RUN!!!!
    
    
    if (p1Wins)
    {
      throwWinner = p1;
      throwLoser = p2;
      p1.incScore();
      o.println(pieceNames[throwWinner.getThrow()] + " beats " + pieceNames[throwLoser.getThrow()]);
    }
    else
    {
      throwWinner = p2;
      throwLoser = p1;
      p2.incScore();
      o.println(pieceNames[throwWinner.getThrow()] + " beats " + pieceNames[throwLoser.getThrow()]);
    }
  }
  
  private void printWin(Player winner, Player loser)
  {
    String winName   = winner.getName();    
    String winPiece  = pieceNames[winner.getThrow()];
    String loseName  = loser.getName();
    String losePiece = pieceNames[loser.getThrow()];
    int    verbRow   = winner.getThrow();
    int    verbCol   = ((loser.getThrow() - winner.getThrow() / 2)) % (gameType + 1);
    if (verbCol < 0)
    {
      //verbCol += (gameType + 1);
      //verbCol %= (gameType + 1);
      verbCol = Math.abs(verbCol);
    }
    o.println("verbCol = " + verbCol);
    String winVerb   = verbMatrix[verbRow][verbCol];
        
    o.println(winPiece + " " + winVerb + " " + losePiece + "! " + winName + " wins!");
    o.println();
  }
}



























































