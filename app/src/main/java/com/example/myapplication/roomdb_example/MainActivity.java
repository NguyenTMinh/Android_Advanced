package com.example.myapplication.roomdb_example;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.R;
import com.example.myapplication.retrofit.PrintfulService;
import com.example.myapplication.retrofit.RetrofitInstance;
import com.example.myapplication.retrofit.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ContactDatabase mContactDatabase;
    private RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;
    private FloatingActionButton mButton;
    private List<Contact> mList;

    // Them Retrofit
    private PrintfulService mPrintfulService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act_rd);

        mContactDatabase = Room.databaseBuilder(this, ContactDatabase.class, "ContactDB")
                .allowMainThreadQueries()
                .build();

        mList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.rv_list);
        mAdapter = new ContactAdapter(mList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mButton = findViewById(R.id.bt_add);
        mButton.setOnClickListener(view -> {
            Dialog dialog = new Dialog(MainActivity.this);

            dialog.setContentView(R.layout.dialog_add_contact);

            final EditText nameEditText = dialog.findViewById(R.id.et_name);
            final EditText mailEditText = dialog.findViewById(R.id.et_mail);
            Button button = dialog.findViewById(R.id.bt_add_2);

            button.setOnClickListener(view1 -> {
                String name = nameEditText.getText().toString().trim();
                String mail = mailEditText.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(mail)) {
                    Contact contact = new Contact();
                    contact.setName(name);
                    contact.setEmail(mail);

                    mContactDatabase.getContactDAO().addContact(contact);

                    mList.add(contact);
                    mAdapter.notifyDataSetChanged();

                    dialog.cancel();
                }
            });

            dialog.show();
        });

        // set retrofit
        mPrintfulService = RetrofitInstance.getService();
        getUsers();
    }

    private void getUsers() {
        Call<List<User>> call = mPrintfulService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    users.stream().forEach(user -> {
                        Contact contact = new Contact();
                        contact.setName(user.getName());
                        contact.setEmail(user.getEmail());

                        mContactDatabase.getContactDAO().addContact(contact);
                        mList.add(contact);
                    });

                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}
