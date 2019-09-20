import com.company.Graph;

import java.io.Console;
import java.util.Stack;


public class GraphHandler {
     private Graph<Integer> graph;
     private Graph<Integer> tgraph;

     private boolean[] visited;

     private Stack<Integer> Date = new Stack<>();



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

    public Graph<Integer> getGraph() {
        return graph;
    }

    private void FillGraphFromListOfPoint(){
        int i = 0;

        for(PointHandler point : PointHandler.getListOfPoints()){
            graph.addArc(point.x-1,point.y-1,i);
            i++;
        }

     }
     private void FillGraphFromListOfTranspositionPoint(){
        int i = 0;
        for (PointHandler point : PointHandler.getListOfTranspositionPoints()){
           // System.out.println("point.x "+(point.x-1)+" point.y "+(point.y-1));
            graph.addArc(point.x-1,point.y-1,i);
            i++;
        }
     }

     public void printEdge(){
         for (int i = 0; i<graph.order();i++) {
             for (Graph.Edge e  : graph.getIncidency().get(i)) {
                 System.out.println("From "+e.source + " to " + e.destination+ "Label "+ e.label );
             }
         }
     }

     public void DFS(){
         visited = new boolean[graph.order()];
         for(int i = 0;i<graph.order();i++){
            visited[i] = false;
         }
         for(int i = 0;i<graph.order();i++){
            if(visited[i]==false)visite(i);
         }
         System.out.println(Date.toString());
     }

     private void visite(int actualS){
        visited[actualS] = true;
        for(Graph.Edge e : graph.getIncidency().get(actualS)){
            if(visited[e.destination]==false)visite(e.destination);
        }
        Date.push(actualS);
     }

     public void TestKosaraju(){
        GraphHandler Tgraph = this.TransposedGraph();
         for(int i = 0;i<tgraph.order();i++){
             visited[i] = false;
         }
         while(!Date.isEmpty()){

             if(visited[Date.peek()]==false && Date.peek() !=null) {
                 int i = Date.pop();
                 Testvisite(i,tgraph);

             }

             System.out.println("-----------------------------------------");
         }
     }

     private void Testvisite(int actualS,Graph graph){
         visited[actualS] =true;
         System.out.println(actualS);
         for(Graph.Edge e : tgraph.getIncidency().get(actualS)){

             if(visited[e.destination] == false){
                 Testvisite(e.destination,graph);
             }

         }
         if(Date.contains(actualS))Date.removeElement(actualS);

     }


}
