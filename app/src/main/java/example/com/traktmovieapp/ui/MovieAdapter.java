package example.com.traktmovieapp.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.traktmovieapp.R;
import example.com.traktmovieapp.databinding.ListItemMovieBinding;
import example.com.traktmovieapp.models.MovieObject;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final MovieObject[] movieObjects;

    public MovieAdapter(MovieObject[] movieObjects) {
        this.movieObjects = movieObjects;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View movieRow = layoutInflater.inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(movieRow);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movieObjects[position]);
    }

    @Override
    public int getItemCount() {
        return movieObjects.length;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ListItemMovieBinding binding;

        public MovieViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        public void bind(MovieObject movieObject) {
            binding.setFanArt(movieObject.movie.images.fanart);
        }
    }
}
