package edu.duke.ece651.SallysStash;

import java.util.ArrayList;

public class SuperStack implements shape, ForCheck {
    private char color;
    private char direction;
    private int dimension;
    private int ref_x, ref_y;
    private int width, length;
    private int single_x, single_y;
    private int long_x, long_y;
    private int check_x, check_y;
    SuperStack(){}
    
    public void SetForCheck(int x, int y, char direction){
        this.ref_x = x;
        this.ref_y = y;
        this.check_x = x ;
        this.check_y = (direction == 'U' || direction == 'L') ? y - 1 : y;
        if(direction == 'U'){
            this.width = 3;
            this.length = 2;
            this.long_x = x + 1;
            this.long_y = y - 1;
            this.single_x = x;
            this.single_y = y;
        }
        else {
            this.long_x = x;
            this.long_y = y;
            this.single_x = x + 1;
            if(direction == 'R'){
                this.width = 2;
                this.length = 3;
                this.single_y = y + 1;
            }
            else if(direction == 'D'){
                this.width = 3;
                this.length = 2;
                this.single_y = y + 1;
            }
            else {
                this.width = 2;
                this.length = 3;
                this.single_y = y - 1;
            }
        }
    }
  
  public boolean CheckStack(boolean isComputer, int x, int y, char direction, int Dimension, board BD, int CurrID){
        SetForCheck(x, y, direction);
        boolean valid = true;
        if(this.check_x + length > 20 || this.check_y + width > 10){
            valid = false;
        }
        if((this.single_x < 0 || this.single_x > 20) || (this.long_x < 0 || this.long_x > 20) || (this.single_y < 0 || this.single_y > 10) || (this.long_y < 0 || this.long_y > 10) ){
            valid = false;
        }
        if(!valid){
          if(!isComputer){
           System.out.println("The input you typed in is out of the board! Please try again!");          
          }
          return false;
        }
        valid = ExistStack(this.single_x, this.single_y,this.long_x, this.long_y, direction,  Dimension, BD, CurrID);
        if(!isComputer && !valid){
          System.out.println("The location already has a stack! Please try again!");
          return false;
        }
        return valid;
    }
    public void SetSuperStack(char color, char direction){
        this.color = color;
        this.direction = direction;
        this.dimension = 3;
    }
    
    public void SetInBoard(board bd, int ID, ArrayList<Integer> RecordHit){
        bd.SetStack(this.single_x, this.single_y, this.color, ID, 1);
        if(RecordHit.contains(1)){
            bd.SetPixelIsHit(this.single_x, this.single_y, true);
        }
        switch (this.direction){
            case 'U':
                SetSeparateStack.SetRow(bd, ID, this.long_x, this.long_y, this.color, -1, RecordHit);
                break;
            case 'R':
                SetSeparateStack.SetColumn(bd, ID, this.long_x, this.long_y, this.color, -1, RecordHit);
                break;
            case 'D':
                SetSeparateStack.SetRow(bd, ID, this.long_x, this.long_y, this.color, 3, RecordHit);
                break;
            case 'L':
                SetSeparateStack.SetColumn(bd, ID, this.long_x, this.long_y, this.color, 3, RecordHit);
                break;
        }
    }

    public boolean ExistStack(int single_x, int single_y,int long_x, int long_y, char direction, int Dimension, board BD, int ReadID){
      pixel temp_single = BD.GetStack(single_x, single_y);
      if(temp_single.getColor() != 'E'&& temp_single.getID() != ReadID){
          return false;
      }
      for(int i = 0; i < Dimension; i++){
        if(direction == 'U' || direction == 'D'){
          pixel temp_y = BD.GetStack(long_x, long_y + i);
          if (temp_y.getColor() != 'E' && temp_y.getID() != ReadID){
            return false;
          }
        }
        else {
          pixel temp_x = BD.GetStack(long_x + i, long_y);
          if (temp_x.getColor() != 'E'&& temp_x.getID() != ReadID) {
            return false;
          }
        }
      }
      return true;
    }
}
