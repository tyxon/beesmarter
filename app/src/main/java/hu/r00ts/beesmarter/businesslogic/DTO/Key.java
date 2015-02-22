package hu.r00ts.beesmarter.businesslogic.DTO;

public class Key {

    public KeyState KeyDown;

    public KeyState KeyUp;

    public Motion getMotion() {
        Motion motion = new Motion();
        motion.X = KeyUp.X - KeyDown.X;
        motion.Y = KeyUp.Y - KeyDown.Y;
        return motion;
    }

    public Long getKeyPressTime() {
        return KeyUp.Time - KeyDown.Time;
    }

}
