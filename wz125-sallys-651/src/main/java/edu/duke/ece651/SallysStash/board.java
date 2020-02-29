package edu.duke.ece651.SallysStash;
public class board{
    private pixel[][] cell;
    board(){
        this.cell = new pixel[20][10];
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 10; j++){
                this.cell[i][j] = new pixel();
            }
        }
    }
    public int TotalHit(){
        int totalhit = 0;
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 10; j++){
               if(this.cell[i][j].getIsHit()){
                   totalhit++;
               }
            }
        }
        return totalhit;
    }
  public void SetStatus(boolean isComputer, int row, int column, board OppoBoard, char who){
        char color_temp = OppoBoard.GetPixelColor(row, column);
        StackInfo Color = new StackInfo();
        if( color_temp != 'E'){
          this.SetPixelColor(row, column, color_temp);
          OppoBoard.SetPixelIsHit(row, column, true);//hit for myself but need to store the color to others
          if(isComputer){
            int ID_Num = OppoBoard.GetStack(row, column).getID();
            System.out.println("Player " + who + " found your " + Color.GetPrompt(ID_Num) + " stack at " + (char)(row + 'A') + (char)(column + '0'));
          }
          else{
            System.out.println("You found a stack!");
          }
        }
        else {
          SetPixelIsMiss(row, column, true);//miss for showing to others
          if (!isComputer) {
            System.out.println("You Missed!");
          }
        }
    }

    public void SetStack(int x, int y , char color, int ID, int Num) {
            this.cell[x][y].setColor(color);
            this.cell[x][y].setID(ID);
            this.cell[x][y].setNum(Num);
    }
    public pixel GetStack(int i, int j){
        return this.cell[i][j];
    }
    public int GetPixelNum(int x, int y){
        return this.cell[x][y].getNum();
    }
    public char GetPixelColor(int x, int y){
        return this.cell[x][y].getColor();
    }
    public void SetPixelColor(int x, int y, char color){
        this.cell[x][y].setColor(color);
    }
    public void SetPixelIsMiss(int x, int y, boolean value){ this.cell[x][y].setIsMiss(value);}
    public void SetPixelIsHit(int x, int y, boolean value){ this.cell[x][y].setIsHit(value);}
    public boolean GetPixelIsMiss(int x, int y){ return this.cell[x][y].getIsMiss();}
    public boolean GetPixelIsHit(int x, int y){ return this.cell[x][y].getIsHit();}
}

