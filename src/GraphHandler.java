import com.company.Graph;

import java.io.Console;
import java.util.ArrayList;
import java.util.Stack;
///Class used to work on graph with different Algorithm

public class GraphHandler {
     private Graph<Integer> graph;
     private Graph<Integer> tgraph;

     private int[] composante;

     private boolean[] visited;
     private Chrono chrono = new Chrono();

     private Stack<Integer> Date = new Stack<>();
    private ArrayList<Integer> ComposanteConnexe = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> ListofComposanteConnexe = new ArrayList<>();



/*
    Constructor used to create a graph transposed or not depending from the @param transposition
    the graph is created from the List of Point generated in the @class PointHandler
 */
    public GraphHandler(Graph graph,boolean transposition){
         this.graph = graph;
         if(PointHandler.getListOfPoints() == null){
             System.out.println("PointHandler not initialized,cannot generate Graph ");
             System.exit(-1);
         }
         if(transposition)FillGraphFromListOfTranspositionPoint();
         else FillGraphFromListOfPoint();
         graph.toString();
     }

     public GraphHandler TransposedGraph(){
        Graph<Integer> TempGraph = new Graph(FileHandler.getMaxValue()*2);
        GraphHandler TGraph = new GraphHandler(TempGraph,true);
        tgraph = TGraph.getGraph();
        return TGraph;
     }
    // return the attribute graph
    public Graph<Integer> getGraph() {
        return graph;
    }

    private void FillGraphFromListOfPoint(){/*Used to fill the attribute graph with edge from a List of point*/
        int i = 0;

        for(PointHandler point : PointHandler.getListOfPoints()){
            graph.addArc(point.x-1,point.y-1,i);
            i++;
        }

     }
     private void FillGraphFromListOfTranspositionPoint(){/*used to fill the attribute graph with edge from a List of point that is permuted version of PointHandler.ListOfPoints*/
        int i = 0;
        for (PointHandler point : PointHandler.getListOfTranspositionPoints()){
           // System.out.println("point.x "+(point.x-1)+" point.y "+(point.y-1));
            graph.addArc(point.x-1,point.y-1,i);
            i++;
        }
     }

     public void printEdge(){ /*print all the edges of the attribute graph in the console */
         for (int i = 0; i<graph.order();i++) {
             for (Graph.Edge e  : graph.getIncidency().get(i)) {
                 System.out.println("From "+e.source + " to " + e.destination+ "Label "+ e.label );
             }
         }
     }

     public void DFS(){ /*Used the Depth First Search in the attribute graph*/
         visited = new boolean[graph.order()]; // create an array of the size of the number of nodes in the graph
         for(int i = 0;i<graph.order();i++){  // set everything to false to use it later as mark to know if the node was visited or not
            visited[i] = false;
         }
         for(int i = 0;i<graph.order();i++){ //Go through every node and check if it has been visited if not call the function visite with the node as parameter
            if(visited[i]==false)visite(i);
         }
        // System.out.println(Date.toString()); // print all of the stack ,useful to know the order in which the node has been processed
     }

     private void visite(int actualS){ //Secondary method in the DFS algorithm ,used as recursiv method
        visited[actualS] = true; // set the node as visited so it won't be visited again
        for(Graph.Edge e : graph.getIncidency().get(actualS)){// go through every incident node,check if it has been visited if not call the function visite with the node as parameter
            if(visited[e.destination]==false)visite(e.destination);
        }
        Date.push(actualS); // Use a stack to keep track of the order of Nodes
     }


     public void TestKosaraju(){ // Implementation of the algorithm of Kosaraju, same logic as DFS but will go through nodes in the order of the stack generated in DFS
        if(Date == null){
            System.out.println("DFS has not been called or failed");
            System.exit(-3);
        }
        chrono.start();
        GraphHandler Tgraph = this.TransposedGraph();
        chrono.stop();
         System.out.println("Transposé"+chrono.getElapsedTime());
        composante = new int[tgraph.order()];
         for(int i = 0;i<tgraph.order();i++){
             visited[i] = false;
             composante[i] = -1;
         }
         int compo = 0;
         chrono.restart();
         while(!Date.isEmpty()){

             //if(visited[Date.peek()]==false && Date.peek() !=null) {
                 int i = Date.pop();
                 if(visited[i]==false){
                 Testvisite(i,compo);
                 compo++;

             }




             //CreateListOfComposanteConnexe(ComposanteConnexe);
             //System.out.println("-----------------------------------------");
         }
         chrono.stop();
         System.out.println("Pile"+chrono.getElapsedTime());


     }

     private void Testvisite(int actualS,int compo){
         visited[actualS] =true;
         //System.out.println(actualS);
         //ComposanteConnexe.add(actualS);
         composante[actualS] = compo;
         for(Graph.Edge e : tgraph.getIncidency().get(actualS)){

             if(visited[e.destination] == false){
                 Testvisite(e.destination,compo);
             }

         }
         //if(Date.contains(actualS))Date.removeElement(actualS);

     }

     private void CreateListOfComposanteConnexe(ArrayList<Integer> arrayList){
        ArrayList<Integer> temp = (ArrayList<Integer>) arrayList.clone();
        ListofComposanteConnexe.add(temp);
        ComposanteConnexe.clear();
     }

     public void Check2SATProblem() {
        /*
         if (ListofComposanteConnexe.isEmpty()) {
             System.out.println("List of Component is empty,make sure you used TestKoraju()");
             System.exit(-2);
         }
         for (ArrayList<Integer> a : ListofComposanteConnexe) {
             for (int i = 0; i < FileHandler.getMaxValue(); i++) {
                 if (a.contains(i) && a.contains(i + FileHandler.getMaxValue())) {
                     System.out.println("It's not 2SAT satisfiable");
                     System.exit(0);
                 }
             }

         }

         */
        for(int i = 0;i<graph.order()/2;i++){
            if(composante[i]==composante[i+FileHandler.getMaxValue()]){
                System.out.println("It's not 2SAT satisfiable");
                System.exit(0);
            }
        }
         System.out.println("It's 2SAT satisfiable ");
         //System.exit(0);
     }


}
