package hu.r00ts.beesmarter.businesslogic;

import android.util.Log;

import hu.r00ts.beesmarter.businesslogic.DTO.*;
import hu.r00ts.beesmarter.businesslogic.biometric.WritingSpeedTest;

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

        WritingSpeedTest writingSpeedTest = new WritingSpeedTest(training, pattern);
        if (!writingSpeedTest.run()) {
           return false;
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

        Log.d("answer", "patternPassword:" + patternPassword);

        return password.equals(patternPassword);
    }

}
