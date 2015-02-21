package hu.r00ts.beesmarter.businesslogic;

import android.util.Log;

import hu.r00ts.beesmarter.businesslogic.DTO.*;

public class PasswordBehaviorChecker {

    private String password;
    private Training training;

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

        return true;
    }

    private boolean isPasswordMatch(Pattern pattern){
        String patternPassword = "";
        for(Key key : pattern.Keys){
            patternPassword = patternPassword.concat(String.valueOf(key.KeyDown.Code));
        }

        return password.compareTo(patternPassword) == 0;
    }

}
