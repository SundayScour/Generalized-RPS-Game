package Player_class.Player_derived_classes;

import Player_class.Player;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class PC implements Player 
{
  private boolean human;
  private String  name;
  private int     wins;
  private int     score;
  private int     myThrow;
 
  private PC ()
  {
    human   = false;
    name    = "UNNAMED";
    wins    = -1;
    score   = -1;
    myThrow = -1;
  }
  
  public PC (boolean human, String name)
  {
    this.human    = human;
    this.name     = name;
    this.wins     = 0;
    this.score    = 0;
    this.myThrow  = -1;
  }
  
  @Override
  public boolean getHuman ()
  {
    return human;
  }

  @Override
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
  public void resetScore ()
  {
    score = 0;
  }
  
  public int rpsThrow (Scanner scnr, int nMax, String[] pieceNames)
  {
    PrintStream o = System.out;
    int pcThrow = -1;
    int myThrow = -1;
    int i = 0;
    while ((pcThrow < 1) || (pcThrow > nMax))
    {
      o.println("What do you throw?:");
      i = 0;
      for (String name : pieceNames)
      {
        i++;
        o.println(i + ") " + name);
      }
      o.print(":");
      pcThrow = scnr.nextInt();
    }
    myThrow = pcThrow - 1;
    //o.println(pieceNames[myThrow]); // Consistency verification
    return myThrow;
  }
  
  public int rpsThrow (Random rnd, int nMax)
  {
    // TODO Auto-generated method stub
    
    // ONLY IMPLEMENTED IN NPC CLASS  
    return -1;
  }

  public int rpsThrow(Random rnd, int nMax, String[] pieceNames)
  {
    // TODO Auto-generated method stub
    
    // ONLY IMPLEMENTED IN NPC CLASS
    return -1;
  }
  
  public int getThrow()
  {
    return myThrow;
  }

}

























