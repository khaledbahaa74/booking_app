package com.example.team;

public class Movie {
    final private  String filmName;
    final private byte[] filmPoster;
    final private int seats;
    final private int price;
    final private String date;
    final private String time;

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getSeats() {
        return seats;
    }

    public int getPrice() {
        return price;
    }

    public Movie(String filmName, byte[] image, int seats, int price, String date, String time) {
        this.filmName = filmName;
        this.filmPoster =image;
        this.seats = seats;
        this.price = price;
        this.date = date;
        this.time = time;
    }

    public String getFilmName() {
        return filmName;
    }

    public byte[] getImage() {
        return filmPoster;
    }
}
