package example.com.traktmovieapp.models;

public class Movie {
    public final String title;
    public final Image images;

    public Movie(String title, Image images) {
        this.title = title;
        this.images = images;
    }
}
