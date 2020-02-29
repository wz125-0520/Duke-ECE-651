package edu.duke.ece651.SallysStash;

import java.util.ArrayList;

public interface shape {
        void SetInBoard(board bd, int ID, ArrayList<Integer> RecordHit);
  boolean CheckStack(boolean isComputer, int x, int y, char direction, int Dimension, board BD, int CurrID);
}

