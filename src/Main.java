import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar ObjectVilleGame.jar <mapfile> <ticks>");
            System.out.println("Example: java -jar ObjectVilleGame.jar mymap.txt 5");
            return;
        }

        String filename = args[0];
        int ticks;

        try {
            ticks = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Tick count must be a valid integer.");
            return;
        }

        if (ticks <= 0) {
            System.out.println("Error: Tick count must be greater than 0.");
            return;
        }

        System.out.println("Loading map: " + filename);

        Grid grid;
        try {
            grid = MapReader.load(filename);
        } catch (IOException e) {
            System.out.println("Error: Map file not found: " + filename);
            return;
        }

        System.out.println("Map loaded successfully. Starting simulation for " + ticks + " ticks.");

        Simulation simulation = new Simulation(grid);
        simulation.run(ticks);
    }
}
