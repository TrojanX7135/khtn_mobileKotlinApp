// Generated by view binder compiler. Do not edit!
package com.example.finalexamandroidkotlin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.finalexamandroidkotlin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemPlantsTypeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView textCategoryItemPlant;

  @NonNull
  public final TextView textNumberCategoryItemPlant;

  private ItemPlantsTypeBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView textCategoryItemPlant, @NonNull TextView textNumberCategoryItemPlant) {
    this.rootView = rootView;
    this.textCategoryItemPlant = textCategoryItemPlant;
    this.textNumberCategoryItemPlant = textNumberCategoryItemPlant;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemPlantsTypeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemPlantsTypeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_plants_type, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemPlantsTypeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.textCategoryItemPlant;
      TextView textCategoryItemPlant = ViewBindings.findChildViewById(rootView, id);
      if (textCategoryItemPlant == null) {
        break missingId;
      }

      id = R.id.textNumberCategoryItemPlant;
      TextView textNumberCategoryItemPlant = ViewBindings.findChildViewById(rootView, id);
      if (textNumberCategoryItemPlant == null) {
        break missingId;
      }

      return new ItemPlantsTypeBinding((ConstraintLayout) rootView, textCategoryItemPlant,
          textNumberCategoryItemPlant);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
