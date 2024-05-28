package pt.ipp.isep.dei.esoft.project.domain.mdisc.MDISC.src;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Executar a US13 para um arquivo de entrada específico
        String inputFileName = "US13_JardimDosSentimentos.csv"; // Nome do arquivo de entrada
        try {
            boolean result = RouteOptimizer.buildIrrigationRoutes(inputFileName);
            if (result) {
                System.out.println("US13 completed successfully.");

                // Após a execução bem-sucedida, obter parâmetros
                Graph originalGraph = new Graph(SVReader.readCSV("C:\\Users\\gonca\\Downloads\\mdisc\\mdisc\\mdisc\\MDISC\\us13_input\\" + inputFileName, ";"));
                Graph mst = new Graph(SVReader.readCSV("C:\\Users\\gonca\\Downloads\\mdisc\\mdisc\\mdisc\\MDISC\\us13_results\\mst" + inputFileName, ";"));

                int graphDimension = originalGraph.getGraph().size();
                int graphOrder = originalGraph.getVertices().size();
                double minimumCost = mst.getGraphCost();

                System.out.println("Graph Dimension: " + graphDimension);
                System.out.println("Graph Order: " + graphOrder);
                System.out.println("Minimum cost: " + minimumCost);
            } else {
                System.out.println("US13 did not complete successfully.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        // Executar a US14 para observar o comportamento assintótico
        String testFilePrefix = "us14_"; // Prefixo dos arquivos de teste
        try {
            boolean testResult = Tests.runTestsForVariableInputsSize(testFilePrefix);
            if (testResult) {
                System.out.println("US14 tests completed successfully.");
            } else {
                System.out.println("US14 tests did not complete successfully.");
            }
        } catch (IOException e) {
            System.err.println("Error during test execution: " + e.getMessage());
        }
    }
}