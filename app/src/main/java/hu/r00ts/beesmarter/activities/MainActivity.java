package hu.r00ts.beesmarter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import hu.r00ts.beesmarter.R;

public class MainActivity extends Activity {

    public EditText serverAddress;
    public Button automaticTestButton;
    public Button manualTestButton;
    public Spinner testCaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serverAddress = (EditText) findViewById(R.id.serverAddress);
        automaticTestButton = (Button) findViewById(R.id.automaticTestButton);
        manualTestButton = (Button) findViewById(R.id.manualTestButton);
        testCaseList = (Spinner) findViewById(R.id.testCase);

        // set dropdownlist data
        String[] items = new String[]{"TEST1", "TEST2", "TEST3", "TEST4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        testCaseList.setAdapter(adapter);

        automaticTestButton.setOnClickListener(automaticTestButtonOnClickListener);
        manualTestButton.setOnClickListener(manualTestButtonOnClickListener);
    }

    OnClickListener automaticTestButtonOnClickListener = new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Toast.makeText(MainActivity.this, serverAddress.getText(), Toast.LENGTH_SHORT).show();
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
