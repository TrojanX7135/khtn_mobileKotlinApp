// Generated by view binder compiler. Do not edit!
package com.example.finalexamandroidkotlin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.finalexamandroidkotlin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSpecies3Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imageBack;

  @NonNull
  public final ImageView imageLike;

  @NonNull
  public final ImageView imagePlant;

  @NonNull
  public final ConstraintLayout layoutMain;

  @NonNull
  public final ConstraintLayout layoutTop;

  @NonNull
  public final RatingBar point;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RecyclerView rcv;

  @NonNull
  public final TextView textCategory;

  @NonNull
  public final TextView textDescriptionPlant;

  @NonNull
  public final TextView textKingdom;

  @NonNull
  public final TextView textPlantName;

  @NonNull
  public final TextView textPoint;

  private FragmentSpecies3Binding(@NonNull ConstraintLayout rootView, @NonNull ImageView imageBack,
      @NonNull ImageView imageLike, @NonNull ImageView imagePlant,
      @NonNull ConstraintLayout layoutMain, @NonNull ConstraintLayout layoutTop,
      @NonNull RatingBar point, @NonNull ProgressBar progressBar, @NonNull RecyclerView rcv,
      @NonNull TextView textCategory, @NonNull TextView textDescriptionPlant,
      @NonNull TextView textKingdom, @NonNull TextView textPlantName, @NonNull TextView textPoint) {
    this.rootView = rootView;
    this.imageBack = imageBack;
    this.imageLike = imageLike;
    this.imagePlant = imagePlant;
    this.layoutMain = layoutMain;
    this.layoutTop = layoutTop;
    this.point = point;
    this.progressBar = progressBar;
    this.rcv = rcv;
    this.textCategory = textCategory;
    this.textDescriptionPlant = textDescriptionPlant;
    this.textKingdom = textKingdom;
    this.textPlantName = textPlantName;
    this.textPoint = textPoint;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSpecies3Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSpecies3Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_species3, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSpecies3Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageBack;
      ImageView imageBack = ViewBindings.findChildViewById(rootView, id);
      if (imageBack == null) {
        break missingId;
      }

      id = R.id.imageLike;
      ImageView imageLike = ViewBindings.findChildViewById(rootView, id);
      if (imageLike == null) {
        break missingId;
      }

      id = R.id.imagePlant;
      ImageView imagePlant = ViewBindings.findChildViewById(rootView, id);
      if (imagePlant == null) {
        break missingId;
      }

      id = R.id.layoutMain;
      ConstraintLayout layoutMain = ViewBindings.findChildViewById(rootView, id);
      if (layoutMain == null) {
        break missingId;
      }

      id = R.id.layoutTop;
      ConstraintLayout layoutTop = ViewBindings.findChildViewById(rootView, id);
      if (layoutTop == null) {
        break missingId;
      }

      id = R.id.point;
      RatingBar point = ViewBindings.findChildViewById(rootView, id);
      if (point == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.rcv;
      RecyclerView rcv = ViewBindings.findChildViewById(rootView, id);
      if (rcv == null) {
        break missingId;
      }

      id = R.id.textCategory;
      TextView textCategory = ViewBindings.findChildViewById(rootView, id);
      if (textCategory == null) {
        break missingId;
      }

      id = R.id.textDescriptionPlant;
      TextView textDescriptionPlant = ViewBindings.findChildViewById(rootView, id);
      if (textDescriptionPlant == null) {
        break missingId;
      }

      id = R.id.textKingdom;
      TextView textKingdom = ViewBindings.findChildViewById(rootView, id);
      if (textKingdom == null) {
        break missingId;
      }

      id = R.id.textPlantName;
      TextView textPlantName = ViewBindings.findChildViewById(rootView, id);
      if (textPlantName == null) {
        break missingId;
      }

      id = R.id.textPoint;
      TextView textPoint = ViewBindings.findChildViewById(rootView, id);
      if (textPoint == null) {
        break missingId;
      }

      return new FragmentSpecies3Binding((ConstraintLayout) rootView, imageBack, imageLike,
          imagePlant, layoutMain, layoutTop, point, progressBar, rcv, textCategory,
          textDescriptionPlant, textKingdom, textPlantName, textPoint);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
