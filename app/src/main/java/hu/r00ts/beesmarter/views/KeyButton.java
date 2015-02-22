package hu.r00ts.beesmarter.views;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.AttributeSet;
import android.widget.Button;

public class KeyButton extends Button {

    private int keyCode;
    private static boolean isShiftPressed = false;

    public KeyButton(Context context) {
        super(context);
    }

    public KeyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setKeyCode(int keyCode){
        this.keyCode = keyCode;
        setText(getVisibleKeyCodeText());
    }

    public String getVisibleKeyCodeText(){
        switch(keyCode){
            case 32:
                return "SPACE";
            case Keyboard.KEYCODE_DELETE:
                return "BACKSPACE";
            case Keyboard.KEYCODE_DONE:
                return "ENTER";
            case Keyboard.KEYCODE_SHIFT:
                return "SHIFT";
            default:
                return getKeyCodeText();
        }
    }

    public String getKeyCodeText(){
        char c = (char)keyCode;
        return Character.toString(c);
    }

    public int getKeyCode(){
        return keyCode;
    }

    public static void setIsShiftPressed(boolean isShiftPressed){
        KeyButton.isShiftPressed = isShiftPressed;
    }

    public void setShiftTexts(){
        switch(keyCode){
            case 32:
                setText("SPACE");
                break;
            case Keyboard.KEYCODE_DELETE:
                setText("BACKSPACE");
                break;
            case Keyboard.KEYCODE_DONE:
                setText("ENTER");
                break;
            case Keyboard.KEYCODE_SHIFT:
                setText("SHIFT");
                break;
            default:
                if(isShiftPressed){
                    setText(getKeyCodeText().toUpperCase());
                }else{
                    setText(getKeyCodeText().toLowerCase());
                }
                break;
        }
    }

    public static boolean getIsShiftPressed(){
        return isShiftPressed;
    }
}
