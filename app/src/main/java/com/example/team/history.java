package com.example.team;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class history extends AppCompatActivity {

    ListView historyList;
    DB dataB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        String mail = (String) getIntent().getSerializableExtra("userMail");
        String phone = (String) getIntent().getSerializableExtra("phone");
        TextView userMail = findViewById(R.id.userMailOutput);
        TextView ph = findViewById(R.id.phoneOutput);
        historyList= findViewById(R.id.historyList);
        dataB = new DB (this);

        ArrayList<BookedMovie> movies = dataB.getAllBookedMovies(mail);
        HistoryAdapter historyAdapter = new HistoryAdapter(this,R.layout.booked,movies);
        historyList.setAdapter(historyAdapter);

        userMail.setText(mail);
        ph.setText(phone);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            Intent intent;
            intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}