package Player_class;

import java.util.Random;
import java.util.Scanner;

import Player_class.Player_derived_classes.NPC;
import Player_class.Player_derived_classes.PC;

public abstract class Player
{
  protected Boolean human;
  protected String  name;
  protected int     wins;
  protected int     score;
  protected int     myThrow;
//  protected Integer     score;
//  protected Integer     myThrow;
  

  public Player ()
  {
    human     = null;
    name      = "_UNINITIALIZED_NAME_";
    wins      = -1;
    score     = -1;
//    score     = null;
    myThrow   = -1;
//    myThrow = null;
  }
  
  public Player (boolean human, String name)
  {
    this.human      = human;
    this.name       = name;
    this.wins       = 0;
    this.score      = 0;
    this.myThrow    = -1;
//    this.myThrow    = null;
  }
  
  public static Player makePlayer (boolean human, String name)
  {
    Object madePlayer = null; // Start from base, so no need to cast either called constructor
    
    if (human)
    {
      madePlayer = new PC(human, name);
    }
    else if (!human)
    {
      madePlayer = new NPC(human, name);
    }
    
//    System.out.println("human = " + human + " name = " + name); // Early development test output
//    System.out.println(madePlayer.toString());                  // Early development test output
    
    return (Player)madePlayer; // NOW I've gotta cast to return proper type
  }
  
  public int getThrow()
  {
    return (int)myThrow;
  }
  
  public boolean getHuman ()
  {
    return (boolean)human;
  }
  
  public String getName ()
  {
    return name;
  }
  
  public int getWins ()
  {
    return wins;
  }
  
  public void addWin ()
  {
    wins++;
  }
  
  public int getScore ()
  {
    return score;
  }
  
  public void incScore ()
  {
    score++;
  }
  
  public void incScore (int addPoints)
  {
    score += Math.abs(addPoints);
  }
  
  public void decScore ()
  {
    score--;
  }
  
  public void decScore (int removePoints)
  {
    score -= Math.abs(removePoints);
  }
  
  public void resetScore ()
  {
    score = 0;
  }
 }









































































































