package com.github.mdfh.flick.utils;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

import java.io.File;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter(value = {"posterPath"}, requireAll = false)
    public static void setMoviePosterPath(ImageView imageView, String path) {
        final String url = "https://image.tmdb.org/t/p/w300" + path;
        if (URLUtil.isValidUrl(url)) {
            Picasso.get()
                    .load(url)
                    .into(imageView);
        }
    }

    @BindingAdapter(value = {"backdropPath"}, requireAll = false)
    public static void setMovieBackDropPath(ImageView imageView, String path) {
        final String url = "https://image.tmdb.org/t/p/w780" + path;
        if (URLUtil.isValidUrl(url)) {
            Picasso.get()
                    .load(url)
                    .into(imageView);
        }
    }

}
