package hu.r00ts.beesmarter.businesslogic.biometric;

import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public abstract class BaseConstraint {
    protected Training training;
    protected Pattern pattern;
    protected double weight = 1;

    BaseConstraint(Training training, Pattern pattern){
        this.training = training;
        this.pattern = pattern;
    }

    public double getWeight(){
        return weight;
    }

    public abstract double getPossibility();
}
