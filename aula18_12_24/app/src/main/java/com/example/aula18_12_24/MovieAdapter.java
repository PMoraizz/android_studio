package com.example.aula18_12_24;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> movieList;
    private OnItemClickListener onItemClickListener;

    public MovieAdapter(Context context, List<Movie> movieList){
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position){
        Movie movie = movieList.get(position);

        holder.titleTextView.setText(movie.getTitle());
        holder.releaseDateTextView.setText(movie.getReleaseDate());
        holder.posterImageView.setImageResource(movie.getPosterImage());
        holder.categoriaImageView.setImageResource(movie.getCategoria());

        holder.itemView.setOnClickListener(v -> {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(movie);
            }
        });
    }

    @Override
    public int getItemCount(){
        return movieList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public  interface OnItemClickListener{
        void onItemClick(Movie movie);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView, releaseDateTextView;
        ImageView posterImageView,categoriaImageView;

        public MovieViewHolder(View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txtNomeFilme);
            releaseDateTextView = itemView.findViewById(R.id.txtData);
            posterImageView = itemView.findViewById(R.id.imgFilme);
            categoriaImageView = itemView.findViewById(R.id.imgCategoria);
        }
    }

}
