/**
 * Here is my Final Project for CSC 222-1H0F SP23 at Laurel Ridge Community College
 * 
 * Generalized RPS Game
 * 
 * (Note: RPS stands for "Rock, Paper, Scissors")
 * 
 * Version 0.4 - "Presentation Mode": The version presented in front of fellow students
 * on the last day of CSC 222-1H0F SP23 in Spring of 2023 at Laurel Ridge Community
 * College on Monday, 8 May 2023 starting at 5PM. 
 * 
 * This 0.4 version:
 * 
 * 1) Implements suggestions from Doug Green for implementing the rpsThrow() method, without undue
 *    complications.
 *    
 *    The main suggestion was to make Random rnd an attribute of the NPC derived class and Scanner
 *    scnr as an attribute of the PC derived class. Then rpsThrow could be an abstract method of 
 *    the base class Player, with the common parameters int nMax and String[] pieceNames, then the
 *    derived classes PC and NPC could override the method using their Scanner and Random
 *    attributes, respectively, as needed.  
 *    
 *    HOWEVER, I'll keep rpsThrow as an Interface rather than make it a method within the abstract
 *    base class Player, JUST SO I CAN have the experience of using an interface within a Java
 *    project.
 *    
 * 2) Implements elmination of all commented out code, with the exception of potentially useful
 *    debugging outputs.
 *    
 * 3) Implements exception handling, expecially for scnr.nextInt(); calls, and attempting to call
 *    methods on invalid Player and Gen_RPS_Game objects.
 *    
 * 4) Breaks out into methods the code blocks within Main_Menu.mainMenuLoop(). Ex: Making the two
 *    Players, selecting a game, etc.
 *    
 * 5) Adds option at EACH input to quit (either quit the game, within a game, or quit the program
 *    within the main menu.
 *    
 * 6) Adds in all necessary comments to make a JavaDoc, and simplifying and eliminating other
 *    comments. The simplification was another suggestion of Doug Green. FULL JavaDoc COMPILATION
 *    COMPLETED!!!
 * 
 * 
 * @title Generalized RPS Game
 * @version 0.4
 * @author Jonathan Wayne Edwards on Monday 8 May 2023
 */
package Main_class;

// Java imports
import java.util.Scanner;

//Custom imports from other parts of the program
import Main_Menu_class.Main_Menu;


/**
 * The Main class and main() methods; standard for Java programs.
 * Simply calls the REAL main method: Main_Menu.mainMenuLoop().
 * 
 * @author Jonathan Wayne Edwards on Monday 8 May 2023
 */
public class Main
{
  /**
   * The standard entry point for non-GUI Java programs.
   * 
   * @param args String[] NOT USED
   */
  public static void main (String [] args)
  {
    Main_Menu.mainMenuLoop();
  }
}