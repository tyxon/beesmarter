package hu.r00ts.beesmarter.businesslogic.biometric;


import java.util.ArrayList;
import java.util.HashMap;

import hu.r00ts.beesmarter.businesslogic.DTO.Key;
import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class HandTest {
    private Training training;
    private Pattern pattern;

    private Integer minPasswordLength = 6;
    private String rightHandPattern = "67890tyuiopghjklvbnm";
    private String leftHandPattern = "12345qwertzasdfgzxcv";
    private ArrayList<Key> rightHandKeys;
    private ArrayList<Key> leftHandKeys;

    public HandTest(Training training, Pattern pattern) {
        this.training = training;
        this.pattern = pattern;
    }

    public Boolean run() {
        Integer mainHand = detectMainHand();
        Integer patternHand = detectPatternHand();
        if (mainHand != 0 && patternHand  != 0) {
            if (mainHand != patternHand) {
                return false;
            }
        }
        return true;
    }

    private Integer detectPatternHand() {
        if (pattern.Keys.size() <  minPasswordLength) {
            return 0;
        }

        Double rightHandTime = 0d;
        for (Key key : rightHandKeys) {
            rightHandTime += key.getKeyPressTime();
        }

        Double leftHandTime = 0d;
        for (Key key : leftHandKeys) {
            leftHandTime += key.getKeyPressTime();
        }

        if (leftHandTime < rightHandTime) {
            return -1;
        }

        if (leftHandTime > rightHandTime) {
            return 1;
        }

        return 0;
    }

    private Integer detectMainHand() {
        if (pattern.Keys.size() <  minPasswordLength) {
            return 0;
        }

        rightHandKeys = new ArrayList<Key>();
        leftHandKeys = new ArrayList<Key>();

        for (Key key : pattern.Keys) {
            if (rightHandPattern.contains(key.KeyDown.Code)) {
                rightHandKeys.add(key);
            }

            if (leftHandPattern.contains(key.KeyDown.Code)) {
                leftHandKeys.add(key);
            }
        }

        if (rightHandKeys.size() <= (minPasswordLength / 2) || leftHandKeys.size() <= (minPasswordLength / 2)) {
            return 0;
        }

        HashMap<String, Double> avarageKeypresses = training.getAvarageKeypresses();

        Double rightHandTime = 0d;
        for (Key key : rightHandKeys) {
            rightHandTime += avarageKeypresses.get(key.KeyDown.Code);
        }
        rightHandTime = rightHandTime / rightHandKeys.size();

        Double leftHandTime = 0d;
        for (Key key : leftHandKeys) {
            leftHandTime += avarageKeypresses.get(key.KeyDown.Code);
        }
        leftHandTime = leftHandTime / leftHandKeys.size();

        if (leftHandTime < rightHandTime) {
            return -1;
        }

        if (leftHandTime > rightHandTime) {
            return 1;
        }

        return 0;
    }
}
