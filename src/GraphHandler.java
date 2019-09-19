import com.company.Graph;

import java.io.Console;


public class GraphHandler {
     private Graph<Integer> graph;
     private boolean transposition;
     int time;

    boolean[] visited;
    int[] StartTime;
    int[] EndTime;

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

     public static GraphHandler TransposedGraph(){
        Graph<Integer> TempGraph = new Graph(FileHandler.getMaxValue()*2);
        GraphHandler TGraph = new GraphHandler(TempGraph,true);
        return TGraph;
     }

    public Graph<Integer> getGraph() {
        return graph;
    }

    public boolean isTransposition() {
        return transposition;
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
            System.out.println("point.x "+(point.x-1)+" point.y "+(point.y-1));
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
         int time = 1;
         visited = new boolean[graph.order()];
         EndTime = new int[graph.order()];
         StartTime = new int[graph.order()];

         for(int i = 0;i<graph.order();i++){
            visited[i] = false;
         }
         for(int i = 0;i<graph.order();i++){
            if(visited[i]==false)visite(i);
         }
     }

     private void visite(int actualS){
        StartTime[actualS] = time;
        visited[actualS] = true;
        time++;
         System.out.println(actualS);
        for(Graph.Edge e : graph.getIncidency().get(actualS)){

            if(visited[e.destination]==false)visite(e.destination);

        }

        EndTime[actualS] = time;
        time++;
     }


}
