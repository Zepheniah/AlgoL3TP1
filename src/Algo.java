import com.company.Graph;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Algo {


    public static void main(String[] args) throws FileNotFoundException {
        FileHandler myscan = new FileHandler(new File("./src/formule-2-sat.txt"));
        Graph graph = new Graph(2*myscan.maxValue);
        PointHandler.init();

        PointHandler.PrintListOfPoints();
        System.out.println("-----------------");
        PointHandler.PrintListOfTranspositionPoints();

    }


}
