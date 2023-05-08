package Player_class.Player_interfaces;

/**
 * This functionality could be done more simply as a method within
 * the abstract base Player class, but I want experience using
 * Interfaces on this project.
 * 
 * @author Jonathan Wayne Edwards on Monday 8 May 2023
 *
 */
public interface Throw_interface
{
  /**
   * This method, when implemented by derived classes, generates
   * a throw for that Player object. For PC, it asks the user for
   * input. For NPC, it simply returns a random number between 0
   * and nMax - 1.
   * @param nMax The number of throwable pieces for a particular
   *        generalized game. 
   * @param pieceNames The String names of the pieces for a given
   *        generalized game.
   */
  public void rpsThrow (int nMax, String[] pieceNames);
}
