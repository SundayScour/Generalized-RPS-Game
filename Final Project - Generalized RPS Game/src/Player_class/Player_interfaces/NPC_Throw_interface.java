package Player_class.Player_interfaces;

import java.util.Random;

public interface NPC_Throw_interface
{
  public void rpsThrow (Random rnd, int nMax);
  
  public void rpsThrow (Random rnd, int nMax, String[] pieceNames);
}