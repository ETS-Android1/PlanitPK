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

public class LocationMiniAdapter extends RecyclerView.Adapter<LocationMiniAdapter.LocationMiniHolder> {
    // Class attributes
    private ArrayList<LocationHelperClass> locations;
    private Context context;
    private LocationMiniAdapter.OnUserClickListener onUserClickListener;

    public LocationMiniAdapter(ArrayList<LocationHelperClass> locations, Context context, LocationMiniAdapter.OnUserClickListener onUserClickListener) {
        this.locations = locations;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    @NonNull
    @Override
    public LocationMiniAdapter.LocationMiniHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.location_holder_mini, parent, false);
        return new LocationMiniAdapter.LocationMiniHolder(view);
    }

    interface OnUserClickListener {
        void OnUserClicked(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationMiniAdapter.LocationMiniHolder holder, int position) {
        holder.locationName.setText(locations.get(position).getName());
        holder.locationCategory.setText(locations.get(position).getCategory());

        Glide.with(context)
                .load(locations.get(position).getImages().getLink1()).error(R.drawable.ic_baseline_image_24).placeholder(R.drawable.ic_baseline_image_24)
                .into(holder.locationImg);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    // View Holder
    class LocationMiniHolder extends RecyclerView.ViewHolder {
        ImageView locationImg;
        TextView locationName, locationCategory;

        public LocationMiniHolder(@NonNull View itemView) {
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

        }
    }
}
