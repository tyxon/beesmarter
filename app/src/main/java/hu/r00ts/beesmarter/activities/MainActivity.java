package hu.r00ts.beesmarter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hu.r00ts.beesmarter.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void manualTest(View view) {
        Intent intent = new Intent(this, ManualTestActivity.class);
        startActivity(intent);
    }
}
