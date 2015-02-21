package hu.r00ts.beesmarter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hu.r00ts.beesmarter.R;
import hu.r00ts.beesmarter.businesslogic.AutomaticTestTask;

public class MainActivity extends Activity {

    public EditText serverAddress;
    public Button automaticTestButton;
    public Button manualTestButton;

    private String defaultIP = "192.168.2.8";
    //private String defaultIP = "192.168.0.100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serverAddress = (EditText) findViewById(R.id.serverAddress);
        automaticTestButton = (Button) findViewById(R.id.automaticTestButton);
        manualTestButton = (Button) findViewById(R.id.manualTestButton);

        serverAddress.setText(defaultIP);
        serverAddress.setHint(defaultIP);

        automaticTestButton.setOnClickListener(automaticTestButtonOnClickListener);
        manualTestButton.setOnClickListener(manualTestButtonOnClickListener);
    }

    OnClickListener automaticTestButtonOnClickListener = new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                AutomaticTestTask automaticTestTask = new AutomaticTestTask(MainActivity.this, serverAddress.getText().toString());
                automaticTestTask.execute();
            }

    };

    OnClickListener manualTestButtonOnClickListener = new OnClickListener(){

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(MainActivity.this, ManualTestActivity.class);
            startActivity(intent);
        }

    };

}
