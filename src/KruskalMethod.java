package pt.ipp.isep.dei.esoft.project.domain.mdisc.MDISC.src;

import java.util.ArrayList;
import java.util.List;

public class KruskalMethod {

    public Graph generateMinimumSpanningTree(Graph otherGraph) {
        Graph minimumSpanningTree = new Graph();
        Vertex vertexOrigin, vertexDestination;
        otherGraph.sortGraphEdgesByCost();
        List<Vertex> vertices = otherGraph.getVertices();
        int numVertices = vertices.size(), numEdges = 0;

        ArrayList<ArrayList<Vertex>> subsets = initializeSubsets(vertices);
        List<Edge> graphEdges = otherGraph.getGraph();

        for (int edgeIndex = 0; numEdges < numVertices - 1 && edgeIndex < graphEdges.size(); edgeIndex++) {
            Edge edge = graphEdges.get(edgeIndex);
            vertexOrigin = edge.getVertexOrigin();
            vertexDestination = edge.getVertexDestination();
            int indexVertexOrigin = -1, indexVertexDestination = -1;

            for (int i = 0; (indexVertexOrigin == -1 || indexVertexDestination == -1) && i < subsets.size(); i++) {
                ArrayList<Vertex> subset = subsets.get(i);
                for (Vertex v : subset) {
                    if (v.equals(vertexOrigin)) {
                        indexVertexOrigin = i;
                    }
                    if (v.equals(vertexDestination)) {
                        indexVertexDestination = i;
                    }
                }
            }
            if (indexVertexOrigin != -1 && indexVertexDestination != -1 && indexVertexOrigin != indexVertexDestination) {
                minimumSpanningTree.addEdge(edge);
                ArrayList<Vertex> subset1 = subsets.get(indexVertexOrigin);
                ArrayList<Vertex> subset2 = subsets.get(indexVertexDestination);
                if (subset1.size() < subset2.size()) {
                    // Swap subset1 and subset2, so subset1 is always the larger one
                    ArrayList<Vertex> temp = subset1;
                    subset1 = subset2;
                    subset2 = temp;
                }
                for (Vertex v : subset2) {
                    subset1.add(v);
                }
                subset2.clear();
                numEdges++;
            }
        }

        // Retornar a árvore de spanning mínima
        return minimumSpanningTree;

    }

    public ArrayList<ArrayList<Vertex>> initializeSubsets(List<Vertex> vertices) {
        int numVertices = vertices.size();
        ArrayList<ArrayList<Vertex>> subsets = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            ArrayList<Vertex> subset = new ArrayList<>();
            subset.add(vertices.get(i));
            subsets.add(subset);
        }
        return subsets;
    }
}
