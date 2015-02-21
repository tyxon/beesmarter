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
                    patternPassword = patternPassword.substring(0, tempPassword.length() - 1);
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
