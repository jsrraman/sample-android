package com.rajaraman.sample.utils.imageview;

import android.graphics.Bitmap;

import com.noveogroup.android.log.Log;
import com.squareup.picasso.Transformation;

public class ScaleToFitWidthHeightTransformation implements Transformation {
    private int mSize;
    private boolean isHeightScale;

    public ScaleToFitWidthHeightTransformation(int size, boolean isHeightScale) {
        mSize = size;
        this.isHeightScale = isHeightScale;

        Log.d("mSize->" + mSize);
    }

    @Override
    public Bitmap transform(Bitmap source) {
        float scale;
        int newSize;
        Bitmap scaleBitmap;

        if (isHeightScale) { // Height is known to us beforehand
            scale = (float) mSize / source.getHeight();
            newSize = Math.round(source.getWidth() * scale);
            scaleBitmap = Bitmap.createScaledBitmap(source, newSize, mSize, true);
        } else { // Width is known to us beforehand
            scale = (float) mSize / source.getWidth();
            newSize = Math.round(source.getHeight() * scale);
            scaleBitmap = Bitmap.createScaledBitmap(source, mSize, newSize, true);
        }

        if (scaleBitmap != source) {
            source.recycle();
        }

        return scaleBitmap;

    }

    @Override
    public String key() {
        return "scaleRespectRatio" + mSize + isHeightScale;
    }
}