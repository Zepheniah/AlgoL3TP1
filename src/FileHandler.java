import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class FileHandler {
    protected Scanner sc;
    protected static int endOfLine = 0;
    public int maxValue;



    public FileHandler(File fileName){
        try {
            sc = new Scanner(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    int Nextint(){
        while(!sc.hasNextInt()){
           sc.next();
        }
        return sc.nextInt();
    }

     public ArrayList<Integer> NextClause(){
        ArrayList<Integer> Clause = new ArrayList<>();
        int lastInteger;
        do{
            lastInteger = Nextint();
            if(lastInteger != endOfLine ){
                Clause.add(lastInteger);
            }
        }while (lastInteger != endOfLine);
        return Clause;
    }



}
