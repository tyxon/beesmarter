package hu.r00ts.beesmarter.businesslogic;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hu.r00ts.beesmarter.businesslogic.DTO.*;
import hu.r00ts.beesmarter.businesslogic.biometric.*;

public class PasswordBehaviorChecker {

    private String password;
    private Training training;

    private List<BaseConstraint> constraints;

    public PasswordBehaviorChecker(String password, Training training){
        this.password = password;
        this.training = training;
    }

    public boolean isOk(Pattern pattern){
        Log.d(PasswordBehaviorChecker.class.getName(), "Checking 'isPasswordMatch'");
        if(!isPasswordMatch(pattern)){
            Log.d(PasswordBehaviorChecker.class.getName(), "False");
            return false;
        }else{
            Log.d(PasswordBehaviorChecker.class.getName(), "True");
        }

        constraints  = new ArrayList<>();
        constraints.add(new OverallTimeConstraint(training, pattern));
        constraints.add(new KeyPressedConstraint(training, pattern));
        constraints.add(new KeyReleasedConstraint(training, pattern));
        constraints.add(new KeyPressPlaceConstraint(training, pattern));

        double possibilities = 0;
        double sumWeights = 0;

        for(BaseConstraint constraint : constraints){
            Log.d(PasswordBehaviorChecker.class.getName(), "Checking " + constraint.getClass().getName());

            double possibility = constraint.getPossibility();
            possibilities += possibility;
            double weight = constraint.getWeight();
            sumWeights += weight;
            Log.d(constraint.getClass().getName(),"Possibility: " + possibility);
            Log.d(constraint.getClass().getName(),"Weight: " + weight);

            Log.d(constraint.getClass().getName(),"Current result: " + possibilities / sumWeights);
        }

        double result = possibilities / sumWeights;
        Log.d(PasswordBehaviorChecker.class.getName() + " FR","Final result: " + result);
        return result > 0.60d;
    }

    private boolean isPasswordMatch(Pattern pattern){
        String patternPassword = "";
        Boolean isUpperCase = false;
        KeyFor: for(Key key : pattern.Keys) {
            switch (key.KeyDown.Code) {
                case "SHIFT":
                    isUpperCase = true;
                    break;
                case "ENTER":
                    break KeyFor;
                case "SPACE":
                    patternPassword = patternPassword.concat(" ");
                    break;
                case "BACKSPACE":
                    String tempPassword = patternPassword;
                    if (tempPassword.length() > 0) {
                        patternPassword = patternPassword.substring(0, tempPassword.length() - 1);
                    }
                    break;
                default:
                    if (isUpperCase) {
                        patternPassword = patternPassword.concat(key.KeyDown.Code.toUpperCase());
                        isUpperCase = false;
                    } else {
                        patternPassword = patternPassword.concat(key.KeyDown.Code);
                    }
                    break;
            }
        }

        return password.equals(patternPassword);
    }

}
