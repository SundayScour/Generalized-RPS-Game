package Player_class.Player_derived_classes;

import Player_class.Player;
import Player_class.Player_interfaces.NPC_Throw_interface;

import java.util.Random;
import java.util.Scanner;

public class NPC extends Player implements NPC_Throw_interface
{
  private boolean human; // Overrides the Boolean wrapper type human from Player base class
//  private int     myThrow;  // Overrides the Integer wrapper type myThrow from Player base class
  
  public NPC ()
  {
    // Should not be called
    super();
    human = false;
  }
  
  public NPC (boolean human, String name)
  {
    super((Boolean)false, name);
  }
  
  @Override
  public void rpsThrow(Random rnd, int nMax)
  {
    // TODO Auto-generated method stub
    
    //System.out.println("Throw between 0 and (" + nMax + " - 1)");
    myThrow = rnd.nextInt(nMax);
    //System.out.println("NPC method: " + myThrow);
  }

  @Override
  public void rpsThrow(Random rnd, int nMax, String[] pieceNames)
  {
    // TODO Auto-generated method stub
    
    //System.out.println("Throw between 0 and (" + nMax + " - 1)"); // For early debugging
    myThrow =  rnd.nextInt(nMax);
    //System.out.println(pieceNames[myThrow]); // Consistency verification    
  }
}  

//////////////////////////////////////////////////////////
//                                                      //
// Below are the old "guts" of the NPC class, before I  //
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
  
  //@Override
  public boolean getHuman ()
  {
    return human;
  }
  
  public String getName ()
  {
    return name;
  }
  
  //@Override
  public int getWins ()
  {
    return wins;
  }
  
  //@Override
  public void addWin ()
  {
    wins += 1;
  }
  
  //@Override
  public int getScore ()
  {
    return score;
  }
  
  //@Override
  public void incScore ()
  {
    score += 1;
  }
  
  //@Override
  public void incScore (int addPoints)
  {
    score += Math.abs(addPoints);
  }
  
  //@Override
  public void decScore ()
  {
    score -= 1;
  }
  
  //@Override
  public void decScore (int removePoints)
  {
    score -= Math.abs(removePoints);
  }
  
  //@Override
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
*/

















































































