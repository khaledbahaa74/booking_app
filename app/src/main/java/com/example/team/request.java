package com.example.team;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class request extends AppCompatActivity {

    String na;
    String t;
    String p;
    String mail;
    String phone;
    String da;
    String ti;
    int reservedTickets;
    int numOfTickets;
    int price;
    int totalPrice;
    DB dataB;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        dataB = new DB(this);
        na = (String) getIntent().getSerializableExtra("name");
        t = (String) getIntent().getSerializableExtra("tickets");
        p = (String) getIntent().getSerializableExtra("price");
        mail = (String) getIntent().getSerializableExtra("userMail");
        phone = (String) getIntent().getSerializableExtra("phone");
        da = (String) getIntent().getSerializableExtra("date");
        ti = (String) getIntent().getSerializableExtra("time");
        numOfTickets = Integer.parseInt(t);
        price = Integer.parseInt(p);
        reservedTickets = 1;
        totalPrice = price;
        Button dec = findViewById(R.id.button1);
        TextView text = findViewById(R.id.num);
        TextView topr = findViewById(R.id.totalprice);
        TextView tipr = findViewById(R.id.ticketprice);
        TextView tickets = findViewById(R.id.tickets);
        TextView mn = findViewById(R.id.movieName);
        TextView email = findViewById(R.id.userMailOutput);
        TextView date = findViewById(R.id.movieDateOutput);
        TextView time = findViewById(R.id.movieTimeOutput);
        mn.setText(na);
        tipr.setText(Integer.toString(price));
        tickets.setText(Integer.toString(numOfTickets));
        text.setText(""+reservedTickets);
        topr.setText(""+totalPrice+" L.E.");
        email.setText(mail);
        date.setText(da);
        time.setText(ti);
        dec.setOnClickListener(v -> {
            if(reservedTickets>1)
            {
                reservedTickets--;
                totalPrice-=price;
            }
            text.setText(""+reservedTickets);
            topr.setText(""+totalPrice+" L.E.");
        });
        Button inc = findViewById(R.id.button2);
        inc.setOnClickListener(v -> {
            if(reservedTickets < numOfTickets)
            {
                reservedTickets++;
                totalPrice+=price;
            }
            text.setText(""+reservedTickets);
            topr.setText(""+totalPrice+" L.E.");
        });
        Button pay = findViewById(R.id.done);
        pay.setOnClickListener(view -> {
            EditText m= findViewById(R.id.month);
            String month = m.getText().toString();
            EditText y= findViewById(R.id.year);
            String year = y.getText().toString();
            EditText n= findViewById(R.id.card);
            String number = n.getText().toString();
            EditText c= findViewById(R.id.cvv);
            String cvv = c.getText().toString();
            if(month.length() < 2 || year.length() < 2 || number.length() < 16 || cvv.length() < 3)
                Toast.makeText(request.this, "Please Enter Valid Data", Toast.LENGTH_LONG).show();
            else if(((month.charAt(0) - '0') > 1) || (month.charAt(0) == '0' && month.charAt(1) == '0'))
                Toast.makeText(request.this, "Please Enter Valid Data", Toast.LENGTH_LONG).show();
            else if((year.charAt(0) != '2') || ((year.charAt(1) - '0') < 1))
                Toast.makeText(request.this, "Please Enter Valid Data", Toast.LENGTH_LONG).show();
            else if(month.charAt(0)== '1' && (month.charAt(1) - '0') > 2)
                Toast.makeText(request.this, "Please Enter Valid Data", Toast.LENGTH_LONG).show();
            else if(cvv.charAt(0) == '0')
                Toast.makeText(request.this, "Please Enter Valid Data", Toast.LENGTH_LONG).show();
            else
            {
                Toast.makeText(request.this, "Tickets Booked", Toast.LENGTH_LONG).show();
                numOfTickets-=reservedTickets;
                dataB.updateMovie(numOfTickets, na);
                System.out.println(date);
                BookedMovie movie = new BookedMovie(mail, na, da, ti, Integer.toString(reservedTickets),Integer.toString(totalPrice));
                dataB.addBookedMovie(movie);
                System.out.println(mail);
                System.out.println(phone);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("Email", mail);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });
    }
}