package hu.r00ts.beesmarter.businesslogic.DTO;

import java.util.ArrayList;
import java.util.List;

public class Pattern {

    public List<Key> Keys = new ArrayList<Key>();

    public Integer getFullTime() {
        return Keys.get(Keys.size() - 1).KeyUp.Time;
    }

    public Integer getPrevTime(Integer index) {
        if (index == 0 || index > Keys.size()) {
            return 0;
        }

        return Keys.get(index).KeyDown.Time - Keys.get(index - 1).KeyUp.Time;
    }

    public Integer getPrevTime(Key key) {
        return getPrevTime(Keys.indexOf(key));
    }

    public Integer getNextTime(Integer index) {
        if (index < 0 || index >= Keys.size()) {
            return 0;
        }

        return Keys.get(index + 1).KeyDown.Time - Keys.get(index).KeyUp.Time;
    }

    public Integer getNextTime(Key key) {
        return getNextTime(Keys.indexOf(key));
    }

    public Integer getPasswordLength() {
        return Keys.size();
    }

    public Double getWriteSpeed() {
        return getPasswordLength().doubleValue() / getFullTime().doubleValue();
    }
}
