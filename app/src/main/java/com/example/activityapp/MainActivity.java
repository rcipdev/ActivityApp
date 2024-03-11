package com.example.activityapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private String sourceActivity = null;

    TextView thread_counter;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread_counter = (TextView) findViewById(R.id.tv_thread_counter);

        Button btn_activity_b = findViewById(R.id.btn_activity_b);
        Button btn_activity_c = findViewById(R.id.btn_activity_c);
        Button dialog_btn = findViewById(R.id.btn_dialog);
        Button btn_exit_app = findViewById(R.id.btn_exit);

        thread_counter.setText(String.format("Thread Counter: %d", count));

        btn_activity_b.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FirstActivity.class);
            sourceActivity = "ActivityB";
            startActivity(intent);
        });

        btn_activity_c.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            sourceActivity = "ActivityC";
            startActivity(intent);
        });

        dialog_btn.setOnClickListener(v -> openDialog());

        btn_exit_app.setOnClickListener(v -> finishAffinity());
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onRestart() {
        super.onRestart();

        if ("ActivityB".equals(sourceActivity)) {
            count += 5;
        } else if ("ActivityC".equals(sourceActivity)) {
            count += 10;
        }

        thread_counter.setText(String.format("Thread Counter: %d", count));
        sourceActivity = null;
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Sample Dialog");
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}