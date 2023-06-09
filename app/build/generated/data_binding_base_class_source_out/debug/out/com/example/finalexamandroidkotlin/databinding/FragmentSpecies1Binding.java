// Generated by view binder compiler. Do not edit!
package com.example.finalexamandroidkotlin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

public final class FragmentSpecies1Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imageBack;

  @NonNull
  public final ImageView imageSearch;

  @NonNull
  public final EditText inputSearch;

  @NonNull
  public final LinearLayout layoutTop;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RecyclerView rcvSpecies;

  private FragmentSpecies1Binding(@NonNull ConstraintLayout rootView, @NonNull ImageView imageBack,
      @NonNull ImageView imageSearch, @NonNull EditText inputSearch,
      @NonNull LinearLayout layoutTop, @NonNull ProgressBar progressBar,
      @NonNull RecyclerView rcvSpecies) {
    this.rootView = rootView;
    this.imageBack = imageBack;
    this.imageSearch = imageSearch;
    this.inputSearch = inputSearch;
    this.layoutTop = layoutTop;
    this.progressBar = progressBar;
    this.rcvSpecies = rcvSpecies;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSpecies1Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSpecies1Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_species1, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSpecies1Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageBack;
      ImageView imageBack = ViewBindings.findChildViewById(rootView, id);
      if (imageBack == null) {
        break missingId;
      }

      id = R.id.imageSearch;
      ImageView imageSearch = ViewBindings.findChildViewById(rootView, id);
      if (imageSearch == null) {
        break missingId;
      }

      id = R.id.inputSearch;
      EditText inputSearch = ViewBindings.findChildViewById(rootView, id);
      if (inputSearch == null) {
        break missingId;
      }

      id = R.id.layoutTop;
      LinearLayout layoutTop = ViewBindings.findChildViewById(rootView, id);
      if (layoutTop == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.rcvSpecies;
      RecyclerView rcvSpecies = ViewBindings.findChildViewById(rootView, id);
      if (rcvSpecies == null) {
        break missingId;
      }

      return new FragmentSpecies1Binding((ConstraintLayout) rootView, imageBack, imageSearch,
          inputSearch, layoutTop, progressBar, rcvSpecies);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
