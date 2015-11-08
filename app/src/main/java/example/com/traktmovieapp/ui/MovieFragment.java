package example.com.traktmovieapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import example.com.traktmovieapp.R;
import example.com.traktmovieapp.models.MovieObject;
import example.com.traktmovieapp.service.MovieService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MovieFragment extends Fragment {

    private Views mViews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onDestroyView() {
        mViews = null;
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViews = new Views(view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.movieRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchRecentMovies();
    }

    private void fetchRecentMovies() {
        setContentShown(false);
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api-v2launch.trakt.tv/movies/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Call<MovieObject[]> call = movieService.getMovies(1, 50, "full,images");

        call.enqueue(new Callback<MovieObject[]>() {
            @Override
            public void onResponse(Response<MovieObject[]> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MovieAdapter movieAdapter = new MovieAdapter(response.body());
                    mViews.movieRecyclerView.setAdapter(movieAdapter);
                    setContentShown(true);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setContentShown(boolean shown) {
        mViews.movieRecyclerView.setVisibility(shown ? View.VISIBLE : View.GONE);
        mViews.progressBar.setVisibility(shown ? View.GONE : View.VISIBLE);
    }

    static class Views {
        final RecyclerView movieRecyclerView;
        final ProgressBar progressBar;

        public Views(View view) {
            movieRecyclerView = (RecyclerView) view.findViewById(R.id.movieRecyclerView);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        }
    }
}
