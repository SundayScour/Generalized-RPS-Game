package Player_class.Player_derived_classes;

import Player_class.Player;
import java.util.Random;
import java.util.Scanner;

public class NPC implements Player
{
  private boolean human;
  private String  name;
  private int     wins;
  private int     score;
  private int     myThrow;
  
  private NPC ()
  {
    human   = true;
    name    = "UNNAMED";
    wins    = -1;
    score   = -1;
    myThrow = -1;
  }
  
  public NPC (boolean human, String name)
  {
    this.human   = human;
    this.name    = name;
    this.wins    = 0;
    this.score   = 0;
    this.myThrow = -1;
  }
  
  @Override
  public boolean getHuman ()
  {
    return human;
  }
  
  public String getName ()
  {
    return name;
  }
  
  @Override
  public int getWins ()
  {
    return wins;
  }
  
  @Override
  public void addWin ()
  {
    wins += 1;
  }
  
  @Override
  public int getScore ()
  {
    return score;
  }
  
  @Override
  public void incScore ()
  {
    score += 1;
  }
  
  @Override
  public void incScore (int addPoints)
  {
    score += Math.abs(addPoints);
  }
  
  @Override
  public void decScore ()
  {
    score -= 1;
  }
  
  @Override
  public void decScore (int removePoints)
  {
    score -= Math.abs(removePoints);
  }
  
  @Override
  public void resetScore()
  {
    score = 0;
  }
  
  public int rpsThrow (Random rnd, int nMax, String[] pieceNames)
  {
//    System.out.println("Throw between 0 and (" + nMax + " - 1)");
    int myThrow =  rnd.nextInt(nMax);
//    System.out.println(pieceNames[myThrow]); // Consistency verification
    return myThrow;
  }

  public int rpsThrow(Random rnd, int nMax)
  {
//    System.out.println("Throw between 0 and (" + nMax + " - 1)");
    return  rnd.nextInt(nMax);
  }

  public int rpsThrow(Scanner scnr, int nMax, String[] pieceNames)
  {
    // TODO Auto-generated method stub
    
    // ONLY IMPLEMENTED IN PC CLASS
    return -1;
  }
  
  public int getThrow()
  {
    return myThrow;
  }
}



















































