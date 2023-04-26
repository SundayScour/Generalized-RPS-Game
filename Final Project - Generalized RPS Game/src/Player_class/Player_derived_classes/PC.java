package Player_class.Player_derived_classes;

import Player_class.Player;
import Player_class.Player_interfaces.PC_Throw_interface;

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class PC extends Player implements PC_Throw_interface
{
  private boolean human;    // Overrides the Boolean wrapper type human from Player base class
//  private int     myThrow;  // Overrides the Integer wrapper type myThrow from Player base class
  
  public PC ()
  {
    super();
    human = true;
  }
  
  public PC (boolean human, String name)
  {
    super ((Boolean)human, name);
  }
  
  public void rpsThrow (Scanner scnr, int nMax, String[] pieceNames)
  {
    PrintStream o = System.out;
    
    int pcThrow = -1;
    while ((pcThrow < 1) || (pcThrow > nMax))
    {
      o.println("What do you throw?:");
      
      int i = 0;
      for (String name : pieceNames)
      {
        i++;
        o.println(i + ") " + name);
      }
      o.print(":");
      pcThrow = scnr.nextInt();
    }
    
    this.myThrow = pcThrow - 1;
    //o.println(pieceNames[myThrow]); // Consistency verification
  }
}

//////////////////////////////////////////////////////////
//                                                      //
// Below are the old "guts" of the PC class, before I   //
// "refactored" with a whole new paradigm. That is, I   //
// COMPLETELY reworked the data structures, turning the //
// Player interface into an abstract class, splitting   //
// off the rpsThrow() methods into TWO interfaces, then //
// having the NPC and PC classes extend Player and      //
// implement their respective interfaces for rpsThrow() //
//                                                      //
//////////////////////////////////////////////////////////
//                                                      //
// P.S. I know <slash><star> and <star><slash> are      //
// SUPPOSED to be used ONLY for JavaDocs, but there's   //
// just, as far as I know, no other convenient way to   //
// comment off dozens or hundreds of lines of code in   //
// two characters. Sorry. Maybe I'll learn a better way //
// -J.W.E. Night of Tue., 25 April 2023                 //
//                                                      //
//////////////////////////////////////////////////////////


/*
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
*/

















































































