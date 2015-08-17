package com.rajaraman.sample.utils.imageview;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.widget.ImageView;

import com.rajaraman.sample.utils.imageview.ScaleToFitWidthHeightTransformation;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageViewUtil {
    // ****************************************** Note *********************************************
    // This method should be only used if either imageview's width or height set to match_parent and
    // the other dimension is fixed
    // Typical usage would be imageview->width:match_parent and imageview->height:200 dp (for example)
    // Even if the height is set to wrap_content but if the placeholder is set to default image it
    // would be treated as fixed height as height of the default image will be set to the imageview
    // for calculation purpose. If the height seems to be set to a value even when imageview height
    // is set to wrap_content, this is the reason
    // ****************************************** Note *********************************************
    public static void loadImageUrlIntoImageView(Activity activity, String imageUrl,
                                                 int placeholderResId, int errorResId,
                                                 ImageView imageView) {
        Picasso.with(activity)
                .load(imageUrl)
                .fit()
                .centerCrop()   // Required to maintain the aspect ratio
                .placeholder(placeholderResId)
                .error(errorResId)
                .into(imageView);
    }

    // ****************************************** Note *********************************************
    // This method should be only used if the either width or height of the imageview is set to
    // wrap_content i.e variable width or height
    // the other dimension is set to match_parent
    // Typical usage would be imageview->width:match_parent and imageview->height:wrap_content
    // ****************************************** Note *********************************************

    // If we know only the size of the width (i.e imageview width -> match_parent and
    // imageview height -> wrap_content (if it's wrap_content we will not know the height of the imageview)
    // => isHeightScale would be false. This is the most common case once would encounter
    // Similarly if we know the height and not width (pretty rare), then isHeightScale would be true
    public static void loadImageUrlIntoImageViewAndTransform(Activity activity, String imageUrl,
                                                             int placeholderResId, int errorResId,
                                                             ImageView imageView, boolean isHeightScale) {

        ScaleToFitWidthHeightTransformation transformation;

        // Note: We cannot get the width and height of the image view just like imageview.getWidth()
        // or imageview.getHeight() as they would return 0 before they are measured in the view hierarchy.
        // Refer the following url for different ways of getting the width and height of a view while
        // it is measured
        // http://www.sherif.mobi/2013/01/how-to-get-widthheight-of-view.html

        // Note:
        // Our desired width/height is either full screen width or full screen height
        // Calculation to get the width of the current display
        Display display = activity.getWindowManager().getDefaultDisplay();

        // x -> width of the display, y -> height of the display
        Point size = new Point();
        display.getSize(size);

        if (isHeightScale) {
            int desiredHeight = size.y;
            transformation = new ScaleToFitWidthHeightTransformation(desiredHeight, true);
        } else {
            int desiredWidth = size.x;
            transformation = new ScaleToFitWidthHeightTransformation(desiredWidth, false);
        }

        Picasso.with(activity)
                .load(imageUrl)
                .transform(transformation)
                .placeholder(placeholderResId)
                .error(errorResId)
                .into(imageView);
    }

    public static void loadResourceIntoImageView(Activity activity, int resourceId,
                                                 int placeholderResId, int errorResId,
                                                 ImageView imageView) {
        Picasso.with(activity)
                .load(resourceId)
                .fit()
                .centerCrop()   // Required to maintain the aspect ratio
                .placeholder(placeholderResId)
                .error(errorResId)
                .into(imageView);
    }

    public static void loadFileIntoImageView(Activity activity, File file,
                                             int placeholderResId, int errorResId,
                                             ImageView imageView) {
        Picasso.with(activity)
                .load(file)
                .fit()
                .centerCrop()   // Required to maintain the aspect ratio
                .placeholder(placeholderResId)
                .error(errorResId)
                .into(imageView);
    }
}