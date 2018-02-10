package com.example.soumit.activitylifecycle.ActivityLifeCycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.soumit.activitylifecycle.R;

public class LifeCycle extends AppCompatActivity {

    private static final String TAG = "LifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart: started!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart: started!");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(this, "onPostResume: started", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPostResume: started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop: started!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop: started!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy: started", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy: started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause: started", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause: started");
    }
}
