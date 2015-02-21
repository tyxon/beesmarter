package hu.r00ts.beesmarter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hu.r00ts.beesmarter.R;

public class CheckPasswordActivity extends Activity {

    public EditText password;
    public Button checkPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_password);

        password = (EditText) findViewById(R.id.password);
        checkPasswordButton = (Button) findViewById(R.id.checkPasswordButton);

        checkPasswordButton.setOnClickListener(checkPasswordButtonOnClickListener);
    }

    View.OnClickListener checkPasswordButtonOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(CheckPasswordActivity.this, ManualTestActivity.class);
            startActivity(intent);
        }

    };

}
