package hu.r00ts.beesmarter.businesslogic.DTO;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class Training {

    public List<Pattern> Patterns = new ArrayList<Pattern>();

    public Double getAvarageWriteSpeed() {
        Double writeSpeed = 0d;
        for (Pattern pattern : Patterns) {
            writeSpeed += pattern.getWriteSpeed();
        }
        return writeSpeed / Patterns.size();
    }

    public Double getHighestWriteSpeed() {
        Double highest = 0d;
        for (Pattern pattern : Patterns) {
            Double writeSpeed = pattern.getWriteSpeed().doubleValue() / pattern.getFullTime().doubleValue();
            if (writeSpeed > highest) {
                highest = writeSpeed;
            }
        }

        return highest;
    }

    public Double getLowestWriteSpeed() {
        Pattern first = Patterns.get(0);
        Double lowest = first.getWriteSpeed().doubleValue() / first.getFullTime().doubleValue();
        for (Pattern pattern : Patterns) {
            Double writeSpeed = pattern.getWriteSpeed().doubleValue() / pattern.getFullTime().doubleValue();
            if (writeSpeed < lowest) {
                lowest = writeSpeed;
            }
        }

        return lowest;
    }

    public HashMap<String, Double> getAvarageKeypresses() {
        HashMap<String, Double> avarageKeypresses = new HashMap<String, Double>();
        Integer divider = 1;
        for (Pattern pattern : Patterns) {
            for (Key key : pattern.Keys) {
                if (key.KeyDown.Code.length() == 1) {
                    if (avarageKeypresses.get(key.KeyDown.Code) == null) {
                        avarageKeypresses.put(key.KeyDown.Code, 0d);
                    }
                    avarageKeypresses.put(key.KeyDown.Code, avarageKeypresses.get(key.KeyDown.Code) + key.getKeyPressTime().doubleValue());
                    divider++;
                }
            }
        }

        for (String key : avarageKeypresses.keySet()) {
            avarageKeypresses.put(key, avarageKeypresses.get(key) / divider);
        }

        return avarageKeypresses;
    }


    public Integer getWriteSpeedMonotony() {
        /*
            -2 csak csökken
            -1 többnyire csökken
            0
            1 többnyire nő
            2 csak nő
             */
        Double[] writeSpeeds = new Double[Patterns.size()];
        Integer i = 0;
        for (Pattern pattern : Patterns) {
            writeSpeeds[i] = pattern.getWriteSpeed();
            i++;
        }

        // nő
        Double temp = 0d;
        Double lowest = writeSpeeds[0];
        Boolean rising = true;
        Boolean semiRising = true;
        for (Double writeSpeed : writeSpeeds) {
            if (writeSpeed > temp) {
                temp = writeSpeed;
            } else {
                rising = false;
            }

            if (writeSpeed < lowest) {
                semiRising = false;
            }
        }
        if (rising) {
            return 2;
        }

        if (semiRising) {
            return 1;
        }

        // csökken
        temp = 0d;
        Double highest = writeSpeeds[0];
        Boolean lowering = true;
        Boolean semiLowering = true;
        for (Double writeSpeed : writeSpeeds) {
            if (writeSpeed < temp) {
                temp = writeSpeed;
            } else {
                lowering = false;
            }

            if (writeSpeed > highest) {
                semiLowering = false;
            }
        }
        if (lowering) {
            return -2;
        }

        if (semiLowering) {
            return -1;
        }

        return 0;
    }

    public int getMaxKeyCount(){
        int maxKeyCount = 0;
        for(Pattern p : Patterns){
            int size = p.Keys.size();
            if(size > maxKeyCount){
                maxKeyCount = size;
            }
        }

        return maxKeyCount;
    }
}
