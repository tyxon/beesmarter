package hu.r00ts.beesmarter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import hu.r00ts.beesmarter.R;

public class ManualTestActivity extends Activity {

    public Button setPasswordButton;
    public Button checkPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_test);
        setPasswordButton = (Button) findViewById(R.id.setPasswordButton);
        checkPasswordButton = (Button) findViewById(R.id.checkPasswordButton);

        setPasswordButton.setOnClickListener(setPasswordButtonOnClickListener);
        checkPasswordButton.setOnClickListener(checkPasswordButtonOnClickListener);
    }

    View.OnClickListener setPasswordButtonOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(ManualTestActivity.this, SetPasswordActivity.class);
            startActivity(intent);
        }

    };

    View.OnClickListener checkPasswordButtonOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(ManualTestActivity.this, CheckPasswordActivity.class);
            startActivity(intent);
        }

    };
}
