package example.com.traktmovieapp.service;

import example.com.traktmovieapp.models.MovieObject;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface MovieService {

    @GET("trending")
    @Headers({
            "trakt-api-version: 2",
            "trakt-api-key: a0c9e1842e5b4bd2fa07bd814a13659b5e6561b9d556144a6ddb73c838844a6b"
    })
    Call<MovieObject[]> getMovies(@Query("page") int page, @Query("limit") int limit,
                                  @Query("extended") String extended);
}
