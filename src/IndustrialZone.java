public class IndustrialZone extends Zone{

    public IndustrialZone(int row, int col) {
        super(row, col);
    }

    @Override
    public String getType() {
        return "I";
    }

    @Override
    public String toString() {
        return "I";
    }

    @Override
    public void computeNewLevel() {
        if (receivedElectricity == 0 && receivedWater == 0) {
            dropToZero();
            return;
        }

        int targetLevel = 0;

        if (receivedPopulation > 0 && receivedElectricity > 0 && receivedWater > 0) {
            targetLevel = 1;

            if (hasSecurity) {
                targetLevel = 2;

                if (receivedPopulation > 0) {
                    targetLevel = 3;
                }
            }
        }

        if (targetLevel > level) {
            levelUp();
        } else if (targetLevel < level) {
            levelDown();
        }
    }
    @Override
    public void computeOutput(){
    }


}
