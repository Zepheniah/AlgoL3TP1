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

    /**
     * find the next Integer in the text file and returns it
     * if it's negative,it's processed
     * @return entier Next Integer in the text file
     */
    int Nextint(){
        while(!sc.hasNextInt()){
           sc.next();
        }
        int entier = sc.nextInt();
        if(entier<0)entier = abs(entier) + getMaxValue();
        return entier;
    }

    /**
     *
     * @param x Integer that may need to be processed to works with the upcoming methods & graph
     * @return x the processed Integer
     */
    public static int Stantardize(int x){ /*revoie un entier unique en fonction de l'entier en entrée*/
        if( x<0 && abs(x)>FileHandler.getMaxValue()) x = abs(x) - FileHandler.getMaxValue();
        if( abs(x) <= getMaxValue() && x<0 ) x = abs(x) + getMaxValue();
        return x;
    }

    /**
     * Find the next Clause in the text file delimited by 0
     * @return Clause in the form of a list
     */
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

    /**
     * Stock every clause found in NextClause() in an array
     * @return ListOfClauses all the clause in the textfile grouped in an array
     */
    public ArrayList<Integer>[] ListOfClauses(){ /*renvoie un tableau de toutes les clauses du fichier*/
        for( int i = 0;i<nbClause;i++){
            ListofClauses[i] = NextClause();
        }
        return ListofClauses;
    }



}
