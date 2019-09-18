import com.company.Graph;



public class GraphHandler {
     private Graph<Integer> graph;
     private boolean transposition;

    public GraphHandler(Graph graph,boolean transposition){
         this.graph = graph;
         if(transposition)FillGraphFromListOfTranspositionPoint();
         else FillGraphFromListOfPoint();
         graph.toString();
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
            graph.addArc(point.x-1,point.y-1,i);
            i++;
        }
     }


}
