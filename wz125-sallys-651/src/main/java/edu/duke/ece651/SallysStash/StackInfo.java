package edu.duke.ece651.SallysStash;

import java.util.*;

public class StackInfo {
    private HashMap<Integer, ArrayList<Integer> > Stack;
    private HashMap<Integer, String> Prompt;

    StackInfo(){
        this.Prompt = new HashMap<Integer, String>();
        this.Stack = new HashMap<Integer, ArrayList<Integer> >();
        for(int i = 1; i  <= 11; i++){
            if(i <= 2){
                ArrayList<Integer> set = new ArrayList<Integer>();
                set.add((int)'G');
                set.add(2);
                this.Stack.put(i, set);
                this.Prompt.put(i, "Green");
            }
            else if(i >= 3 && i <= 5){
                ArrayList<Integer> set = new ArrayList<Integer>();
                set.add((int)'P');
                set.add(3);
                this.Stack.put(i, set);
                this.Prompt.put(i, "Purple");
            }
            else if(i >= 6 && i <= 8){
                ArrayList<Integer> set = new ArrayList<Integer>();
                set.add((int)'R');
                set.add(3);
                this.Stack.put(i, set);
                this.Prompt.put(i, "Red");
            }
            else{
                ArrayList<Integer> set = new ArrayList<Integer>();
                set.add((int)'B');
                set.add(3);
                this.Stack.put(i, set);
                this.Prompt.put(i, "Blue");
            }
        }
    }
    public ArrayList<Integer> GetStack(int Key){
        return this.Stack.get(Key);
    }
    public String GetPrompt(int Key){
        return this.Prompt.get(Key);
    }

}

