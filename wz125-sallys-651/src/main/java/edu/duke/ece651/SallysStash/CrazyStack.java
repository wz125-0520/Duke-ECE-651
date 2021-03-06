package edu.duke.ece651.SallysStash;

import java.util.ArrayList;

public class CrazyStack implements shape, ForCheck {
    private char color;
    private char direction;
    private int dimension;
    private int ref_x, ref_y;
    private int next_x, next_y;
    private int check_x, check_y;
    private int width, length;
    CrazyStack(){}

    public void SetForCheck(int x, int y, char direction){
        this.ref_x = x;
        this.ref_y = y;
        if(direction == 'U' || direction == 'D'){
            this.width = 2;
            this.length = 5;
            this.next_x = ref_x + 2;
            this.check_x = x;
            switch (direction){
                case 'U':
                    this.next_y = this.ref_y + 1;
                    this.check_y = y;
                    break;
                case 'D':
                    this.next_y = this.ref_y - 1;
                    this.check_y = y - 1;
                    break;
            }
        }
        else {
            this.width = 5;
            this.length = 2;
            this.next_y = ref_y + 2;
            this.check_y = ref_y;
            switch (direction){
                case 'R':
                    this.next_x = this.ref_x - 1;
                    this.check_x = this.ref_x - 1;
                    break;
                case 'L':
                    this.next_x = this.ref_x + 1;
                    this.check_x = this.ref_x;
                    break;
            }
        }
    }
  public boolean CheckStack(boolean isComputer, int x, int y, char direction, int Dimension, board BD, int CurrID){
        SetForCheck(x, y, direction);
        boolean valid = true;
        if(this.check_x + this.length > 20 || this.check_y + this.width > 10){
          valid = false;
        }
        if( this.next_x < 0 || this.next_x > 20 || this.next_y < 0 || this.next_y > 10 ){
          valid = false;
        }
        if(!valid){
          if (!isComputer) {
            System.out.println("The input you typed in is out of the board! Please try again!");
          }
          return false;
        }
        valid = ExistStack(this.ref_x, this.ref_y, this.next_x, this.next_y, direction, Dimension, BD, CurrID);
        if(!isComputer && !valid){
          System.out.println("The location already has a stack! Please try again!");
        }
        return valid;
    }

    public void SetCrazyStack(char color, char direction){
        this.color = color;
        this.direction = direction;
        this.dimension = 3;
    }

    public void SetInBoard(board bd, int ID, ArrayList<Integer> RecordHit){
        switch (this.direction) {
            case 'U':
                SetSeparateStack.SetColumn(bd, ID, this.ref_x, this.ref_y, this.color, 0, RecordHit);
                SetSeparateStack.SetColumn(bd, ID, this.next_x, this.next_y, this.color, -3, RecordHit);
                break;
            case 'R':
                SetSeparateStack.SetRow(bd, ID, this.ref_x, this.ref_y, this.color, 0, RecordHit);
                SetSeparateStack.SetRow(bd, ID, this.next_x, this.next_y, this.color, -3, RecordHit);
                break;
            case 'D':
                SetSeparateStack.SetColumn(bd, ID, this.next_x, this.next_y, this.color, 2, RecordHit);
                SetSeparateStack.SetColumn(bd, ID, this.ref_x, this.ref_y, this.color, 5, RecordHit);
                break;
            case 'L':
                SetSeparateStack.SetRow(bd, ID, this.next_x, this.next_y, this.color, 2, RecordHit);
                SetSeparateStack.SetRow(bd, ID, this.ref_x, this.ref_y, this.color, 5, RecordHit);
                break;
        }
    }

    public boolean ExistStack(int ref_x, int ref_y,int next_x, int next_y, char direction, int Dimension, board BD, int CurrID){
      for(int i = 0; i < Dimension; i++){ 
        if(direction == 'U' || direction == 'D'){
          pixel temp_ref = BD.GetStack(ref_x + i, ref_y);
          pixel temp_next = BD.GetStack(next_x + i, next_y);
          if (temp_ref.getColor() != 'E' && temp_ref.getID() != CurrID){
            return false;
          }
          if (temp_next.getColor() != 'E' && temp_next.getID() != CurrID){
            return false;
          }
        }
        else {
          pixel temp_ref = BD.GetStack(ref_x, ref_y + i);
          pixel temp_next = BD.GetStack(next_x, next_y + i);
          if (temp_ref.getColor() != 'E' && temp_ref.getID() != CurrID){
            return false;
          }      
          if (temp_next.getColor() != 'E'&& temp_next.getID() != CurrID){
            return false;
          }     
        }
      }
      return true;
    }

}
