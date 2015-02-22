package hu.r00ts.beesmarter.businesslogic.biometric;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hu.r00ts.beesmarter.businesslogic.DTO.Key;
import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class KeyReleasedConstraint extends BaseConstraint {

    public KeyReleasedConstraint(Training training, Pattern pattern) {
        super(training, pattern);
    }

    @Override
    public double getPossibility() {
        int maxKeyCount = training.getMaxKeyCount();
        Log.d(KeyPressedConstraint.class.getName(), "maxKeyCount: " + maxKeyCount);
        List<Long> currentTimes = null;
        List<Double> possibilities = new ArrayList<>();

        for(int i = 0; i < maxKeyCount; i++) {
            currentTimes = new ArrayList<>();
            for (Pattern p : training.Patterns) {
                int keySize = p.Keys.size();
                if(i < keySize){
                    Key key = p.Keys.get(i);
                    currentTimes.add(key.getKeyPressTime());
                }
            }

            double currentPossibility = 0;
            if(!currentTimes.isEmpty()) {
                int size = currentTimes.size();

                double max = 0;
                for (Long time : currentTimes) {
                    if(time > max){
                        max = time;
                    }
                }

                double min = max;
                for (Long time : currentTimes) {
                    if(time < min){
                        min = time;
                    }
                }

                if(size > 1) {
                    double average = 0;
                    for (Long time : currentTimes) {
                        average += time;
                    }
                    average /= size;
                    double sq = 0;
                    for (Long time : currentTimes) {
                        sq += Math.pow(time - average, 2);
                    }
                    double s = Math.sqrt(sq / (size - 1));

                    min = average - s;
                    max = average + s;
                }

                if(i < pattern.Keys.size()){
                    Key patternKey = pattern.Keys.get(i);
                    long patternKeyKeyPressTime = patternKey.getKeyPressTime();
                    if(patternKeyKeyPressTime >= min && patternKeyKeyPressTime <= max){
                        currentPossibility = 1;
                    }
                }
            }

            possibilities.add(currentPossibility);
        }

        if(!possibilities.isEmpty()) {
            int posSum = 0;
            for (double pos : possibilities) {
                posSum += pos;
            }

            return posSum / (double)possibilities.size();
        }

        return 0;
    }


}
