package com.example.myapplication.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;


public class MainActivity extends AppCompatActivity {

    TextView textName, textEmail;
    private MainActDbBinding activityMainBinding;
    private MainActivityClickHandlers clickHandlers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act_db);

        Person person1 = new Person("Le Van A","a@gmail.com");

        activityMainBinding = DataBindingUtil.setContentView(
                this,
                R.layout.main_act_db
        );

        activityMainBinding.setPerson(person1);

        clickHandlers = new MainActivityClickHandlers(this);
        activityMainBinding.setClickHandlers(clickHandlers);
    }

    public class MainActivityClickHandlers{
        Context context;

        public MainActivityClickHandlers(Context context){
            this.context = context;
        }

        public void button1OnClick(View view){
            Toast.makeText(context,"Hello", Toast.LENGTH_SHORT).show();
        }

        public void button2OnClick(View view){
            Toast.makeText(context,"Bye", Toast.LENGTH_SHORT).show();
        }
    }
}