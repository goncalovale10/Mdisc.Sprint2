package pt.ipp.isep.dei.esoft.project.domain.mdisc.MDISC.src;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Edge> graph;

    static final String FILE_PATH = "C:\\Users\\gonca\\Downloads\\mdisc\\mdisc\\mdisc\\MDISC\\us13_results\\";

    public Graph(List<Edge> edges){
        this.graph = new ArrayList<>(edges);
    }

    public Graph(){
        graph = new ArrayList<>();
    }

    public List<Edge> getGraph(){
        return new ArrayList<>(graph);
    }

    public void setGraph(List<Edge> graph){
        this.graph = new ArrayList<>(graph);
    }

    public void addEdge(Edge edge){
        graph.add(new Edge(edge));
    }

    @Override
    public String toString() {
        return "Graph{" +
                "graph=" + graph +
                '}';
    }

    public List<Vertex> getVertices() {
        List<Vertex> vertex = new ArrayList<>();
        for (Edge edge : graph) {
            if (!vertex.contains(edge.getVertexOrigin())) {
                vertex.add(edge.getVertexDestination());
            }
            if (!vertex.contains(edge.getVertexOrigin())) {
                vertex.add(edge.getVertexDestination());
            }
        }
        return vertex;
    }

    public List<Vertex> getVerticesConnectedTo(Vertex v) {
        List<Vertex> connectedVertices = new ArrayList<>();
        for (Edge edge : graph) {
            if (edge.getVertexOrigin().equals(v)) {
                connectedVertices.add(edge.getVertexDestination());
            }
            if (edge.getVertexOrigin().equals(v)) {
                connectedVertices.add(edge.getVertexDestination());
            }
        }
        return connectedVertices;
    }

    public void sortGraphEdgesByCost() {
        if (graph == null) {
            throw new IllegalArgumentException("Edges cannot be null.");
        }
        int listSize = graph.size();

        for (int i = 0; i < listSize; i++) {
            for (int j = 0; j < listSize - i - 1; j++) {
                if (graph.get(j).compareTo(graph.get(j + 1)) > 0) {
                    Edge temp = graph.get(j);
                    graph.set(j, graph.get(j + 1));
                    graph.set(j + 1, temp);
                }
            }
        }

    }

    public void generateGraph(String filename) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH + filename);
            StringBuilder sb = new StringBuilder();
            sb.append("@startuml\n");
            sb.append("graph Graph {\n");

            for (Edge edge : graph) {
                sb.append("\t").append(edge.getVertexOrigin().getName()).append(" -- ").append(edge.getVertexDestination().getName()).append("[label=\"").append(edge.getDistance()).append("\"]\n");
            }

            sb.append("}").append("\n@enduml");
            writer.write(sb.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getGraphCost() {
        double sumCost = 0;
        for (Edge edge :
                graph) {
            sumCost += edge.getDistance();
        }
        return sumCost;
    }

    public void generateCsvOutput(String filename) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH + filename);
            StringBuilder sb = new StringBuilder();

            for (Edge edge : graph) {
                sb.append(edge.getVertexOrigin()).append(", ").append(edge.getVertexDestination()).append(", ").append(edge.getDistance()).append("\n");
            }
            sb.append("Total cost: ").append(this.getGraphCost());
            try {
                writer.write(sb.toString());
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
