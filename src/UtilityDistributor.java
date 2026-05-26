import java.util.LinkedList;
import java.util.Queue;

    public class UtilityDistributor {

        public void distribute(Grid grid, UtilityProvider provider) {

            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getCols(); col++) {

                    Cell cell = grid.getCell(row, col);

                    if (cell instanceof Zone) {
                        Zone zone = (Zone) cell;
                        zone.updateDemand();
                    }
                }
            }

            boolean[][] visited = new boolean[grid.getRows()][grid.getCols()];
            Queue<int[]> queue = new LinkedList<>();

            int providerRow = provider.getRow();
            int providerCol = provider.getCol();
            int remainingUtility = provider.getCapacity();


            queue.add(new int[]{providerRow, providerCol, remainingUtility});
            visited[providerRow][providerCol] = true;

            int[][] directions = {
                    {-1, 0},
                    {1, 0},
                    {0, -1},
                    {0, 1}
            };

            while (!queue.isEmpty()) {
                int[] current = queue.remove();

                int currentRow = current[0];
                int currentCol = current[1];
                int remaining = current[2];

                for (int[] direction : directions) {
                    int nextRow = currentRow + direction[0];
                    int nextCol = currentCol + direction[1];

                    if (nextRow < 0 || nextRow >= grid.getRows()){
                        continue;
                    }
                    if (nextCol < 0 || nextCol >= grid.getCols()){
                        continue;
                    }
                    if (visited[nextRow][nextCol]){
                        continue;
                    }

                    Cell cell = grid.getCell(nextRow, nextCol);

                    if (!cell.isConnectable()){
                        continue;
                    }

                    visited[nextRow][nextCol] = true;

                    int newRemaining = remaining;

                    if (cell instanceof Zone) {
                        Zone zone = (Zone) cell;

                        int demand = zone.getUtilityDemand();
                        int given = Math.min(newRemaining, demand);

                        provider.applyUtility(zone, given);

                        newRemaining -= given;
                    }

                    if (newRemaining > 0) {
                        queue.add(new int[]{nextRow, nextCol, newRemaining});
                    }
                }
            }
        }
    }

