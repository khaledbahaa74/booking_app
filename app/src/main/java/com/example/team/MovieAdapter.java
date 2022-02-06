package com.example.team;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {
    Context context;
    int resource;
    ImageView userimage;

    public MovieAdapter(@NonNull Context context, int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @SuppressLint({"SetTextI18n", "ViewHolder"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        Movie currentMovie = getItem(position);
        TextView FilmName = convertView.findViewById(R.id.filmNameOutput);
        FilmName.setText(currentMovie.getFilmName());
        TextView seats = convertView.findViewById(R.id.availableSeatsOutput);
        if(currentMovie.getSeats() > 0)
            seats.setText(String.valueOf(currentMovie.getSeats()));
        else
            seats.setText("NO");
        TextView price = convertView.findViewById(R.id.seatPriceOutput);
        price.setText(String.valueOf(currentMovie.getPrice()));
        userimage = convertView.findViewById(R.id.moviePosterOutput);
        Bitmap bmp = BitmapFactory.decodeByteArray(currentMovie.getImage(), 0, currentMovie.getImage().length);
        if(bmp != null)
            userimage.setImageBitmap(Bitmap.createScaledBitmap(bmp, 200, 250, false));
        userimage.setImageBitmap(bmp);
        TextView date = convertView.findViewById(R.id.movieDateOutput);
        TextView time = convertView.findViewById(R.id.movieTimeOutput);
        date.setText(currentMovie.getDate());
        time.setText(currentMovie.getTime());
        return convertView;
    }
}