package edu.duke.ece651.SallysStash;

public class pixel{
    private int ID;
    private char color;
    private boolean IsHit;
    private boolean IsMiss;
  //private boolean empty;
    private int Num;
    pixel() {
        this.ID = 0;
        this.color = 'E';
        this.IsHit = false;
        this.IsMiss = false;
    }

    public void setNum(int Num){this.Num = Num;}
    public int getNum(){return this.Num;}
    public void setColor(char color){
        this.color = color;
    }
    public char getColor(){
        return this.color;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    public boolean getIsMiss(){
        return this.IsMiss;
    }
    public boolean getIsHit(){
        return this.IsHit;
    }
    public void setIsHit(boolean value){
        this.IsHit = value;
    }
    public void setIsMiss(boolean value){
        this.IsMiss = value;
    }
    public int getID(){return this.ID;}
}
