package com.rajaraman.sample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.rajaraman.sample.R;
import com.rajaraman.sample.utils.imageview.ImageViewUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_imageview_test)
public class ImageViewTestActivity extends Activity {
    @ViewById(R.id.imageview_width_height_wrap_content)
    ImageView imageviewWidthHeightWrapContent;

    @ViewById(R.id.imageview_height_wrap_content)
    ImageView imageviewHeightWrapContent;

    @ViewById(R.id.imageview_height_fixed_01)
    ImageView imageviewHeightFixed01;

    @ViewById(R.id.imageview_height_fixed_02)
    ImageView imageviewHeightFixed02;

    @ViewById(R.id.imageview_height_fixed_transform_01)
    ImageView imageviewHeightFixedTransform01;

    @ViewById(R.id.imageview_height_fixed_transform_02)
    ImageView imageviewHeightFixedTransform02;


    String imageUrl01 = "http://www.zastavki.com/pictures/originals/2013/Photoshop_Image_of_the_horse_053857_.jpg";
    String imageUrl02 = "http://www.causingeffect.com/images/made/images/example/cow_square_100_100.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void initViews() {
        ImageViewUtil.loadResourceIntoImageView(this, R.drawable.fb_bg, R.drawable.fb_bg, R.drawable.fb_bg,
                imageviewWidthHeightWrapContent);

        ImageViewUtil.loadImageUrlIntoImageView(this, imageUrl01, R.drawable.fb_bg, R.drawable.fb_bg,
                imageviewHeightWrapContent);

        ImageViewUtil.loadImageUrlIntoImageView(this, imageUrl01, R.drawable.fb_bg, R.drawable.fb_bg,
                imageviewHeightFixed01);

        ImageViewUtil.loadImageUrlIntoImageView(this, imageUrl02, R.drawable.fb_bg, R.drawable.fb_bg,
                imageviewHeightFixed02);

        // ImageView height is set to wrap_content, so use imageview's width to calculate the aspect ratio
        ImageViewUtil.loadImageUrlIntoImageViewAndTransform(this, imageUrl01, R.drawable.fb_bg, R.drawable.fb_bg,
                imageviewHeightFixedTransform01, false);

        // ImageView height is set to wrap_content, so use imageview's width to calculate the aspect ratio
        ImageViewUtil.loadImageUrlIntoImageViewAndTransform(this, imageUrl02, R.drawable.fb_bg, R.drawable.fb_bg,
                imageviewHeightFixedTransform02, false);

    }
}