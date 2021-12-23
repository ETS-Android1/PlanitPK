package com.rafaeraza.planitpk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LocationBasicAdapter extends RecyclerView.Adapter<LocationBasicAdapter.LocationBasicHolder> implements Filterable {
    /*
     Class attributes
     */
    private ArrayList<LocationHelperClass> locations;
    private ArrayList<LocationHelperClass> locationsAll;
    private Context context;
    private LocationBasicAdapter.OnUserClickListener onUserClickListener;

    public LocationBasicAdapter(ArrayList<LocationHelperClass> locations, Context context, OnUserClickListener onUserClickListener) {
        this.locations = locations;
        this.locationsAll = new ArrayList<>(locations);
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    /*
    Filterable implemented methods
     */
    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String searchQuery = removeLastCharacter(charSequence.toString());
            List<LocationHelperClass> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(locationsAll);
            } else {
                for (LocationHelperClass location : locationsAll) {
                    if (location.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                        filteredList.add(location);
                    } else if (location.getCategory().toLowerCase().contains(searchQuery.toLowerCase())) {
                        filteredList.add(location);
                    } else if (location.getDistrict().toLowerCase().contains(searchQuery.toLowerCase())) {
                        filteredList.add(location);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            locations.clear();
            locations.addAll((Collection<? extends LocationHelperClass>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    @NonNull
    @Override
    public LocationBasicAdapter.LocationBasicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.location_holder, parent, false);

        return new LocationBasicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationBasicAdapter.LocationBasicHolder holder, int position) {
        holder.txtLocationName.setText(locations.get(position).getName());
        holder.txtLocationCategory.setText(locations.get(position).getCategory());
        holder.txtLocationDesc.setText(locations.get(position).getDesc());
        if (locations.get(position).getRating() != 0) {
            holder.txtLocationRating.setText(String.valueOf(locations.get(position).getRating()));
        } else {
            holder.txtLocationRating.setText("N/A");
        }
        Glide.with(context).load(locations.get(position).getImages().getLink1()).error(R.drawable.ic_baseline_image_24).placeholder(R.drawable.ic_baseline_image_24).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    interface OnUserClickListener {
        void onUserClicked(int position);
    }

    /*
    View Holder
     */
    class LocationBasicHolder extends RecyclerView.ViewHolder {
        TextView txtLocationName, txtLocationCategory, txtLocationDesc, txtLocationRating;
        ImageView imageView;

        public LocationBasicHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserClickListener.onUserClicked(getAdapterPosition());
                }
            });
            txtLocationName = itemView.findViewById(R.id.locationName);
            txtLocationCategory = itemView.findViewById(R.id.locationCategory);
            txtLocationDesc = itemView.findViewById(R.id.locationDesc);
            txtLocationRating = itemView.findViewById(R.id.txtLocationRating);
            imageView = itemView.findViewById(R.id.locationImg);
        }
    }

    public String removeLastCharacter(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 's') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
