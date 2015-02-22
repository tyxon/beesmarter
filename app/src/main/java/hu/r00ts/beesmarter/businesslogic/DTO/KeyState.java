package hu.r00ts.beesmarter.businesslogic.DTO;

public class KeyState {

    public long Time;

    public String Code;

    public int X;

    public int Y;

    public String toDebugString(String tag){
        return String.format("%s - Code: %s, X: %s%%, Y: %s%%, Time: %s", tag, Code, X, Y, Time);
    }

}
