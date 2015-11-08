package example.com.traktmovieapp.binder;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Creating Custom Attributes
 */
public class ImageBinder {

    private ImageBinder() {
        // Hide Constructor
    }

    @BindingAdapter("moviePosterUrl")
    public static void setMoviePosterUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}
