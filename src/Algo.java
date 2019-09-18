import com.company.Graph;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Algo {


    public static void main(String[] args) throws FileNotFoundException {
        File fileName = new File("./src/formule-2-sat.txt");
        int nbClause;
        List<Integer> Clause;
        ArrayList<Integer>[] test = new ArrayList[4];
        FileHandler myscan = new FileHandler(fileName);
        myscan.maxValue = myscan.Nextint();
        nbClause = myscan.Nextint();
        Graph graph = new Graph(2*myscan.maxValue);

        for( int i = 0;i<nbClause;i++){
            test[i] = myscan.NextClause();
        }

        for(PointHandler point : PointHandler.CreateListOfPointFromClause(test)){
            System.out.println("x= "+point.x+" y = "+point.y);
        }

    }


}
