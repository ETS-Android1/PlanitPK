package com.rafaeraza.planitpk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LocationMiniRatingAdapter extends RecyclerView.Adapter<LocationMiniRatingAdapter.LocationMiniRatingHolder> {
    // Class attributes
    private ArrayList<LocationHelperClass> locations;
    private Context context;
    private LocationMiniRatingAdapter.OnUserClickListener onUserClickListener;

    public LocationMiniRatingAdapter(ArrayList<LocationHelperClass> locations, Context context, LocationMiniRatingAdapter.OnUserClickListener onUserClickListener) {
        this.locations = locations;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    @NonNull
    @Override
    public LocationMiniRatingAdapter.LocationMiniRatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.location_holder_mini_rating, parent, false);
        return new LocationMiniRatingAdapter.LocationMiniRatingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationMiniRatingHolder holder, int position) {
        holder.locationName.setText(locations.get(position).getName());
        holder.locationCategory.setText(locations.get(position).getCategory());
        if (locations.get(position).getRating() != 0) {
            holder.locationRating.setText(String.valueOf(locations.get(position).getRating()));
        } else {
            holder.locationRating.setText("N/A");
        }

        Glide.with(context)
                .load(locations.get(position).getImages().getLink1()).error(R.drawable.ic_baseline_image_24).placeholder(R.drawable.ic_baseline_image_24)
                .into(holder.locationImg);

    }

    interface OnUserClickListener {
        void OnUserClicked(int position);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    // View Holder
    class LocationMiniRatingHolder extends RecyclerView.ViewHolder {
        ImageView locationImg;
        TextView locationName, locationCategory, locationRating;

        public LocationMiniRatingHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserClickListener.OnUserClicked(getAdapterPosition());
                }
            });

            locationImg = itemView.findViewById(R.id.locationImg);
            locationName = itemView.findViewById(R.id.locationName);
            locationCategory = itemView.findViewById(R.id.locationCategory);
            locationRating = itemView.findViewById(R.id.txtLocationRating);
        }
    }
}
