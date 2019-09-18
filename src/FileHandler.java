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
    protected int endOfLine = 0;
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

    ArrayList<Integer> NextClause(){
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

    ArrayList<Point> CreateListOfPointFromClause(ArrayList<Integer>[] ListOfClause) {
        int x = 0;
        int y = 0;
        List<Point> ListOfPoints = new ArrayList<>();
        for (int i = 0; i < ListOfClause.length; i++) {
            x = (int) ListOfClause[i].get(0);
            y = (int) ListOfClause[i].get(1);
            ListOfPoints.add(new Point(-x, y));
            ListOfPoints.add(new Point(-y, x));
        }
        return (ArrayList<Point>) ListOfPoints;
    }

}
