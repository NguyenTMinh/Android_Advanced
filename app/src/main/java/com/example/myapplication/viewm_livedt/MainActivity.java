package com.example.myapplication.viewm_livedt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.viewm_livedt.viewmodel.ActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityViewModel mViewModel;

    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tv);
        mButton = findViewById(R.id.bt);

        mViewModel = new ViewModelProvider(this).get(ActivityViewModel.class);

        LiveData count = mViewModel.getmLiveData();

        count.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                String content = "Clicked " + (Integer)o;
                mTextView.setText(content);
            }
        });

        String content1 = "Clicked " + mViewModel.getCounter();
        mTextView.setText(content1);

        mButton.setOnClickListener(view -> {
            mViewModel.getIncreasedCounter();
        });
    }
}