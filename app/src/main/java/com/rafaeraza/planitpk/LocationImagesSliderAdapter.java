package com.rafaeraza.planitpk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

public class LocationImagesSliderAdapter extends PagerAdapter {
    Context context;
    String img1, img2, img3;
    String[] images;
    LayoutInflater layoutInflater;

    public LocationImagesSliderAdapter(Context context, String img1, String img2, String img3) {
        this.context = context;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        initializeImages(this.img1, this.img2, this.img3);
    }

    void initializeImages(String imgLink1, String imgLink2, String imgLink3) {
        images = new String[] {
                imgLink1,
                imgLink2,
                imgLink3
        };
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.location_images_slides_layout, container, false);

        ImageView imageView = view.findViewById(R.id.locationImg);

        Glide.with(context)
                .load(images[position])
                .error(R.drawable.ic_baseline_image_24)
                .placeholder(R.drawable.ic_baseline_image_24)
                .centerCrop()
                .into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
