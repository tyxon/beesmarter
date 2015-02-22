package hu.r00ts.beesmarter.businesslogic.DTO;

import java.util.ArrayList;
import java.util.List;

public class Pattern {

    public List<Key> Keys = new ArrayList<Key>();

    public Long getFullTime() {
        return Keys.get(Keys.size() - 1).KeyUp.Time;
    }

    public Long getPrevTime(Integer index) {
        if (index == 0 || index > Keys.size()) {
            return Long.valueOf(0);
        }

        return Keys.get(index).KeyDown.Time - Keys.get(index - 1).KeyUp.Time;
    }

    public Long getPrevTime(Key key) {
        return getPrevTime(Keys.indexOf(key));
    }

    public Long getNextTime(Integer index) {
        if (index < 0 || index >= Keys.size() - 1) {
            return Long.valueOf(0);
        }

        return Keys.get(index + 1).KeyDown.Time - Keys.get(index).KeyUp.Time;
    }

    public Long getNextTime(Key key) {
        return getNextTime(Keys.indexOf(key));
    }

    public Integer getPasswordLength() {
        return Keys.size();
    }

    public Double getWriteSpeed() {
        return getPasswordLength().doubleValue() / getFullTime().doubleValue();
    }

    public boolean isCloseToPattern(Pattern pattern) {
        boolean isOk = true;
        for(Key key : pattern.Keys){
            double possibility = 1;
            int index = pattern.Keys.indexOf(key);
            if(index < Keys.size()){
                Key currentKey = Keys.get(index);
                if(Math.abs(currentKey.getKeyPressTime() - key.getKeyPressTime()) > 40){
                    possibility -= 0.11;
                }

                if(Math.abs(currentKey.KeyUp.X - key.KeyDown.X) > 50 && Math.abs(currentKey.KeyUp.Y - key.KeyDown.Y) > 50){
                    possibility -= 0.11;
                }
                if(possibility < 0.8){
                    isOk = false;
                    break;
                }
            }
        }

        return isOk;
    }
}
