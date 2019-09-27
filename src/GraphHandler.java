import com.company.Graph;

import java.io.Console;
import java.util.ArrayList;
import java.util.Stack;


public class GraphHandler {
     private Graph<Integer> graph;
     private Graph<Integer> tgraph;

     private boolean[] visited;

     private Stack<Integer> Date = new Stack<>();
    private ArrayList<Integer> ComposanteConnexe = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> ListofComposanteConnexe = new ArrayList<>();




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

    private void FillGraphFromListOfPoint(){/*permet de créer tout les♥arc d'un graphe à♥partir d'une liste de points*/
        int i = 0;

        for(PointHandler point : PointHandler.getListOfPoints()){
            graph.addArc(point.x-1,point.y-1,i);
            i++;
        }

     }
     private void FillGraphFromListOfTranspositionPoint(){/*permet de créer tout les♥arc d'un graphe à♥partir d'une liste de points dont les coordonné on été permuté*/
        int i = 0;
        for (PointHandler point : PointHandler.getListOfTranspositionPoints()){
           // System.out.println("point.x "+(point.x-1)+" point.y "+(point.y-1));
            graph.addArc(point.x-1,point.y-1,i);
            i++;
        }
     }

     public void printEdge(){ /*permet d'afficher un arc dans la console*/
         for (int i = 0; i<graph.order();i++) {
             for (Graph.Edge e  : graph.getIncidency().get(i)) {
                 System.out.println("From "+e.source + " to " + e.destination+ "Label "+ e.label );
             }
         }
     }

     public void DFS(){ /*effectue un parcours en profondeur sur le graphe*/
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
             CreateListOfComposanteConnexe(ComposanteConnexe);
             System.out.println("-----------------------------------------");
         }


     }

     private void Testvisite(int actualS,Graph graph){
         visited[actualS] =true;
         System.out.println(actualS);
         ComposanteConnexe.add(actualS);
         for(Graph.Edge e : tgraph.getIncidency().get(actualS)){

             if(visited[e.destination] == false){
                 Testvisite(e.destination,graph);
             }

         }
         if(Date.contains(actualS))Date.removeElement(actualS);

     }

     private void CreateListOfComposanteConnexe(ArrayList<Integer> arrayList){
        ArrayList<Integer> temp = (ArrayList<Integer>) arrayList.clone();
        ListofComposanteConnexe.add(temp);
        ComposanteConnexe.clear();
     }

     public void Check2SATProblem(){
        if(ListofComposanteConnexe.isEmpty()){
            System.out.println("List of Component is empty,make sure you used TestKoraju()");
            System.exit(-2);
        }
         for(ArrayList<Integer> a : ListofComposanteConnexe) {
             for(int i = 0;i<FileHandler.getMaxValue();i++){
                 if (a.contains(i)&& a.contains(i+FileHandler.getMaxValue())){
                     System.out.println("It's not 2SAT satisfiable");
                     System.exit(0);
                 }
             }
             System.out.println("It's 2SAT satisfiable");
         }

     }


}
