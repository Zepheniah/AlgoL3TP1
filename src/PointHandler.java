import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
/// Class used to generate,group or process Point related things
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
    /**permute x & y in the point in parameter and returns it
     * @param point
     * @return a new point with permuted coord**/
     protected static PointHandler  permute(PointHandler point){
        int z,w;
        z = point.x;
        w = point.y;
        return new PointHandler(w,z);
    }
    /**
    @param ListOfClause array of array list(all the clauses generated from the text file
    @return ListOfPoints The list of point according to implication transformation
     **/
    public static ArrayList<PointHandler> CreateListOfPointFromClause(ArrayList<Integer>[] ListOfClause) {
        int x = 0;
        int y = 0;
        List<PointHandler> TempListOfPoints = new ArrayList<>();
        for (int i = 0; i < ListOfClause.length; i++) {
            if(ListOfClause[i].size()> 2){
                System.out.println("Not binary clauses");
                System.exit(-3);
            }
            x = (int) ListOfClause[i].get(0);
            y = (int) ListOfClause[i].get(1);
            TempListOfPoints.add(new PointHandler(FileHandler.Stantardize(-x), FileHandler.Stantardize(y)));
            TempListOfPoints.add(new PointHandler(FileHandler.Stantardize(-y), FileHandler.Stantardize(x)));
        }
        ListOfPoints = (ArrayList<PointHandler>)((ArrayList<PointHandler>) TempListOfPoints).clone();
        return ListOfPoints;
    }
    /**
    @param ListOfPoint array list of point
    @return ListOfTranspositionPoints An array list where all the point from the @param has been permuted
    **/
    public  static ArrayList<PointHandler> CreateListOfTranspostionPoint(ArrayList<PointHandler> ListOfPoint){
        List<PointHandler> TempListOfTranspositionPoints = new ArrayList<>();
        for(PointHandler point : ListOfPoint){
            TempListOfTranspositionPoints.add(permute(point));
        }
        ListOfTranspositionPoints = (ArrayList<PointHandler>)((ArrayList<PointHandler>) TempListOfTranspositionPoints).clone();
        return ListOfTranspositionPoints;
    }
/**
    Print all the points from the attribute list of points
 **/
    public static void PrintListOfPoints(){
        if(ListOfPoints.isEmpty()){
            System.out.println("Empty List");
        }
        for(PointHandler point : ListOfPoints){
            System.out.println("x= "+point.x+" y = "+point.y);
        }
    }
    /**
     Print all the points from the attribute ListOftranspositionPoints
      **/
    public static void PrintListOfTranspositionPoints(){
        for(PointHandler point : ListOfTranspositionPoints){
            System.out.println("x= "+point.x+" y = "+point.y);
        }
    }
}
