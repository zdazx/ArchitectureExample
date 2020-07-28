package com.thoughtworks.architectureexample;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private ArchViewModel archViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.increase_text_view);
        Button increaseBtn = findViewById(R.id.increase_btn);
        increaseBtn.setOnClickListener(view -> startIncrease());

        archViewModel = new ViewModelProvider(this).get(ArchViewModel.class);
        final Observer<Integer> observer = integer -> textView.setText(String.valueOf(integer));
        archViewModel.getNumber().observe(this, observer);
    }

    private void startIncrease() {
        archViewModel.increase();
    }
}