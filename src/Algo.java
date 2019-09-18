import com.company.Graph;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Algo {


    public static void main(String[] args) throws FileNotFoundException {
        FileHandler myscan = new FileHandler(new File("./src/formule-2-sat.txt"));
        PointHandler.init();
        GraphHandler graph = new GraphHandler( new Graph(2*myscan.maxValue),false);

        /*
        PointHandler.PrintListOfPoints();
        System.out.println("-----------------");
        PointHandler.PrintListOfTranspositionPoints();
        System.out.println("-----------------");
        */

    }


}
