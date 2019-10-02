import com.company.Graph;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Algo {


    public static void main(String[] args) throws FileNotFoundException {
        FileHandler myscan = new FileHandler(new File("./src/unsat10000d"));
        PointHandler.init();
        Chrono chrono = new Chrono();

        GraphHandler graph = new GraphHandler( new Graph(2*myscan.maxValue),false);
        //graph.printEdge();
        chrono.start();
        graph.DFS();



        graph.TestKosaraju();



        graph.Check2SATProblem();

        chrono.stop();
        System.out.println(chrono.getElapsedTime());
        chrono.restart();


/*
        PointHandler.PrintListOfPoints();
        System.out.println("-----------------");
        PointHandler.PrintListOfTranspositionPoints();
        System.out.println("-----------------");
*/


    }


}
