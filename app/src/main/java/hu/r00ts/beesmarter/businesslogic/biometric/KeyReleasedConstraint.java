package hu.r00ts.beesmarter.businesslogic.biometric;


import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class KeyReleasedConstraint extends BaseConstraint {

    public KeyReleasedConstraint(Training training, Pattern pattern) {
        super(training, pattern);
    }

    @Override
    public double getPossibility() {
        return 1;
    }


}
