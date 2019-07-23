package com.example.android.popularmovies1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies1.Model.MoviesData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private ArrayList<MoviesData> mMoviesData;
    private final MoviesAdapterOnClickHandler mClickHandler;
    private Context context;


    public interface MoviesAdapterOnClickHandler {
        void onClick(String weatherForDay);
    }


    public MoviesAdapter(MoviesAdapterOnClickHandler clickHandler, Context context) {
        mClickHandler = clickHandler;
        this.context = context;
    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public final ImageView mMoviePosterImageView;

        public MoviesAdapterViewHolder(View view) {
            super(view);
            mMoviePosterImageView = view.findViewById(R.id.moviePoster);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String movieTitle = mMoviesData.get(adapterPosition).getTitle();
            mClickHandler.onClick(movieTitle);
        }
    }

    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.movie_list_row, viewGroup, false);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapterViewHolder adapterViewHolder, int position) {
        Log.d("MoviesAdapter", mMoviesData.get(position).getPosterPath());
        Picasso.get().load(Constants.IMAGE_URL+mMoviesData.get(position).getPosterPath()).into(adapterViewHolder.mMoviePosterImageView);
    }

    @Override
    public int getItemCount() {
        if (null == mMoviesData) return 0;
        return mMoviesData.size();
    }

    public void setMoviesData(ArrayList<MoviesData> movieData) {
        mMoviesData = movieData;
        notifyDataSetChanged();
    }
}