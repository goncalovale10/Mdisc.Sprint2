package pt.ipp.isep.dei.esoft.project.domain.mdisc.MDISC.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class  SVReader {

    public static List<Edge> readCSV(String filePath, String separator) throws FileNotFoundException {

        List<Edge> edges = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("The file " + filePath + " doesn't exist.");
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] parts = line.split(separator);

                if (parts.length == 3) {
                    String nomeOrigem = parts[0].trim();
                    String nomeDestino = parts[1].trim();
                    double distance;
                    try {
                        distance = Double.parseDouble(parts[2].trim());
                        if (distance < 0) {
                            throw new IllegalArgumentException("Distance can't be negative");
                        }
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException("Distance is not in a valid format");
                    }

                    Vertex origin = new Vertex(nomeOrigem);
                    Vertex destination = new Vertex(nomeDestino);
                    Edge edge = new Edge(origin, destination, distance);
                    edges.add(edge);
                }
            }
        }

        return edges;
    }
}
