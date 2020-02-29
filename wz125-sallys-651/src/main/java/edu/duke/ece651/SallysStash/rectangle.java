package edu.duke.ece651.SallysStash;

import java.util.ArrayList;

public class rectangle implements shape {
  private char color;
  private char direction;
  private int dimension;
  private int x;
  private int y;

  rectangle() {
  }

  public void SetRectangle(int row, int column, char direction, char color, int dimension) {
    this.color = color;
    this.direction = direction;
    this.dimension = dimension;
    this.x = row;
    this.y = column;
  }

  public void SetInBoard(board bd, int ID, ArrayList<Integer> RecordHit) {
    if (this.direction == 'H') {
      for (int i = 0; i < this.dimension; i++) {
        bd.SetStack(this.x, this.y + i, this.color, ID, i + 1);
        if (RecordHit.contains(i + 1)) {
          bd.SetPixelIsHit(this.x, this.y + i, true);
        }
      }
    } else {
      for (int i = 0; i < this.dimension; i++) {
        bd.SetStack(this.x + i, this.y, this.color, ID, i + 1);
        if (RecordHit.contains(i + 1)) {
          bd.SetPixelIsHit(this.x + i, this.y, true);
        }
      }
    }
  }

  public boolean IsValidInput(char row, char column, char direction) {
    if (direction != 'H' && direction != 'V') {
      System.out.println("The input is not valid! The third character has to be H/V.");
    } else if (row > 'T' || row < 'A') {
      System.out.println("The input is not valid! The first character needs to be a~t ot A~T.");
    } else if (column < '0' || column > '9') {
      System.out.println("The input is not valid! The column needs in the range of 0~9.");
    } else {
      return true;
    }
    return false;
  }

  public boolean CheckStack(boolean isComputer, int x, int y, char direction, int Dimension, board BD, int CurrID) {
    boolean exist = true;
    if ((x + Dimension > 20 && direction == 'V') || (y + Dimension > 10 && direction == 'H')) {
      if (!isComputer) {
        System.out.println("The input you typed in is out of the board! Please try again!");
      }
      return false;
    } else {
      for (int i = 0; i < Dimension; i++) {
        if (direction == 'H') {
          if (BD.GetStack(x, y + i).getColor() != 'E') {
            if (BD.GetStack(x, y + i).getID() != CurrID) {
              exist = false;
            }
          }
        } else {
          if (BD.GetStack(x + i, y).getColor() != 'E') {
            if (BD.GetStack(x + i, y).getID() != CurrID) {
              exist = false;
            }
          }
        }
      }
      if(!exist && !isComputer){
        System.out.println("The location already has a stack! Please try again!");
      }
      return exist;
    }
  }
}
