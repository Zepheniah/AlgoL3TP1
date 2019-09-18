import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
           if(sc.next() == "-")return sc.nextInt()+maxValue;
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

}
