public class HousingZone extends Zone {

    public HousingZone(int row, int col) {
        super(row, col);
    }

    @Override
    public String getType() {
        return "H";
    }

    @Override
    public String toString() {
        return "H";
    }

    @Override
    public void computeNewLevel(){
        if (receivedElectricity == 0 && receivedWater == 0 && receivedInternet == 0) {
            dropToZero();
            return;
        }
        int targetLevel = 0;

        boolean hasBasicUtilities = receivedElectricity > 0 && receivedWater > 0 && receivedInternet > 0;

        if (hasBasicUtilities) {
            targetLevel = 1;

            if (hasSecurity && hasHealth && hasEducation) {
                targetLevel = 2;

                if (receivedLifestyle > 0) {
                    targetLevel = 3;
                }
            }
        }

        if (targetLevel > level) {
            levelUp();
        }
        else if (targetLevel < level) {
            levelDown();
        }
    }

    @Override
    public void computeOutput() {
        if (level == 0) {
            currentOutput = 0;
            updateDemand();
            return;
        }

        int m = Math.min(receivedElectricity, Math.min(receivedWater, receivedInternet));

        if (level == 1) {
            currentOutput = m;
        }
        else if (level == 2) {
            currentOutput = 2 * m;
        }
        else if (level == 3) {
            currentOutput = 2 * m + receivedLifestyle;
        }

        updateDemand();
    }


    public int getProducedPopulation() {
        return currentOutput;



    }





}