package com.rafaeraza.planitpk;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RatingDialog extends AppCompatDialogFragment {

    int rating;
    ImageView ratingOne, ratingTwo, ratingThree, ratingFour, ratingFive;
    private RatingDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LocationDetails locationDetails = (LocationDetails) getActivity();
        String locationName = locationDetails.sendLocationName();

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.rating_dialog_layout, null);

        builder.setView(view)
                .setTitle("Rate Location")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.setRating(rating);
                    }
                });

        ratingOne = view.findViewById(R.id.rating1);
        ratingOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ratingOne.isSelected() && !ratingTwo.isSelected() && !ratingThree.isSelected()
                        && !ratingFour.isSelected() && !ratingFive.isSelected()) {
                    ratingOne.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingOne.setSelected(true);
                    rating = 1;
                } else {
                    if (!ratingTwo.isSelected()) {
                        ratingOne.setImageResource(R.drawable.ic_baseline_star_border_24);
                        ratingOne.setSelected(false);
                        rating = 0;
                    }
                }
            }
        });
        ratingTwo = view.findViewById(R.id.rating2);
        ratingTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ratingTwo.isSelected() && !ratingThree.isSelected()
                        && !ratingFour.isSelected() && !ratingFive.isSelected()) {
                    ratingOne.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingTwo.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingOne.setSelected(true);
                    ratingTwo.setSelected(true);
                    rating = 2;
                } else {
                    if (!ratingThree.isSelected()) {
                        ratingOne.setImageResource(R.drawable.ic_baseline_star_border_24);
                        ratingTwo.setImageResource(R.drawable.ic_baseline_star_border_24);
                        ratingOne.setSelected(false);
                        ratingTwo.setSelected(false);
                        rating = 0;
                    }
                }
            }
        });
        ratingThree = view.findViewById(R.id.rating3);
        ratingThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ratingThree.isSelected() && !ratingFour.isSelected()
                        && !ratingFive.isSelected()) {
                    ratingOne.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingTwo.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingThree.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingOne.setSelected(true);
                    ratingTwo.setSelected(true);
                    ratingThree.setSelected(true);
                    rating = 3;
                } else {
                    if (!ratingFour.isSelected()) {
                        ratingOne.setImageResource(R.drawable.ic_baseline_star_border_24);
                        ratingTwo.setImageResource(R.drawable.ic_baseline_star_border_24);
                        ratingThree.setImageResource(R.drawable.ic_baseline_star_border_24);
                        ratingOne.setSelected(false);
                        ratingTwo.setSelected(false);
                        ratingThree.setSelected(false);
                        rating = 0;
                    }
                }
            }
        });
        ratingFour = view.findViewById(R.id.rating4);
        ratingFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ratingFour.isSelected() && !ratingFive.isSelected()) {
                    ratingOne.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingTwo.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingThree.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingFour.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingOne.setSelected(true);
                    ratingTwo.setSelected(true);
                    ratingThree.setSelected(true);
                    ratingFour.setSelected(true);
                    rating = 4;
                } else {
                    if (!ratingFive.isSelected()) {
                        ratingOne.setImageResource(R.drawable.ic_baseline_star_border_24);
                        ratingTwo.setImageResource(R.drawable.ic_baseline_star_border_24);
                        ratingThree.setImageResource(R.drawable.ic_baseline_star_border_24);
                        ratingFour.setImageResource(R.drawable.ic_baseline_star_border_24);
                        ratingOne.setSelected(false);
                        ratingTwo.setSelected(false);
                        ratingThree.setSelected(false);
                        ratingFour.setSelected(false);
                        rating = 0;
                    }
                }
            }
        });
        ratingFive = view.findViewById(R.id.rating5);
        ratingFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ratingFive.isSelected()) {
                    ratingOne.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingTwo.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingThree.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingFour.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingFive.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingOne.setSelected(true);
                    ratingTwo.setSelected(true);
                    ratingThree.setSelected(true);
                    ratingFour.setSelected(true);
                    ratingFive.setSelected(true);
                    rating = 5;
                } else {
                    ratingOne.setImageResource(R.drawable.ic_baseline_star_border_24);
                    ratingTwo.setImageResource(R.drawable.ic_baseline_star_border_24);
                    ratingThree.setImageResource(R.drawable.ic_baseline_star_border_24);
                    ratingFour.setImageResource(R.drawable.ic_baseline_star_border_24);
                    ratingFive.setImageResource(R.drawable.ic_baseline_star_border_24);
                    ratingOne.setSelected(false);
                    ratingTwo.setSelected(false);
                    ratingThree.setSelected(false);
                    ratingFour.setSelected(false);
                    ratingFive.setSelected(false);
                    rating = 0;
                }
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (RatingDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "Must implement RatingDialogListener");
        }
    }

    public interface RatingDialogListener {
        void setRating(int newRating);
    }
}
