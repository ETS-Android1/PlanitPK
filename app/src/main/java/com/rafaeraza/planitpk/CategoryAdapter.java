package com.rafaeraza.planitpk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    /*
     Class attributes
     */
    private ArrayList<CategoryHelperClass> categories;
    private Context context;
    private OnUserClickListener onUserClickListener;

    /*
     Constructor
     */
    public CategoryAdapter(ArrayList<CategoryHelperClass> categories, Context context, OnUserClickListener onUserClickListener) {
        this.categories = categories;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    /*
    Interface
     */
    interface OnUserClickListener {
        void OnUserClicked(int position);
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_holder, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryHolder holder, int position) {
        holder.categoryName.setText(categories.get(position).getName());

        Glide.with(context)
                .load(categories.get(position).getImage()).error(R.drawable.ic_baseline_image_24).placeholder(R.drawable.ic_baseline_image_24)
                .into(holder.categoryImg);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    /*
     View Holder
     */
    class CategoryHolder extends RecyclerView.ViewHolder {
        ImageView categoryImg;
        TextView categoryName;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserClickListener.OnUserClicked(getAdapterPosition());
                }
            });

            categoryName = itemView.findViewById(R.id.categoryName);

            categoryImg = itemView.findViewById(R.id.categoryImg);
        }
    }
}
