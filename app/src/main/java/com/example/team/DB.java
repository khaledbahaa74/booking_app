package com.example.team;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    public DB(Context context) {
        super(context, "ahgzly.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(userMail Text primary key , password Text, phone Text, isAdmin Text)");
        db.execSQL("create table movies(name Text, image BLOB, price INTEGER, seats INTEGER, date Text, time Text)");
        db.execSQL("create table bookedMovies(email Text, name Text, date Text, time Text, bookedTickets Text, totalPrice Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists users");
        db.execSQL("drop Table if exists movies");
        db.execSQL("drop Table if exists bookedMovies");
    }

    public Boolean insertUser(String userMail, String password, String phone, String isAdmin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("userMail", userMail);
        content.put("password", password);
        content.put("phone", phone);
        content.put("isAdmin", isAdmin);
        long result = db.insert("users", null, content);
        return result != -1;
    }

    public Boolean checkUser(String userMail) {
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from users where usermail = ?", new String[]{userMail});
        return cursor.getCount() > 0;
    }

    public Boolean checkUserPass(String userMail, String password, String IsAdmin) {
        SQLiteDatabase db = this.getWritableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from users where usermail = ? and password = ? " +
                "and isadmin = ?", new String[]{userMail, password, IsAdmin});
        return cursor.getCount() > 0;
    }

    public void addMovie(Movie movie) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", movie.getFilmName());
        values.put("image", movie.getImage());
        values.put("price", movie.getPrice());
        values.put("seats", movie.getSeats());
        values.put("date", movie.getDate());
        values.put("time", movie.getTime());
        sqLiteDatabase.insert("movies", null, values);
    }

    public void addBookedMovie(BookedMovie movie) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", movie.getMail());
        values.put("name", movie.getName());
        values.put("date", movie.getDate());
        values.put("time", movie.getTime());
        values.put("bookedTickets", movie.getBookedTickets());
        values.put("totalPrice", movie.getTotalPrice());
        sqLiteDatabase.insert("bookedMovies", null, values);
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        SQLiteDatabase sqL = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqL.rawQuery("select * from movies",null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString((cursor.getColumnIndex("name")));
                byte[] image = cursor.getBlob(1);
                int seats = cursor.getInt((cursor.getColumnIndex("seats")));
                int price = cursor.getInt((cursor.getColumnIndex("price")));
                String date = cursor.getString((cursor.getColumnIndex("date")));
                String time = cursor.getString((cursor.getColumnIndex("time")));
                Movie movie = new Movie(name,image,seats,price,date,time);
                movies.add(movie);
                cursor.moveToNext();
            }
        }
        return movies;
    }

    public ArrayList<BookedMovie> getAllBookedMovies(String Email) {
        ArrayList<BookedMovie> bookedMovies = new ArrayList<>();
        SQLiteDatabase sqL = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqL.rawQuery("select * from bookedMovies where email = ?",new String[]{Email});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String mail = cursor.getString((cursor.getColumnIndex("email")));
                String name = cursor.getString((cursor.getColumnIndex("name")));
                String date = cursor.getString((cursor.getColumnIndex("date")));
                String time = cursor.getString((cursor.getColumnIndex("time")));
                String bookedTickets = cursor.getString((cursor.getColumnIndex("bookedTickets")));
                String totalPrice = cursor.getString((cursor.getColumnIndex("totalPrice")));
                BookedMovie movie = new BookedMovie(mail, name,date,time,bookedTickets, totalPrice);
                bookedMovies.add(movie);
                cursor.moveToNext();
            }
        }
        return bookedMovies;
    }

    public void updateMovie(int seats, String name)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("seats", seats);
        sqLiteDatabase.update("movies", values, "name = ?", new String[]{name});
    }

    public  String [] getUserData(String mail)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from users where userMail = ?", new String[]{mail});
        String[] data = new String[2];
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString((cursor.getColumnIndex("userMail")));
                String phone = cursor.getString((cursor.getColumnIndex("phone")));
                data[0] = name;
                data[1] = phone;
                cursor.moveToNext();
            }
        }
        return  data;
    }
}


