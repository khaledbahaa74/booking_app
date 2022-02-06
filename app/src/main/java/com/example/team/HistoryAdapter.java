package com.example.team;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter {
    Context context;
    int resource;

    public HistoryAdapter(@NonNull Context context, int resource, @NonNull List<BookedMovie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @SuppressLint({"SetTextI18n", "ViewHolder"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        BookedMovie currentMovie = (BookedMovie) getItem(position);
        TextView filmName = convertView.findViewById(R.id.bookedMovieNameOutput);
        filmName.setText(currentMovie.getName());
        TextView date = convertView.findViewById(R.id.bookedMovieDateOutput);
        date.setText(String.valueOf(currentMovie.getDate()));
        TextView time = convertView.findViewById(R.id.bookedMovieTimeOutput);
        time.setText(String.valueOf(currentMovie.getTime()));
        TextView tickets = convertView.findViewById(R.id.ticketsOutput);
        tickets.setText(String.valueOf(currentMovie.getBookedTickets()));
        TextView price = convertView.findViewById(R.id.totalPriceOutput);
        price.setText(String.valueOf(currentMovie.getTotalPrice()));
        return convertView;
    }
}
