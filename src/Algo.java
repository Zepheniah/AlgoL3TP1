import com.company.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Algo {


    public static void main(String[] args) throws FileNotFoundException {
        File fileName = new File("./src/formule-2-sat.txt");
        int nbClause;
        List<Integer> Clause;
        FileHandler myscan = new FileHandler(fileName);
        myscan.maxValue = myscan.Nextint();
        Graph graph = new Graph(2*myscan.maxValue);
        nbClause = myscan.Nextint();//

    }


}
