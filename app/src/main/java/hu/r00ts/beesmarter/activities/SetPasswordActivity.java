package hu.r00ts.beesmarter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hu.r00ts.beesmarter.R;

public class SetPasswordActivity extends Activity {

    public EditText password;
    public Button setPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        password = (EditText) findViewById(R.id.password);
        setPasswordButton = (Button) findViewById(R.id.setPasswordButton);

        setPasswordButton.setOnClickListener(setPasswordButtonOnClickListener);
    }

    View.OnClickListener setPasswordButtonOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(SetPasswordActivity.this, ManualTestActivity.class);
            startActivity(intent);
        }

    };

}
