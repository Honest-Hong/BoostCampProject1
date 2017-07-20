package com.project.boostcamp.asynctaskexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnUpdateListener{
    private ProgressBar progressBar;
    private ProgressAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        findViewById(R.id.button_start).setOnClickListener(this);
        findViewById(R.id.button_reset).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button_start:
                if(task!= null && !task.isCancelled()) task.cancel(true);
                task = new ProgressAsyncTask(this);
                task.execute(0, 0, 0);
                break;
            case R.id.button_reset:
                task.cancel(true);
                break;
        }
    }

    @Override
    public void onUpdate(int value) {
        progressBar.setProgress(value);
    }
}
