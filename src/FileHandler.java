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
    protected static int nbClause;
    protected static int maxValue;

    protected static ArrayList<Integer>[] ListofClauses;



    public FileHandler(File fileName){
        try {
            sc = new Scanner(fileName);
            maxValue = Nextint();
            nbClause = Nextint();
            ListofClauses = new ArrayList[nbClause];
            ListOfClauses();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int getNbClause() {
        return nbClause;
    }

    public static int getMaxValue() {
        return maxValue;
    }

    public static ArrayList<Integer>[] getListofClauses() {
        return ListofClauses;
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
    public ArrayList<Integer>[] ListOfClauses(){
        for( int i = 0;i<nbClause;i++){
            ListofClauses[i] = NextClause();
        }
        return ListofClauses;
    }



}
