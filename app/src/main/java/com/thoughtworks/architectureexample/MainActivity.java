package com.thoughtworks.architectureexample;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button increaseBtn = findViewById(R.id.increase_btn);
        increaseBtn.setOnClickListener(view -> startIncrease());
    }

    private void startIncrease() {

    }
}