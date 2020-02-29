package edu.duke.ece651.SallysStash;

public interface ForCheck {
  void SetForCheck(int x, int y, char direction);
  boolean ExistStack(int ref_x, int ref_y,int next_x, int next_y, char direction, int Dimension, board BD, int CurrID);
}
