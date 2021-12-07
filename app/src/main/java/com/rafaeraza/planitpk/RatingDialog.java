package com.rafaeraza.planitpk;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class RatingDialog extends AppCompatDialogFragment {

    ImageView ratingOne, ratingTwo, ratingThree, ratingFour, ratingFive;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
                    }
                });

        ratingOne = view.findViewById(R.id.rating1);
        ratingOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ratingOne.isSelected()) {
                    ratingOne.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingOne.setSelected(true);
                } else {
                    ratingOne.setImageResource(R.drawable.ic_baseline_star_border_24);
                    ratingOne.setSelected(false);
                }
            }
        });
        ratingTwo = view.findViewById(R.id.rating2);
        ratingTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ratingTwo.isSelected()) {
                    ratingTwo.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingTwo.setSelected(true);
                } else {
                    ratingTwo.setImageResource(R.drawable.ic_baseline_star_border_24);
                    ratingTwo.setSelected(false);
                }
            }
        });
        ratingThree = view.findViewById(R.id.rating3);
        ratingThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ratingThree.isSelected()) {
                    ratingThree.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingThree.setSelected(true);
                } else {
                    ratingThree.setImageResource(R.drawable.ic_baseline_star_border_24);
                    ratingThree.setSelected(false);
                }
            }
        });
        ratingFour = view.findViewById(R.id.rating4);
        ratingFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ratingFour.isSelected()) {
                    ratingFour.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingFour.setSelected(true);
                } else {
                    ratingFour.setImageResource(R.drawable.ic_baseline_star_border_24);
                    ratingFour.setSelected(false);
                }
            }
        });
        ratingFive = view.findViewById(R.id.rating5);
        ratingFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ratingFive.isSelected()) {
                    ratingFive.setImageResource(R.drawable.ic_baseline_star_24);
                    ratingFive.setSelected(true);
                } else {
                    ratingFive.setImageResource(R.drawable.ic_baseline_star_border_24);
                    ratingFive.setSelected(false);
                }
            }
        });

        return builder.create();
    }
}
