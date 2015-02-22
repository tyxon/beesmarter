package hu.r00ts.beesmarter.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.*;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.r00ts.beesmarter.R;
import hu.r00ts.beesmarter.businesslogic.DTO.Key;
import hu.r00ts.beesmarter.businesslogic.DTO.KeyState;
import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;
import hu.r00ts.beesmarter.views.KeyButton;

public class SetPasswordActivity extends Activity {

    public EditText password;
    public EditText passwordCheck;
    public Button setPasswordButton;
    public Button endPatternButton;
    public TextView timer;
    public LinearLayout keyboardLayout;
    public List<KeyButton> buttons;

    public Pattern pattern;
    public static Training training;

    long startTime;
    boolean isStarted;
    String currentPassword;

    boolean isStartedCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        isStarted = false;
        isStartedCounter = false;
        currentPassword = null;

        password = (EditText) findViewById(R.id.password);
        passwordCheck = (EditText) findViewById(R.id.passwordCheck);
        setPasswordButton = (Button) findViewById(R.id.setPasswordButton);
        endPatternButton = (Button) findViewById(R.id.endPatternButton);
        keyboardLayout = (LinearLayout) findViewById(R.id.keyboardLayout);
        timer = (TextView) findViewById(R.id.timer);

        setPasswordButton.setOnClickListener(setPasswordButtonOnClickListener);
        endPatternButton.setOnClickListener(endPatternButtonOnClickListener);

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

        training = new Training();
    }

    void startCounter(){
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                new AlertDialog.Builder(SetPasswordActivity.this).setTitle("Time").setMessage("Time is up.").setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                SetPasswordActivity.this.endPattern();
                            }
                        }).show();
            }

        }.start();
    }

    OnClickListener setPasswordButtonOnClickListener = new OnClickListener(){

        @Override
        public void onClick(View arg0) {
            if (KeyButton.getIsShiftPressed()) {
                KeyButton.setIsShiftPressed(false);
                setShiftTexts();
            }
            isStarted = false;

            String p = password.getText().toString();
            if(currentPassword == null){
                if(!p.isEmpty()){
                    currentPassword = p;
                }
            }else{
                if(!currentPassword.equals(password.getText().toString()) && !p.isEmpty()){
                    new AlertDialog.Builder(SetPasswordActivity.this).setTitle("Alert").setMessage("Wrong password. Try again.").setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).show();
                    pattern = null;
                }
            }

            if(p.isEmpty()){
                new AlertDialog.Builder(SetPasswordActivity.this).setTitle("Alert").setMessage("Empty password. Try again.").setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            } else{
                createNewPattern();
            }

            password.setText("");
            passwordCheck.setText("");
        }

    };


    OnClickListener endPatternButtonOnClickListener = new OnClickListener(){

        @Override
        public void onClick(View arg0) {
            endPattern();
        }

    };

    void endPattern(){
        finish();
    }

    OnTouchListener keyButtonOnClickListener;

    {
        keyButtonOnClickListener = new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                KeyButton keyButton = (KeyButton) v;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(!isStarted && Keyboard.KEYCODE_DONE != keyButton.getKeyCode()){
                        isStarted = true;
                        createNewPattern();
                        Log.d("STATE", "started");
                    }else if(pattern != null){
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
                    if(!isStartedCounter && keyButton.getKeyCode() != Keyboard.KEYCODE_DONE){
                        isStartedCounter = true;
                        startCounter();
                    }
                    Key key = null;
                    if(pattern != null) {
                        for (Key k : pattern.Keys) {
                            if (k.KeyDown.Code.equals(keyButton.getVisibleKeyCodeText())) {
                                key = k;
                            }
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
                            if(currentPassword == null){
                                if(!p.isEmpty()){
                                    currentPassword = p;
                                }
                            } else{
                                if(!currentPassword.equals(password.getText().toString()) && !p.isEmpty()){
                                    new AlertDialog.Builder(SetPasswordActivity.this).setTitle("Alert").setMessage("Wrong password. Try again.").setPositiveButton("Ok",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            }).show();
                                    pattern = null;
                                }
                            }
                            if(p.isEmpty()){
                                new AlertDialog.Builder(SetPasswordActivity.this).setTitle("Alert").setMessage("Empty password. Try again.").setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).show();
                            }else{
                                createNewPattern();
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

                    passwordCheck.setText(password.getText());

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

    public long getCurrentTime(){
        return System.currentTimeMillis() - startTime;
    }

    public void createNewPattern(){
        startTime = System.currentTimeMillis();

        if(pattern != null && pattern.Keys.size() > 0){
            Log.d("STATE", "end");
            training.Patterns.add(pattern);
            setPasswordButton.setText(String.valueOf(training.Patterns.size()));
        }

        pattern = new Pattern();
    }
}
