package hu.r00ts.beesmarter.businesslogic.biometric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hu.r00ts.beesmarter.businesslogic.DTO.Key;
import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class KeyPressPlaceConstraint extends BaseConstraint {

    private Double xThreshold = 0d;
    private Double yThreshold = 0d;
    private HashMap<String, List<Key>> keys = new HashMap<>();
    private HashMap<String, Double> xDispersions = new HashMap<>();
    private HashMap<String, Double> yDispersions = new HashMap<>();

    public KeyPressPlaceConstraint(Training training, Pattern pattern) {
        super(training, pattern);
    }

    @Override
    public double getPossibility() {
        setKeys();
        setXDispersion();
        setYDispersion();

        Double possibility = 0d;

        Integer valid = 0;
        Integer keyListSize = 0;

        Double avgX = 0d;
        Double avgY = 0d;
        for (Key key : pattern.Keys) {
            List<Key> keyList = keys.get(key.KeyDown.Code);
            avgX = getXAvg(keyList);
            Double xDispersion = xDispersions.get(key.KeyDown.Code);
            avgY = getYAvg(keyList);
            Double yDispersion = yDispersions.get(key.KeyDown.Code);
            if ((key.KeyDown.X <= (avgX + xDispersion) || key.KeyDown.X >= (avgX - xDispersion))
                    && (key.KeyDown.Y <= (avgY + yDispersion) || key.KeyDown.Y >= (avgY - yDispersion))) {
                valid++;
            }
            keyListSize = keyList.size();
        }
        possibility += valid.doubleValue() / keyListSize;
        return possibility * getWeight();
    }

    private void setKeys() {
        for (Pattern pattern : training.Patterns) {
            for (Key key : pattern.Keys) {
                List<Key> hashMapKey = keys.get(key.KeyDown.Code);
                if (keys.get(key.KeyDown.Code) == null) {
                    List<Key> keyList = new ArrayList<>();
                    keyList.add(key);
                    keys.put(key.KeyDown.Code, keyList);
                } else {
                    hashMapKey.add(key);
                }
            }
        }
    }

    private void setXDispersion() {
        for (String keyString : keys.keySet()) {
            List<Key> keyList = keys.get(keyString);
            Double avg = getXAvg(keyList);
            Double dispersion = 0d;
            for (Key key : keyList) {
                dispersion += (key.KeyDown.X - avg) * (key.KeyDown.X - avg);
            }
            xDispersions.put(keyString, Math.sqrt(dispersion / (keyList.size() - 1)));
        }
    }

    private void setYDispersion() {
        for (String keyString : keys.keySet()) {
            List<Key> keyList = keys.get(keyString);
            Double avg = getYAvg(keyList);
            Double dispersion = 0d;
            for (Key key : keyList) {
                dispersion += (key.KeyDown.Y - avg) * (key.KeyDown.Y - avg);
            }
            yDispersions.put(keyString, Math.sqrt(dispersion / (keyList.size() - 1)));
        }
    }

    private Double getXAvg(List<Key> keys) {
        Double avg = 0d;
        for (Key key : keys) {
            avg += key.KeyDown.X;
        }

        return avg / keys.size();
    }

    private Double getYAvg(List<Key> keys) {
        Double avg = 0d;
        for (Key key : keys) {
            avg += key.KeyDown.Y;
        }

        return avg / keys.size();
    }
}
