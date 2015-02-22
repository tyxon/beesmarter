package hu.r00ts.beesmarter.businesslogic.biometric;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hu.r00ts.beesmarter.businesslogic.DTO.Key;
import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class HandTest {
    private Training training;
    private Pattern pattern;

    private Integer minPasswordLength = 6;
    private String rightHandPattern = "67890tyuiopghjklvbnm";
    private String leftHandPattern = "12345qwertzasdfgzxcv";

    public HandTest(Training training, Pattern pattern) {
        this.training = training;
        this.pattern = pattern;
    }

    public Boolean run() {
        return false;
    }

    private Integer detectMainHand() {
        if (pattern.Keys.size() <  6) {
            return 0;
        }

        ArrayList<Key> rightHandKeys = new ArrayList<Key>();
        ArrayList<Key> leftHandKeys = new ArrayList<Key>();

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

        



        return 0;
    }
}
