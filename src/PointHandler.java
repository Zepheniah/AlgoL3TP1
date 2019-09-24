import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class PointHandler {

    public int x;
    public int y;
    private static ArrayList<PointHandler> ListOfPoints;
    private static ArrayList<PointHandler> ListOfTranspositionPoints;

    public static ArrayList<PointHandler> getListOfTranspositionPoints() {
        return ListOfTranspositionPoints;
    }

    public static ArrayList<PointHandler> getListOfPoints() {
        return ListOfPoints;
    }

    public PointHandler(int x, int y){
        this.x = x;
        this.y = y;
    }
    public static void init(){
        PointHandler.CreateListOfPointFromClause(FileHandler.ListofClauses);
        PointHandler.CreateListOfTranspostionPoint(PointHandler.getListOfPoints());
    }

     protected static PointHandler  permute(PointHandler point){ /*permute les coordonnées x et y du point donné en entrée*/
        int z,w;
        z = point.x;
        w = point.y;
        return new PointHandler(w,z);
    }
    /*
    Prends en entrée une liste de clause
    renvoie une liste de point avec x: la source d'un arc et y: sa destination
     */
    public static ArrayList<PointHandler> CreateListOfPointFromClause(ArrayList<Integer>[] ListOfClause) {
        int x = 0;
        int y = 0;
        List<PointHandler> TempListOfPoints = new ArrayList<>();
        for (int i = 0; i < ListOfClause.length; i++) {
            x = (int) ListOfClause[i].get(0);
            y = (int) ListOfClause[i].get(1);
            TempListOfPoints.add(new PointHandler(FileHandler.Stantardize(-x), FileHandler.Stantardize(y)));
            TempListOfPoints.add(new PointHandler(FileHandler.Stantardize(-y), FileHandler.Stantardize(x)));
        }
        ListOfPoints = (ArrayList<PointHandler>)((ArrayList<PointHandler>) TempListOfPoints).clone();
        return ListOfPoints;
    }
    /*
    Prends en entrée une liste de point
    renvoie cette liste de point avec toute les coordonnées x et y permuter
    */
    public  static ArrayList<PointHandler> CreateListOfTranspostionPoint(ArrayList<PointHandler> ListOfPoint){
        List<PointHandler> TempListOfTranspositionPoints = new ArrayList<>();
        for(PointHandler point : ListOfPoint){
            TempListOfTranspositionPoints.add(permute(point));
        }
        ListOfTranspositionPoints = (ArrayList<PointHandler>)((ArrayList<PointHandler>) TempListOfTranspositionPoints).clone();
        return ListOfTranspositionPoints;
    }

    public static void PrintListOfPoints(){/*affiche ListOfPoints dans la console*/
        for(PointHandler point : ListOfPoints){
            System.out.println("x= "+point.x+" y = "+point.y);
        }
    }

    public static void PrintListOfTranspositionPoints(){/*affiche ListOfTranspositionPoints dans la console*/
        for(PointHandler point : ListOfTranspositionPoints){
            System.out.println("x= "+point.x+" y = "+point.y);
        }
    }
}
