package edu.duke.ece651.SallysStash;
import java.util.Random;
import java.util.Vector;

public class GenerateRandom {
    public String getInputRandom(int length, int times){
        Vector<String> base = new Vector<>();
        String third = "HVhv";
        if(times >= 6){
            third = "uldrULDR";
        }
        base.add("abcdefghijklmnopqrstABCDEFGHIJKLMNOPQRST");
        base.add("0123456789");
        base.add(third);
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++){
            int number = random.nextInt(base.get(i).length());
            sb.append(base.get(i).charAt(number));
        }
        return sb.toString();
    }

    public String getInputChoice(int timesMove, int timesSonar){
        String base = "MSDmsd";
        if(timesMove == 0){
            base = base.replace("M","");
            base = base.replace("m","");
        }
        if(timesSonar == 0){
            base = base.replace("S","");
            base = base.replace("s","");
        }
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int number = random.nextInt(base.length());
        return sb.append(base.charAt(number)).toString();
    }
}
