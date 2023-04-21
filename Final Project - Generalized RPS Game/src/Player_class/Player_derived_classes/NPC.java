package Player_class.Player_derived_classes;

import Player_class.Player;
import java.util.Random;

public class NPC extends Player
{
  private int myThrow = -1;
  
  private NPC ()
  {
    human = true;
    name  = "UNNAMED";
    wins  = -1;
    score = -1;
  }
  
  public NPC (boolean human, String name)
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
  public void NPCThrow (Random rnd, int nMax, String[] pieceNames)
  {
    System.out.println("Throw between 0 and (" + nMax + " - 1)");
    myThrow =  rnd.nextInt(nMax);
    System.out.println(pieceNames[myThrow]); // Consistency verification
  }
}

















