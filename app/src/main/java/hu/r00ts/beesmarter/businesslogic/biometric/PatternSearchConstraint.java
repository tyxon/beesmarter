package hu.r00ts.beesmarter.businesslogic.biometric;

import java.util.ArrayList;
import java.util.List;

import hu.r00ts.beesmarter.businesslogic.DTO.Key;
import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class PatternSearchConstraint extends BaseConstraint {

    public PatternSearchConstraint(Training training, Pattern pattern) {
        super(training, pattern);
    }

    @Override
    public double getPossibility() {
        for(Pattern p : training.Patterns){
            if(p.isCloseToPattern(pattern)){
                return 1.1;
            }
        }

        return 0.5;
    }
}
