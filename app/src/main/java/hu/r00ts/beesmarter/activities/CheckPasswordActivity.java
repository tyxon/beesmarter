package hu.r00ts.beesmarter.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hu.r00ts.beesmarter.R;
import hu.r00ts.beesmarter.businesslogic.DTO.Key;
import hu.r00ts.beesmarter.businesslogic.DTO.KeyState;
import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;
import hu.r00ts.beesmarter.businesslogic.PasswordBehaviorChecker;
import hu.r00ts.beesmarter.views.KeyButton;

public class CheckPasswordActivity extends Activity {

    public EditText password;
    public Button checkPasswordButton;

    public LinearLayout keyboardLayout;
    public List<KeyButton> buttons;

    public Pattern pattern;

    long startTime;
    boolean isStarted;

    boolean isStartedCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_password);

        isStarted = false;

        password = (EditText) findViewById(R.id.checkPassword);
        checkPasswordButton = (Button) findViewById(R.id.checkPasswordButton);
        keyboardLayout = (LinearLayout) findViewById(R.id.checkPasswordKeyboardLayout);

        checkPasswordButton.setOnClickListener(checkPasswordButtonOnClickListener);

        //add keyboard buttons
        List<List<Integer>> keyRows = new ArrayList<>();
        List<Integer> keyRow = new ArrayList<>();
        keyRows.add(keyRow);
        keyRow.add(49);
        keyRow.add(50);
        keyRow.add(51);
        keyRow.add(52);
        keyRow.add(53);
        keyRow.add(54);
        keyRow.add(55);
        keyRow.add(56);
        keyRow.add(57);
        keyRow.add(48);

        keyRow = new ArrayList<>();
        keyRows.add(keyRow);
        keyRow.add(113);
        keyRow.add(119);
        keyRow.add(101);
        keyRow.add(114);
        keyRow.add(116);
        keyRow.add(121);
        keyRow.add(117);
        keyRow.add(105);
        keyRow.add(111);
        keyRow.add(112);

        keyRow = new ArrayList<>();
        keyRows.add(keyRow);
        keyRow.add(97);
        keyRow.add(115);
        keyRow.add(100);
        keyRow.add(102);
        keyRow.add(103);
        keyRow.add(104);
        keyRow.add(106);
        keyRow.add(107);
        keyRow.add(108);

        keyRow = new ArrayList<>();
        keyRows.add(keyRow);
        keyRow.add(122);
        keyRow.add(120);
        keyRow.add(99);
        keyRow.add(118);
        keyRow.add(98);
        keyRow.add(110);
        keyRow.add(109);

        keyRow = new ArrayList<>();
        keyRows.add(keyRow);
        keyRow.add(Keyboard.KEYCODE_SHIFT);
        keyRow.add(32);
        keyRow.add(46);
        keyRow.add(44);
        keyRow.add(Keyboard.KEYCODE_DELETE);
        keyRow.add(Keyboard.KEYCODE_DONE);

        buttons = new ArrayList<>();
        for(List<Integer> row : keyRows){
            LinearLayout keyboardRow = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            keyboardRow.setGravity(Gravity.CENTER_HORIZONTAL);
            keyboardRow.setLayoutParams(params);
            keyboardRow.setOrientation(LinearLayout.HORIZONTAL);
            keyboardLayout.addView(keyboardRow);
            for(Integer keyCode : row){
                KeyButton button = new KeyButton(new ContextThemeWrapper(this,R.style.KeyboardButton),null,R.style.KeyboardButton);
                ViewGroup.LayoutParams buttonLayout = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                button.setLayoutParams(buttonLayout);
                button.setKeyCode(keyCode);
                keyboardRow.addView(button);
                buttons.add(button);

                button.setOnTouchListener(keyButtonOnClickListener);
            }
        }

        pattern = new Pattern();
    }

    View.OnClickListener checkPasswordButtonOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            if(SetPasswordActivity.training != null && SetPasswordActivity.training.Patterns.size() > 0){
                if (KeyButton.getIsShiftPressed()) {
                    KeyButton.setIsShiftPressed(false);
                    setShiftTexts();
                }
                isStarted = false;

                String p = password.getText().toString();

                if(p.isEmpty()){
                    new AlertDialog.Builder(CheckPasswordActivity.this).setTitle("Alert").setMessage("Empty password. Try again.").setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).show();
                } else{
                    checkPassword();
                }

                password.setText("");
            }else{
                new AlertDialog.Builder(CheckPasswordActivity.this).setTitle("Alert").setMessage("No patterns set.").setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                CheckPasswordActivity.this.finish();
                            }
                        }).show();
            }

        }

    };

    public void checkPassword(){
        if(pattern != null && pattern.Keys.size() > 0){
            PasswordBehaviorChecker passwordBehaviorChecker = new PasswordBehaviorChecker(SetPasswordActivity.currentPassword, SetPasswordActivity.training);
            boolean isOk = passwordBehaviorChecker.isOk(pattern);
            new AlertDialog.Builder(CheckPasswordActivity.this).setTitle("Information").setMessage(isOk ? "Password OK" : "Password NOT OK").setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            createNewPattern();
                        }
                    }).show();
        }
    }

    public long getCurrentTime(){
        return System.currentTimeMillis() - startTime;
    }

    View.OnTouchListener keyButtonOnClickListener;

    {
        keyButtonOnClickListener = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                KeyButton keyButton = (KeyButton) v;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(!isStarted && Keyboard.KEYCODE_DONE != keyButton.getKeyCode()){
                        isStarted = true;
                    }

                    if(pattern.Keys.size() != 0 || Keyboard.KEYCODE_DONE != keyButton.getKeyCode()){
                        Key key = new Key();
                        pattern.Keys.add(key);
                        KeyState keyStateDown = new KeyState();

                        keyStateDown.Code = keyButton.getVisibleKeyCodeText();
                        keyStateDown.X = (int) (event.getX() / keyButton.getWidth() * 100);
                        keyStateDown.Y = (int) (event.getY() / keyButton.getHeight() * 100);
                        keyStateDown.Time = getCurrentTime();

                        Log.d("KEY", keyStateDown.toDebugString("DOWN"));

                        key.KeyDown = keyStateDown;
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Key key = null;
                    for (Key k : pattern.Keys) {
                        if (k.KeyDown.Code.equals(keyButton.getVisibleKeyCodeText())) {
                            key = k;
                        }
                    }
                    if(key != null){
                        KeyState keyStateUp = new KeyState();

                        keyStateUp.Code = keyButton.getVisibleKeyCodeText();
                        keyStateUp.X = (int)(event.getX() / keyButton.getWidth() * 100);
                        keyStateUp.Y = (int)(event.getY() / keyButton.getHeight() * 100);
                        keyStateUp.Time = getCurrentTime();

                        Log.d("KEY", keyStateUp.toDebugString("UP"));

                        key.KeyUp = keyStateUp;
                    }

                    String text = password.getText().toString();
                    switch (keyButton.getKeyCode()) {
                        case 32:
                            password.setText(text.concat(" "));
                            if (KeyButton.getIsShiftPressed()) {
                                KeyButton.setIsShiftPressed(false);
                                setShiftTexts();
                            }
                            break;
                        case Keyboard.KEYCODE_DELETE:
                            password.setText(text.substring(0, text.length() - 1));
                            if (KeyButton.getIsShiftPressed()) {
                                KeyButton.setIsShiftPressed(false);
                                setShiftTexts();
                            }
                            break;
                        case Keyboard.KEYCODE_DONE:
                            if (KeyButton.getIsShiftPressed()) {
                                KeyButton.setIsShiftPressed(false);
                                setShiftTexts();
                            }
                            isStarted = false;

                            String p = password.getText().toString();
                            if(p.isEmpty()){
                                new AlertDialog.Builder(CheckPasswordActivity.this).setTitle("Alert").setMessage("Empty password. Try again.").setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).show();
                            }else{
                                checkPassword();
                            }

                            password.setText("");
                            break;
                        case Keyboard.KEYCODE_SHIFT:
                            KeyButton.setIsShiftPressed(!KeyButton.getIsShiftPressed());
                            setShiftTexts();
                            break;
                        default:
                            String buttonText = keyButton.getText().toString();
                            password.setText(text.concat(buttonText));
                            if (KeyButton.getIsShiftPressed()) {
                                KeyButton.setIsShiftPressed(false);
                                setShiftTexts();
                            }
                            break;
                    }

                    return true;
                }

                return false;
            }

        };
    }

    public void setShiftTexts(){
        for(KeyButton button : buttons){
            button.setShiftTexts();
        }
    }

    public void createNewPattern(){
        startTime = System.currentTimeMillis();

        pattern = new Pattern();
    }
}
