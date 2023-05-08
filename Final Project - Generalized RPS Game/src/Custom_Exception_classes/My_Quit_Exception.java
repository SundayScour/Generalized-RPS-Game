package Custom_Exception_classes;

/**
 * This custom exception type is unnecessary, but I wanted to include a
 * custom exception type in my program as practice.
 * 
 * @author Jonathan Wayne Edwards on Monday 8 May 2023
 *
 */
@SuppressWarnings("serial") // Prevents a weird warning during compile
public class My_Quit_Exception extends Exception
{
  /**
   * Just the default contructor to make my custom exception
   */
  public My_Quit_Exception ()
  {    
  }
  
  /**
   * This constructor also accepts a message about the error.
   * In practice, this exception is always thrown with the
   * message "* I QUIT! *"
   * 
   * @param message A message describing the error. Passed to the base Java
   *        Exception class using the super() constructor.
   */
  public My_Quit_Exception (String message)
  {
    super(message);
  }
}
