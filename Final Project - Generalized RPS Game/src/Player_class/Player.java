package Player_class;

import java.util.Random;
import java.util.Scanner;

import Player_class.Player_derived_classes.NPC;
import Player_class.Player_derived_classes.PC;

public interface Player
{
//   public boolean human;
/*
  Protected String name;
  protected int wins;
  protected int score;

  
  public Player ()
  {
    // DOES NOTHING.
  }
*/  
  public static Object makePlayer (boolean human, String name)
  {
    Object madePlayer = null;
    if (human)
    {
      madePlayer = new PC(human, name);
    }
    else if (!human)
    {
      madePlayer = new NPC(human, name);
    }
    
//    System.out.println("human = " + human + " name = " + name);
//    System.out.println(madePlayer.toString());
    return madePlayer;
  }
  
//  public int getThrow();
//  {
//    return -12345678;
//  }
  
  public boolean getHuman ();
//  {
//    return false;
//  }
  
  public String getName ();
//  {
//    return "";
//  }
  
  public int getWins ();
//  {
//    return -1;
//  }
  
  public void addWin ();
//  {
//  }
  
  public int getScore ();
//  {
//    return -1;
//  }
  
  public void incScore ();
//  {
//  }
  
  public void incScore (int addPoints);
//  {
//  }
  
  public void decScore ();
//  {
//  }
  
  public void decScore (int removePoints);
//  {
//  }
  
  public void resetScore ();
 
  public int rpsThrow (Random rnd, int nMax);

//  {
//  }
  
  public int rpsThrow (Random rnd, int nMax, String[] pieceNames);
//  {  
//  }
  
  public int rpsThrow (Scanner scnr, int nMax, String[] pieceNames);
//  {
//  }
}


























