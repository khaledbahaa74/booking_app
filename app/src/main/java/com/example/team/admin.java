package com.example.team;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class admin extends AppCompatActivity {

    public EditText editName;
    EditText editPrice;
    EditText editTickets;
    Button confirmButton;
    Spinner date;
    Spinner time;
    ListView adminList;
    DB sqLiteDatabase;
    ImageButton poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        editName= findViewById(R.id.filmNameInput);
        editPrice= findViewById(R.id.seatPriceInput);
        editTickets= findViewById(R.id.availableSeatsInput);
        confirmButton= findViewById(R.id.confirmButton);
        poster= findViewById(R.id.moviePosterInput);
        date = findViewById(R.id.dateSpinner);
        time = findViewById(R.id.timeSpinner);
        adminList= findViewById(R.id.adminList);
        sqLiteDatabase= new DB(this);

        ArrayList<Movie> contacts=sqLiteDatabase.getAllMovies();
        MovieAdapter contactAdapter=new MovieAdapter(this,R.layout.item_contact,contacts);
        adminList.setAdapter(contactAdapter);

        confirmButton.setOnClickListener(view -> {
            String numVal =editTickets.getText().toString();
            String priceVal = editPrice.getText().toString();
            String nameVal=editName.getText().toString();
            if (numVal.length() == 0 || nameVal.length() == 0 || priceVal.length() == 0)
            {
                Toast.makeText(admin.this, "Enter Completed Data", Toast.LENGTH_SHORT).show();
            }else{
                int tickets = Integer.parseInt(numVal);
                int price = Integer.parseInt(priceVal);
                Drawable drawable = poster.getDrawable();
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bb = stream.toByteArray();
                String da = date.getSelectedItem().toString();
                String ti = time.getSelectedItem().toString();
                Movie movie = new Movie(nameVal, bb, tickets, price, da, ti);
                sqLiteDatabase.addMovie(movie);
                Toast.makeText(admin.this, "Movie Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), admin.class);
                startActivity(intent);
            }
        });
    }
    public void openGallery(View view){
        Intent intentImg=new Intent(Intent.ACTION_GET_CONTENT);
        intentImg.setType("image/*");
        startActivityForResult(intentImg,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==100)
        {
            assert data != null;
            Uri uri=data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                poster.setImageBitmap(decodeStream);
            }catch (Exception ex)
            {
                Log.e("ex",ex.getMessage());
            }

        }
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