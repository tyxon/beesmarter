package hu.r00ts.beesmarter.businesslogic.biometric;


import hu.r00ts.beesmarter.businesslogic.DTO.Key;
import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class OverallTimeConstraint extends BaseConstraint {

    private Double threshold = 0d;
    private Double minFullTime = 0d;
    private Double maxFullTime = 0d;

    public OverallTimeConstraint(Training training, Pattern pattern) {
        super(training, pattern);
    }

    @Override
    public double getPossibility() {
        //threshold = getAvgKeyPressTime() * 2;
        setMinMaxOverallTime();
        threshold = minFullTime / 3;

        Double patternFullTime = pattern.getFullTime().doubleValue();
        if (patternFullTime >= minFullTime && patternFullTime <= maxFullTime) {
            return 1 * getWeight();
        } else if (patternFullTime >= minFullTime - threshold) {
            return ((minFullTime - threshold) / patternFullTime) * getWeight();
        } else if (patternFullTime <= maxFullTime + threshold) {
            return (patternFullTime / (maxFullTime + threshold)) * getWeight();
        } else {
            return 0.1 * getWeight();
        }
    }


    private Double getAvgKeyPressTime() {
        Double avgKeyPressTime = 0d;
        for (Pattern pattern : training.Patterns) {
            Double avgKeyPressTimePerPattern = 0d;
            for (Key key : pattern.Keys) {
                avgKeyPressTimePerPattern += key.getKeyPressTime().doubleValue();
            }
            avgKeyPressTime += avgKeyPressTimePerPattern / pattern.Keys.size();
        }

        return avgKeyPressTime / training.Patterns.size();
    }

    private void setMinMaxOverallTime() {
        for (Pattern pattern : training.Patterns) {
            Double fullTime = pattern.getFullTime().doubleValue();
            if (minFullTime == 0d || minFullTime > fullTime) {
                minFullTime = fullTime;
            }

            if (maxFullTime < fullTime) {
                maxFullTime = fullTime;
            }
        }
    }

}
