package com.example.teht5;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Counter createCount;
    private Counter startCount;
    private Counter resumeCount;
    private SharedPreferences.Editor editor;
    private Button button;
    private TextView creations;
    private TextView visibles;
    private TextView foregrounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Debug","onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.createCount = new Counter(100,0);
        this.startCount = new Counter(100,0);
        this.resumeCount = new Counter(100,0);

        button = findViewById(R.id.resetbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValue(v);
            }
        });

        creations = findViewById(R.id.creations);
        visibles = findViewById(R.id.visibles);
        foregrounds = findViewById(R.id.foreground);

        getPreferences();
        this.createCount.clickedButtonPlus();
        updateValues();

    }

    private void updateValues() {
        creations.setText(Integer.toString(this.createCount.getCurrentValue()));
        visibles.setText(Integer.toString(this.startCount.getCurrentValue()));
        foregrounds.setText(Integer.toString(this.resumeCount.getCurrentValue()));
    }

    public void resetValue(View v) {
        this.createCount.setToZero();
        this.startCount.setToZero();
        this.resumeCount.setToZero();
        updateValues();
    }

    protected void onStart() {
        Log.d("Debug", "onStart() called.");
        this.startCount.clickedButtonPlus();
        super.onStart();
        updateValues();
    }

    protected void onResume() {
        super.onResume();
        this.resumeCount.clickedButtonPlus();
        updateValues();
        Log.d("Debug", "onResume() called.");
    }

    protected void onPause() {
        Log.d("Debug", "onPause() called.");
        setPreferences();
        super.onPause();
    }

    protected void onStop() {
        Log.d("Debug","onStop() called.");
        super.onStop();
    }

    protected void onRestart() {
        Log.d("Debug","onRestart() called.");
        updateValues();
        super.onRestart();
    }

    protected void onDestroy() {
        Log.d("Debug","onDestroy() called");
        super.onDestroy();
    }


    private void setPreferences() {
        editor = getSharedPreferences("savedPrefs", MODE_PRIVATE).edit();
        editor.putInt("creates", createCount.getCurrentValue());
        editor.putInt("starts", startCount.getCurrentValue());
        editor.putInt("resumes", resumeCount.getCurrentValue());
        editor.apply();
    }

    private void getPreferences() {

        SharedPreferences prefs = getSharedPreferences("savedPrefs", MODE_PRIVATE);
        int storedCreates = prefs.getInt("creates", 0);
        int storedStarts = prefs.getInt("starts", 0);
        int storedResumes = prefs.getInt("resumes",0);

        this.createCount.setValue(storedCreates);
        this.startCount.setValue(storedStarts);
        this.resumeCount.setValue(storedResumes);
    }
}
