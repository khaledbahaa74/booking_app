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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView moviesList;
    DB dataB;

    String loggedInMail;
    String loggedInPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        loggedInMail = (String) getIntent().getSerializableExtra("Email");
        loggedInPhone =(String) getIntent().getSerializableExtra("phone");
        System.out.println(loggedInMail);
        System.out.println(loggedInPhone);

        moviesList= findViewById(R.id.moviesList);
        dataB = new DB (this);

        ArrayList<Movie>movies=dataB.getAllMovies();
        MovieAdapter contactAdapter=new MovieAdapter(this,R.layout.item_contact,movies);
        moviesList.setAdapter(contactAdapter);
        moviesList.setOnItemClickListener((adapterView, view, i, l) -> {
            Movie currentMovie = movies.get(i);
            String name = currentMovie.getFilmName();
            String ticket = Integer.toString(currentMovie.getSeats());
            String price = Integer.toString(currentMovie.getPrice());
            String date = (currentMovie.getDate());
            String time = (currentMovie.getTime());
            if(Integer.parseInt(ticket) == 0)
                Toast.makeText(MainActivity.this, "No Tickets Available For This Show", Toast.LENGTH_LONG).show();
            else {
                Intent intent = new Intent(getApplicationContext(), request.class);
                intent.putExtra("name", name);
                intent.putExtra("tickets", ticket);
                intent.putExtra("price", price);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("userMail", loggedInMail);
                intent.putExtra("phone", loggedInPhone);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.historyPage:
                Intent intent = new Intent(getApplicationContext(), history.class);
                intent.putExtra("userMail", loggedInMail);
                intent.putExtra("phone", loggedInPhone);
                startActivity(intent);
                return true;
            case R.id.logout:
                intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}