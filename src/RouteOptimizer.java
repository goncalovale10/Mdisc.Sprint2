package pt.ipp.isep.dei.esoft.project.domain.mdisc.MDISC.src;

import java.io.FileNotFoundException;
import java.util.List;

public class RouteOptimizer {
    public static boolean buildIrrigationRoutes(String fileName) throws FileNotFoundException {
        final String FILE_PATH = "C:\\Users\\gonca\\Downloads\\mdisc\\mdisc\\mdisc\\MDISC\\us13_input\\";

        List<Edge> edges = SVReader.readCSV(FILE_PATH + fileName, ";");
        if (!edges.isEmpty()) {
            Graph graph = new Graph(edges);
            KruskalMethod k = new KruskalMethod();
            Graph mst = k.generateMinimumSpanningTree(graph);

            String fileOutput = fileName.replace(".csv", "").trim();
            graph.generateGraph("graph" + fileOutput + ".puml");
            mst.generateGraph("mst" + fileOutput + ".puml");
            mst.generateCsvOutput("mst" + fileOutput + ".csv");
            return true;
        } else {
            System.out.println("Nothing Detected. Graph is either null or empty.");
            return false;
        }
    }


}