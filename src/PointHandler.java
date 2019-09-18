import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PointHandler {

    public int x;
    public int y;
    private static ArrayList<PointHandler> ListOfPoints;
    private static ArrayList<PointHandler> ListOfTranspositionPoints;

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

     protected static PointHandler  permute(PointHandler point){
        int z,w;
        z = point.x;
        w = point.y;
        return new PointHandler(w,z);
    }

    public static ArrayList<PointHandler> CreateListOfPointFromClause(ArrayList<Integer>[] ListOfClause) {
        int x = 0;
        int y = 0;
        List<PointHandler> TempListOfPoints = new ArrayList<>();
        for (int i = 0; i < ListOfClause.length; i++) {
            x = (int) ListOfClause[i].get(0);
            y = (int) ListOfClause[i].get(1);
            TempListOfPoints.add(new PointHandler(-x, y));
            TempListOfPoints.add(new PointHandler(-y, x));
        }
        ListOfPoints = (ArrayList<PointHandler>)((ArrayList<PointHandler>) TempListOfPoints).clone();
        return ListOfPoints;
    }

    public  static ArrayList<PointHandler> CreateListOfTranspostionPoint(ArrayList<PointHandler> ListOfPoint){
        List<PointHandler> TempListOfTranspositionPoints = new ArrayList<>();
        for(PointHandler point : ListOfPoint){
            TempListOfTranspositionPoints.add(permute(point));
        }
        ListOfTranspositionPoints = (ArrayList<PointHandler>)((ArrayList<PointHandler>) TempListOfTranspositionPoints).clone();
        return ListOfTranspositionPoints;
    }

    public static void PrintListOfPoints(){
        for(PointHandler point : ListOfPoints){
            System.out.println("x= "+point.x+" y = "+point.y);
        }
    }

    public static void PrintListOfTranspositionPoints(){
        for(PointHandler point : ListOfTranspositionPoints){
            System.out.println("x= "+point.x+" y = "+point.y);
        }
    }
}
