import com.company.Graph;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Algo {


    public static void main(String[] args) throws FileNotFoundException {
        Chrono chrono = new Chrono();

        FileHandler myscan = new FileHandler(new File("./src/sat100000d"));
        PointHandler.init();

        GraphHandler graph = new GraphHandler( new Graph(2*myscan.maxValue),false);
        //graph.printEdge();
        chrono.start();
        graph.DFS();



        graph.TestKosaraju();



        graph.Check2SATProblem();

        chrono.stop();
        System.out.println("Temps des algo"+chrono.getElapsedTime());
        chrono.restart();


/*
        PointHandler.PrintListOfPoints();
        System.out.println("-----------------");
        PointHandler.PrintListOfTranspositionPoints();
        System.out.println("-----------------");
*/


    }


}
