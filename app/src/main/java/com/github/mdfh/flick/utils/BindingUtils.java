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

    @BindingAdapter(value = {"imageUrl"}, requireAll = false)
    public static void setImageUrl(ImageView imageView, String url) {
        if (URLUtil.isValidUrl(url)) {
            Picasso.get()
                    .load(url)
                    .into(imageView);
        }
    }

}
