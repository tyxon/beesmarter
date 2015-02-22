package hu.r00ts.beesmarter.businesslogic.biometric;


import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class EnterTest {

    private Training training;
    private Pattern pattern;

    public EnterTest(Training training, Pattern pattern) {
        this.training = training;
        this.pattern = pattern;
    }

    public Boolean run() {
        Boolean allMainHasEnter = true;
        for (Pattern trainingPattern : training.Patterns) {
            if (!hasEnter(trainingPattern)) {
                allMainHasEnter = false;
                break;
            }
        }

        if (hasEnter(pattern) == allMainHasEnter) {
            return true;
        }

        return false;
    }

    public Boolean hasEnter(Pattern pattern) {
        if (pattern.Keys.get(pattern.Keys.size() - 1).KeyDown.Code.equals("ENTER")) {
            return true;
        }

        return false;
    }
}
