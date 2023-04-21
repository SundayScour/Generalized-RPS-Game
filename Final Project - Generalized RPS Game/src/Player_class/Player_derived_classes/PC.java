package Player_class.Player_derived_classes;

import Player_class.Player;
import java.io.PrintStream;
import java.util.Scanner;

public class PC extends Player 
{
  private int myThrow = -1;
  
  private PC ()
  {
    human = false;
    name  = "UNNAMED";
    wins  = -1;
    score = -1;
  }
  
  public PC (boolean human, String name)
  {
    this.human = human;
    this.name  = name;
    this.wins  = 0;
    this.score = 0;
  }
  
  public int getThrow()
  {
    return myThrow;
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
  
  //@Override
  public void PCThrow (Scanner scnr, int nMax, String[] pieceNames)
  {
    PrintStream o = System.out;
    int pcThrow = -1;
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
  }
}

























