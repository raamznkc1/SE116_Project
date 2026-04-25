import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapReader {

    public static Grid load(String filename) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                    lines.add(line.trim());
            }
        }

        int rows = lines.size();
        int cols = lines.get(0).length();
        Grid grid = new Grid(rows, cols);

        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < cols; j++) {
                grid.setCell(i, j, parseCell(String.valueOf(line.charAt(j)), i, j));
            }
        }

        return grid;
    }

    private static Cell parseCell(String token, int row, int col) {
        switch (token) {
            case "E": return new EmptyCell(row, col);
            case "R": return new RoadCell(row, col);
            default: throw new IllegalArgumentException(
                    "Unknowwn cell typ: " + token + " at (" + row + "," + col + ")"
            );
        }
    }
}