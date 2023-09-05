package com.example.myapplication.retrofit;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitMainActivity extends AppCompatActivity {
    private PrintfulService mPrintfulService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act_db);

        mPrintfulService = RetrofitInstance.getService();
    }

    private void getUsers() {
        Call call = mPrintfulService.getUsers();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("MinhNTn", "onResponse: successful");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("MinhNTn", "onFailure: fail");
            }
        });
    }
}
