package hu.r00ts.beesmarter.businesslogic.biometric;


import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class KeyPressedConstraint extends BaseConstraint {

    public KeyPressedConstraint(Training training, Pattern pattern) {
        super(training, pattern);
    }

    @Override
    public double getPossibility() {
        return 1;
    }
}
