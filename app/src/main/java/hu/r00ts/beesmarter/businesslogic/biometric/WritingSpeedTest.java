package hu.r00ts.beesmarter.businesslogic.biometric;


import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class WritingSpeedTest {

    private Training training;
    private Pattern pattern;

    private Double averageTestThreshold = 0.003;
    private Double highestTestThreshold = 0.003;
    private Double lowestTestThreshold = 0.003;

    public WritingSpeedTest(Training training, Pattern pattern) {
        this.training = training;
        this.pattern = pattern;
    }

    public Boolean run() {
        return monotonyTest();
    }

    private Boolean monotonyTest() {
        switch (training.getWriteSpeedMonotony()) {
            case -2:
                return lowestTest();
            case -1:
                return lowestTest() && averageTest();
            case 0:
                return averageTest();
            case 1:
                return highTest() && averageTest();
            case 2:
                return highTest();
        }
        return false;
    }

    private Boolean averageTest() {
        Double speedDiff = Math.abs(training.getAvarageWriteSpeed() - pattern.getWriteSpeed());
        if (speedDiff <= averageTestThreshold) {
            return true;
        }
        return false;
    }

    private Boolean highTest() {
        Double speedDiff = Math.abs(training.getHighestWriteSpeed() - pattern.getWriteSpeed());
        if (speedDiff <= highestTestThreshold) {
            return true;
        }
        return false;
    }


    private Boolean lowestTest() {
        Double speedDiff = Math.abs(training.getLowestWriteSpeed() - pattern.getWriteSpeed());
        if (speedDiff <= lowestTestThreshold) {
            return true;
        }
        return false;
    }



}
