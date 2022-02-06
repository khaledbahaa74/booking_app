package com.example.team;

public class BookedMovie {
    private final String mail;
    private final String name;
    private final String date;
    private final String time;
    private final String bookedTickets;
    private final String totalPrice;

    public BookedMovie(String m, String n, String d, String t, String bt, String tp)
    {
        this.mail = m;
        this.name = n;
        this.date = d;
        this.time = t;
        this.bookedTickets = bt;
        this.totalPrice = tp;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getBookedTickets() {
        return bookedTickets;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getMail() {
        return mail;
    }
}
